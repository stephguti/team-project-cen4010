package com.bookstore.bookstore_api;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WishlistRepository extends JpaRepository<WishlistModel, Long> {
}
