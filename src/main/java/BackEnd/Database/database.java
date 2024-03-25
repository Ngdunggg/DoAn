package BackEnd.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.*;

public class database {

    public static void connectDb() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/db-do-an";
        String username = "postgres";
        String password = "hanhtinhsongsong";

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            System.out.println("success connect to db");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
