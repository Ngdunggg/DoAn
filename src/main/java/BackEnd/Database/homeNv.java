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
        String sql = "SELECT id FROM ticket";
        try {

            ResultSet resultSet = st.executeQuery((sql));
            String rs;
            rs = resultSet.getString(1);
            resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
