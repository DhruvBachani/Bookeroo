package com.rmit.sept.ordermicroservices;

import com.rmit.sept.ordermicroservices.Repositories.PurchasedBookRepository;
import com.rmit.sept.ordermicroservices.Repositories.TransactionRepository;
import com.rmit.sept.ordermicroservices.model.PurchasedBook;
import com.rmit.sept.ordermicroservices.model.Transaction;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TransactionPurchasedBookRepositoryUnitTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private PurchasedBookRepository purchasedBookRepository;

    @Test
    public void save_WhenAllDetailsAreFilled()
    {
        // given
        PurchasedBook pB1 = new PurchasedBook();
        Transaction transaction1 = new Transaction();

        pB1.setIsbn("99023");
        pB1.setSeller("1");
        pB1.setCondition("New");
        pB1.setQuantity(1);
        pB1.setCost(90.00);
        pB1.setTransaction(transaction1);
        pB1.setCurrency("AU");
        pB1.setDeliveryStatus("Processing");

        transaction1.setPayPalOrderId("76888240N62785030");
        transaction1.setPayPalEmail("sb-hpt5g7382004@business.example.com");
        transaction1.setUserId("2");
        transaction1.setTotalCost(90.00);
        transaction1.setCurrency("AU");
        transaction1.setShippingName("Linda Vu");
        transaction1.setAddress("The Land of Ooks");
        transaction1.setCreate_At(new Date());


        // when persisted, then no exception is thrown
        Assertions.assertDoesNotThrow(() -> entityManager.persist(transaction1));
        Assertions.assertDoesNotThrow(() -> entityManager.persist(pB1));
        entityManager.flush();
    }


    @Test (expected = ConstraintViolationException.class)
    public void save_ThrowException_WhenISBNIsBlank()
    {
        // given
        Transaction transaction1 = new Transaction();
        PurchasedBook pB1 = new PurchasedBook();

        transaction1.setPayPalOrderId("76888240N62785030");
        transaction1.setPayPalEmail("sb-hpt5g7382004@business.example.com");
        transaction1.setUserId("2");
        transaction1.setTotalCost(90.00);
        transaction1.setCurrency("AU");
        transaction1.setShippingName("Linda Vu");
        transaction1.setAddress("The Land of Ooks");
        transaction1.setCreate_At(new Date());

        entityManager.persist(transaction1);
        entityManager.flush();

        pB1.setSeller("1");
        pB1.setCondition("New");
        pB1.setQuantity(1);
        pB1.setCost(90.00);
        pB1.setTransaction(transaction1);
        pB1.setCurrency("AU");
        pB1.setDeliveryStatus("Processing");

        // when
        entityManager.persist(pB1);
        entityManager.flush();


        // then, exception is thrown
    }

    @Test (expected = ConstraintViolationException.class)
    public void save_ThrowException_WhenPayPalIDIsBlank()
    {
        // given
        PurchasedBook pB1 = new PurchasedBook();
        Transaction transaction1 = new Transaction();
        pB1.setIsbn("99023");
        pB1.setSeller("1");
        pB1.setCondition("New");
        pB1.setQuantity(1);
        pB1.setCost(90.00);
        pB1.setTransaction(transaction1);
        pB1.setCurrency("AU");
        pB1.setDeliveryStatus("Processing");

        transaction1.setPayPalEmail("sb-hpt5g7382004@business.example.com");
        transaction1.setUserId("2");
        transaction1.setTotalCost(90.00);
        transaction1.setCurrency("AU");
        transaction1.setShippingName("Linda Vu");
        transaction1.setAddress("The Land of Ooks");
        transaction1.setCreate_At(new Date());

        // when
        entityManager.persist(transaction1);
        entityManager.flush();

        // then, exception is thrown

    }

    @Test (expected = ConstraintViolationException.class)
    public void save_ThrowException_WhenPayPalEmailIsBlank()
    {
        // given
        PurchasedBook pB1 = new PurchasedBook();
        Transaction transaction1 = new Transaction();
        pB1.setIsbn("99023");
        pB1.setSeller("1");
        pB1.setCondition("New");
        pB1.setQuantity(1);
        pB1.setCost(90.00);
        pB1.setTransaction(transaction1);
        pB1.setCurrency("AU");
        pB1.setDeliveryStatus("Processing");

        transaction1.setPayPalOrderId("76888240N62785030");
        transaction1.setUserId("2");
        transaction1.setTotalCost(90.00);
        transaction1.setCurrency("AU");
        transaction1.setShippingName("Linda Vu");
        transaction1.setAddress("The Land of Ooks");
        transaction1.setCreate_At(new Date());
        // when
        entityManager.persist(transaction1);
        entityManager.flush();

        // then, exception is thrown

    }

    @Test
    public void save_WhenTwoPaypalIDAreUnique()
    {
        //given
        PurchasedBook pB1 = new PurchasedBook();
        Transaction transaction1 = new Transaction();
        pB1.setIsbn("99023");
        pB1.setSeller("1");
        pB1.setCondition("New");
        pB1.setQuantity(1);
        pB1.setCost(90.00);
        pB1.setTransaction(transaction1);
        pB1.setCurrency("AU");
        pB1.setDeliveryStatus("Processing");

        transaction1.setPayPalOrderId("76888240N62785030");
        transaction1.setPayPalEmail("sb-hpt5g7382004@personal.example.com");
        transaction1.setUserId("2");
        transaction1.setTotalCost(90.00);
        transaction1.setCurrency("AU");
        transaction1.setShippingName("Linda Vu");
        transaction1.setAddress("The Land of Ooks");
        transaction1.setCreate_At(new Date());

        PurchasedBook pB2 = new PurchasedBook();
        Transaction transaction2 = new Transaction();

        pB2.setIsbn("111234");
        pB2.setSeller("1");
        pB2.setCondition("Old");
        pB2.setQuantity(2);
        pB2.setCost(50.00);
        pB2.setTransaction(transaction2);
        pB2.setCurrency("AU");
        pB2.setDeliveryStatus("Processing");

        transaction2.setPayPalOrderId("xxxxxxxxxxxxxxxxx");
        transaction2.setPayPalEmail("sb-hpt5g7382004@personal.example.com");
        transaction2.setUserId("3");
        transaction2.setTotalCost(100.00);
        transaction2.setCurrency("AU");
        transaction2.setShippingName("John Doe");
        transaction2.setAddress("The Swamps");
        transaction2.setCreate_At(new Date());


        // when persists, then, no exception is thrown
        Assertions.assertDoesNotThrow(() -> entityManager.persist(transaction1));
        Assertions.assertDoesNotThrow(() -> entityManager.persist(pB1));
        Assertions.assertDoesNotThrow(() -> entityManager.persist(transaction2));
        Assertions.assertDoesNotThrow(() -> entityManager.persist(pB2));
        entityManager.flush();
    }

    @Test (expected = ConstraintViolationException.class)
    public void save_ThrowException_WhenPayPalIDIsNotUnique()
    {
        //given
        PurchasedBook pB1 = new PurchasedBook();
        Transaction transaction1 = new Transaction();

        pB1.setIsbn("99023");
        pB1.setSeller("1");
        pB1.setCondition("New");
        pB1.setQuantity(1);
        pB1.setCost(90.00);
        pB1.setTransaction(transaction1);
        pB1.setCurrency("AU");
        pB1.setDeliveryStatus("Processing");

        transaction1.setPayPalOrderId("76888240N62785030");
        transaction1.setUserId("2");
        transaction1.setTotalCost(90.00);
        transaction1.setCurrency("AU");
        transaction1.setShippingName("Linda Vu");
        transaction1.setAddress("The Land of Ooks");
        transaction1.setCreate_At(new Date());

        PurchasedBook pB2 = new PurchasedBook();
        Transaction transaction2 = new Transaction();
        pB2.setIsbn("111234");
        pB2.setSeller("1");
        pB2.setCondition("Old");
        pB2.setQuantity(2);
        pB2.setCost(50.00);
        pB2.setTransaction(transaction2);
        pB2.setCurrency("AU");
        pB2.setDeliveryStatus("Processing");

        transaction2.setPayPalOrderId("76888240N62785030");
        transaction2.setPayPalEmail("sb-hpt5g7382004@personal.example.com");
        transaction2.setUserId("3");
        transaction2.setTotalCost(100.00);
        transaction2.setCurrency("AU");
        transaction2.setShippingName("John Doe");
        transaction2.setAddress("The Swamps");

        // when
        entityManager.persist(transaction1);
        entityManager.persist(pB1);
        entityManager.persist(transaction2);
        entityManager.persist(pB2);
        entityManager.flush();

        // then, exception is thrown
    }

}
