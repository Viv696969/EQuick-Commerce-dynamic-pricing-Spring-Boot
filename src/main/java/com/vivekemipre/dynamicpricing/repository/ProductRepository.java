package com.vivekemipre.dynamicpricing.repository;


import com.vivekemipre.dynamicpricing.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,String> {

}
