package com.example.games4u;

import java.sql.*;

public class SQLiteDataBase {


    public static void createNewDatabase(String fileName) {

        String url = "jdbc:sqlite:D:/Intellij IDEA/Intellij IDEA 2021.2.3/Games4U/" + fileName;

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

    public static void connect() {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:D:/Intellij IDEA/Intellij IDEA 2021.2.3/Games4U/testowa_bazka";
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static void createNewTable() {
        // SQLite connection string
        String url = "jdbc:sqlite:D:/Intellij IDEA/Intellij IDEA 2021.2.3/Games4U/testowa_bazka";

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS games (\n"
                + " id INT PRIMARY KEY NOT NULL,\n"
                + " name TEXT NOT NULL,\n"
                + " type TEXT NOT NULL, \n"
                + " price INT NOT NULL \n"
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
}
