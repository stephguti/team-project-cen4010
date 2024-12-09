package com.bookstore.bookstore_api;

// import com.bookstore.bookstore_api.bookRepository;

import com.bookstore.cart_dtos.OrderItemDTO; // Adjust package if necessary


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/order-items")
public class Order_Items_Controller {

    private final Order_Items_Service orderItemsService;
    private final bookRepository bookRepository;

    @Autowired
    public Order_Items_Controller(Order_Items_Service orderItemsService, bookRepository bookRepository) {
        this.orderItemsService = orderItemsService;
        this.bookRepository = bookRepository;
    }

    // Get all order items
    @GetMapping
    public List<OrderItemDTO> getAllOrderItems() {
        List<Order_Items_Model> orderItems = orderItemsService.getAllOrderItems();
    
        return orderItems.stream().map(orderItem -> {
            // Fetch book details
            Book book = bookRepository.findById(orderItem.getBookId().intValue())
                    .orElseThrow(() -> new IllegalArgumentException("Book not found"));
    
            // Prepare the response DTO
            OrderItemDTO dto = new OrderItemDTO();
            dto.setOrderItemId(orderItem.getOrderItemId());
            dto.setBookId(orderItem.getBookId());
            dto.setQuantity(orderItem.getQuantity());
            dto.setPrice(orderItem.getPrice());
            dto.setBookName(book.getTitle());
            return dto;
        }).toList();
    }

    // Get an order item by ID


    @GetMapping("/{orderItemId}")
    public ResponseEntity<OrderItemDTO> getOrderItemById(@PathVariable Long orderItemId) {
        Order_Items_Model orderItem = orderItemsService.getOrderItemById(orderItemId);
        if (orderItem == null) {
            return ResponseEntity.notFound().build();
        }
        // Fetch book details
        Book book = bookRepository.findById(orderItem.getBookId().intValue())
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));

        // Prepare the response DTO
        OrderItemDTO dto = new OrderItemDTO();
        dto.setOrderItemId(orderItem.getOrderItemId());
        dto.setBookId(orderItem.getBookId());
        dto.setQuantity(orderItem.getQuantity());
        dto.setPrice(orderItem.getPrice());
        dto.setBookName(book.getTitle());

        return ResponseEntity.ok(dto);
    }

    // @GetMapping("/{orderItemId}")
    // public ResponseEntity<Order_Items_Model> getOrderItemById(@PathVariable Long orderItemId) {
    //     Order_Items_Model orderItem = orderItemsService.getOrderItemById(orderItemId);
    //     if (orderItem == null) {
    //         return ResponseEntity.notFound().build();
    //     }
    //     return ResponseEntity.ok(orderItem);
    // }

    // Create a new order item
    @PostMapping
    public ResponseEntity<OrderItemDTO> createOrderItem(@RequestBody Map<String, Object> payload) {
        Long orderId = Long.valueOf(payload.get("orderId").toString());
        Long bookId = Long.valueOf(payload.get("bookId").toString());
        Integer quantity = Integer.valueOf(payload.get("quantity").toString());

        Order_Items_Model orderItem = orderItemsService.createOrderItem(orderId, bookId, quantity);

        // Fetch book details to include in the response
        Book book = bookRepository.findById(bookId.intValue())
                .orElseThrow(() -> new IllegalArgumentException("Book not found with ID: " + bookId));

        // Prepare the response DTO
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setOrderItemId(orderItem.getOrderItemId());
        orderItemDTO.setBookId(orderItem.getBookId());
        orderItemDTO.setQuantity(orderItem.getQuantity());
        orderItemDTO.setPrice(orderItem.getPrice());
        orderItemDTO.setBookName(book.getTitle()); // Include book name in response

        return ResponseEntity.ok(orderItemDTO);
    }

    // Delete an order item
    @DeleteMapping("/{orderItemId}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Long orderItemId) {
        try {
            orderItemsService.deleteOrderItem(orderItemId);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Get all order items for a specific order
    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<Order_Items_Model>> getOrderItemsByOrderId(@PathVariable Long orderId) {
        List<Order_Items_Model> orderItems = orderItemsService.getOrderItemsByOrderId(orderId);
        if (orderItems.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orderItems);
    }
}
