package com.example.games4u.controllers;

import com.example.games4u.CartQuantity;
import com.example.games4u.SQLiteDataBase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
public class AboutController {

    // endpoint
    @RequestMapping("/about")
    public String aboutPage(Model model){
        int quantity = 0;
        List<CartQuantity> cartQuantities;
        cartQuantities = SQLiteDataBase.takeAllCartId();
        for(int i = 0; i < cartQuantities.size(); i++) {
           quantity = quantity + cartQuantities.get(i).getQuantity();
        }

        model.addAttribute("quantity", quantity);
        return "about.html";
    }

}
