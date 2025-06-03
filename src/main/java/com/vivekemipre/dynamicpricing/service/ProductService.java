package com.vivekemipre.dynamicpricing.service;

import com.vivekemipre.dynamicpricing.dto.ProductResponse;
import com.vivekemipre.dynamicpricing.entity.Product;
import com.vivekemipre.dynamicpricing.repository.ProductRepository;
import com.vivekemipre.dynamicpricing.util.DynamicPricingCalculator;
import com.vivekemipre.dynamicpricing.util.RedisUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {


    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RedisUtility redisUtility;

    @Autowired
    private DynamicPricingCalculator dynamicPricingCalculator;

    private double getDynamicPrice(double basePrice,Map<String,Boolean> eatenAt,int currentDemand,String osType,boolean isPeekTime){
        double dynamicPrice=dynamicPricingCalculator.getProductDemandPrice(basePrice,currentDemand,osType,true);
        if (eatenAt.get(dynamicPricingCalculator.categorizeTiming(LocalDateTime.now()))){
            dynamicPrice*=1.10002;
        }
        else {
            dynamicPrice *= 0.98;
        }
        return  dynamicPrice;
    }


    public ProductResponse viewProduct(String productId,String city,int pinCode,String osType){
        int currentDemand=redisUtility.getProductDemand(city, productId, pinCode);
        Product product=productRepository.findById(productId).get();
        double dynamicPrice=getDynamicPrice(product.getPrice(),product.getEatenAt(),currentDemand,osType,true);
        redisUtility.increaseDemand(city,productId,pinCode,1);
        return new ProductResponse(product,dynamicPrice);
    }

    public List<ProductResponse>  getProducts(int page, int size, String city, int pinCode, String osType){
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepository.findAll(pageable);

        List<String> keys = products.stream()
                .map(product -> redisUtility.getKey(city,product.getId(),pinCode ))
                .toList();

        Map<String, Integer> priceMap = redisUtility.getProductDemandBatched(keys);

        return products.stream()

                .map(product -> {
                    String key = redisUtility.getKey(city,product.getId(), pinCode);
                    int demand = priceMap.getOrDefault(key,0);
                    double dynamicPrice=getDynamicPrice(product.getPrice(),product.getEatenAt(),demand,osType,true);
                    return new ProductResponse(product, dynamicPrice);
                })

                .toList();


    }

    public void addProduct(String productName,String productBrand,double price,String productDescription,List<String> eatingTimeList){
        Map<String,Boolean> eatingMap=new HashMap<>();
        eatingMap.put("breakfast",false);
        eatingMap.put("lunch",false);
        eatingMap.put("dinner",false);

        Product product=Product.builder()
                .productName(productName)
                .brand(productBrand)
                .description(productDescription)
                .price(price)
                .build();

        for(String eatingTime:eatingTimeList){
            eatingMap.put(eatingTime,true);
        }

        product.setEatenAt(eatingMap);
        productRepository.save(product);
    }




}
