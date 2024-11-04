package com.bookstore.bookstore_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-items")
public class Order_Items_Controller {

    private final Order_Items_Service orderItemsService;

    @Autowired
    public Order_Items_Controller(Order_Items_Service orderItemsService) {
        this.orderItemsService = orderItemsService;
    }

    @GetMapping
    public List<Order_Items_Model> getAllOrderItems() {
        return orderItemsService.getAllOrderItems();
    }

    @GetMapping("/{orderItemId}")
    public Order_Items_Model getOrderItemById(@PathVariable Long orderItemId) {
        return orderItemsService.getOrderItemById(orderItemId);
    }

    @PostMapping
    public Order_Items_Model createOrderItem(@RequestBody Order_Items_Model orderItem) {
        return orderItemsService.createOrderItem(orderItem);
    }

    @DeleteMapping("/{orderItemId}")
    public void deleteOrderItem(@PathVariable Long orderItemId) {
        orderItemsService.deleteOrderItem(orderItemId);
    }

    @GetMapping("/order/{orderId}")
    public List<Order_Items_Model> getOrderItemsByOrderId(@PathVariable Long orderId) {
        return orderItemsService.getOrderItemsByOrderId(orderId);
    }
}
