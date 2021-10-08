package com.rmit.sept.ordermicroservices.services;

import com.paypal.http.HttpResponse;
import com.paypal.orders.Item;
import com.paypal.orders.Order;
import com.paypal.orders.OrdersGetRequest;
import com.paypal.orders.PurchaseUnit;
import com.rmit.sept.ordermicroservices.Repositories.PurchasedBookRepository;
import com.rmit.sept.ordermicroservices.Repositories.TransactionRepository;
import com.rmit.sept.ordermicroservices.config.PayPalClient;
import com.rmit.sept.ordermicroservices.model.PurchasedBook;
import com.rmit.sept.ordermicroservices.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        // Currency will be set to the first unit - which might be redundant..
        userTransaction.setCurrency(paypalOrder.purchaseUnits().get(0).items().get(0).unitAmount().currencyCode());

        userTransaction.setShippingName(paypalOrder.purchaseUnits().get(0).shippingDetail().name().fullName());
        String address = paypalOrder.purchaseUnits().get(0).shippingDetail().addressPortable().toString();
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
            if (!transaction.getId().equals(userId)) {
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


}
