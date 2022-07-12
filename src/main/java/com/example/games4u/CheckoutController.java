package com.example.games4u;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CheckoutController {
    @RequestMapping("/checkout")
    public String checkoutPage(){
        return "checkout.html";
    }
}
