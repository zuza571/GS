package com.example.games4u.controllers;

import com.example.games4u.Game;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CheckoutController {

    @RequestMapping("/checkout")
    public String checkoutPage(Model model){
        Game g1 = new Game(1, "Grand Theft Auto V", "Sandbox", 100);
        model.addAttribute("g1", g1);
        return "checkout.html";
    }
}
