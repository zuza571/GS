package com.example.games4u.controllers;

import com.example.games4u.CartQuantity;
import com.example.games4u.Game;
import com.example.games4u.SQLiteDataBase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {

    // endpoint
    @RequestMapping("/cart")
    public String cartPage(Model model) {

        List<Game> gamesIds = new ArrayList<>();
        int quantity = 0;
        int subtotal = 0;
        List<CartQuantity> cartQuantities;
        cartQuantities = SQLiteDataBase.takeAllCartId();
        for(int i = 0; i < cartQuantities.size(); i++) {
            int id = cartQuantities.get(i).getId();
            Game game = SQLiteDataBase.sellectById(id);
            game.setQuantity(cartQuantities.get(i).getQuantity());
            quantity = quantity + game.getQuantity();
            subtotal = subtotal + (game.getPrice() * game.getQuantity());
            gamesIds.add(game);
        }

        model.addAttribute("gamesIds", gamesIds);
        model.addAttribute("quantity", quantity);
        model.addAttribute("subtotal", subtotal);
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
