package com.example.games4u;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CartController {

    // endpoint
    @RequestMapping("/cart")
    public String cartPage(){
        return "cart.html";
    }

}
