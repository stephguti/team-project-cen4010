package com.bookstore.bookstore_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
// import com.bookstore.bookstore_api.PaymentsService;

import java.util.List;
// import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final PaymentsService paymentsService;

    @Autowired
    public OrderService(OrderRepository orderRepository, PaymentsService paymentsService) {
        this.orderRepository = orderRepository;
        this.paymentsService = paymentsService;
    }

    public List<OrderModel> getAllOrders() {
        return orderRepository.findAll();
    }

    public OrderModel getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    public OrderModel createOrder(OrderModel order) {
        // Save the new order
        OrderModel savedOrder = orderRepository.save(order);
    
        // Automatically create a payment for the new order
        PaymentsModel payment = new PaymentsModel();
        payment.setOrder(savedOrder); // Set the entire OrderModel
        payment.setPaymentDate(order.getOrderDate());
        payment.setAmount(order.getTotalAmount());
        payment.setStatus("Pending");
    
        paymentsService.createPayment(payment);
    
        return savedOrder;
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

