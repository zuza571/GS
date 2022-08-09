package com.example.games4u.controllers;

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

        // todo: dodac quantity do cart.html
        List<Game> gamesIds = new ArrayList<>();
        List<Integer> cartIds;
        cartIds = SQLiteDataBase.takeAllCartId();
        for(int i:cartIds) {
            Game game = SQLiteDataBase.sellectById(i);
            gamesIds.add(game);
        }

        model.addAttribute("gamesIds", gamesIds);
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
