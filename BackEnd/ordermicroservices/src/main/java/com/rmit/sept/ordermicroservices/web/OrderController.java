package com.rmit.sept.ordermicroservices.web;

import com.paypal.http.HttpResponse;
import com.paypal.payments.Refund;
import com.rmit.sept.ordermicroservices.model.Cart;
import com.rmit.sept.ordermicroservices.model.PurchasedBook;
import com.rmit.sept.ordermicroservices.model.Transaction;
import com.rmit.sept.ordermicroservices.payload.CheckoutRequest;
import com.rmit.sept.ordermicroservices.payload.RefundRequest;
import com.rmit.sept.ordermicroservices.security.JwtUtil;
import com.rmit.sept.ordermicroservices.services.CartService;
import com.rmit.sept.ordermicroservices.services.MapValidationErrorService;
import com.rmit.sept.ordermicroservices.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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

    // Retrieves all users for frontend
    @PostMapping("/refund")
    public String getRefund(@Valid @RequestBody RefundRequest refundRequest) throws IOException {
        return orderService.refundOrder(refundRequest.getOrderId(), true);
    }


    @PostMapping("/addToCart")
    private ResponseEntity<?> addToCart(@Valid @RequestBody List<Long> ads, BindingResult result, @RequestHeader("authorization") String token){
        if(token==null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null) return errorMap;

        Cart newCart = cartService.saveCart(JwtUtil.getUserIdFromJWT(token.replace("Bearer","").trim()), ads);
        return new ResponseEntity<Cart>(newCart, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteCartItem/{adId}")
    private ResponseEntity<?> deleteCartItem(@RequestHeader("authorization") String token, @PathVariable String adId){

        if(token==null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        cartService.deleteItem(JwtUtil.getUserIdFromJWT(token.replace("Bearer","").trim()), Long.valueOf(adId));
        return new ResponseEntity<>(HttpStatus.OK);
//        cartService.saveCart(1L, adIds);
    }

    @GetMapping("/cartItems")
    private List<Long> getCartItems(@RequestHeader("authorization") String token){
        if(token==null){
            return null;
        }
        return cartService.getCartItems(JwtUtil.getUserIdFromJWT(token.replace("Bearer","").trim()));
    }
}
