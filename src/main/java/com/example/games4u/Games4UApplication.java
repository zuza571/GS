package com.example.games4u;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@Controller
public class Games4UApplication {

    public static void main(String[] args) {
        SpringApplication.run(Games4UApplication.class, args);

        // Creating a new database
        // SQLiteDataBase.createNewDatabase("testowa_bazka");

        // Connection to database
        SQLiteDataBase.connect();

        // Creating a new table
        // SQLiteDataBase.createNewTable();
    }

    @RequestMapping("/")
    public String homePage(Model model){
        Game g1 = new Game(1, "Grand Theft Auto V", "Sandbox", 100);
        model.addAttribute("g1", g1);
        return "index.html";
    }




}
