package com.example.games4u;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
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
}
