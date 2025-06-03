package com.vivekemipre.dynamicpricing.dto;

import com.vivekemipre.dynamicpricing.entity.Product;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Map;

@Data
public class ProductResponse {

    private String ProductName;
    private double dynamicPrice;
    private String id;
    private String brand;
    private double price;
    private String description;

    public ProductResponse(Product product,double dynamicPrice) {
        ProductName = product.getProductName();
        this.dynamicPrice = dynamicPrice;
        this.id = product.getId();
        this.brand = product.getBrand();
        this.price = product.getPrice();
        this.description = product.getDescription();
    }
}
