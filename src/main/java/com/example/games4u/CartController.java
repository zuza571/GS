package com.example.games4u;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

}
