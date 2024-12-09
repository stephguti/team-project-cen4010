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
        BigDecimal price = BigDecimal.valueOf(book.getPrice()).setScale(2, RoundingMode.HALF_UP);
        orderItem.setPrice(price);

        // Save the order item
        Order_Items_Model savedOrderItem = orderItemsRepository.save(orderItem);

        // Update the order's total amount
        updateOrderTotalAmount(order);

        return savedOrderItem;
    }

    public void deleteOrderItem(Long orderItemId) {
        Order_Items_Model orderItem = orderItemsRepository.findById(orderItemId)
                .orElseThrow(() -> new IllegalArgumentException("Order item not found with ID: " + orderItemId));

        // Fetch the associated order
        OrderModel order = orderItem.getOrder();

        // Delete the order item
        orderItemsRepository.deleteById(orderItemId);

        // Update the order's total amount
        updateOrderTotalAmount(order);
    }

    public List<Order_Items_Model> getOrderItemsByOrderId(Long orderId) {
        OrderModel order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
        return orderItemsRepository.findByOrder(order);
    }

    private void updateOrderTotalAmount(OrderModel order) {
        // Calculate the total amount from all order items
        BigDecimal totalAmount = order.getOrderItems().stream()
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Update the order's total amount
        order.setTotalAmount(totalAmount);
        orderRepository.save(order);
    }
}
