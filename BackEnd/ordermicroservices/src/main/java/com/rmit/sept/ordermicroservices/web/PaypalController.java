package com.rmit.sept.ordermicroservices.web;



import com.rmit.sept.ordermicroservices.model.Order;
import com.rmit.sept.ordermicroservices.services.PaypalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

@Controller
public class PaypalController {

    @Autowired
    PaypalService service;

    public static final String SUCCESS_URL = "pay/success";
    public static final String CANCEL_URL = "pay/cancel";

    @PostMapping("/pay")
    public String payment(@ModelAttribute("order") Order order) {
        try {
            // TODO: Data must be taken from the checkout microservice
            // Specifically, book id, title, cost of each book
            // Currently it is hardcoded.
            Payment payment = service.createPayment(5.00, "AUD", "paypal",
                    "sale", "testing", "http://localhost:8000/" + CANCEL_URL,
                    "http://localhost:8000/" + SUCCESS_URL);

            // Upon success, we execute successPay

            for(Links link:payment.getLinks()) {
                if(link.getRel().equals("approval_url")) {
                    // TODO: redirect correctly - complete frontend as well (ties with checkout microservice)
                    return "redirect:"+link.getHref();
                }
            }

        } catch (PayPalRESTException e) {

            e.printStackTrace();
        }
        return "redirect:/dashboard";
    }

    @GetMapping(value = CANCEL_URL)
    public String cancelPay() {
        return "cancel";
    }

    @GetMapping(value = SUCCESS_URL)
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        try {
            Payment payment = service.executePayment(paymentId, payerId);
            System.out.println(payment.toJSON());
            // TODO: Save information into database
            if (payment.getState().equals("approved")) {
                return "success";
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/dashboard";
    }

}