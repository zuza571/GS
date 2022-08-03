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
    /*
    @Autowired
    DataBase games;

     */

    public static void main(String[] args) {
        SpringApplication.run(Games4UApplication.class, args);

        Game g1 = new Game(1, "Grand Theft Auto V", "Sandbox", 100);
        Game g2 = new Game(2, "The Witcher 3: Wild Hunt", "RPG", 120);
        Game g3 = new Game(3, "The Last of Us: Part II", "Survival horror", 80);
        Game g4 = new Game(4, "Cyberpunk 2077", "RPG", 150);

    }

    @RequestMapping("/")
    public String homePage(){
        return "index.html";
    }




}
