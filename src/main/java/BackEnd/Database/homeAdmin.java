package BackEnd.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
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
}
