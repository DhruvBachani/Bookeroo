package com.rmit.sept.ordermicroservices.web;

import com.paypal.http.HttpResponse;
import com.paypal.payments.Refund;
import com.rmit.sept.ordermicroservices.model.PurchasedBook;
import com.rmit.sept.ordermicroservices.model.Transaction;
import com.rmit.sept.ordermicroservices.payload.CheckoutRequest;
import com.rmit.sept.ordermicroservices.payload.RefundRequest;
import com.rmit.sept.ordermicroservices.services.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final Logger log = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    OrderService orderService;

    // Saves all successful PayPal transaction sent from the client
    @PostMapping("/checkout")
    public void saveOrder(@Valid @RequestBody CheckoutRequest checkoutRequest) {
        log.info("POST Request to  /checkout, PayPalID " + checkoutRequest.getPaypalOrderId() + " UserID: " + checkoutRequest.getUserId());
        orderService.saveOrder(checkoutRequest.getPaypalOrderId(), checkoutRequest.getUserId());
    }

    @GetMapping("/user/all")
    public List<Transaction> getAllTransactions(){
        log.info("GET Request to /user/all");
        return orderService.getAllTransactions();
    }

    @GetMapping("/user/{userId}")
    public List<Transaction> getUserTransactions(@PathVariable String userId) {
        log.info("GET Request /user" + userId);
        return orderService.getTransactionsByUserId(userId);
    }

    @PostMapping("/refund")
    public String sendRefund(@Valid @RequestBody RefundRequest refundRequest) {
        log.info("GET Request to /refund, orderID: " + refundRequest.getOrderId());
        return orderService.refundOrder(refundRequest.getOrderId());
    }

}