package com.vivekemipre.dynamicpricing.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductDto {
    private String productName;
    private String productDescription;
    private double productPrice;
    private String productBrand;
    private List<String> eatingTimes;
}
