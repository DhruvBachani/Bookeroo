package com.rmit.sept.ordermicroservices.web;

import com.rmit.sept.ordermicroservices.model.PurchasedBook;
import com.rmit.sept.ordermicroservices.model.Transaction;
import com.rmit.sept.ordermicroservices.payload.CheckoutRequest;
import com.rmit.sept.ordermicroservices.security.JwtTokenProvider;
import com.rmit.sept.ordermicroservices.services.CartService;
import com.rmit.sept.ordermicroservices.services.MapValidationErrorService;
import com.rmit.sept.ordermicroservices.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @Autowired
    OrderService orderService;

    @Autowired
    CartService cartService;

    @PostMapping("/checkout")
    public void saveOrder(@Valid @RequestBody CheckoutRequest checkoutRequest) throws IOException {
        System.out.println(checkoutRequest.getPaypalOrderId() + " " + checkoutRequest.getUserId());
        orderService.saveOrder(checkoutRequest.getPaypalOrderId(), checkoutRequest.getUserId());
    }

    @GetMapping("/checkout/{orderId}")
    public Transaction getOrder(@PathVariable String orderId){
        return null;
    }

    @GetMapping("/user/all")
    public List<Transaction> getAllTransactions(){
        return orderService.getAllTransactions();
    }

    @GetMapping("/book/all")
    public List<PurchasedBook> getAllBookTransactions(){
        return orderService.getAllBookTransactions();
    }

    @GetMapping("/user/{userId}")
    public List<Transaction> getUserTransactions(@PathVariable String userId) {
        return orderService.getTransactionsByUserId(userId);
    }

    @DeleteMapping("/checkout/{orderId}")
    public void deleteOrder(@PathVariable String orderId) {

    }

    @PatchMapping("/checkout/{orderId}")
    public void updateOrder(@PathVariable String orderId) {

    }


    @PostMapping("/addToCart")
    private ResponseEntity<?> addToCart(@Valid @RequestBody List<Long> ads, BindingResult result){
        JwtTokenProvider.validateToken();
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null) return errorMap;
        System.out.println("====================");

        System.out.println(ads);

        cartService.saveCart(1L, ads);
        return null;
//        cartService.saveCart(1L, adIds);
    }

    @DeleteMapping("/deleteCartItem")
    private ResponseEntity<?> deleteCartItem(@Valid @RequestBody Long ad, BindingResult result){

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null) return errorMap;


        cartService.deleteItem(1L, ad);
        return null;
//        cartService.saveCart(1L, adIds);
    }

    @GetMapping("/cartItems")
    private List<Long> getCartItems(){
        return cartService.getCartItems(1L);
    }
}
