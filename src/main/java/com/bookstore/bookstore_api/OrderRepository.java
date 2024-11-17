package com.bookstore.bookstore_api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderModel, Long> {
    List<OrderModel> findByUserId(Long userId);
    List<OrderModel> findByUserIdAndStatus(Long userId, String status);
}
