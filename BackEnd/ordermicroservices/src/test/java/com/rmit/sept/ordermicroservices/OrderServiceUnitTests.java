package com.rmit.sept.ordermicroservices;


import com.paypal.http.HttpResponse;
import com.paypal.orders.Order;
import com.paypal.orders.OrdersGetRequest;
import com.rmit.sept.ordermicroservices.Repositories.TransactionRepository;
import com.rmit.sept.ordermicroservices.config.PayPalClient;
import com.rmit.sept.ordermicroservices.model.PurchasedBook;
import com.rmit.sept.ordermicroservices.model.Transaction;
import com.rmit.sept.ordermicroservices.services.OrderService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;


@RunWith(MockitoJUnitRunner.class)
public class OrderServiceUnitTests {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private TransactionRepository transactionRepository;

    private PurchasedBook pB1 = new PurchasedBook();
    private PurchasedBook pB2 = new PurchasedBook();


    private Transaction transaction1 = new Transaction();
    private Transaction transaction2 = new Transaction();

    @Mock
    PayPalClient payPalClient;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        // Filling our 'database' with mock data
        long id = 1;
        pB1.setId(id);
        pB1.setIsbn("99023");
        pB1.setSeller("1");
        pB1.setCondition("New");
        pB1.setQuantity(1);
        pB1.setCost(90.00);
        pB1.setTransaction(transaction1);
        pB1.setCurrency("AU");
        pB1.setDeliveryStatus("Processing");

        List<PurchasedBook> book = new ArrayList<>();
        book.add(pB1);

        transaction1.setId(id);
        transaction1.setPayPalOrderId("76888240N62785030");
        transaction1.setPayPalEmail("sb-hpt5g7382004@business.example.com");
        transaction1.setUserId("2");
        transaction1.setPurchases(book);
        transaction1.setTotalCost(90.00);
        transaction1.setCurrency("AU");
        transaction1.setShippingName("Linda Vu");
        transaction1.setAddress("The Land of Ooks");
        transaction1.setCreate_At(new Date());

        long id2 = 2;
        pB2.setId(id2);
        pB2.setIsbn("111234");
        pB2.setSeller("1");
        pB2.setCondition("Old");
        pB2.setQuantity(2);
        pB2.setCost(50.00);
        pB2.setTransaction(transaction2);
        pB2.setCurrency("AU");
        pB2.setDeliveryStatus("Processing");

        List<PurchasedBook> book2 = new ArrayList<>();
        book2.add(pB2);

        transaction2.setId(id2);
        transaction2.setPayPalOrderId("76888240N62785030");
        transaction2.setPayPalEmail("sb-hpt5g7382004@personal.example.com");
        transaction2.setUserId("3");
        transaction2.setPurchases(book2);
        transaction2.setTotalCost(100.00);
        transaction2.setCurrency("AU");
        transaction2.setShippingName("John Doe");
        transaction2.setAddress("The Swamps");
        long DAY_IN_MS = 1000 * 60 * 60 * 24;
        transaction2.setCreate_At(new Date(System.currentTimeMillis() - (2 * DAY_IN_MS)));



        OrdersGetRequest request = new OrdersGetRequest("1234");
        // Creating mock dependencies to not rely on external databases

    }

    @Test
    public void getTransactionsByUserId_ReturnList_IfValidUserId() {
        String customerID = "2";
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction1);
        transactions.add(transaction2);
        // Stub
        Mockito.when(transactionRepository.findAll()).thenReturn(transactions);

        List<Transaction> found = orderService.getTransactionsByUserId(customerID);

        for( Transaction transaction : found) {
            assertEquals(customerID, transaction.getUserId());
        }
    }


    @Test
    public void getTransactionsByUserId_ReturnList_IfInvalidUserId() {
        String customerID = "9999";
        List<Transaction> found = orderService.getTransactionsByUserId(customerID);

        assertTrue(found.isEmpty());
    }

    @Test
    public void getAllTransactions_ReturnList() {

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction1);
        transactions.add(transaction2);
        // Stub
        Mockito.when(transactionRepository.findAll()).thenReturn(transactions);

        List<Transaction> found = orderService.getAllTransactions();

        assertEquals(transactions.size(), found.size());
    }


    @Test
    public void refundOrder_ReturnErrorString_IfValidOrderOutsideTwoHours() {
        // Stub
        Mockito.when(transactionRepository.getById(transaction2.getId())).thenReturn(transaction2);


        String refund = orderService.refundOrder("2");

        assertEquals("Error: Order has been placed for more than 2 hours", refund);
    }

    @Test (expected = NullPointerException.class)
    public void refundOrder_ReturnErrorString_IfNonExistingOrder() {
        String refund = orderService.refundOrder("1222");
    }


}