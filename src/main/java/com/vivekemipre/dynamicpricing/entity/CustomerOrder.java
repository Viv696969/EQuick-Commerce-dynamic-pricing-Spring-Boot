package com.vivekemipre.dynamicpricing.entity;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;


import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerOrder {

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

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime orderedAt;

    @Column(columnDefinition = "TEXT")
    private String address;

    private double totalPrice;


}
