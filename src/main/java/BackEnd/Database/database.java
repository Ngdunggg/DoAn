package BackEnd.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.*;

public class database {

    private static Statement st;

    public static void connectDb() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/db_do_an";
        String username = "postgres";
        String password = "hanhtinhsongsong";

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            st = con.createStatement();
            homeNv.setConnection(con);

            System.out.println("success connect to db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static Statement getSt(){
        return st;
    }
}
