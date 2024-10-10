package com.bookstore.bookstore_api;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name= "Users")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "Users_ID")  // Maps Users_ID to userID
    private int userID;

    @Column(name = "Username")  // Maps Username to username
    private String username;

    @Column(name = "Email")  // Maps Email to email
    private String email;

    @Column(name = "First_name")  // Maps First_name to firstName
    private String firstName;

    @Column(name = "Last_name")  // Maps Last_name to lastName
    private String lastName;

    @Column(name = "Pass_Word")  // Maps Pass_Word to password
    private String password;

    @Column(name = "User_Address")  // Maps User_Address to userAddress
    private String userAddress;

    @Column(name = "Credit_Card")  // Maps Credit_Card to creditCard
    private String creditCard;

    // getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }


}
