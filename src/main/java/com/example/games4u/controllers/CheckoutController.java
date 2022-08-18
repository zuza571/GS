package com.example.games4u.controllers;

import com.example.games4u.services.CartService;
import com.example.games4u.services.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;

@Controller
public class CheckoutController {

    @Autowired
    CartService cartService;
    @Autowired
    CheckoutService checkoutService;

    @RequestMapping("/checkout")
    public String checkoutPage(Model model){
        model.addAttribute("gamesIds", cartService.getGames());
        model.addAttribute("subtotal", cartService.getSubtotal());
        return "checkout.html";
    }

    // json order data from js
    @RequestMapping(value = "/order", method = RequestMethod.POST, consumes = "application/json")
        @ResponseBody
        public void order(@RequestBody String order) throws FileNotFoundException {
        checkoutService.orderInfo(order);
    }
}
