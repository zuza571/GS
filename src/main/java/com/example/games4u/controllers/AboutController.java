package com.example.games4u.controllers;
import com.example.games4u.services.AboutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AboutController {

    @Autowired
    AboutService aboutService;

    // endpoint
    @RequestMapping("/about")
    public String aboutPage(Model model){
        model.addAttribute("quantity", aboutService.getQuatntity());
        return "about.html";
    }

}
