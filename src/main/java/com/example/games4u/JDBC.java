package com.example.games4u;
import java.sql.*;

public class JDBC {

    static String daneZBazy;

    static void wyswietlDaneZBazy(ResultSet rs){
        try{
            daneZBazy = rs.getString(1);
            System.out.println("\n" + daneZBazy + " ");
            daneZBazy = rs.getString(2);
            System.out.println(daneZBazy + " ");
            daneZBazy = rs.getString(3);
            System.out.println(daneZBazy);
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String connectionURL = "jdbc:mysql://localhost:3306/mydb?user=root&password=zuzaikuba";
        // Proste zapytanie do bazy danych
        String query = "Select * FROM mydb.games";

        Connection conn = null;

        try {
            // Ustawiamy sterownik MySQL - jest to generalnie niepotrzebne gdyż dzieje się automatycznie
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Ustawiamy dane dotyczące połączenia
            conn = DriverManager.getConnection(connectionURL);

            // Uruchamiamy zapytanie do bazy danych
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                wyswietlDaneZBazy(rs);
            }

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
