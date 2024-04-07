package BackEnd.Database;
import com.mycompany.mytest.My;
import javax.swing.JOptionPane;
import java.security.SecureRandom;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class homeNv {
    private static Statement st = database.getSt();
    private static Connection con;
    private static Character sda;
//    private static PreparedStatement pst;
    public static void setConnection(Connection connection)
    {
        con=connection;

    }

    //Xe vao
    public static Map<String, String> GetVehicleInInfo()
    {
        String sql = "SELECT * FROM public.vehicle" +
                "ORDER BY id ASC ";
        try{
            ResultSet resultSet = st.executeQuery((sql));
            Map<String, String> res = new HashMap<>();
            resultSet.next();
            res.put("Palte",resultSet.getString(1));
            return  res;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public static List<Map<String, String>> getTicketInfo() throws SQLException {
        List<Map<String, String>> resultList = new ArrayList<>();
        String sql = "SELECT t.id, t.ticket_type_id, t.time_in, t.time_out, t.out_date, t.vehicle_id, t.area_id, " +
                "tt.name AS ticket_type_name, tt.cost, v.name AS vehicle_name " +
                "FROM ticket t " +
                "INNER JOIN ticket_type tt ON t.ticket_type_id = tt.id " +
                "INNER JOIN vehicle v ON t.vehicle_id = v.id " +
                "ORDER BY t.id ASC ";
        try {
            ResultSet resultSet = st.executeQuery(sql);
            while (resultSet.next()) {
                Map<String, String> row = new HashMap<>();
                row.put("id", resultSet.getString(1));
                row.put("ticket_type_id", resultSet.getString(2));
                row.put("time_in", resultSet.getString(3));
                row.put("time_out", resultSet.getString(4));
                row.put("out_date", resultSet.getString(5));
                row.put("vehicle_id", resultSet.getString(6));
                row.put("area_id", resultSet.getString(7));
                row.put("name", resultSet.getString(8));
                row.put("cost", resultSet.getString(9));
                row.put("vehicle_name", resultSet.getString(10));
                resultList.add(row);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }


    public static void insertVehicleInfo(String Plate, String name,int areaId) throws SQLException {

       PreparedStatement pst = con.prepareStatement("INSERT INTO public.vehicle(id, name,parking_area_id) VALUES (?,?,?)");
        pst.setString(1,Plate);
        pst.setString(2,name);
        pst.setInt(3,areaId);

        pst.executeUpdate();
    }

    public static void insertTicketInfo(String ticketCode, Timestamp timeIn, Integer ticketType, Integer area, String vehicle_id) throws SQLException {

        try {
            PreparedStatement pst = con.prepareStatement("INSERT INTO public.ticket (id, time_in, area_id, ticket_type_id,vehicle_id, emp_id) VALUES (?, ?, ?, ?,?, ?)") ;
            pst.setString(1, ticketCode);
            pst.setTimestamp(2, timeIn);
            pst.setInt(3, area);
            pst.setInt(4, ticketType);
            pst.setString(5,vehicle_id);
            pst.setInt(6, Integer.parseInt(My.user_in));

            int rowsAffected = pst.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting ticket: " + e.getMessage());
        }
    }

    public static void getboxTicketTypeInfo()
    {
        String sql = "SELECT id FROM ticket_type";
        try {

            ResultSet resultSet = st.executeQuery((sql));
            String rs;
            rs = resultSet.getString(1);
            resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void insertTimeOut(Timestamp timeOut, String vehicleId) throws SQLException {

        PreparedStatement pst = con.prepareStatement("UPDATE ticket SET  time_out = ? WHERE vehicle_id = ?");
        pst.setTimestamp(1,timeOut);
        pst.setString(2,vehicleId);

        pst.executeUpdate();
    }

    public static Map<String,String> searchByVehicleId(String palte) throws SQLException
    {
        Map<String, String> res = new HashMap<>();
        try {
            String sql = "SELECT t.id, t.ticket_type_id, t.time_in, t.time_out, t.out_date, t.vehicle_id, t.area_id, " +
                    "tt.name AS ticket_type_name, tt.cost, v.name AS vehicle_name " +
                    "FROM ticket t " +
                    "INNER JOIN ticket_type tt ON t.ticket_type_id = tt.id " +
                    "INNER JOIN vehicle v ON t.vehicle_id = v.id " +
                    "WHERE t.vehicle_id ='"+palte+"';";
            ResultSet resultSet = st.executeQuery((sql));
            if (resultSet.next()) {
                res.put("id", resultSet.getString(1));
                res.put("ticket_type_id",resultSet.getString(2));
                res.put("time_in",resultSet.getString(3));
                res.put("time_out",resultSet.getString(4));
                res.put("out_date",resultSet.getString(5));
                res.put("vehicle_id",resultSet.getString(6));
                res.put("area_id",resultSet.getString(7));
                res.put("name", resultSet.getString(8));
                res.put("cost", resultSet.getString(9));
                res.put("vehicle_name", resultSet.getString(10));
            } else {

                System.out.print("Khong co du lieu");
                res.put("vehicle_id", "");
            }

            return res;

        }catch (SQLException e)
        {
            res.put("vehicle_id", "");
            return res;
        }
    }
    public static Map<String,String> searchByTicketId(String ticket_id) throws SQLException
    {
        Map<String, String> res = new HashMap<>();
        try {
            String sql = "SELECT t.id, t.ticket_type_id, t.time_in, t.time_out, t.out_date, t.vehicle_id, t.area_id, " +
                    "tt.name AS ticket_type_name, tt.cost, v.name AS vehicle_name " +
                    "FROM ticket t " +
                    "INNER JOIN ticket_type tt ON t.ticket_type_id = tt.id " +
                    "INNER JOIN vehicle v ON t.vehicle_id = v.id " +
                    "WHERE t.id ='"+ticket_id+"';";
            ResultSet resultSet = st.executeQuery((sql));
            System.out.println(sql);
            if (resultSet.next()) {
                res.put("id", resultSet.getString(1));
                res.put("ticket_type_id",resultSet.getString(2));
                res.put("time_in",resultSet.getString(3));
                res.put("time_out",resultSet.getString(4));
                res.put("out_date",resultSet.getString(5));
                res.put("vehicle_id",resultSet.getString(6));
                res.put("area_id",resultSet.getString(7));
                res.put("name", resultSet.getString(8));
                res.put("cost", resultSet.getString(9));
                res.put("vehicle_name", resultSet.getString(10));
            } else {

                System.out.print("Khong co du lieu");
                res.put("id", "");
            }

            return res;

        }catch (SQLException e)
        {
            res.put("id", "");
            return res;
        }
    }

    public void insertVehicleType(int vehicleType) throws SQLException
    {
        try {
            PreparedStatement pst = con.prepareStatement("insert into vehicle(name) values(?)");
            pst.setInt(1,vehicleType);

            pst.executeUpdate();

        }catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static boolean isTicketIdExists( String id) throws SQLException {

        try  {
          PreparedStatement pst = con.prepareStatement("SELECT COUNT(*) FROM ticket WHERE id = ?");
            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            return rs.getInt(1) > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public  static void insertTicketMonth(String ticketId, String vehicleId, String cusName, String cusPhone, int ticketType,  int areaId, Timestamp timeIn,Timestamp outDate, int emp_id) throws  SQLException
    {
        try {
            PreparedStatement pst = con.prepareStatement("insert into ticket(id, vehicle_id, cus_name, cus_phone, ticket_type_id, area_id,time_in,out_date, emp_id) values (?,?,?,?,?,?,?,?,?)");
            pst.setString(1, ticketId);
            pst.setString(2, vehicleId);
            pst.setString(3, cusName);
            pst.setString(4, cusPhone);
            pst.setInt(5, ticketType);
            pst.setInt(6, areaId);
            pst.setTimestamp(7,timeIn);
            pst.setTimestamp(8,outDate);
            pst.setInt(9, emp_id);

            pst.executeUpdate();
        }catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
    public static Map<String,String> getCost(int ticketId) throws SQLException {
        Map<String,String> res = new HashMap<>();
        try {
            String sql = "SELECT cost FROM ticket_type WHERE id =" + ticketId;
            ResultSet resultSet = st.executeQuery(sql);
            if (resultSet.next()) {
                res.put("cost", resultSet.getString(1));
            } else {
                res.put("cost", "");
            }
        } catch (SQLException e) {
            res.put("cost", "");
        }
        return res;
    }

    public static boolean updateTicketMonth(String ticketId, String vehicleId, String cusName, String cusPhone, int ticketType, String vehicleName, int areaId) throws SQLException {
        String sql = "SELECT id FROM ticket WHERE id = ?";
        String res = "";

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, ticketId);
            ResultSet resultSet = pst.executeQuery();
            if (resultSet.next()) {
                res = resultSet.getString(1);
            }
            resultSet.close();

        } catch (SQLException e) {
            // Xử lý ngoại lệ
            e.printStackTrace();
        }

        if (res.equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng mã thẻ");
            return false;
        } else {

            try {
                if (!cusName.equals("")) {

                    PreparedStatement pstUpdate = con.prepareStatement("UPDATE ticket SET cus_name = ? WHERE id = ?");
                    pstUpdate.setString(1, cusName);
                    pstUpdate.setString(2,ticketId);
                    pstUpdate.executeUpdate();
                }

                if (!cusPhone.equals("")) {

                    PreparedStatement pstUpdate = con.prepareStatement("UPDATE ticket SET cus_phone = ? WHERE id = ?");
                    pstUpdate.setString(1, cusPhone);
                    pstUpdate.setString(2, ticketId);
                    pstUpdate.executeUpdate();
                }

                if (ticketType != 0) {

                    PreparedStatement pstUpdate = con.prepareStatement("UPDATE ticket SET ticket_type_id = ? WHERE id = ?");
                    pstUpdate.setInt(1, ticketType);
                    pstUpdate.setString(2, ticketId);
                    pstUpdate.executeUpdate();
                }

                if (!vehicleName.equals("")) {

                    PreparedStatement pstUpdate = con.prepareStatement("UPDATE vehicle SET name = ? WHERE id = (SELECT vehicle_id FROM ticket WHERE id = ?)");
                    pstUpdate.setString(1, vehicleName);
                    pstUpdate.setString(2, ticketId);
                    pstUpdate.executeUpdate();
                }

                if (areaId != 0) {
                    sql = "UPDATE ticket SET area_id = ? WHERE id = ?";
                    PreparedStatement pstUpdate = con.prepareStatement("UPDATE ticket SET area_id = ? WHERE id = ?");
                    pstUpdate.setInt(1, areaId);
                    pstUpdate.setString(2, ticketId);
                    pstUpdate.executeUpdate();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return true;
    }
    public static  boolean deleteTicket(String ticketId) throws  SQLException{
         String res = "";

        try{
            PreparedStatement pst = con.prepareStatement("select id from ticket where id=? and (ticket_type_id = 2 or ticket_type_id = 4)");
            pst.setString(1,ticketId);
            ResultSet resultSet = pst.executeQuery();
            if (resultSet.next()) {
                res = resultSet.getString(1);
            }
            resultSet.close();

        }catch (SQLException e) {
           throw  new RuntimeException(e);
        }
        if (res.equals(""))
        {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng mã vé","Thông báo",JOptionPane.INFORMATION_MESSAGE);
            return false;
        }else{
            PreparedStatement pstDelete = con.prepareStatement("delete from ticket where id =? ");
            pstDelete.setString(1,ticketId);
            pstDelete.executeUpdate();
            return true;
        }
    }
    public static boolean vehicleIdExist(String plate) throws SQLException {
        try {
            PreparedStatement pst = con.prepareStatement("SELECT COUNT(*) FROM ticket WHERE vehicle_id = ?");
            pst.setString(1, plate);
            ResultSet resultSet = pst.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }







}

