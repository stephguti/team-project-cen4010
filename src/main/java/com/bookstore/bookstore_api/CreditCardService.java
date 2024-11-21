package com.bookstore.bookstore_api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditCardService {
   
    @Autowired
    private CreditCardRepository creditCardRepository;

    public CreditCard addCreditCard(String username, String bankName, String creditCardNumber) {
        CreditCard creditCard = new CreditCard();
        creditCard.setUsernameCreditCard(username);
        creditCard.setBankName(bankName);
        creditCard.setCreditCardNumber(creditCardNumber);
        return creditCardRepository.save(creditCard);

    }
}
