package com.example.games4u;
import java.sql.*;

public class JDBC {

    static String dataFromDB;

    public static void main(String[] args) {
        String connectionURL = "jdbc:mysql://localhost:3306/Games4UDataBase/test?user=root&password=zuzaikuba";
        // Proste zapytanie do bazy danych
        String query = "Select * FROM Games";

        Connection conn = null;

        try {
            // Ustawiamy dane dotyczące połączenia
            conn = DriverManager.getConnection(connectionURL);

            // Ustawiamy sterownik MySQL
            Class.forName("com.mysql.jdbc.Driver");

            // Uruchamiamy zapytanie do bazy danych
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            conn.close();
        } catch (ClassNotFoundException e){
            System.out.println("Problem ze sterownikiem");
        } catch (SQLException e) {
            // e.printStackTrace();
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());

        }
    }
}
