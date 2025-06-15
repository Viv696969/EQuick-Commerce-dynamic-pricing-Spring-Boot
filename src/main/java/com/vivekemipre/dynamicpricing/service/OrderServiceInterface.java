package com.vivekemipre.dynamicpricing.service;

import com.vivekemipre.dynamicpricing.entity.CustomerOrder;

import java.util.List;
import java.util.Map;

public interface OrderServiceInterface {

    CustomerOrder placeOrder(String userId, String customerAddress, double totalPrice, String city, int pinCode);

    Map<String,?> getOrderDetails(String orderId);

    List<CustomerOrder> getUserOrders(String userId);
}
