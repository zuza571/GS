package com.example.games4u;
import java.sql.*;

public class JDBC {

    static String dataFromDB;

    static void showData(ResultSet rs){
        try{
            dataFromDB = rs.getString(1);
            System.out.println("\n" + dataFromDB + " ");
            dataFromDB = rs.getString(2);
            System.out.println(dataFromDB + " ");
            dataFromDB = rs.getString(3);
            System.out.println(dataFromDB);
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String connectionURL = "jdbc:mysql://localhost:3306/mydb?user=root&password=zuzaikuba";
        // Simple query to db
        String query = "Select * FROM mydb.games";

        Connection conn = null;

        try {
            // We put the MySQL driver - it is generally unnecessary as it happens automatically
            Class.forName("com.mysql.cj.jdbc.Driver");

            // We set the connection data
            conn = DriverManager.getConnection(connectionURL);

            // We run a query to the database
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                showData(rs);
            }

            conn.close();
        } catch (ClassNotFoundException e){
            System.out.println("driver's issue");
        } catch (SQLException e) {
            // e.printStackTrace();
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());

        }
    }
}
