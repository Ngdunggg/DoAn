package BackEnd.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.*;

public class database {

    public static void connectDb() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/Parking_Manager";
        String username = "postgres";
        String password = "123456";

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            System.out.println("success connect to db");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}