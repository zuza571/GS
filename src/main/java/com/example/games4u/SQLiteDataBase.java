package com.example.games4u;

import org.slf4j.impl.StaticMarkerBinder;

import javax.swing.*;
import java.io.*;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLiteDataBase {

    public static void createNewDatabase(String fileName) {

        // URL for DataBase
        String url = "jdbc:sqlite:" + Paths.get("").toAbsolutePath().toString() + "\\" + fileName;
        System.out.println("new database url: " + url);

        try {
            Connection conn = DriverManager.getConnection(url);
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }



    public static Connection connect() {
        Connection conn = null;
        try {
            // db parameters - URL for DataBase
            String url = "jdbc:sqlite:" + Paths.get("").toAbsolutePath().toString() + "\\" + "Games4UDataBase";
            System.out.println("connection url: " + url);

            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }

    public static void createNewTable() {
        // SQLite connection string
        String url = "jdbc:sqlite:" + Paths.get("").toAbsolutePath().toString() + "\\" + "Games4UDataBase";
        System.out.println("table url: " + url);

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS games (\n"
                + " id INT PRIMARY KEY NOT NULL, \n"
                + " name TEXT NOT NULL, \n"
                + " type TEXT NOT NULL, \n"
                + " price INT NOT NULL, \n"
                + " image BLOB NOT NULL \n"
                + ");";

        try{
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            System.out.println("Your table has been added");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insert(int id, String name, String type, int price, File file, Connection conn) {

        String sql = "INSERT or REPLACE INTO games(id, name, type, price, image) VALUES(?,?,?,?,?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, type);
            pstmt.setInt(4, price);


            // -------------------------
            // inserting image to database
            // nie wiem czy dziala

            byte[] image = null;

            try {
                FileInputStream fis = new FileInputStream(file);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buf = new byte[1024];

                for (int i; (i = fis.read(buf)) !=-1;){
                    baos.write(buf,0,i);
                }

                image = baos.toByteArray();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

            pstmt.setBytes(5,image);

            pstmt.executeUpdate();

            System.out.println("Item has been added to database.");

            // -------------------------

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            Logger.getLogger(SQLiteDataBase.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public static List<Game> selectAll(Connection conn) {

        List<Game> games = new ArrayList<>();

        String query = "SELECT * FROM games";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Game game = new Game();
                game.setId(rs.getInt("id"));
                game.setName(rs.getString("name"));
                game.setType(rs.getString("type"));
                game.setPrice(rs.getInt("price"));
                // game.setImage(rs.getBlob("image"));
                games.add(game);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return games;
    }
}
