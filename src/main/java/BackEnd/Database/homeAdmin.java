package BackEnd.Database;

import javax.swing.*;
import java.sql.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static java.lang.Integer.*;

public class homeAdmin {
    private static Statement st = database.getSt();
    public static Map<String, String> getTicketTypeInfo() {
        String sql = "SELECT * FROM public.ticket_type " +
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

    public static boolean updateEmp(String id, String name, String address, String dob, String phone_num, String username, String password, String role) throws SQLException {
        String sql = "select id from employee where id ='" + id + "';";
        String res;
        try {
            ResultSet resultSet = st.executeQuery(sql);
            resultSet.next();
            res = resultSet.getString(1);
            resultSet.close();
        } catch (SQLException e) {
            res = "";
        }
        if(res.equals("")) {
            JOptionPane.showMessageDialog(null ,"Vui lòng nhập đúng mã nhân viên để sửa thông tin", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return false;
        } else {
            if(!name.equals("")) {
                try {
                    sql = "update employee set full_name = ? where employee.id = ?";
                    PreparedStatement preparedStatement = st.getConnection().prepareStatement(sql);
                    preparedStatement.setString(1, name);
                    preparedStatement.setInt(2, Integer.parseInt(id));
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if(!address.equals("")) {
                try {
                    sql = "update employee set address = ? where employee.id = ?";
                    PreparedStatement preparedStatement = st.getConnection().prepareStatement(sql);
                    preparedStatement.setString(1, address);
                    preparedStatement.setInt(2, Integer.parseInt(id));
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (!dob.equals("")) {
                try {
                    sql = "update employee set dob = ? where employee.id = ?";
                    PreparedStatement preparedStatement = st.getConnection().prepareStatement(sql);
                    preparedStatement.setDate(1, Date.valueOf(dob));
                    preparedStatement.setInt(2, Integer.parseInt(id));
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if(!phone_num.equals("")) {
                try {
                    sql = "update employee set phone_num = ? where employee.id = ?";
                    PreparedStatement preparedStatement = st.getConnection().prepareStatement(sql);
                    preparedStatement.setString(1, phone_num);
                    preparedStatement.setInt(2, Integer.parseInt(id));
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if(!username.equals("")) {
                try {
                    sql = "update employee set username = ? where employee.id = ?";
                    PreparedStatement preparedStatement = st.getConnection().prepareStatement(sql);
                    preparedStatement.setString(1, username);
                    preparedStatement.setInt(2, Integer.parseInt(id));
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null ,"Trùng lặp username vui lòng chọn username khác", "Thông báo", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }

            if(!password.equals("")) {
                try {
                    sql = "update employee set user_password = ? where employee.id = ?";
                    PreparedStatement preparedStatement = st.getConnection().prepareStatement(sql);
                    preparedStatement.setString(1, password);
                    preparedStatement.setInt(2, Integer.parseInt(id));
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if(!role.equals("")) {
                try {
                    sql = "update employee set user_role = ? where employee.id = ?";
                    PreparedStatement preparedStatement = st.getConnection().prepareStatement(sql);
                    preparedStatement.setString(1, role);
                    preparedStatement.setInt(2, Integer.parseInt(id));
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    public static boolean deleteEmp(String id) throws SQLException {
        String sql = "select id from employee where id ='" + id + "';";
        String res;
        try {
            ResultSet resultSet = st.executeQuery(sql);
            resultSet.next();
            res = resultSet.getString(1);
            resultSet.close();
        } catch (SQLException e) {
            res = "";
        }

        if (res.equals("")) {
            JOptionPane.showMessageDialog(null ,"Vui lòng nhập chính xác mã nhân viên để xoá", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return false;
        } else {
            sql = "delete from employee where employee.id = ?";
            PreparedStatement preparedStatement = st.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, parseInt(id));
            preparedStatement.executeUpdate();
            return true;
        }
    }

    public static String sumMoney(String s, int option, Timestamp frist, Timestamp last) throws SQLException {
        String sql = "select sum(ticket_type.cost)\n" +
                "from ticket\n" +
                "inner join ticket_type on ticket.ticket_type_id = ticket_type.id\n" +
                "inner join vehicle on ticket.vehicle_id = vehicle.id\n" +
                "inner join parking_area on ticket.area_id = parking_area.id\n";
        String sqlUpdate = "and (vehicle.name = '" + s + "')";
        if (option != 0) {
            sql = sql + "where (time_in >= '" + frist + "' and time_in <='" + last + "') " + sqlUpdate;
        }
        if (option == 0 && frist == null && last==null) {
            sql = "select sum(ticket_type.cost)\n" +
            "from ticket\n" +
            "inner join ticket_type on ticket.ticket_type_id = ticket_type.id\n" +
            "inner join vehicle on ticket.vehicle_id = vehicle.id\n" +
            "inner join parking_area on ticket.area_id = parking_area.id\n";
        }
        if (option == 0 && frist != null && last!=null) {
            sql = "select sum(ticket_type.cost)\n" +
                    "from ticket\n" +
                    "inner join ticket_type on ticket.ticket_type_id = ticket_type.id\n" +
                    "inner join vehicle on ticket.vehicle_id = vehicle.id\n" +
                    "inner join parking_area on ticket.area_id = parking_area.id\n" +
                    "where (time_in >= '" + frist + "' and time_in <='" + last + "') ";
        }
        ResultSet resultSet = st.executeQuery(sql);
        System.out.print(sql);
        resultSet.next();
        if(resultSet.getString(1) == null){
            return "0";
        }
        return resultSet.getString(1);
    }

    public static String sumLuotGui(String s, int option, Timestamp frist, Timestamp last) throws SQLException {
        String sql = "select sum(ticket_type.cost)\n" +
                "from ticket\n" +
                "inner join ticket_type on ticket.ticket_type_id = ticket_type.id\n" +
                "inner join vehicle on ticket.vehicle_id = vehicle.id\n" +
                "inner join parking_area on ticket.area_id = parking_area.id\n";
        String sqlUpdate = "and (vehicle.name = '" + s + "')";
        if (option != 0) {
            sql = sql + "where (time_in >= '" + frist + "' and time_in <='" + last + "') " + sqlUpdate;
        }
        if (option == 0 && frist == null && last==null) {
            sql = "select sum(ticket_type.cost)\n" +
                    "from ticket\n" +
                    "inner join ticket_type on ticket.ticket_type_id = ticket_type.id\n" +
                    "inner join vehicle on ticket.vehicle_id = vehicle.id\n" +
                    "inner join parking_area on ticket.area_id = parking_area.id\n";
        }
        if (option == 0 && frist != null && last!=null) {
            sql = "select sum(ticket_type.cost)\n" +
                    "from ticket\n" +
                    "inner join ticket_type on ticket.ticket_type_id = ticket_type.id\n" +
                    "inner join vehicle on ticket.vehicle_id = vehicle.id\n" +
                    "inner join parking_area on ticket.area_id = parking_area.id\n" +
                    "where (time_in >= '" + frist + "' and time_in <='" + last + "') ";
        }
        ResultSet resultSet = st.executeQuery(sql);
        resultSet.next();
        return resultSet.getString(1);
    }

    public static int countVehicleIn(String s,  int options, String name_area) throws SQLException {
        String sql = "select count(vehicle.name)\n" +
                "from ticket\n" +
                "inner join ticket_type on ticket.ticket_type_id = ticket_type.id\n" +
                "inner join vehicle on ticket.vehicle_id = vehicle.id\n" +
                "inner join parking_area on ticket.area_id = parking_area.id\n";
        String updateSql;
        String currentTime = java.sql.Timestamp.from(Instant.now()).toString();
        System.out.println(currentTime);
        if(options == 0) {
            updateSql = "ticket.time_out <= '" +  currentTime + "'";
            sql = sql + updateSql;
        } else {
            updateSql = "where vehicle.name = '" + s + "' and ticket.time_out <= '" + currentTime + "' " + "and parking_area.name = '" + name_area + "'" ;
            sql = sql + updateSql;
        }
        System.out.println(sql);
        ResultSet resultSet = st.executeQuery(sql);
        resultSet.next();
        if (resultSet.getString(1) == null) {
            return 0;
        }
        return  resultSet.getInt(1);
    }

    public static String countLuotGuiThang(String s, int options, Timestamp sqlDate, Timestamp sqlDateLast) throws SQLException {
        String sql = "select count(vehicle.name)\n" +
                "from ticket\n" +
                "inner join ticket_type on ticket.ticket_type_id = ticket_type.id\n" +
                "inner join vehicle on ticket.vehicle_id = vehicle.id\n" +
                "inner join parking_area on ticket.area_id = parking_area.id\n";
        if (options == -1)  sql = sql + "where (ticket_type.name = 'motorbike_month' or ticket_type.name = 'car_month')";
        if(options == 0) {
            sql = sql + "where (ticket_type.name = 'motorbike_month' or ticket_type.name = 'car_month')" + " and (ticket.time_in >='" + sqlDate + "' and ticket.time_in <='" + sqlDateLast + "')";
        }
        if (options == 1) {
            sql = sql + "where ticket_type.name = 'motorbike_month'" + " and ticket.time_in >='" + sqlDate + "' and ticket.time_in <='" + sqlDateLast + "'";
        }
        if (options == 2) {
            sql = sql + "where ticket_type.name = 'car_month'" + " and ticket.time_in >='" + sqlDate + "' and ticket.time_in <='" + sqlDateLast + "'";
        }
        System.out.println(sql);
        ResultSet resultSet = st.executeQuery(sql);
        resultSet.next();
        return resultSet.getString(1);
    }

    public static String sumMoneyMonth(String s, int options, Timestamp sqlDate, Timestamp sqlDateLast) throws SQLException {
        String sql = "select sum(ticket_type.cost)\n" +
                "from ticket\n" +
                "inner join ticket_type on ticket.ticket_type_id = ticket_type.id\n" +
                "inner join vehicle on ticket.vehicle_id = vehicle.id\n" +
                "inner join parking_area on ticket.area_id = parking_area.id\n";
        if (options == -1)  sql = sql + "where (ticket_type.name = 'motorbike_month' or ticket_type.name = 'car_month')";
        if(options == 0) {
            sql = sql + "where (ticket_type.name = 'motorbike_month' or ticket_type.name = 'car_month')" + " and (ticket.time_in >='" + sqlDate + "' and ticket.time_in <='" + sqlDateLast + "')";
        }
        if (options == 1) {
            sql = sql + "where ticket_type.name = 'motorbike_month'" + " and ticket.time_in >='" + sqlDate + "' and ticket.time_in <='" + sqlDateLast + "'";
        }
        if (options == 2) {
            sql = sql + "where ticket_type.name = 'car_month'" + " and ticket.time_in >='" + sqlDate + "' and ticket.time_in <='" + sqlDateLast + "'";
        }
        ResultSet resultSet = st.executeQuery(sql);
        System.out.print(sql);
        resultSet.next();
        if(resultSet.getString(1) == null){
            return "0";
        }
        return resultSet.getString(1);
    }
}
