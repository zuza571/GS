package com.example.games4u.controllers;

import com.example.games4u.services.CartService;
import com.example.games4u.services.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CheckoutController {

    @Autowired
    CartService cartService;
    CheckoutService checkoutService;

    @RequestMapping("/checkout")
    public String checkoutPage(Model model){
        model.addAttribute("gamesIds", cartService.getGames());
        model.addAttribute("subtotal", cartService.getSubtotal());
        return "checkout.html";
    }
}
