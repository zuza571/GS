package com.example.games4u.controllers;

import com.example.games4u.Game;
import com.example.games4u.Games4UApplication;
import com.example.games4u.SQLiteDataBase;
import com.example.games4u.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.concurrent.SettableListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {

    // endpoint
    @RequestMapping("/cart")
    public String cartPage(Model model) {
        List<Integer> cartIds;
        List<Game> games = new ArrayList<>();
        cartIds = SQLiteDataBase.takeAllCartId();
        for(int i:cartIds) {
            Game game = SQLiteDataBase.sellectById(i);
            games.add(game);
        }
        model.addAttribute("games", games);
        return "cart.html";
    }

    // mapping for buttons
    @GetMapping ("/cart/increase/{itemIndex}")
    public String increaseItemsInCart (@PathVariable Integer itemIndex) {
        CartService.increaseItem(itemIndex);
        return "redirect:/cart/";
    }

    @GetMapping ("/cart/decrease/{itemIndex}")
    public String decreaseItemsInCart (@PathVariable Integer itemIndex) {
        CartService.decreaseItem(itemIndex);
        return "redirect:/cart/";
    }
}
