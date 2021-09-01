package com.rmit.sept.ordermicroservices.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order {

    // A generic order model
    // Incomplete needs to add book items etc.
    private double price;
    private String currency;
    private String method;
    private String intent;
    private String description;

}

//{
//        "price": 5.00,
//        "currency": "AUD",
//        "method": "paypal",
//        "intent":"sale",
//        "description": "testing"
//}