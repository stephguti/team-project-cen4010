package com.bookstore.bookstore_api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import com.bookstore.bookstore_api.CreditCardService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/UserCreditCard")
public class CreditCardController {

    @Autowired
    private CreditCardService creditCardService;

    // Post mapping to create a credit card for a user with a user name and credit card details
    @PostMapping("/add")
    public ResponseEntity<String> addCreditCard(@RequestBody CreditCard creditCard) {
        creditCardService.addCreditCard(
            creditCard.getUsernameCreditCard(),
            creditCard.getBankName(),
            creditCard.getCreditCardNumber()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body("Success: Credit Card added");
    }
}
