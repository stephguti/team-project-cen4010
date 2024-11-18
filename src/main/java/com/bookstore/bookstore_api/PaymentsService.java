package com.bookstore.bookstore_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentsService {

    private final PaymentsRepository paymentsRepository;

    @Autowired
    public PaymentsService(PaymentsRepository paymentsRepository) {
        this.paymentsRepository = paymentsRepository;
    }

    public List<PaymentsModel> getAllPayments() {
        return paymentsRepository.findAll();
    }

    public PaymentsModel getPaymentById(Long paymentId) {
        return paymentsRepository.findById(paymentId).orElse(null);
    }

    public PaymentsModel createPayment(PaymentsModel payment) {
        return paymentsRepository.save(payment);
    }

    public void deletePayment(Long paymentId) {
        paymentsRepository.deleteById(paymentId);
    }

    public List<PaymentsModel> getPaymentsByOrderId(Long orderId) {
        return paymentsRepository.findByOrderId(orderId);
    }
}
