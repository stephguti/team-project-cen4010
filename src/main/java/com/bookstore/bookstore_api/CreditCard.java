package com.bookstore.bookstore_api;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name= "user_credit_card")
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CC_ID")
    private int ccId;
    
    @Column(name = "Username_credit_card")  // Maps Username_credit_card to usernameCreditCard
    private String usernameCreditCard;

    @Column(name = "Bank_name")  // Maps Bank_name to bankName
    private String bankName;

    
    @Column(name = "Credit_card_number")  // Maps Credit_card_number to creditCardNumber
    private String creditCardNumber;

    // getters and setters
    public String getUsernameCreditCard() {
        return usernameCreditCard;
    }

    public void setUsernameCreditCard(String usernameCreditCard) {
        this.usernameCreditCard = usernameCreditCard;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }
    
}
