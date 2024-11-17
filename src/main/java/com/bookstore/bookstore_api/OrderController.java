package com.bookstore.bookstore_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final Order_Items_Service orderItemsService;
    private final PaymentsService paymentsService;

    @Autowired
    public OrderController(OrderService orderService, Order_Items_Service orderItemsService, PaymentsService paymentsService) {
        this.orderService = orderService;
        this.orderItemsService = orderItemsService;
        this.paymentsService = paymentsService;
    }

    @GetMapping
    public List<OrderModel> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{orderId}")
    public OrderModel getOrderById(@PathVariable Long orderId) {
        return orderService.getOrderById(orderId);
    }

    @PostMapping
    public OrderModel createOrder(@RequestBody OrderModel order) {
        return orderService.createOrder(order);
    }

    @DeleteMapping("/{orderId}")
    public void deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
    }

    @GetMapping("/user/{userId}")
    public List<OrderModel> getOrdersByUserId(@PathVariable Long userId) {
        return orderService.getOrdersByUserId(userId);
    }

    @PutMapping("/{orderId}")
    public OrderModel updateOrder(@PathVariable Long orderId, @RequestBody OrderModel updatedOrder) {
        return orderService.updateOrder(orderId, updatedOrder);
    }

    @GetMapping("/{orderId}/details")
    public Map<String, Object> getOrderWithItems(@PathVariable Long orderId) {
        OrderModel order = orderService.getOrderById(orderId);
        List<Order_Items_Model> orderItems = orderItemsService.getOrderItemsByOrderId(orderId);
        List<PaymentsModel> payments = paymentsService.getPaymentsByOrderId(orderId);
    
        Map<String, Object> response = new HashMap<>();
        response.put("order", order);
        response.put("orderItems", orderItems);
        response.put("payments", payments);
    
        return response;
    }

    @GetMapping("/user/{userId}/saved")
    public List<OrderModel> getSavedOrdersByUserId(@PathVariable Long userId) {
        return orderService.getOrdersByUserIdAndStatus(userId, "Saved");
}

}
