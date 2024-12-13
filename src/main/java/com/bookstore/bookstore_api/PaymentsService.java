package com.bookstore.bookstore_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PaymentsService {

    private final PaymentsRepository paymentsRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public PaymentsService(PaymentsRepository paymentsRepository, OrderRepository orderRepository) {
        this.paymentsRepository = paymentsRepository;
        this.orderRepository = orderRepository;
    }

    public PaymentsModel createPayment(PaymentsModel payment) {
        // Save the payment
        PaymentsModel savedPayment = paymentsRepository.save(payment);

        // Check if the payment amount equals the total amount due
        OrderModel order = payment.getOrder();
        List<PaymentsModel> payments = paymentsRepository.findByOrder(order);

        BigDecimal totalPaid = payments.stream()
                .map(PaymentsModel::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        if (totalPaid.compareTo(order.getTotalAmount()) >= 0) {
            // Mark the order as closed
            order.setStatus("Closed");
            orderRepository.save(order);
        }

        return savedPayment;
    }


    public List<PaymentsModel> getPaymentsByOrder(OrderModel order) {
        return paymentsRepository.findByOrder(order);
    }


    public List<PaymentsModel> getAllPayments() {
        return paymentsRepository.findAll();
    }

    public PaymentsModel getPaymentById(Long paymentId) {
        return paymentsRepository.findById(paymentId).orElse(null);
    }

    public void deletePayment(Long paymentId) {
        if (!paymentsRepository.existsById(paymentId)) {
            throw new IllegalArgumentException("Payment not found with ID: " + paymentId);
        }
        paymentsRepository.deleteById(paymentId);
    }


    // public List<PaymentsModel> getPaymentsByOrderId(Long orderId) {
    //     return paymentsRepository.findByOrderId(orderId);
    // }
}
