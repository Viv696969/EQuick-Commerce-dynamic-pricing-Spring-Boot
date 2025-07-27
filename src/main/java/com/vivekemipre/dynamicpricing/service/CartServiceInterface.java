package com.vivekemipre.dynamicpricing.service;

import com.vivekemipre.dynamicpricing.entity.Cart;

import java.util.List;

public interface CartServiceInterface {


    List<Cart> getCartItems(String userId);

    void addProductToCart(String productId,String userId,double price,String city,int pinCode);

    void deleteProductFromCart(String cartId);

    Cart changeQuantity(String cartId,int quantityChange,String action);
}
