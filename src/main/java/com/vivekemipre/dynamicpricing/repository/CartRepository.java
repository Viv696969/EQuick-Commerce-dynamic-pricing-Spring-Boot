package com.vivekemipre.dynamicpricing.repository;

import com.vivekemipre.dynamicpricing.entity.Cart;
import com.vivekemipre.dynamicpricing.entity.CustomUser;
import com.vivekemipre.dynamicpricing.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart,String> {

    List<Cart> findByUser(CustomUser user);

    Cart findByUserAndProduct(CustomUser user, Product product);
}
