package com.bookstore.bookstore_api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Order_Items_Repository extends JpaRepository<Order_Items_Model, Long> {
    
    // List<Order_Items_Model> findByOrderId(Long orderId);
    List<Order_Items_Model> findByOrder(OrderModel order);

}
