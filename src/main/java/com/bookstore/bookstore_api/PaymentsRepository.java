package com.bookstore.bookstore_api;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PaymentsRepository extends JpaRepository<PaymentsModel, Long> {
    List<PaymentsModel> findByOrderId(Long orderId); 
}
