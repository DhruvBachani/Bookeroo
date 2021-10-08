package com.rmit.sept.checkoutmicroservices.services;

import com.rmit.sept.checkoutmicroservices.Repositories.CartRepository;
import com.rmit.sept.checkoutmicroservices.model.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;
import java.util.Locale;


@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;

        public CartItem addItem(CartItem cartItem){
            return cartRepository.save(cartItem);
        }
//    public Ad saveAd(Ad newAd) {
//        if(!validIsbn(newAd.getIsbn())){
//            throw new InvalidIsbnException("Book with given Isbn doesn't exists");
//        }
//        if (!validCondition(newAd.getCondition())){
//            throw new InvalidConditionException("Invalid Condition provided");
//        }
//        return adRepository.save(newAd);
//    }

}