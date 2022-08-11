package com.example.games4u.controllers;

import com.example.games4u.CartQuantity;
import com.example.games4u.Game;
import com.example.games4u.SQLiteDataBase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CheckoutController {

    @RequestMapping("/checkout")
    public String checkoutPage(Model model){
        List<Game> gamesIds = new ArrayList<>();
        int subtotal = 0;
        List<CartQuantity> cartQuantities;
        cartQuantities = SQLiteDataBase.takeAllCartId();
        for(int i = 0; i < cartQuantities.size(); i++) {
            int id = cartQuantities.get(i).getId();
            Game game = SQLiteDataBase.sellectById(id);
            game.setQuantity(cartQuantities.get(i).getQuantity());
            subtotal = subtotal + (game.getPrice() * game.getQuantity());
            gamesIds.add(game);
        }

        model.addAttribute("gamesIds", gamesIds);
        model.addAttribute("subtotal", subtotal);
        return "checkout.html";
    }
}
