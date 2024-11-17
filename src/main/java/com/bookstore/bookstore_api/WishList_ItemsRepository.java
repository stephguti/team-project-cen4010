package com.bookstore.bookstore_api;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishList_ItemsRepository extends JpaRepository<WishList_ItemsModel, Long> {
    List<WishList_ItemsModel> findByWishListId(Long wishListId);
}
