package com.rmit.sept.ordermicroservices;



import com.rmit.sept.ordermicroservices.Repositories.CartRepository;
import com.rmit.sept.ordermicroservices.model.Cart;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;



@RunWith(SpringRunner.class)
@DataJpaTest
public class CartRepositoryUnitTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CartRepository cartRepository;

    private Cart cart = new Cart();


    @Test
    public void save_WhenAllDetailsAreFilled_Test()
    {
        cart.setUserId(1L);
        Long ads [] = {1L, 4L, 5L};
        cart.setAds(Arrays.asList(ads));
        Assertions.assertDoesNotThrow(() -> entityManager.persist(cart));
        entityManager.flush();

    }

    @Test
    public void findByUserId_ifExists_ReturnsCartObject(){
        cart.setUserId(1L);
        Long ads [] = {1L, 4L, 5L};
        cart.setAds(Arrays.asList(ads));
        entityManager.persist(cart);
        entityManager.flush();

        assertEquals(new Long(1), cartRepository.findByUserId(1L).getUserId());
        assertEquals(new ArrayList<Long>(Arrays.asList(ads)), cartRepository.findByUserId(1L).getAds());
    }






}
