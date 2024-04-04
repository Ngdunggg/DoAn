package BackEnd.Database;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class homeNv {
    private static Statement st = database.getSt();
    private static Connection con;
    private static PreparedStatement pst;
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

    public static ArrayList<Map<String, String>> getTicketInfo()
    {
        String sql = "SELECT * FROM public.ticket INNER JOIN public.ticket_type ON ticket.ticket_type_id = ticket_type.id" +
                "ORDER BY public.ticket.id ASC ";
        ArrayList<Map<String,String>> ticketInfoList = new ArrayList<>();
        try{
            ResultSet resultSet = st.executeQuery((sql));
            Map<String, String > res = new HashMap<>();
            resultSet.next();
            res.put("ticket_code",resultSet.getString(1));
            resultSet.next();
            res.put("plate", resultSet.getString(6));
            resultSet.next();
            res.put("time_in",resultSet.getString(3));
            resultSet.next();
            res.put("ticket_type",resultSet.getString(10));
            resultSet.next();
            res.put("area", resultSet.getString(7));
            ticketInfoList.add(res);
            return ticketInfoList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void insertVehicleInfo(String Plate) throws SQLException {
        // Sử dụng PreparedStatement để tránh lỗ hổng SQL injection và xử lý giá trị động.
        String sql = "INSERT INTO public.vehicle(id) VALUES (?)";
        pst = con.prepareStatement(sql);
        pst.setString(1, Plate);

        // Thực thi câu lệnh SQL.
        pst.executeUpdate();
    }

    public static void insertTicketInfo(String ticketCode, Timestamp timeIn, Integer ticketType, Integer area, String vehicle_id) throws SQLException {

//        System.out.println(sql); // In câu lệnh SQL để kiểm tra xem có đúng không

        try {
            pst = con.prepareStatement("INSERT INTO public.ticket (id, time_in, area_id, ticket_type_id,vehicle_id) VALUES (?, ?, ?, ?,?)") ;
            pst.setString(1, ticketCode);
            pst.setTimestamp(2, timeIn);
            pst.setInt(3, area);
            pst.setInt(4, ticketType);
            pst.setString(5,vehicle_id);

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

    public static Map<String,String> searchByVehicleId(String palte) throws SQLException
    {
        Map<String, String> res = new HashMap<>();
        try {
            String sql = "select id, ticket_type_id, time_in, time_out,out_date,vehicle_id, area_id, name, cost from ticket where vehicle_id='"+palte+"';";
            ResultSet resultSet = st.executeQuery(sql);
            resultSet.next();
            res.put("id",resultSet.getString(1));
            res.put("ticket_type_id",resultSet.getString(2));
            res.put("time_in",resultSet.getString(3));
            res.put("time_out",resultSet.getString(4));
            res.put("out_date",resultSet.getString(5));
            res.put("vehicle_id",resultSet.getString(6));
            res.put("area_id",resultSet.getString(7));

            return res;

        }catch (SQLException e)
        {
            res.put("vehicle id","");
            return res;
        }
    }
    public static Map<String,String> searchByTicketId(String ticket_id) throws SQLException
    {
        Map<String, String> res = new HashMap<>();
        try {
            String sql = "SELECT t.id, t.ticket_type_id, t.time_in, t.time_out, t.out_date, t.vehicle_id, t.area_id, tt.name AS ticket_type_name, tt.cost " +
                    "FROM ticket t " +
                    "INNER JOIN ticket_type tt ON t.ticket_type_id = tt.id where vehicle_id='"+ticket_id+"';";
            ResultSet resultSet = st.executeQuery(sql);
            resultSet.next();
            res.put("id",resultSet.getString(1));
            res.put("ticket_type_id",resultSet.getString(2));
            res.put("time_in",resultSet.getString(3));
            res.put("time_out",resultSet.getString(4));
            res.put("out_date",resultSet.getString(5));
            res.put("vehicle_id",resultSet.getString(6));
            res.put("area_id",resultSet.getString(7));
            res.put("name",resultSet.getString(8));
            res.put("cost", resultSet.getString(9));

            return res;

        }catch (SQLException e)
        {
            res.put("ticket id","");
            return res;
        }
    }





}
