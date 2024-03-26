package BackEnd.Database;

import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class login {
    private static Statement statement = database.getSt();


    public static Map<String, String> getLoginInfo(String username) {
        String sql = "select * from employee";
        try {
            ResultSet rs =statement.executeQuery(sql);
            Map<String, String> res = new HashMap<>();
            while(rs.next()) {
//                System.out.print(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3))
                res.put("username", rs.getString(2));
//                rs.next();
                res.put("password", rs.getString(1));
            }
            return res;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Map<String, String> getLoginInfomation(String username) {
        String sql = "select username, user_password, user_role from employee " + "where username = '" + username + "'";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            Map<String, String> res = new HashMap<>();
            while(resultSet.next()) {
                res.put("username", resultSet.getString(1));
                res.put("password", resultSet.getString(2));
                res.put("role", resultSet.getString(3));
            }
            return res;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
