package com.vivekemipre.dynamicpricing.service;


import com.vivekemipre.dynamicpricing.entity.Cart;
import com.vivekemipre.dynamicpricing.entity.CustomUser;
import com.vivekemipre.dynamicpricing.entity.CustomerOrder;
import com.vivekemipre.dynamicpricing.entity.OrderItems;
import com.vivekemipre.dynamicpricing.repository.*;
import com.vivekemipre.dynamicpricing.util.RedisUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrderService implements OrderServiceInterface{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CustomUserRepository customUserRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private RedisUtility redisUtility;


    public CustomerOrder placeOrder(String userId,String customerAddress,double totalPrice,String city,int pinCode){
        CustomUser user=customUserRepository.findById(userId).get();
        List<Cart> cartList=cartRepository.findByUser(user);
        CustomerOrder order=CustomerOrder.builder().
                address(customerAddress).
                totalPrice(totalPrice).
                user(user).build();

        orderRepository.save(order);

        for(Cart cartItem:cartList){
            orderItemRepository.save(
            OrderItems.builder().customerOrder(order).product(cartItem.getProduct()).price(cartItem.getPrice()).quantity(cartItem.getQuantity()).build()
            );
            redisUtility.increaseDemand(city,cartItem.getProduct().getProductName(),pinCode,3);
        }
        return order;

    }

    public Map<String,?> getOrderDetails(String orderId){
        CustomerOrder order=orderRepository.findById(orderId).get();
        List<OrderItems> orderItemsList=orderItemRepository.findByCustomerOrder(order);
        return Map.of("orderDetails",order,"orderedItems",orderItemsList);

    }

    public List<CustomerOrder> getUserOrders(String userId){

        CustomUser user = customUserRepository.findById(userId).get();
        return orderRepository.findByUser(user);
    }




}
