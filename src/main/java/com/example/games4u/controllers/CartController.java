package com.example.games4u.controllers;

import com.example.games4u.SQLiteDataBase;
import com.example.games4u.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CartController {

    @Autowired
    CartService cartService;

    // endpoint
    @RequestMapping("/cart")
    public String cartPage(Model model) {
        model.addAttribute("gamesIds", cartService.getGames());
        model.addAttribute("quantity", cartService.getQuantity());
        model.addAttribute("subtotal", cartService.getSubtotal());
        return "cart.html";
    }

    @RequestMapping("/remove/cart/{id}")
    public @ResponseBody ResponseEntity removeFromCart(@PathVariable(value = "id") int id) {
        SQLiteDataBase.removeByCartId(id);
        // HTTP 200 code
        return new ResponseEntity<>("result successful result", HttpStatus.OK);
    }

    @RequestMapping("/add/quantity/{id}")
    public @ResponseBody ResponseEntity addCartQuantity(@PathVariable(value = "id") int id) {
        SQLiteDataBase.addQuantity(id);
        // HTTP 200 code
        return new ResponseEntity<>("result successful result", HttpStatus.OK);
    }

    @RequestMapping("/subtract/quantity/{id}")
    public @ResponseBody ResponseEntity subtractCartQuantity(@PathVariable(value = "id") int id) {
        SQLiteDataBase.subtractQuantity(id);
        // HTTP 200 code
        return new ResponseEntity<>("result successful result", HttpStatus.OK);
    }


}
