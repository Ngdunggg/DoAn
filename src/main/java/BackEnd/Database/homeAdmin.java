package BackEnd.Database;

import java.sql.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class homeAdmin {
    private static Statement st = database.getSt();
    public static Map<String, String> getTicketTypeInfo() {
        String sql = "SELECT * FROM public.ticket_type" +
                "ORDER BY id ASC ";
        try {
            ResultSet resultSet = st.executeQuery((sql));
            Map<String, String> res = new HashMap<>();
            resultSet.next();
//            res.put("motor_day", resultSet.getString(2));
            res.put("motor_day_cost", resultSet.getString(3));
            resultSet.next();
//            res.put("motor_month", resultSet.getString(2));
            res.put("motor_month_cost", resultSet.getString(3));
            resultSet.next();
//            res.put("car_day", resultSet.getString(2));
            res.put("car_day_cost", resultSet.getString(3));
            resultSet.next();
//            res.put("car_month", resultSet.getString(2));
            res.put("car_month_cost", resultSet.getString(3));
            return res;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateTicketTypeInfo(String name, String cost) throws SQLException {
        String sql = "UPDATE ticket_type " +
                "SET cost= " + cost +
                " WHERE name = '" + name + "'";

        System.out.print(sql);
                st.executeUpdate(sql);
    }

    public static Map<String, String > searchByName(String name) throws SQLException {
        Map<String, String> res = new HashMap<>();
        try {
            String sql = "select id, full_name, dob, phone_num, address, user_role, username, user_password from employee where full_name ='" + name + "';";
            ResultSet resultSet = st.executeQuery(sql);
            resultSet.next();
            res.put("id", resultSet.getString(1));
            res.put("fullname", resultSet.getString(2));
            res.put("dob", resultSet.getString(3));
            res.put("phone_num", resultSet.getString(4));
            res.put("address", resultSet.getString(5));
            res.put("user_role", resultSet.getString(6));
            res.put("username", resultSet.getString(7));
            res.put("password", resultSet.getString(8));
            return res;
        } catch (SQLException e) {
            res.put("id", "");
            return res;
        }

    }

    public static Map<String, String > searchById(String id) throws SQLException {
        Map<String, String> res = new HashMap<>();
        try {
            String sql = "select id, full_name, dob, phone_num, address, user_role, username, user_password from employee where id ='" + id + "';";
            ResultSet resultSet = st.executeQuery(sql);
            resultSet.next();
            res.put("id", resultSet.getString(1));
            res.put("fullname", resultSet.getString(2));
            res.put("dob", resultSet.getString(3));
            res.put("phone_num", resultSet.getString(4));
            res.put("address", resultSet.getString(5));
            res.put("user_role", resultSet.getString(6));
            res.put("username", resultSet.getString(7));
            res.put("password", resultSet.getString(8));
            return res;
        } catch (SQLException e) {
            res.put("id", "");
            return res;
        }

    }

    public static  void insertToEmp(String name, String address, String dob, String phone_num, String username, String password, String role) throws SQLException {
        try {
            String sql = "insert into employee (full_name, phone_num, address, username, user_role, user_password, dob) " +
                    "values (?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement preparedStatement = st.getConnection().prepareStatement(sql);
//            preparedStatement.setString(1, String.valueOf(id));
            preparedStatement.setString(1, name);
//            preparedStatement.setString(2, dob);
            preparedStatement.setString(2, phone_num);
            preparedStatement.setString(3, address);
            preparedStatement.setString(4, username);
            preparedStatement.setString(5, role);
            preparedStatement.setString(6, password);
            preparedStatement.setDate(7, Date.valueOf(dob));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
