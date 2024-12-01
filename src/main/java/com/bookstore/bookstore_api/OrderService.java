package com.bookstore.bookstore_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
// import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderModel> getAllOrders() {
        return orderRepository.findAll();
    }

    public OrderModel getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    public OrderModel createOrder(OrderModel order) {
        return orderRepository.save(order);
    }

    // public void deleteOrder(Long orderId) {
    //     orderRepository.deleteById(orderId);
    // }

    public void deleteOrder(Long orderId) {
        if (!orderRepository.existsById(orderId)) {
            throw new IllegalArgumentException("Order not found with ID: " + orderId);
        }
        orderRepository.deleteById(orderId);
    }
    

    public List<OrderModel> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    public OrderModel updateOrder(Long orderId, OrderModel updatedOrder) {
        return orderRepository.findById(orderId)
                .map(existingOrder -> {
                    existingOrder.setUserId(updatedOrder.getUserId());
                    existingOrder.setOrderDate(updatedOrder.getOrderDate());
                    existingOrder.setTotalAmount(updatedOrder.getTotalAmount());
                    existingOrder.setStatus(updatedOrder.getStatus());
                    return orderRepository.save(existingOrder);
                })
                .orElse(null); 
    }


    public List<OrderModel> getOrdersByUserIdAndStatus(Long userId, String status) {
        return orderRepository.findByUserIdAndStatus(userId, status);
    }
}

