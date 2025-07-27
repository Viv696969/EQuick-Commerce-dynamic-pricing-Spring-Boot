package com.vivekemipre.dynamicpricing.controller;

import com.vivekemipre.dynamicpricing.dto.AddToCartDto;
import com.vivekemipre.dynamicpricing.dto.ChangeCartItemQuantityDto;
import com.vivekemipre.dynamicpricing.dto.DeleteCartItemDto;
import com.vivekemipre.dynamicpricing.dto.ViewCartDto;
import com.vivekemipre.dynamicpricing.entity.Cart;
import com.vivekemipre.dynamicpricing.service.CartServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartServiceInterface cartService;

    @GetMapping("/v1/view-cart/{userId}")
    public ResponseEntity<?> viewCart(@PathVariable("userId") String userId){
            List<Cart> cartList =cartService.getCartItems(userId);
            return ResponseEntity.status(200).body(cartList);
    }

    @PostMapping("/v1/delete-item")
    public ResponseEntity<?> deleteCartItem(@RequestBody DeleteCartItemDto deleteCartItemDto){
        cartService.deleteProductFromCart(deleteCartItemDto.getCartId());
        return ResponseEntity.status(200).build();
    }

    @PostMapping("/v1/change-item-quantity")
    public ResponseEntity<?> changeCartItemQuantity(@RequestBody ChangeCartItemQuantityDto changeCartItemQuantityDto){
        Cart cart=cartService.changeQuantity(changeCartItemQuantityDto.getCartItemId(), changeCartItemQuantityDto.getQuantityChange(), changeCartItemQuantityDto.getAction());
        return ResponseEntity.status(201).body(cart);

    }


    @PostMapping("/v1/add-to-cart")
    public ResponseEntity<?> addProductToCart(@RequestBody AddToCartDto addToCartDto){
        cartService.addProductToCart(addToCartDto.getProductId(),addToCartDto.getUserId(),addToCartDto.getPrice(),addToCartDto.getCity(), addToCartDto.getPinCode());
        return ResponseEntity.status(201).build();
    }







}
