package com.bookstore.bookstore_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Order_Items_Service {

    private final Order_Items_Repository orderItemsRepository;

    @Autowired
    public Order_Items_Service(Order_Items_Repository orderItemsRepository) {
        this.orderItemsRepository = orderItemsRepository;
    }

    public List<Order_Items_Model> getAllOrderItems() {
        return orderItemsRepository.findAll();
    }

    public Order_Items_Model getOrderItemById(Long orderItemId) {
        return orderItemsRepository.findById(orderItemId).orElse(null);
    }

    public Order_Items_Model createOrderItem(Order_Items_Model orderItem) {
        return orderItemsRepository.save(orderItem);
    }

    public void deleteOrderItem(Long orderItemId) {
        orderItemsRepository.deleteById(orderItemId);
    }

    public List<Order_Items_Model> getOrderItemsByOrderId(Long orderId) {
        return orderItemsRepository.findByOrderId(orderId);
    }
}
