package BackEnd.Database;

import com.mycompany.mytest.HomeForNV;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.*;

public class database {

    private static Statement st;

    public static void connectDb() throws SQLException {
        String url = dotenv.PostgreUrl;
        String username = dotenv.name;
        String password = dotenv.password;

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
