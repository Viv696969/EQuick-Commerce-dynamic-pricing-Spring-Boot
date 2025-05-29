package com.vivekemipre.dynamicpricing.service;

import com.vivekemipre.dynamicpricing.entity.Cart;
import com.vivekemipre.dynamicpricing.entity.CustomUser;
import com.vivekemipre.dynamicpricing.entity.Product;
import com.vivekemipre.dynamicpricing.repository.CartRepository;
import com.vivekemipre.dynamicpricing.repository.CustomUserRepository;
import com.vivekemipre.dynamicpricing.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CustomUserRepository customUserRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<Cart> getCartItems(String userId){
        CustomUser customUser=customUserRepository.findById(userId).get();
        return cartRepository.findByUser(customUser);
    }

    public void addProductToCart(String productId,String userId,int quantity,double price){
        CustomUser customUser=customUserRepository.findById(userId).get();
        Product product=productRepository.findById(productId).get();

        Cart cart=cartRepository.findByUserAndProduct(customUser,product);
        if(cart==null){
            Cart cart1=Cart.builder()
                            .product(product).user(customUser).price(price).quantity(quantity).build();
            cartRepository.save(cart1);
        }
        else{
            cart.setQuantity(cart.getQuantity()+1);
            cartRepository.save(cart);
        }

    }

    public void

}
