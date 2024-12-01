package com.bookstore.bookstore_api;

import jakarta.persistence.*;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "order_items")
public class Order_Items_Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long orderItemId;

    @Column(name = "book_id", nullable = false)
    private Long bookId;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "order_id", nullable = false)
    @JsonIgnore // Prevents serialization of the OrderModel in JSON
    private OrderModel order;


    

    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public OrderModel getOrder() {
        return order;
    }

    public void setOrder(OrderModel order) {
        this.order = order;
    }
}


// @Entity
// @Table(name = "order_items")
// public class Order_Items_Model {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     @Column(name = "order_item_id")
//     private Long orderItemId;

//     @Column(name = "order_id", nullable = false)
//     private Long orderId;

//     @Column(name = "book_id", nullable = false)
//     private Long bookId;

//     @Column(name = "quantity", nullable = false)
//     private Integer quantity;

//     @Column(name = "price", nullable = false)
//     private BigDecimal price;

//     // Getters and Setters

//     public Long getOrderItemId() {
//         return orderItemId;
//     }

//     public void setOrderItemId(Long orderItemId) {
//         this.orderItemId = orderItemId;
//     }

//     public Long getOrderId() {
//         return orderId;
//     }

//     public void setOrderId(Long orderId) {
//         this.orderId = orderId;
//     }

//     public Long getBookId() {
//         return bookId;
//     }

//     public void setBookId(Long bookId) {
//         this.bookId = bookId;
//     }

//     public Integer getQuantity() {
//         return quantity;
//     }

//     public void setQuantity(Integer quantity) {
//         this.quantity = quantity;
//     }

//     public BigDecimal getPrice() {
//         return price;
//     }

//     public void setPrice(BigDecimal price) {
//         this.price = price;
//     }
// }
