package com.rmit.sept.ordermicroservices.services;

import com.paypal.http.HttpResponse;
import com.paypal.http.serializer.Json;
import com.paypal.orders.*;
import com.paypal.payments.CapturesRefundRequest;
import com.paypal.payments.LinkDescription;
import com.rmit.sept.ordermicroservices.Repositories.PurchasedBookRepository;
import com.rmit.sept.ordermicroservices.Repositories.TransactionRepository;
import com.rmit.sept.ordermicroservices.config.PayPalClient;
import com.rmit.sept.ordermicroservices.model.PurchasedBook;
import com.rmit.sept.ordermicroservices.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.json.JSONObject;
import com.paypal.payments.Refund;


import java.io.IOException;
import java.util.*;

@Service
public class OrderService {

    @Autowired
    PayPalClient payPalClient;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    PurchasedBookRepository purchasedBookRepository;


    // Retrieve order from Paypal
    public Order getOrder(String orderId) throws IOException {
        OrdersGetRequest request = new OrdersGetRequest(orderId);
        HttpResponse<Order> response = payPalClient.client().execute(request);
        return response.result();
    }

    public void saveOrder(String orderId, String userId) throws IOException {

        List<PurchasedBook> list = new ArrayList<>();

        Order paypalOrder = getOrder(orderId);
        Transaction userTransaction = new Transaction();
        userTransaction.setPayPalOrderId(paypalOrder.id());
        userTransaction.setPayPalEmail(paypalOrder.payer().email());
        userTransaction.setUserId(userId);
        int totalCost = 0;
        for (PurchaseUnit unit : paypalOrder.purchaseUnits()) {
            for (Item item : unit.items()) {
                PurchasedBook book = new PurchasedBook();
                book.setIsbn(item.sku());
                book.setSeller(unit.referenceId());
                book.setCondition(item.description());
                book.setQuantity(Integer.parseInt(item.quantity()));
                book.setCost(Double.parseDouble(item.unitAmount().value()) * book.getQuantity());
                totalCost += book.getCost();
                book.setCurrency(item.unitAmount().currencyCode());
                book.setTransaction(userTransaction);
                book.setDeliveryStatus("Processing");
                list.add(book);
            }
        }
        userTransaction.setTotalCost(totalCost);
        userTransaction.setCurrency(paypalOrder.purchaseUnits().get(0).items().get(0).unitAmount().currencyCode());

        userTransaction.setShippingName(paypalOrder.purchaseUnits().get(0).shippingDetail().name().fullName());
        String address = paypalOrder.purchaseUnits().get(0).shippingDetail().addressPortable().addressLine1() +  " " +
                paypalOrder.purchaseUnits().get(0).shippingDetail().addressPortable().adminArea2() + " " +
                paypalOrder.purchaseUnits().get(0).shippingDetail().addressPortable().adminArea1() + " " +
                paypalOrder.purchaseUnits().get(0).shippingDetail().addressPortable().postalCode() + " " +
                paypalOrder.purchaseUnits().get(0).shippingDetail().addressPortable().countryCode()
                ;
        System.out.println(address);
        userTransaction.setAddress(address);
        userTransaction.setCreate_At(new Date());
        transactionRepository.save(userTransaction);

        for (PurchasedBook book : list) {
            purchasedBookRepository.save(book);
        }
    }

    public List<Transaction> getTransactionsByUserId(String userId) {
        List<Transaction> userTransactions = new ArrayList<>();
        transactionRepository.findAll().forEach(transaction -> {
            if (!transaction.getUserId().equals(userId)) {
                userTransactions.add(transaction);
            }
        });
        return userTransactions;
    }

    public List<Transaction> getAllTransactions() {
        List<Transaction> allTransactions = new ArrayList<>();
        transactionRepository.findAll().forEach(transaction -> allTransactions.add(transaction));
        return allTransactions;
    }

    public List<PurchasedBook> getAllBookTransactions() {
        List<PurchasedBook> allPurchasedBooks = new ArrayList<>();
        purchasedBookRepository.findAll().forEach(book -> allPurchasedBooks.add(book));
        return allPurchasedBooks;
    }

    public String refundOrder(String orderID, boolean debug) throws IOException {

        Transaction order = transactionRepository.getById(Long.parseLong(orderID,10));

        // Check if 2 hours have passed since ordering
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(order.getCreate_At());
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(order.getCreate_At());
        calendar2.add(Calendar.HOUR_OF_DAY, 2);
        int result = calendar.compareTo(calendar2);

        if (result < 0) {
            Order paypalOrder = getOrder(order.getPayPalOrderId());

            // Iterate through the PayPal order to get each refund id
            List<String> refundIds = new ArrayList<>();
            Iterator<PurchaseUnit> it = paypalOrder.purchaseUnits().iterator();
            while (it.hasNext()) {
                PurchaseUnit unit = it.next();
                Iterator<Capture> captureIterator = unit.payments().captures().iterator();
                while (captureIterator.hasNext()) {
                    Capture cap = captureIterator.next();
                    refundIds.add(cap.id());
                }
            }

            for (String refundId : refundIds) {
                CapturesRefundRequest request = new CapturesRefundRequest(refundId);
                request.prefer("return=representation");

                HttpResponse<Refund> response = payPalClient.client().execute(request);
                if (debug) {
                    System.out.println("Status Code: " + response.statusCode());
                    System.out.println("Status: " + response.result().status());
                    System.out.println("Refund Id: " + response.result().id());
                }
            }

            for(PurchasedBook bookOrder: order.getPurchases()) {
                bookOrder.setDeliveryStatus("Cancelled");
                purchasedBookRepository.save(bookOrder);
            }
        } else {
            return "Error: Order has been placed for more than 2 hours";
        }
        return "Refund Successful";
    }
}
