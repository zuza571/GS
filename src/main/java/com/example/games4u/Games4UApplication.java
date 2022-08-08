package com.example.games4u;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;

import javax.imageio.ImageIO;
import javax.net.ssl.SSLSession;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        model.addAttribute("games", games);
        return "index.html";
    }

    @RequestMapping("/add/cart/{id}")
    public @ResponseBody ResponseEntity addCart(@PathVariable(value = "id") int id) {
        SQLiteDataBase.insertCartId(id);
        return new ResponseEntity<>("result successful result", HttpStatus.OK);
    }

}
