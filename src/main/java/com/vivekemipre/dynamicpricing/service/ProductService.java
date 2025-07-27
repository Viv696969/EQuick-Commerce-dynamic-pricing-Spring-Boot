package com.vivekemipre.dynamicpricing.service;

import com.vivekemipre.dynamicpricing.dto.PagedResponse;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService implements ProductServiceInterface {


    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RedisUtility redisUtility;

    @Autowired
    private DynamicPricingCalculator dynamicPricingCalculator;

    public ProductResponse viewProduct(String productId,String city,int pinCode){
        Product product=productRepository.findById(productId).get();
        int currentDemand=redisUtility.getProductDemand(city, product.getProductName(), pinCode);
        double dynamicPrice= dynamicPricingCalculator.getProductDynamicPrice(product.getPrice(), currentDemand, true, product.getEatenAt());
        redisUtility.increaseDemand(city,product.getProductName(),pinCode,1);
        return new ProductResponse(product,dynamicPrice);
    }

    public PagedResponse<ProductResponse> getProducts(int page, int size, String city, int pinCode){
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepository.findAll(pageable);

        List<String> keys = products.stream()
                .map(product -> redisUtility.getKey(city,product.getProductName(),pinCode ))
                .toList();

        Map<String, Integer> priceMap = redisUtility.getProductDemandBatched(keys);


        List<ProductResponse> productResponses = products.stream()
                .map(product -> {
                    String key = redisUtility.getKey(city, product.getProductName(), pinCode);
                    int demand = priceMap.getOrDefault(key, 0);
                    double dynamicPrice = dynamicPricingCalculator.getProductDynamicPrice(
                            product.getPrice(), demand, true, product.getEatenAt());
                    return new ProductResponse(product, dynamicPrice);
                })
                .toList();

        return new PagedResponse<ProductResponse>(
                productResponses,
                products.getNumber(),
                products.getSize(),
                products.getTotalElements(),
                products.getTotalPages(),
                products.hasNext(),
                products.hasPrevious()
        );


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
