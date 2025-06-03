package com.vivekemipre.dynamicpricing.repository;

import com.vivekemipre.dynamicpricing.entity.CustomUser;
import com.vivekemipre.dynamicpricing.entity.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<CustomerOrder,String> {

    CustomerOrder findByUser(CustomUser user);

}
