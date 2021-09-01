package com.rmit.sept.ordermicroservices.services;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import com.paypal.api.payments.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

@Service
public class PaypalService {

    @Autowired
    private APIContext apiContext;

    // https://developer.paypal.com/docs/api/orders/v1/#orders
    // For creating the payment
    // https://github.com/paypal/PayPal-Java-SDK/blob/master/rest-api-sample/src/main/java/com/paypal/api/payments/servlet/PaymentWithPayPalServlet.java
    public Payment createPayment(
            // TODO: First, need to add List<Book> books for capture
            Double total,
            String currency,
            String method,
            // Always SALE or AUTHORIZE
            String intent,
            String description,
            String cancelUrl,
            String successUrl) throws PayPalRESTException{

        // Creating the payment object for paypal to read
        // TODO: then calculate the math
        Details details = new Details();
        details.setShipping("0");
        // details.setSubtotal();
        details.setTax("0");

        Amount amount = new Amount();
        amount.setCurrency(currency);
        total = new BigDecimal(total).setScale(2, RoundingMode.HALF_UP).doubleValue();
        // Total must be equal to sum of shipping, tax and subtotal.
        amount.setTotal(String.format("%.2f", total));

        Transaction transaction = new Transaction();
        transaction.setReferenceId("1"); //
        transaction.setDescription(description);
        transaction.setAmount(amount);

        ItemList itemList = new ItemList();
        List<Item> items = new ArrayList<Item>();

        //TODO: then turn this into a loop
        Item item = new Item();
        item.setSku("1").setName("Narnia").setQuantity("1").setCurrency("AUD").setPrice("0");
        items.add(item);
        itemList.setItems(items);

        transaction.setItemList(itemList);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod(method.toString());

        Payment payment = new Payment();
        payment.setIntent(intent.toString());
        payment.setPayer(payer);
        payment.setTransactions(transactions);
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(cancelUrl);
        redirectUrls.setReturnUrl(successUrl);
        payment.setRedirectUrls(redirectUrls);

        return payment.create(apiContext);
    }

    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException{
        Payment payment = new Payment();
        payment.setId(paymentId);
        PaymentExecution paymentExecute = new PaymentExecution();
        paymentExecute.setPayerId(payerId);
        return payment.execute(apiContext, paymentExecute);
    }


}