package com.example.games4u;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
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

        try {
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

            // =========================================================
            // inserting image to database
            byte[] image = null;

            try {
                FileInputStream fis = new FileInputStream(file);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buf = new byte[1024];

                for (int i; (i = fis.read(buf)) != -1; ) {
                    baos.write(buf, 0, i);
                }

                image = baos.toByteArray();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

            pstmt.setBytes(5, image);
            pstmt.executeUpdate();
            // ==========================================================

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

                // ============================================
                // converting from byte[] to BufferedImage
                byte[] blob = rs.getBytes("image");
                ByteArrayInputStream bis = new ByteArrayInputStream(blob);
                BufferedImage image = ImageIO.read(bis);

                // saving image to jpg file with its id name -> for html
                ImageIO.write(image, "jpg", new File("src/main/resources/static/images-html/" +
                        rs.getInt("id") + ".jpg"));
                game.setImage(image);
                // ============================================

                games.add(game);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        return games;
    }

    public static Game sellectById(int id) {
        Connection conn = SQLiteDataBase.connect();
        Game game = new Game();

        String query = "SELECT * FROM games WHERE id = " + "\"" + id + "\"";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            game.setId(rs.getInt(1));
            game.setName(rs.getString(2));
            game.setType(rs.getString(3));
            game.setPrice(rs.getInt(4));

            byte[] blob = rs.getBytes("image");
            ByteArrayInputStream bis = new ByteArrayInputStream(blob);
            BufferedImage image = ImageIO.read(bis);
            game.setImage(image);

        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }

        try{
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return game;
    }

    public static void insertCartById(int id) {
        Connection conn = SQLiteDataBase.connect();

        String sql = "INSERT or REPLACE INTO cart_id(id, quantity) VALUES(?,?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setInt(2, 1);
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try{
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void removeAllCartId() {
        Connection conn = SQLiteDataBase.connect();

        String sql = "DELETE FROM cart_id";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try{
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static List<Integer> takeAllCartId() {
        Connection conn = SQLiteDataBase.connect();
        List<Integer> cartId = new ArrayList<>();
        String sql = "SELECT * FROM cart_id";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                cartId.add(rs.getInt(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try{
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return cartId;
    }

    public static void removeByCartId(int id) {
        Connection conn = SQLiteDataBase.connect();

        String sql = "DELETE FROM cart_id WHERE id = " + "\"" + id + "\"";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try{
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void addQuantity (int id) {
        Connection conn = SQLiteDataBase.connect();

        String query1 = "SELECT * FROM cart_id WHERE id = " + "\"" + id + "\"";
        String query2 = "REPLACE INTO cart_id(id,quantity) VALUES(?,?)";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query1);
            int oldId = rs.getInt(1);
            int newQuantity = rs.getInt(2)+1;

            try (PreparedStatement pstmt = conn.prepareStatement(query2)) {
                pstmt.setInt(1, oldId);
                pstmt.setInt(2, newQuantity);
                pstmt.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try{
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void subtractQuantity(int id) {
        Connection conn = SQLiteDataBase.connect();

        String query1 = "SELECT * FROM cart_id WHERE id = " + "\"" + id + "\"";
        String query2 = "REPLACE INTO cart_id(id,quantity) VALUES(?,?)";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query1);
            int oldId = rs.getInt(1);
            int newQuantity = rs.getInt(2)-1;

            try (PreparedStatement pstmt = conn.prepareStatement(query2)){
                pstmt.setInt(1, oldId);
                pstmt.setInt(2, newQuantity);
                pstmt.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try{
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}