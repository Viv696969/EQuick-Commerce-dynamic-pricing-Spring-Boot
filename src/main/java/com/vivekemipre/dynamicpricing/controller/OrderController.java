package com.vivekemipre.dynamicpricing.controller;


import com.vivekemipre.dynamicpricing.dto.PlaceOrderDto;
import com.vivekemipre.dynamicpricing.entity.CustomerOrder;
import com.vivekemipre.dynamicpricing.service.OrderServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderServiceInterface orderService;

    @PostMapping("/v1/place-order")
    public ResponseEntity<?> placeOrderV1(@RequestBody PlaceOrderDto placeOrderDto){

        orderService.placeOrder(placeOrderDto.getUserId(),placeOrderDto.getCustomerAddress(),
                placeOrderDto.getTotalPrice(),placeOrderDto.getCity(),placeOrderDto.getPinCode());
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/v1/get-orders")
    public ResponseEntity<List<CustomerOrder>> getOrders(@RequestParam("userId") String userId){
        List<CustomerOrder> orderList= orderService.getUserOrders(userId);
        return ResponseEntity.status(200).body(orderList);

    }

    @GetMapping("/v1/order-details/{orderId}")
    public ResponseEntity<?> getOrderDetails(@PathVariable("orderId") String orderId){
       Map<String,?> orderDetails=orderService.getOrderDetails(orderId);
       return ResponseEntity.status(200).body(orderDetails);
    }


}
