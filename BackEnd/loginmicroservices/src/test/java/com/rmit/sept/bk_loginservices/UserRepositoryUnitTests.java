package com.rmit.sept.bk_loginservices;

import static org.junit.Assert.*;

import com.rmit.sept.bk_loginservices.Repositories.UserRepository;
import com.rmit.sept.bk_loginservices.model.User;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryUnitTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    public void save_WhenAllDetailsAreFilled_Test()
    {
        // given
        User customer = new User();
        customer.setFullName("Linda Vu");
        customer.setUserType("Customer");
        customer.setPassword(bCryptPasswordEncoder.encode("asdasdasd"));
        customer.setUsername("linda@gmail.com");
        customer.setAddress("420 Swamped Uni Street");
        customer.setPhoneNumber("0412121121");

        // when persisted, then no exception is thrown
        Assertions.assertDoesNotThrow(() -> entityManager.persist(customer));
        entityManager.flush();
    }

    @Test
    public void save_WhenAddressIsBlank_Test()
    {
        // given
        User customer = new User();
        customer.setFullName("Linda Vu");
        customer.setUserType("Customer");
        customer.setPassword(bCryptPasswordEncoder.encode("asdasdasd"));
        customer.setUsername("linda@gmail.com");
        customer.setPhoneNumber("0412121121");
        entityManager.persist(customer);
        entityManager.flush();

        // when persisted, then no exception is thrown
        Assertions.assertDoesNotThrow(() -> entityManager.persist(customer));
        entityManager.flush();

    }

    @Test
    public void save_WhenPhoneNumberIsBlank_Test()
    {
        // given
        User customer = new User();
        customer.setFullName("Linda Vu");
        customer.setUserType("Customer");
        customer.setPassword(bCryptPasswordEncoder.encode("asdasdasd"));
        customer.setUsername("linda@gmail.com");
        customer.setAddress("420 Swamped Uni Street");

        //when persisted, then no exception is thrown
        Assertions.assertDoesNotThrow(() -> entityManager.persist(customer));
        entityManager.flush();
    }

    @Test
    public void findByUsername_ReturnUser_WhenUsernameIsValid_Test()
    {
        // given
        User customer = new User();
        customer.setFullName("Linda Vu");
        customer.setUserType("Customer");
        customer.setPassword(bCryptPasswordEncoder.encode("asdasdasd"));
        customer.setUsername("linda@gmail.com");
        customer.setAddress("420 Swamped Uni Street");
        customer.setPhoneNumber("0412121121");
        entityManager.persist(customer);
        entityManager.flush();

        // when
        User found = userRepository.findByUsername(customer.getUsername());

        // then
        assertEquals(found.getUsername(), customer.getUsername());
    }

    @Test (expected = ConstraintViolationException.class)
    public void save_ThrowException_WhenUsernameIsBlank_Test()
    {
        // given
        User customer = new User();
        customer.setFullName("Linda Vu");
        customer.setUserType("Customer");
        customer.setPassword(bCryptPasswordEncoder.encode("asdasdasd"));
        customer.setAddress("420 Swamped Uni Street");
        customer.setPhoneNumber("0412121121");

        // when
        entityManager.persist(customer);
        entityManager.flush();

        // then, exception is thrown
    }

    @Test (expected = ConstraintViolationException.class)
    public void save_ThrowException_WhenPasswordIsBlank_Test()
    {
        // given
        User customer = new User();
        customer.setFullName("Linda Vu");
        customer.setUserType("Customer");
        customer.setUsername("linda@gmail.com");
        customer.setAddress("420 Swamped Uni Street");
        customer.setPhoneNumber("0412121121");

        // when
        entityManager.persist(customer);
        entityManager.flush();

        // then, exception is thrown

    }

    @Test (expected = ConstraintViolationException.class)
    public void save_ThrowException_WhenUserTypeIsBlank_Test()
    {
        // given
        User customer = new User();
        customer.setFullName("Linda Vu");
        customer.setPassword(bCryptPasswordEncoder.encode("asdasdasd"));
        customer.setUsername("linda@gmail.com");
        customer.setAddress("420 Swamped Uni Street");
        customer.setPhoneNumber("0412121121");

        // when
        entityManager.persist(customer);
        entityManager.flush();

        // then, exception is thrown

    }

    @Test (expected = ConstraintViolationException.class)
    public void save_ThrowException_WhenFullNameIsBlank_Test()
    {
        //given
        User customer = new User();
        customer.setUserType("Customer");
        customer.setPassword(bCryptPasswordEncoder.encode("asdasdasd"));
        customer.setUsername("linda@gmail.com");
        customer.setAddress("420 Swamped Uni Street");
        customer.setPhoneNumber("0412121121");

        // when
        entityManager.persist(customer);
        entityManager.flush();

        // then, exception is thrown
    }

    @Test
    public void save_WhenTwoUserAreUnique_Test()
    {
        //given
        User customer = new User();
        customer.setFullName("Linda Vu");
        customer.setUserType("Publisher");
        customer.setPassword(bCryptPasswordEncoder.encode("asdasdasd"));
        customer.setUsername("linda@gmail.com");
        customer.setAddress("420 Swamped Uni Street");
        customer.setPhoneNumber("0412121121");
        customer.setAbn_number("12345678901");


        User customer1 = new User();
        customer1.setFullName("Jimmy Vu");
        customer1.setUserType("Publisher");
        customer1.setPassword(bCryptPasswordEncoder.encode("asdasdasd"));
        customer1.setUsername("jimmy@gmail.com");
        customer.setPhoneNumber("0412345678");
        customer1.setAbn_number("12312312312");


        // when persists, then, no exception is thrown
        Assertions.assertDoesNotThrow(() -> entityManager.persist(customer));
        Assertions.assertDoesNotThrow(() -> entityManager.persist(customer1));
        entityManager.flush();
    }

    @Test (expected = PersistenceException.class)
    public void save_ThrowException_WhenUsernameIsNotUnique_Test()
    {
        //given
        User customer = new User();
        customer.setFullName("Linda Vu");
        customer.setUserType("Customer");
        customer.setPassword(bCryptPasswordEncoder.encode("asdasdasd"));
        customer.setUsername("linda@gmail.com");
        customer.setAddress("420 Swamped Uni Street");
        customer.setPhoneNumber("0412121121");

        User customer1 = new User();
        customer1.setFullName("Jimmy Vu");
        customer1.setUserType("Customer");
        customer1.setPassword(bCryptPasswordEncoder.encode("asdasdasd"));
        customer1.setUsername("linda@gmail.com");



        // when
        entityManager.persist(customer);
        entityManager.persist(customer1);
        entityManager.flush();

        // then, exception is thrown
    }

    @Test (expected = PersistenceException.class)
    public void save_ThrowException_WhenPhoneNumberIsNotUnique_Test()
    {
        //given
        User customer = new User();
        customer.setFullName("Linda Vu");
        customer.setUserType("Customer");
        customer.setPassword(bCryptPasswordEncoder.encode("asdasdasd"));
        customer.setUsername("linda@gmail.com");
        customer.setAddress("420 Swamped Uni Street");
        customer.setPhoneNumber("0412121121");

        User customer1 = new User();
        customer1.setFullName("Jimmy Vu");
        customer1.setUserType("Customer");
        customer1.setPassword(bCryptPasswordEncoder.encode("asdasdasd"));
        customer1.setUsername("Jimmy@gmail.com");
        customer1.setPhoneNumber("0412121121");

        // when
        entityManager.persist(customer);
        entityManager.persist(customer1);
        entityManager.flush();

        // then, exception is thrown
    }

    @Test (expected = PersistenceException.class)
    public void save_ThrowException_WhenABNNumberIsNotUnique_Test()
    {
        //given
        User customer = new User();
        customer.setFullName("Linda Vu");
        customer.setUserType("Publisher");
        customer.setPassword(bCryptPasswordEncoder.encode("asdasdasd"));
        customer.setUsername("linda@gmail.com");
        customer.setAddress("420 Swamped Uni Street");
        customer.setPhoneNumber("0412121121");
        customer.setAbn_number("12345678901");

        User customer1 = new User();
        customer1.setFullName("Jimmy Vu");
        customer1.setUserType("Publisher");
        customer1.setPassword(bCryptPasswordEncoder.encode("asdasdasd"));
        customer1.setUsername("Jimmy@gmail.com");
        customer1.setPhoneNumber("0412345678");
        customer1.setAbn_number("12345678901");

        // when
        entityManager.persist(customer);
        entityManager.persist(customer1);
        entityManager.flush();

        // then, exception is thrown
    }



}
