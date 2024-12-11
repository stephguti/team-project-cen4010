package com.bookstore.bookstore_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class Order_Items_Service {

    private final Order_Items_Repository orderItemsRepository;
    private final OrderRepository orderRepository;
    private final bookRepository bookRepository;

    @Autowired
    public Order_Items_Service(Order_Items_Repository orderItemsRepository, OrderRepository orderRepository, bookRepository bookRepository) {
        this.orderItemsRepository = orderItemsRepository;
        this.orderRepository = orderRepository;
        this.bookRepository = bookRepository;
    }

    public List<Order_Items_Model> getAllOrderItems() {
        return orderItemsRepository.findAll();
    }

    public Order_Items_Model getOrderItemById(Long orderItemId) {
        return orderItemsRepository.findById(orderItemId).orElse(null);
    }

    public Order_Items_Model createOrderItem(Long orderId, Long bookId, Integer quantity) {
        // Fetch the order
        OrderModel order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found with ID: " + orderId));
    
        // Fetch the book
        Book book = bookRepository.findById(bookId.intValue())
                .orElseThrow(() -> new IllegalArgumentException("Book not found with ID: " + bookId));
    
        // Create the order item
        Order_Items_Model orderItem = new Order_Items_Model();
        orderItem.setOrder(order);
        orderItem.setBookId(bookId);
        orderItem.setQuantity(quantity);
    
        // Calculate and set the total price for the order item
        BigDecimal price = BigDecimal.valueOf(book.getPrice())
                .multiply(BigDecimal.valueOf(quantity))
                .setScale(2, RoundingMode.HALF_UP);
        orderItem.setPrice(price);
    
        // Save the order item
        Order_Items_Model savedOrderItem = orderItemsRepository.save(orderItem);
    
        // Update the order's total amount
        updateOrderTotalAmount(order);
    
        return savedOrderItem;
    }
    

    public String deleteOrderItemByUserIdAndBookId(Long userId, Long bookId) {
        // Check if the user has orders
        boolean userHasOrders = !orderRepository.findByUserId(userId).isEmpty();
        if (!userHasOrders) {
            throw new IllegalArgumentException("User with ID " + userId + " does not exist.");
        }
    
        // Find the order items for the user and book
        List<Order_Items_Model> orderItems = orderItemsRepository.findByOrderUserIdAndBookId(userId, bookId);
    
        if (orderItems.isEmpty()) {
            throw new IllegalArgumentException("Book with ID " + bookId + " not found in the user's order items.");
        }
    
        // Assuming there is only one matching item
        Order_Items_Model orderItem = orderItems.get(0);
    
        // Fetch the associated order
        OrderModel order = orderItem.getOrder();
    
        // Delete the order item
        orderItemsRepository.delete(orderItem);
    
        // Update the order's total amount
        updateOrderTotalAmount(order);
    
        // Return success message
        return "Book with ID " + bookId + " successfully removed from user with ID " + userId + "'s order items.";
    }
    
    


    public List<Order_Items_Model> getOrderItemsByOrderId(Long orderId) {
        OrderModel order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
        return orderItemsRepository.findByOrder(order);
    }

    private void updateOrderTotalAmount(OrderModel order) {
        // Calculate the total amount from all order items
        BigDecimal totalAmount = order.getOrderItems().stream()
                .map(Order_Items_Model::getPrice) // Use the already calculated total price
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    
        // Update the order's total amount
        order.setTotalAmount(totalAmount);
        orderRepository.save(order);
    }
    
}
