package com.example.games4u;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AboutController {

    // endpoint
    @RequestMapping("/about")
    public String aboutPage(){
        return "about.html";
    }


}
