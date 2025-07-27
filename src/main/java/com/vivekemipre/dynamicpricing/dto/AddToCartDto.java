package com.vivekemipre.dynamicpricing.dto;

import lombok.Data;

@Data
public class AddToCartDto {

    String userId;
    double price;
    String city;
    int pinCode;
    String productId;
}

