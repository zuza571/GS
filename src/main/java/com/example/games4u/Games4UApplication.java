package com.example.games4u;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.Connection;

@SpringBootApplication
@Controller
public class Games4UApplication {

    public static void main(String[] args) throws MalformedURLException {
        SpringApplication.run(Games4UApplication.class, args);

        // Creating a new database
        SQLiteDataBase.createNewDatabase("Games4UDataBase");

        // Connection to database
        Connection conn = SQLiteDataBase.connect();

        // Creating a new table
        SQLiteDataBase.createNewTable();

        File file = new File("https://drive.google.com/file/d/1yD-dR_3c63rOfESLPPQwfi3sZb7Y0NUQ/view?usp=sharing");

        //String url = Paths.get("").toAbsolutePath().toString() + "\\src\\main\\resources\\static\\images\\cyberpunk.jpg";
        //File file = new File(url);

        SQLiteDataBase.insert(1, "Cyberpunk 2077", "RPG", 150, file, conn);
    }

    @RequestMapping("/")
    public String homePage(Model model){
        // Game g1 = new Game(1, "Grand Theft Auto V", "Sandbox", 100);
        // model.addAttribute("g1", g1);
        return "index.html";
    }




}
