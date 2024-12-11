package com.bookstore.bookstore_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.bookstore.cart_dtos.OrderItemDTO;

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

    @GetMapping
    public List<OrderItemDTO> getAllOrderItems() {
        List<Order_Items_Model> orderItems = orderItemsService.getAllOrderItems();

        return orderItems.stream().map(orderItem -> {
            Book book = bookRepository.findById(orderItem.getBookId().intValue())
                    .orElseThrow(() -> new IllegalArgumentException("Book not found"));

            OrderItemDTO dto = new OrderItemDTO();
            dto.setOrderItemId(orderItem.getOrderItemId());
            dto.setBookId(orderItem.getBookId());
            dto.setQuantity(orderItem.getQuantity());
            dto.setPrice(orderItem.getPrice());
            dto.setBookName(book.getTitle());
            return dto;
        }).toList();
    }

    @GetMapping("/{orderItemId}")
    public ResponseEntity<OrderItemDTO> getOrderItemById(@PathVariable Long orderItemId) {
        Order_Items_Model orderItem = orderItemsService.getOrderItemById(orderItemId);
        if (orderItem == null) {
            return ResponseEntity.notFound().build();
        }

        Book book = bookRepository.findById(orderItem.getBookId().intValue())
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));

        OrderItemDTO dto = new OrderItemDTO();
        dto.setOrderItemId(orderItem.getOrderItemId());
        dto.setBookId(orderItem.getBookId());
        dto.setQuantity(orderItem.getQuantity());
        dto.setPrice(orderItem.getPrice());
        dto.setBookName(book.getTitle());

        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<OrderItemDTO> createOrderItem(@RequestBody Map<String, Object> payload) {
        Long orderId = Long.valueOf(payload.get("orderId").toString());
        Long bookId = Long.valueOf(payload.get("bookId").toString());
        Integer quantity = Integer.valueOf(payload.get("quantity").toString());

        Order_Items_Model orderItem = orderItemsService.createOrderItem(orderId, bookId, quantity);

        Book book = bookRepository.findById(bookId.intValue())
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));

        OrderItemDTO dto = new OrderItemDTO();
        dto.setOrderItemId(orderItem.getOrderItemId());
        dto.setBookId(orderItem.getBookId());
        dto.setQuantity(orderItem.getQuantity());
        dto.setPrice(orderItem.getPrice());
        dto.setBookName(book.getTitle());

        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/user/{userId}/book/{bookId}")
    public ResponseEntity<String> deleteOrderItemByUserIdAndBookId(@PathVariable Long userId, @PathVariable Long bookId) {
        try {
            String successMessage = orderItemsService.deleteOrderItemByUserIdAndBookId(userId, bookId);
            return ResponseEntity.ok(successMessage);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


}
