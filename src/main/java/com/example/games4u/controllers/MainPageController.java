package com.example.games4u.controllers;

import com.example.games4u.SQLiteDataBase;
import com.example.games4u.services.MainPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainPageController {

    @Autowired
    MainPageService mainPageService;

    @RequestMapping("/")
    public String homePage(Model model) {
        model.addAttribute("games", mainPageService.getGames());
        model.addAttribute("quantity", mainPageService.getQuantity());
        return "index.html";
    }

    @RequestMapping("/add/cart/{id}")
    public @ResponseBody
    ResponseEntity addToCart(@PathVariable(value = "id") int id) {
        SQLiteDataBase.insertCartById(id);
        // HTTP 200 code
        return new ResponseEntity<>("result successful result", HttpStatus.OK);
    }
}
