package com.example.games4u;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@SpringBootApplication
@Controller
public class Games4UApplication {

    // nwm czy to tu :(((((
    @Autowired
    DataBase games;

    public static void main(String[] args) {
        SpringApplication.run(Games4UApplication.class, args);
    }

    @RequestMapping("/")
    public String homePage(){
        return "index.html";
    }




}
