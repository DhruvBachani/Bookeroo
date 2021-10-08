package com.rmit.sept.checkoutmicroservices.web;


import com.rmit.sept.checkoutmicroservices.model.CartItem;
import com.rmit.sept.checkoutmicroservices.services.CartService;
import com.rmit.sept.checkoutmicroservices.services.MapValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@CrossOrigin
@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @Autowired
    private CartService cartService;





    @PostMapping("/addItem")
    private ResponseEntity<?> createAd(@Valid @RequestBody CartItem cartItem, BindingResult result){

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null)return errorMap;
        CartItem newCartItem = cartService.addItem(cartItem);

        return  new ResponseEntity<CartItem>(newCartItem, HttpStatus.CREATED);
    }

}
