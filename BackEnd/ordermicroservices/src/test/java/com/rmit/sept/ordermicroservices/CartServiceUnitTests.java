package com.rmit.sept.ordermicroservices;



import com.rmit.sept.ordermicroservices.Repositories.CartRepository;
import com.rmit.sept.ordermicroservices.model.Cart;
import com.rmit.sept.ordermicroservices.services.CartService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;


import java.awt.print.Book;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.any;


@RunWith(MockitoJUnitRunner.class)
public class CartServiceUnitTests {

    @InjectMocks
    private CartService cartService;

    @Mock
    private CartRepository cartRepository;


    private Cart cart = new Cart();

    private ArrayList<Cart>  carts;


    @Before
    public void init() {
        carts = new ArrayList<>();
        Cart cart1 = new Cart();
        cart1.setId(1L);
        cart1.setUserId(1L);
        Long ads[] = {1L,4L,5L};
        cart1.setAds(Arrays.asList(ads));
        carts.add(cart1);
        // Creating mock dependencies to not rely on external databases
        Mockito.when(cartRepository.findByUserId(any(Long.class))).thenAnswer(new Answer<Cart>() {
            @Override
            public Cart answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                Long id = (Long) args[0];
                for(Cart cart: carts){
                    if(cart.getUserId().equals(id)) return cart;
                }
                return null;
            }
        });




    }



    @Test
    public void getCartItems_returnsListOfAdIds_ifUserHasSomethinginCart(){
        ArrayList<Long> expectedAdIds = new ArrayList<Long>(Arrays.asList(new Long[]{1L, 4L, 5L}));
        assertEquals(expectedAdIds,cartService.getCartItems(1L));

    }

    @Test
    public void getCartItems_returnsNull_ifUserDoesntexist(){
        assertNull(cartService.getCartItems(3L));

    }

    @Test
    public void deleteItem_deletes_ifUserAndCartItemexists(){
        cartService.deleteItem(1L, 4L);
        ArrayList<Long> expectedAdIds = new ArrayList<Long>(Arrays.asList(new Long[]{1L, 5L}));

        assertEquals(expectedAdIds, cartRepository.findByUserId(1L).getAds());

    }

    @Test
    public void deleteCartItems_deletes_ifUserAndCartItemexists(){
        cartService.deleteItem(1L, 4L);
        ArrayList<Long> expectedAdIds = new ArrayList<Long>(Arrays.asList(new Long[]{1L, 5L}));

        assertEquals(expectedAdIds, cartRepository.findByUserId(1L).getAds());

    }

    @Test
    public void deleteCart_deletesAllItemsinCart_ifUserAndCartItemexists(){
        cartService.deleteCart(1L);
        assertTrue(cartRepository.findByUserId(1L).getAds().isEmpty());

    }






}
