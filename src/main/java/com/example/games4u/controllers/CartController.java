package com.example.games4u.controllers;

import com.example.games4u.Game;
import com.example.games4u.services.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CartController {

    // endpoint
    @RequestMapping("/cart")
    public String cartPage(Model model){
        Game g1 = new Game(1, "Grand Theft Auto V", "Sandbox", 100);
        model.addAttribute("g1", g1);
        return "cart.html";
    }

    // mapping for buttons
    @GetMapping ("/increase/{itemIndex}")
    public String increaseItemsInCart (@PathVariable Integer itemIndex) {
        CartService.increaseItem(itemIndex);
        return "redirect:/cart/";
    }

    @GetMapping ("/decrease/{itemIndex}")
    public String decreaseItemsInCart (@PathVariable Integer itemIndex) {
        CartService.decreaseItem(itemIndex);
        return "redirect:/cart/";
    }

}
