package com.example.games4u.controllers;

import com.example.games4u.Order;
import com.example.games4u.SQLiteDataBase;
import com.example.games4u.services.PayPalService;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PayPalController {

    @Autowired
    PayPalService service;

    public static final String SUCCESS_URL = "payment/success";
    public static final String CANCEL_URL = "payment/cancel";

    @PostMapping("/payment")
    public String payment(@ModelAttribute("order") Order order) {
        try {
            Payment payment = service.createPayment(order.getPrice(), order.getName(), order.getSurname(),
                    order.getEmail(), "http://localhost:8080/" + CANCEL_URL,
                    "http://localhost:8080/" + SUCCESS_URL);

            for(Links link:payment.getLinks()) {
                if(link.getRel().equals("approval_url")) {
                    return "redirect:"+link.getHref();
                }
            }

        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }

    @GetMapping(value = CANCEL_URL)
    public String cancelPay() {
        return "cancel.html";
    }

    @GetMapping(value = SUCCESS_URL)
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        try {
            Payment payment = service.executePayment(paymentId, payerId);
            System.out.println();
            System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")) {
                return "success.html";
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/";
    }

}
