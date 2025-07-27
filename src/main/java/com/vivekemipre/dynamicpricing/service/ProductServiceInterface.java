package com.vivekemipre.dynamicpricing.service;

import com.vivekemipre.dynamicpricing.dto.PagedResponse;
import com.vivekemipre.dynamicpricing.dto.ProductResponse;

import java.util.List;

public interface ProductServiceInterface {
    ProductResponse viewProduct(String productId, String city, int pinCode);

    PagedResponse<ProductResponse> getProducts(int page, int size, String city, int pinCode);

    void addProduct(String productName,String productBrand,double price,String productDescription,List<String> eatingTimeList);
}
