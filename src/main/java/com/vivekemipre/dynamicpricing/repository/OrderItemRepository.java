package com.vivekemipre.dynamicpricing.repository;

import com.vivekemipre.dynamicpricing.entity.CustomerOrder;
import com.vivekemipre.dynamicpricing.entity.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItems ,String> {

    List<OrderItems> findByCustomerOrder(CustomerOrder order);
}
