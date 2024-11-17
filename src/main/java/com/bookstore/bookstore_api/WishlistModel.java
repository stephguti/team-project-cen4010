package com.bookstore.bookstore_api;

import jakarta.persistence.*;

@Entity
@Table(name = "wishlist")
public class WishlistModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "wish_list_name", nullable = false)
    private String wishListName;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    public WishlistModel() {
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWishListName() {
        return wishListName;
    }

    public void setWishListName(String wishListName) {
        this.wishListName = wishListName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
