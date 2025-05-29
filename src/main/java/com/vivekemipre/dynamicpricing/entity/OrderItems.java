package com.vivekemipre.dynamicpricing.entity;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItems {

    @Id
    private String id;

    @PrePersist
    private void  setId(){
        if (this.id!=null){
            this.id= NanoIdUtils.randomNanoId();
        }
    }

    @ManyToOne
    private Product product;

    @ManyToOne
    private CustomerOrder customerOrder;

    private int quantity;

    private double price;


}
