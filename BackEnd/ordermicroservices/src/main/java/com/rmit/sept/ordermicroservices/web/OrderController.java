package com.rmit.sept.ordermicroservices.web;

import com.paypal.http.HttpResponse;
import com.paypal.payments.Refund;
import com.rmit.sept.ordermicroservices.model.PurchasedBook;
import com.rmit.sept.ordermicroservices.model.Transaction;
import com.rmit.sept.ordermicroservices.payload.CheckoutRequest;
import com.rmit.sept.ordermicroservices.payload.RefundRequest;
import com.rmit.sept.ordermicroservices.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/checkout")
    public void saveOrder(@Valid @RequestBody CheckoutRequest checkoutRequest) throws IOException {
        System.out.println(checkoutRequest.getPaypalOrderId() + " " + checkoutRequest.getUserId());
        orderService.saveOrder(checkoutRequest.getPaypalOrderId(), checkoutRequest.getUserId());
    }

    @GetMapping("/checkout/{orderId}")
    public Transaction getOrder(@PathVariable String orderId){
        return null;
    }

    @GetMapping("/user/all")
    public List<Transaction> getAllTransactions(){
        return orderService.getAllTransactions();
    }

    @GetMapping("/book/all")
    public List<PurchasedBook> getAllBookTransactions(){
        return orderService.getAllBookTransactions();
    }

    @GetMapping("/user/{userId}")
    public List<Transaction> getUserTransactions(@PathVariable String userId) {
        return orderService.getTransactionsByUserId(userId);
    }

    @DeleteMapping("/checkout/{orderId}")
    public void deleteOrder(@PathVariable String orderId) {

    }

    @PatchMapping("/checkout/{orderId}")
    public void updateOrder(@PathVariable String orderId) {
    }

    // Retrieves all users for frontend
    @PostMapping("/refund")
    public String getRefund(@Valid @RequestBody RefundRequest refundRequest) throws IOException {
        return orderService.refundOrder(refundRequest.getOrderId(), true);
    }

}