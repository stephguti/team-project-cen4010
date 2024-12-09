package com.bookstore.bookstore_api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentsRepository extends JpaRepository<PaymentsModel, Long> {
    List<PaymentsModel> findByOrder(OrderModel order);
}
