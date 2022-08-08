package com.example.games4u.controllers;

import com.example.games4u.Game;
import com.example.games4u.SQLiteDataBase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
public class AboutController {

    // endpoint
    @RequestMapping("/about")
    public String aboutPage(Model model){

        List<Game> gamesIds = new ArrayList<>();
        List<Integer> cartIds;
        cartIds = SQLiteDataBase.takeAllCartId();
        for(int i:cartIds) {
            Game game = SQLiteDataBase.sellectById(i);
            gamesIds.add(game);
        }

        model.addAttribute("gamesIds", gamesIds);
        return "about.html";
    }

}
