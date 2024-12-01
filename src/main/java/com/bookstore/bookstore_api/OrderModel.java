package com.bookstore.bookstore_api;

import jakarta.persistence.*; 
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;



// @Entity
// @Table(name = "orders")
// public class OrderModel {



//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     @Column(name = "order_id")
//     private Long orderId;

//     @Column(name = "user_id", nullable = false)
//     private Long userId;

//     @Column(name = "order_date", nullable = false)
//     private LocalDateTime orderDate;

//     @Column(name = "total_amount", nullable = false)
//     private BigDecimal totalAmount;

//     @Column(name = "status")
//     private String status;

//     // Getters and Setters

//     public Long getOrderId() {
//         return orderId;
//     }

//     public void setOrderId(Long orderId) {
//         this.orderId = orderId;
//     }

//     public Long getUserId() {
//         return userId;
//     }

//     public void setUserId(Long userId) {
//         this.userId = userId;
//     }

//     public LocalDateTime getOrderDate() {
//         return orderDate;
//     }

//     public void setOrderDate(LocalDateTime orderDate) {
//         this.orderDate = orderDate;
//     }

//     public BigDecimal getTotalAmount() {
//         return totalAmount;
//     }

//     public void setTotalAmount(BigDecimal totalAmount) {
//         this.totalAmount = totalAmount;
//     }

//     public String getStatus() {
//         return status;
//     }

//     public void setStatus(String status) {
//         this.status = status;
//     }
// }

@Entity
@Table(name = "orders")
public class OrderModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "order_date", nullable = false)
    private LocalDateTime orderDate;

    @Column(name = "total_amount", nullable = false)
    private BigDecimal totalAmount;

    @Column(name = "status")
    private String status;

    // New One-to-Many relationship
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order_Items_Model> orderItems;

    // Getters and Setters

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Order_Items_Model> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<Order_Items_Model> orderItems) {
        this.orderItems = orderItems;
    }
}

