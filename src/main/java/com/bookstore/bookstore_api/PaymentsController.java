package com.bookstore.bookstore_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentsController {

    private final PaymentsService paymentService;

    @Autowired
    public PaymentsController(PaymentsService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    public List<PaymentsModel> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @GetMapping("/{paymentId}")
    public PaymentsModel getPaymentById(@PathVariable Long paymentId) {
        return paymentService.getPaymentById(paymentId);
    }

    @PostMapping
    public PaymentsModel createPayment(@RequestBody PaymentsModel payment) {
        return paymentService.createPayment(payment);
    }

    @DeleteMapping("/{paymentId}")
    public void deletePayment(@PathVariable Long paymentId) {
        paymentService.deletePayment(paymentId);
    }

    @GetMapping("/order/{orderId}")
    public List<PaymentsModel> getPaymentsByOrderId(@PathVariable Long orderId) {
        return paymentService.getPaymentsByOrderId(orderId);
    }
}
