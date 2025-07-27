package com.vivekemipre.dynamicpricing.dto;


import lombok.Data;

@Data
public class PlaceOrderDto {
    private String userId;
    private String customerAddress;
    private String city;
    private int pinCode;
    private double totalPrice;
}
