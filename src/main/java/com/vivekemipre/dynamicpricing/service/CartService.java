package com.vivekemipre.dynamicpricing.service;

import com.vivekemipre.dynamicpricing.entity.Cart;
import com.vivekemipre.dynamicpricing.entity.CustomUser;
import com.vivekemipre.dynamicpricing.entity.Product;
import com.vivekemipre.dynamicpricing.repository.CartRepository;
import com.vivekemipre.dynamicpricing.repository.CustomUserRepository;
import com.vivekemipre.dynamicpricing.repository.ProductRepository;
import com.vivekemipre.dynamicpricing.util.RedisUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CartService implements CartServiceInterface {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CustomUserRepository customUserRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RedisUtility redisUtility;

    @Override
    public List<Cart> getCartItems(String userId){
        CustomUser customUser=customUserRepository.findById(userId).get();
        return cartRepository.findByUser(customUser);
    }

    @Override
    public void addProductToCart(String productId,String userId,double price,String city,int pinCode){
        CustomUser customUser=customUserRepository.findById(userId).get();
        Product product=productRepository.findById(productId).get();

        Cart cart=cartRepository.findByUserAndProduct(customUser,product);
        if(cart==null){
            Cart cart1=Cart.builder()
                            .product(product).user(customUser).price(price).quantity(1).build();
            cartRepository.save(cart1);
        }
        else{
            cart.setQuantity(cart.getQuantity()+1);
            cartRepository.save(cart);
        }
        redisUtility.increaseDemand(city,product.getProductName(),pinCode,2);

    }

    @Override
    public void deleteProductFromCart(String cartId){
        try {
            Cart cart=cartRepository.findById(cartId).get();
            cartRepository.delete(cart);
        } catch (Exception e) {
            
        }
    }

    @Override
    public Cart changeQuantity(String cartId,int quantityChange,String action){
        Cart cart=cartRepository.findById(cartId).get();
        if (action.equals("increment")){
            cart.setQuantity(cart.getQuantity()+quantityChange);
        }
        else{
            cart.setQuantity(cart.getQuantity()-quantityChange);

        }
        if(cart.getQuantity()<=0){
            cartRepository.delete(cart);
            return null;
        }
        return cartRepository.save(cart);
    }


}
