package com.vivekemipre.dynamicpricing.entity;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cart {

    @Id
    private String id;

    @PrePersist
    private void  setId(){
        if (this.id!=null){
            this.id= NanoIdUtils.randomNanoId();
        }
    }


    @ManyToOne
    private CustomUser user;

    @OneToOne
    private Product product;

    private int quantity;

    private double price;


    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime addedAt;

}
