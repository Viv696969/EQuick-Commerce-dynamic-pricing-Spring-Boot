package com.vivekemipre.dynamicpricing.repository;

import com.vivekemipre.dynamicpricing.entity.CustomUser;
import com.vivekemipre.dynamicpricing.entity.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<CustomerOrder,String> {

    List<CustomerOrder> findByUser(CustomUser user);

}
