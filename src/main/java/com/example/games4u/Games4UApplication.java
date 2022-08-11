package com.example.games4u;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@Controller
@EnableAsync
public class Games4UApplication {

    public static void main(String[] args) {
        SpringApplication.run(Games4UApplication.class, args);

        SQLiteDataBase.removeAllCartId();

        // Creating a new database
        // SQLiteDataBase.createNewDatabase("Games4UDataBase");

        // Connection to database
        //Connection conn = SQLiteDataBase.connect();

        // Creating a new table
        // SQLiteDataBase.createNewTable();

        // Adding a new row in DB
        // String url = Paths.get("").toAbsolutePath().toString() + "\\src\\main\\resources\\static\\images\\gow.jpg";
        // File file = new File(url);
        // SQLiteDataBase.insert(3, "God of War", "Action", 200, file, conn);

    }

    @RequestMapping("/")
    public String homePage(Model model) {
        List<Game> games;
        Connection conn = SQLiteDataBase.connect();
        games = SQLiteDataBase.selectAll(conn);

        int quantity = 0;
        List<CartQuantity> cartQuantities;
        cartQuantities = SQLiteDataBase.takeAllCartId();
        for(int i = 0; i < cartQuantities.size(); i++) {
            int id = cartQuantities.get(i).getId();
            Game game = SQLiteDataBase.sellectById(id);
            game.setQuantity(cartQuantities.get(i).getQuantity());
            quantity = quantity + game.getQuantity();
        }

        for (int i = 0; i < cartQuantities.size(); i++) {
            if (games.get(i).getId() == cartQuantities.get(i).getId())
                games.get(i).setInCart(true);
        }

        model.addAttribute("games", games);
        model.addAttribute("quantity", quantity);
        return "index.html";
    }

    @RequestMapping("/add/cart/{id}")
    public @ResponseBody ResponseEntity addToCart(@PathVariable(value = "id") int id) {
        SQLiteDataBase.insertCartById(id);
        // HTTP 200 code
        return new ResponseEntity<>("result successful result", HttpStatus.OK);
    }

}
