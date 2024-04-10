/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.mytest;

import BackEnd.Database.database;
import BackEnd.Database.dotenv;
import BackEnd.Database.homeNv;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author ASUS
 */
public class HomeForNV extends JFrame {

    /**
     * Creates new form HomeForNV
     */
    public HomeForNV() {
        initComponents();
        setLocationRelativeTo(null);
        Timestamp time = new Timestamp(System.currentTimeMillis());
        textGioVao.setText(String.valueOf(time));
        txtMaThe.setText(ticketCodetmp);
        txtMaTheDKVThang.setText(ticketCodetmp);
        showTable();


    }

    public void showTableXeRa() {
        DefaultTableModel tableModel = new DefaultTableModel();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        String[] colsName = {"Mã vé", "Biển số xe", "Khu gửi","Loại xe","Giờ vào", "Giờ ra", "Số tiền"};
        tableModel.setColumnIdentifiers(colsName);
        String sql = "SELECT t.id, t.ticket_type_id, t.time_in, t.time_out, t.out_date, t.vehicle_id, t.area_id, " +
                "tt.name AS ticket_type_name, tt.cost, v.name AS vehicle_name " +
                "FROM ticket t " +
                "INNER JOIN ticket_type tt ON t.ticket_type_id = tt.id " +
                "INNER JOIN vehicle v ON t.vehicle_id = v.id where t.time_out is null " +
                "ORDER BY t.id ASC";

        System.out.println(sql);
        String url = dotenv.PostgreUrl;
        String username = dotenv.name;
        String password = dotenv.password;

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();
            ResultSet resultSet = st.executeQuery(sql);
            System.out.println("success connect to db");
            while(resultSet.next()) {
                String row[] = new String[7];
                row[0] = resultSet.getString(1);
                row[1] = resultSet.getString(6);
                row[2] = resultSet.getString(7);
                row[3] = resultSet.getString(10);
                row[4] = resultSet.getString(3);
                row[5] = resultSet.getString(4);
                row[6] = resultSet.getString(9);
                tableModel.addRow(row);
            }
            tableXR.setModel(tableModel);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showPlThongKe() {
        switchPanel(plThongKe);
        this.setVisible(true);
    }

    public void showTable() {
        DefaultTableModel tableModel = new DefaultTableModel();
        String[] colsName = {"Mã vé", "Biển số xe", "Khu gửi","Loại xe","Giờ vào", "Số tiền"};
        tableModel.setColumnIdentifiers(colsName);
        Timestamp time = new Timestamp(System.currentTimeMillis());
        String sql = "SELECT t.id, t.ticket_type_id, t.time_in, t.time_out, t.out_date, t.vehicle_id, t.area_id, tt.cost, v.name AS vehicle_name, pa.name AS parking_area_name " +
                "FROM ticket t " +
                "INNER JOIN ticket_type tt ON t.ticket_type_id = tt.id " +
                "INNER JOIN vehicle v ON t.vehicle_id = v.id " +
                "INNER JOIN parking_area pa ON t.area_id = pa.id where t.time_out is null " +
                "ORDER BY t.id ASC";
        String url = dotenv.PostgreUrl;
        String username = dotenv.name;
        String password = dotenv.password;

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();
            ResultSet resultSet = st.executeQuery(sql);
            System.out.println("success connect to db");
            while(resultSet.next()) {
                String row[] = new String[6];
                row[0] = resultSet.getString(1);
                row[1] = resultSet.getString(6);
                row[2] = resultSet.getString(10);
                row[3] = resultSet.getString(9);
                row[4] = resultSet.getString(3);
                row[5] = resultSet.getString(8);
                tableModel.addRow(row);
            }
            tableXevao.setModel(tableModel);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void resetTicket(){
        txtMaTheDKVThang.setText(null);
        txtBienSoDKVThang.setText(null);
        txtTenKhachHang.setText(null);
        txtSdtDKVThang.setText(null);
    }

    public void switchPanel(JPanel panel){
        Show.removeAll();
        Show.add(panel);
        Show.repaint();
        Show.revalidate();
    }
    public void showTableTicket() {
        DefaultTableModel tableModel = new DefaultTableModel();
        String[] colsName = {"Mã vé", "Tên Khách Hàng", "Số điện thoại","Biển số xe","Loại xe", "Loai vé","Ngày đăng ký","Tiền"};
        tableModel.setColumnIdentifiers(colsName);
        String sql = "SELECT ticket.id, cus_name, cus_phone, vehicle_id, vehicle.name AS vehicle_name, " +
                "ticket_type.name AS ticket_name, time_in, ticket_type.cost AS cost " +
                "FROM ticket " +
                "INNER JOIN vehicle ON vehicle.id = vehicle_id " +
                "INNER JOIN ticket_type ON ticket_type.id = ticket_type_id " +
                "WHERE ticket_type_id = 2 OR ticket_type_id = 4";

        String url = dotenv.PostgreUrl;
        String username = dotenv.name;
        String password = dotenv.password;

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();
            ResultSet resultSet = st.executeQuery(sql);
            System.out.println("success connect to db");
            while(resultSet.next()) {
                String row[] = new String[8];
                row[0] = resultSet.getString(1);
                row[1] = resultSet.getString(2);
                row[2] = resultSet.getString(3);
                row[3] = resultSet.getString(4);
                row[4] = resultSet.getString(5);
                row[5] = resultSet.getString(6);
                row[6] = resultSet.getString(7);
                row[7] = resultSet.getString(8);

                tableModel.addRow(row);
            }
            tableVeThang.setModel(tableModel);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    public void initComponents() {

        buttonGroup1 = new ButtonGroup();
        Menu = new JPanel();
        jLabel1 = new JLabel();
        btXeVao = new JButton();
        btXeRA = new JButton();
        btThongKe1 = new JButton();
        btVeThang = new JButton();
        btLogOut = new JButton();
        btThoatCTrinh = new JButton();
        Show = new JPanel();
        plXeVao = new JPanel();
        btVao = new JButton();
        jPanel2 = new JPanel();
        jLabel6 = new JLabel();
        boxLuaChonLoaiXe = new JComboBox<>();
        textBienSo = new JTextField();
        jLabel5 = new JLabel();
        jLabel2 = new JLabel();
        jPanel3 = new JPanel();
        jLabel4 = new JLabel();
        txtMaThe = new JTextField();
        jLabel15 = new JLabel();
        txtBienSo = new JTextField();
        jLabel16 = new JLabel();
        textGioVao = new JTextField();
        jLabel17 = new JLabel();
        jLabel18 = new JLabel();
        boxLuaChonLoaiVe = new JComboBox<>();
        boxLuaChonKhu = new JComboBox<>();
        jPanel4 = new JPanel();
        jScrollPane6 = new JScrollPane();
        tableXevao = new JTable();
        plXeRa = new JPanel();
        jLabel3 = new JLabel();
        jPanel5 = new JPanel();
        jLabel7 = new JLabel();
        txtMaTheXR = new JTextField();
        jLabel19 = new JLabel();
        txtBienSoXR = new JTextField();
        jLabel20 = new JLabel();
        textGioVaoXR = new JTextField();
        jLabel21 = new JLabel();
        jLabel22 = new JLabel();
        jLabel23 = new JLabel();
        textGioRaXR = new JTextField();
        txtLoaiVeXR = new JTextField();
        txtKhuGuiXR = new JTextField();
        jLabel24 = new JLabel();
        txtLoaiXeXR = new JTextField();
        jLabel25 = new JLabel();
        txtTienXR = new JTextField();
        jLabel8 = new JLabel();
        boxLuaChonTimKiemXR = new JComboBox<>();
        txtTimKiemXR = new JTextField();
        btTimKiemXR = new JButton();
        btRaXR = new JButton();
        jPanel1 = new JPanel();
        jScrollPane2 = new JScrollPane();
        tableXR = new JTable();
        plThongKe = new JPanel();
        jLabel9 = new JLabel();
        jPanel6 = new JPanel();
        btTKLuotGui = new JButton();
        jPanel7 = new JPanel();
        btTKLuotDKVeThang = new JButton();
        plVeThang = new JPanel();
        jLabel10 = new JLabel();
        jPanel8 = new JPanel();
        jLabel11 = new JLabel();
        txtMaTheDKVThang = new JTextField();
        jLabel26 = new JLabel();
        txtBienSoDKVThang = new JTextField();
        jLabel27 = new JLabel();
        txtTenKhachHang = new JTextField();
        jLabel28 = new JLabel();
        jLabel29 = new JLabel();
        jLabel30 = new JLabel();
        txtSdtDKVThang = new JTextField();
        jLabel31 = new JLabel();
        jLabel32 = new JLabel();
        txtTienDKVThang = new JTextField();
        boxLoaiVeDKVThang = new JComboBox<>();
        boxLoaiXeDKVThang = new JComboBox<>();
        boxKhuGuiDKVThang = new JComboBox<>();
        btDangKyVeThang = new JButton();
        btSuaDKVThang = new JButton();
        btXoaDKVThang = new JButton();
        jPanel9 = new JPanel();
        jScrollPane1 = new JScrollPane();
        tableVeThang = new JTable();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        Menu.setBackground(new Color(51, 51, 51));

        jLabel1.setFont(new Font("Segoe UI", 1, 30)); // NOI18N
        jLabel1.setForeground(new Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel1.setText("Nhân viên");

        btXeVao.setBackground(new Color(51, 51, 51));
        btXeVao.setFont(new Font("Segoe UI", 1, 30)); // NOI18N
        btXeVao.setForeground(new Color(255, 255, 255));
        btXeVao.setText("Xe vào");
        btXeVao.setBorder(null);
        btXeVao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btXeVaoActionPerformed(evt);
            }
        });

        btXeRA.setBackground(new Color(51, 51, 51));
        btXeRA.setFont(new Font("Segoe UI", 1, 30)); // NOI18N
        btXeRA.setForeground(new Color(255, 255, 255));
        btXeRA.setText("Xe ra");
        btXeRA.setBorder(null);
        btXeRA.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btXeRAActionPerformed(evt);
            }
        });

        btThongKe1.setBackground(new Color(51, 51, 51));
        btThongKe1.setFont(new Font("Segoe UI", 1, 30)); // NOI18N
        btThongKe1.setForeground(new Color(255, 255, 255));
        btThongKe1.setText("Thống kê");
        btThongKe1.setBorder(null);
        btThongKe1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btThongKe1ActionPerformed(evt);
            }
        });

        btVeThang.setBackground(new Color(51, 51, 51));
        btVeThang.setFont(new Font("Segoe UI", 1, 30)); // NOI18N
        btVeThang.setForeground(new Color(255, 255, 255));
        btVeThang.setText("Vé tháng");
        btVeThang.setBorder(null);
        btVeThang.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btVeThangActionPerformed(evt);
            }
        });

        btLogOut.setBackground(new Color(51, 51, 51));
        btLogOut.setFont(new Font("Segoe UI", 1, 30)); // NOI18N
        btLogOut.setForeground(new Color(255, 255, 255));
        btLogOut.setText("Đăng Xuất");
        btLogOut.setBorder(null);
        btLogOut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btLogOutActionPerformed(evt);
            }
        });

        btThoatCTrinh.setBackground(new Color(51, 51, 51));
        btThoatCTrinh.setFont(new Font("Segoe UI", 1, 30)); // NOI18N
        btThoatCTrinh.setForeground(new Color(255, 255, 255));
        btThoatCTrinh.setText("Thoát");
        btThoatCTrinh.setBorder(null);
        btThoatCTrinh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btThoatCTrinhActionPerformed(evt);
            }
        });

        GroupLayout MenuLayout = new GroupLayout(Menu);
        Menu.setLayout(MenuLayout);
        MenuLayout.setHorizontalGroup(
            MenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(MenuLayout.createSequentialGroup()
                .addGroup(MenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(btLogOut, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(MenuLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(MenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(btVeThang, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btThongKe1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                            .addComponent(btXeVao, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btXeRA, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(btThoatCTrinh, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        MenuLayout.setVerticalGroup(
            MenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(MenuLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addComponent(btXeVao, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btXeRA, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(btThongKe1, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(btVeThang, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btLogOut, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btThoatCTrinh, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
        );

        Show.setLayout(new CardLayout());

        btVao.setBackground(new Color(102, 102, 102));
        btVao.setFont(new Font("Segoe UI", 0, 30)); // NOI18N
        btVao.setForeground(new Color(255, 255, 255));
        btVao.setText("Vào");


        btVao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {

                try {
                    btVaoActionPerformed(evt);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
//                String sql = "SELECT * FROM public.ticket" +
//                        "ORDER BY id ASC ";
//                try {
//                    database.connectDb();
//                    Statement stmt=null ;
//                    ResultSet rs = stmt.executeQuery(sql);
//
//                    ResultSetMetaData metaData = rs.getMetaData();
//                    int columnCount = metaData.getColumnCount();
//
//                    ArrayList<Object[]> data = new ArrayList<>();
//
//                    while (rs.next()){
//
//                    }
//                } catch (SQLException e) {
//                    throw new RuntimeException(e);
//                }

            }
        });

        jPanel2.setBorder(BorderFactory.createTitledBorder(null, "Thông tin xe", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Segoe UI", 0, 18))); // NOI18N

        jLabel6.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel6.setText("Loại xe");

        boxLuaChonLoaiXe.setModel(new DefaultComboBoxModel<>(new String[] { "xe máy", "ô tô", "xe đạp" }));
        boxLuaChonLoaiXe.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                boxLuaChonLoaiXeActionPerformed(evt);
            }
        });

        textBienSo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                textBienSoActionPerformed(evt);
            }
        });

        jLabel5.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("Biển số xe");

        GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(boxLuaChonLoaiXe, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
                    .addComponent(textBienSo, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textBienSo, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(boxLuaChonLoaiXe, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(jLabel6, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                .addContainerGap(68, Short.MAX_VALUE))
        );

        jLabel2.setFont(new Font("Segoe UI", 1, 50)); // NOI18N
        jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel2.setText("Xe vào");

        jPanel3.setBorder(BorderFactory.createTitledBorder(null, "Thông tin thẻ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Segoe UI", 0, 18))); // NOI18N

        jLabel4.setFont(new Font("sansserif", 0, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel4.setText("Mã thẻ");

        txtMaThe.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                txtMaTheActionPerformed(evt);
            }

        });

        jLabel15.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
        jLabel15.setText("Biển số xe");

        txtBienSo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                txtBienSoActionPerformed(evt);
            }
        });

        jLabel16.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
        jLabel16.setText("Giờ vào");

        textGioVao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt)
            {
                textGioVaoActionPerformed(evt);


            }


        });

        jLabel17.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
        jLabel17.setText("Loại vé");

        jLabel18.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
        jLabel18.setText("Khu gửi");

        boxLuaChonLoaiVe.setModel(new DefaultComboBoxModel<>(new String[] { "Vé xe máy", "Vé ô tô", "Vé xe máy tháng", "vé ô tô tháng" }));
        boxLuaChonLoaiVe.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                boxLuaChonLoaiVeActionPerformed(evt);
            }
        });

        boxLuaChonKhu.setModel(new DefaultComboBoxModel<>(new String[] { "Khu A", "Khu B", "Khu C" }));

        GroupLayout jPanel3Layout = new GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel4, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtMaThe, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel15, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtBienSo, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)))
                        .addGap(20, 20, 20))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel17, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(boxLuaChonLoaiVe, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel18, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(boxLuaChonKhu, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel16, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textGioVao, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaThe, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel15, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(txtBienSo))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(textGioVao, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel18, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                        .addComponent(boxLuaChonKhu, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                        .addComponent(boxLuaChonLoaiVe)))
                .addGap(16, 16, 16))
        );

        tableXevao.setModel(new DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã vé", "Biển số xe", "Khu gửi", "Loại xe", "Giờ vào", "Giờ ra", "Số tiền"
            }
        ) {
            Class[] types = new Class [] {
                String.class, String.class, String.class, String.class, String.class, String.class, String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane6.setViewportView(tableXevao);

        GroupLayout jPanel4Layout = new GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
        );

        GroupLayout plXeVaoLayout = new GroupLayout(plXeVao);
        plXeVao.setLayout(plXeVaoLayout);
        plXeVaoLayout.setHorizontalGroup(
            plXeVaoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(GroupLayout.Alignment.TRAILING, plXeVaoLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(jPanel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                .addComponent(btVao, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
            .addComponent(jPanel4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        plXeVaoLayout.setVerticalGroup(
            plXeVaoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(plXeVaoLayout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGroup(plXeVaoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(plXeVaoLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(plXeVaoLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                    .addGroup(plXeVaoLayout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(btVao, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Show.add(plXeVao, "card2");

        jLabel3.setFont(new Font("Segoe UI", 1, 50)); // NOI18N
        jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel3.setText("Xe Ra");

        jPanel5.setBorder(BorderFactory.createTitledBorder(null, "Thông tin thẻ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Segoe UI", 0, 18))); // NOI18N

        jLabel7.setFont(new Font("sansserif", 0, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel7.setText("Mã thẻ");

        txtMaTheXR.setFont(new Font("Segoe UI", 0, 14)); // NOI18N
        txtMaTheXR.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                txtMaTheXRActionPerformed(evt);
            }
        });

        jLabel19.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
        jLabel19.setText("Biển số xe");

        txtBienSoXR.setFont(new Font("Segoe UI", 0, 14)); // NOI18N
        txtBienSoXR.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                txtBienSoXRActionPerformed(evt);
            }
        });

        jLabel20.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
        jLabel20.setText("Giờ vào");

        textGioVaoXR.setFont(new Font("Segoe UI", 0, 14)); // NOI18N
        textGioVaoXR.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                textGioVaoXRActionPerformed(evt);
                setTextFieldReadOnly(textGioVao);
            }
        });

        jLabel21.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
        jLabel21.setText("Loại vé");

        jLabel22.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
        jLabel22.setText("Khu gửi");

        jLabel23.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
        jLabel23.setText("Giờ ra");

        textGioRaXR.setFont(new Font("Segoe UI", 0, 14)); // NOI18N
        textGioRaXR.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                textGioRaXRActionPerformed(evt);
            }
        });

        txtLoaiVeXR.setFont(new Font("Segoe UI", 0, 14)); // NOI18N

        txtKhuGuiXR.setFont(new Font("Segoe UI", 0, 14)); // NOI18N

        jLabel24.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
        jLabel24.setText("Loại xe");

        txtLoaiXeXR.setFont(new Font("Segoe UI", 0, 14)); // NOI18N

        jLabel25.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
        jLabel25.setText("Thành tiền");

        txtTienXR.setFont(new Font("Segoe UI", 0, 14)); // NOI18N

        GroupLayout jPanel5Layout = new GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addComponent(jLabel7, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
                                    .addGap(36, 36, 36)
                                    .addComponent(txtMaTheXR, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addComponent(jLabel19, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtBienSoXR, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel20, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(textGioVaoXR, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)))
                        .addGap(53, 53, 53)
                        .addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel21, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel24, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel22, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel25, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtLoaiXeXR, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtKhuGuiXR, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtLoaiVeXR, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtTienXR, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE))
                                .addGap(32, 32, 32))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel23, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(textGioRaXR, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel7, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtMaTheXR, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel19, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtBienSoXR, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel21, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtLoaiVeXR))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtLoaiXeXR, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(textGioVaoXR, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel22, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtKhuGuiXR, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel20, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(textGioRaXR, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(39, 39, 39))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTienXR, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jLabel8.setFont(new Font("Segoe UI", 0, 15)); // NOI18N
        jLabel8.setText("Tìm kiếm thông tin:");

        boxLuaChonTimKiemXR.setFont(new Font("Segoe UI", 0, 14)); // NOI18N
        boxLuaChonTimKiemXR.setModel(new DefaultComboBoxModel<>(new String[] { "Mã thẻ", "Biển số xe" }));

        btTimKiemXR.setBackground(new Color(102, 102, 102));
        btTimKiemXR.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
        btTimKiemXR.setForeground(new Color(255, 255, 255));
        btTimKiemXR.setText("Tìm kiếm");
        btTimKiemXR.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    btTimKiemXRActionPerformed(evt);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        btRaXR.setBackground(new Color(102, 102, 102));
        btRaXR.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
        btRaXR.setForeground(new Color(255, 255, 255));
        btRaXR.setText("Ra");
        btRaXR.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    btRaXRActionPerformed(evt);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            private void btRaXRActionPerformed(ActionEvent evt) throws SQLException {
                String checkOutTicketId = txtTimKiemXR.getText();
                int op = boxLuaChonTimKiemXR.getSelectedIndex();
                Timestamp time = new Timestamp(System.currentTimeMillis());
                Timestamp timeOut=time;
                database.connectDb();
                var resultList = homeNv.getTicketInfo();
                if (checkOutTicketId.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Thông tin không được để trống!");
                } else {

                    if (op != 0 && op != 1) {
                        JOptionPane.showMessageDialog(null, "Lựa chọn không hợp lệ!");
                    } else {

                        boolean found = false;
                        for (Map<String, String> res : resultList) {
                            String ticketId = res.get("id");
                            String vehicleId = res.get("vehicle_id");
                            if (op == 0) {
                                if (checkOutTicketId.equals(ticketId)) {

                                    homeNv.insertTimeOutTicket(timeOut,checkOutTicketId);
                                    found = true;
                                    break;
                                }
                            } else if (op == 1) {
                                if (checkOutTicketId.equals(vehicleId)) {
                                    homeNv.insertTimeOut(timeOut,checkOutTicketId);
                                    found = true;
                                    break;
                                }
                            }
                        }
                        if (found) {

                            txtBienSoXR.setText("");
                            txtMaTheXR.setText("");
                            textGioVaoXR.setText("");
                            textGioRaXR.setText("");
                            txtLoaiVeXR.setText("");
                            txtLoaiXeXR.setText("");
                            txtKhuGuiXR.setText("");
                            txtTienXR.setText("");
                            txtTimKiemXR.setText("");
                            JOptionPane.showMessageDialog(null,"Thanh toán Thành công!");
                            showTableXeRa();
                        } else {
                            JOptionPane.showMessageDialog(null, "Mã không tồn tại!");
                        }
                    }
                }


            }
        });

        tableXR.setModel(new DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã thẻ", "Biển số xe", "Loại xe", "Loại vé", "Khu Gửi", "Giờ vào ", "Giờ ra", "Tiền"
            }
        ) {
            Class[] types = new Class [] {
                String.class, String.class, String.class, String.class, String.class, String.class, String.class, Float.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tableXR);

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        GroupLayout plXeRaLayout = new GroupLayout(plXeRa);
        plXeRa.setLayout(plXeRaLayout);
        plXeRaLayout.setHorizontalGroup(
            plXeRaLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(GroupLayout.Alignment.TRAILING, plXeRaLayout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .addGroup(plXeRaLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addGroup(plXeRaLayout.createSequentialGroup()
                        .addComponent(btTimKiemXR, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btRaXR, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
                    .addGroup(plXeRaLayout.createSequentialGroup()
                        .addComponent(jLabel8, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(boxLuaChonTimKiemXR, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(txtTimKiemXR, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addComponent(jPanel5, GroupLayout.PREFERRED_SIZE, 655, GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
            .addComponent(jPanel1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        plXeRaLayout.setVerticalGroup(
            plXeRaLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(plXeRaLayout.createSequentialGroup()
                .addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(plXeRaLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGroup(plXeRaLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(plXeRaLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                            .addComponent(boxLuaChonTimKiemXR, GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(txtTimKiemXR, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addGroup(plXeRaLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(btTimKiemXR, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                            .addComponent(btRaXR, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(13, 13, 13))
        );

        Show.add(plXeRa, "card3");

        jLabel9.setFont(new Font("Segoe UI", 1, 50)); // NOI18N
        jLabel9.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel9.setText("Thống Kê");

        jPanel6.setBackground(new Color(255, 255, 255));

        btTKLuotGui.setBackground(new Color(153, 153, 153));
        btTKLuotGui.setFont(new Font("Segoe UI", 1, 24)); // NOI18N
        btTKLuotGui.setForeground(new Color(0, 0, 0));
        btTKLuotGui.setText("Lượt gửi xe");
        btTKLuotGui.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btTKLuotGuiActionPerformed(evt);
            }
        });

        GroupLayout jPanel6Layout = new GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(152, 152, 152)
                .addComponent(btTKLuotGui, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(205, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(429, Short.MAX_VALUE)
                .addComponent(btTKLuotGui, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                .addGap(260, 260, 260))
        );

        jPanel7.setBackground(new Color(255, 255, 255));

        btTKLuotDKVeThang.setBackground(new Color(153, 153, 153));
        btTKLuotDKVeThang.setFont(new Font("Segoe UI", 1, 24)); // NOI18N
        btTKLuotDKVeThang.setForeground(new Color(0, 0, 0));
        btTKLuotDKVeThang.setText("Lượt đăng ký vé tháng");
        btTKLuotDKVeThang.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btTKLuotDKVeThangActionPerformed(evt);
            }
        });

        GroupLayout jPanel7Layout = new GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btTKLuotDKVeThang, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                .addGap(98, 98, 98))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btTKLuotDKVeThang, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                .addGap(258, 258, 258))
        );

        GroupLayout plThongKeLayout = new GroupLayout(plThongKe);
        plThongKe.setLayout(plThongKeLayout);
        plThongKeLayout.setHorizontalGroup(
            plThongKeLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, GroupLayout.DEFAULT_SIZE, 1010, Short.MAX_VALUE)
            .addGroup(plThongKeLayout.createSequentialGroup()
                .addComponent(jPanel6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel7, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        plThongKeLayout.setVerticalGroup(
            plThongKeLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(plThongKeLayout.createSequentialGroup()
                .addComponent(jLabel9, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(plThongKeLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        Show.add(plThongKe, "card4");

        jLabel10.setFont(new Font("Segoe UI", 1, 50)); // NOI18N
        jLabel10.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel10.setText("Đăng ký vé tháng");

        jPanel8.setBorder(BorderFactory.createTitledBorder(null, "Thông tin thẻ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Segoe UI", 0, 18))); // NOI18N

        jLabel11.setFont(new Font("sansserif", 0, 18)); // NOI18N
        jLabel11.setHorizontalAlignment(SwingConstants.LEFT);
        jLabel11.setText("Mã thẻ:");

        txtMaTheDKVThang.setFont(new Font("Segoe UI", 0, 14)); // NOI18N
        txtMaTheDKVThang.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                txtMaTheDKVThangActionPerformed(evt);
            }
        });

        jLabel26.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
        jLabel26.setText("Biển số xe:");

        txtBienSoDKVThang.setFont(new Font("Segoe UI", 0, 14)); // NOI18N
        txtBienSoDKVThang.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                txtBienSoDKVThangActionPerformed(evt);
            }
        });

        jLabel27.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
        jLabel27.setText("Tên khách hàng:");

        txtTenKhachHang.setFont(new Font("Segoe UI", 0, 14)); // NOI18N
        txtTenKhachHang.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                txtTenKhachHangActionPerformed(evt);
            }
        });

        jLabel28.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
        jLabel28.setText("Loại vé:");

        jLabel29.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
        jLabel29.setText("Khu gửi:");

        jLabel30.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
        jLabel30.setText("Số điện thoại:");

        txtSdtDKVThang.setFont(new Font("Segoe UI", 0, 14)); // NOI18N
        txtSdtDKVThang.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                txtSdtDKVThangActionPerformed(evt);
            }
        });

        jLabel31.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
        jLabel31.setText("Loại xe:");

        jLabel32.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
        jLabel32.setText("Thành tiền:");

        txtTienDKVThang.setFont(new Font("Segoe UI", 0, 14)); // NOI18N

        boxLoaiVeDKVThang.setModel(new DefaultComboBoxModel<>(new String[] { "Vé tháng xe máy", "Vé tháng ô tô" }));

        boxLoaiXeDKVThang.setModel(new DefaultComboBoxModel<>(new String[] { "xe máy", "ô tô" }));

        boxKhuGuiDKVThang.setModel(new DefaultComboBoxModel<>(new String[] { "Khu A", "Khu B", "Khu C" }));

        GroupLayout jPanel8Layout = new GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(jLabel30, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel26, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addGroup(jPanel8Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(txtMaTheDKVThang, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtBienSoDKVThang, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtTenKhachHang, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSdtDKVThang, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel8Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(jLabel32, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))
                    .addGroup(GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel28, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel31, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(txtTienDKVThang)
                            .addComponent(boxLoaiVeDKVThang, 0, 137, Short.MAX_VALUE))
                        .addGap(18, 18, 18))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(boxKhuGuiDKVThang, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                            .addComponent(boxLoaiXeDKVThang, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaTheDKVThang, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(boxLoaiVeDKVThang, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(boxLoaiXeDKVThang, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel26, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtBienSoDKVThang, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel31, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenKhachHang, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addComponent(boxKhuGuiDKVThang, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(txtSdtDKVThang, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTienDKVThang, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        btDangKyVeThang.setBackground(new Color(102, 102, 102));
        btDangKyVeThang.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
        btDangKyVeThang.setForeground(new Color(255, 255, 255));
        btDangKyVeThang.setText("Đăng ký");
        btDangKyVeThang.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    btDangKyVeThangActionPerformed(evt);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            private void btDangKyVeThangActionPerformed(ActionEvent evt) throws SQLException {
                Timestamp time = new Timestamp(System.currentTimeMillis());
                Timestamp timeDk = time;
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(time);
                calendar.add(Calendar.DAY_OF_MONTH, 30); // Thêm 30 ngày vào Timestamp
                Timestamp timeOutDate = new Timestamp(calendar.getTimeInMillis());
                String ticketIdTemp= generateRandomID();
                String ticketCode =ticketCodetmp ;
                if(ticketCode.equals(ticketCodetmp))
                {
                    do {
                        database.connectDb();
                        ticketCode= ticketIdTemp;
                    }while (homeNv.isTicketIdExists(ticketCode));
                    txtMaTheDKVThang.setText(ticketCode);
                }
                String vehicleId = txtBienSoDKVThang.getText();
                String cusName = txtTenKhachHang.getText();
                String cusPhone = txtSdtDKVThang.getText();
                int ticketType = boxLoaiVeDKVThang.getSelectedIndex();
                int vehicleType = boxLoaiXeDKVThang.getSelectedIndex();
                String vehicleName = (String)boxLoaiXeDKVThang.getSelectedItem();
                int areaId = boxKhuGuiDKVThang.getSelectedIndex()+1;
                if(!ticketCode.isEmpty() && !vehicleId.isEmpty() && !cusName.isEmpty() && !cusPhone.isEmpty()&&ticketType==vehicleType){
                    if(ticketType==0){
                        ticketType+=2;
                    }else{
                        ticketType+=3;
                    }
                    database.connectDb();
                    var resCost = homeNv.getCost(ticketType);
                    String cost = (String) resCost.get("cost");
                    JOptionPane.showMessageDialog(null,"Số tiền thanh toán: "+ cost +" VND");
                    homeNv.insertVehicleInfo(vehicleId,vehicleName,areaId);
                    homeNv.insertTicketMonth(ticketCode,vehicleId,cusName,cusPhone,ticketType,areaId,timeDk,timeOutDate, Integer.parseInt(My.user_in));
                    txtMaTheDKVThang.setText(ticketIdTemp);
                    txtTienDKVThang.setText("");
                    txtBienSoDKVThang.setText("");
                    txtTenKhachHang.setText("");
                    txtSdtDKVThang.setText("");
                    JOptionPane.showMessageDialog(null,"Đăng ký vé tháng thành công");
                }else {
                    JOptionPane.showMessageDialog(null,"Thông tin không đúng hoặc để trống");
                }
                showTableTicket();
            }
        });

        btSuaDKVThang.setBackground(new Color(102, 102, 102));
        btSuaDKVThang.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
        btSuaDKVThang.setForeground(new Color(255, 255, 255));
        btSuaDKVThang.setText("Sửa");

        btSuaDKVThang.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    btSuaDKVThangActionPerformed(evt);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            private void btSuaDKVThangActionPerformed(ActionEvent evt) throws SQLException {
                String ticketId = txtMaTheDKVThang.getText();
                String vehicleId = txtBienSoDKVThang.getText();
                String cusName = txtTenKhachHang.getText();
                String cusPhone = txtSdtDKVThang.getText();
                int ticketType = boxLoaiVeDKVThang.getSelectedIndex();
                int vehicleType = boxLoaiXeDKVThang.getSelectedIndex();
                String vehicleName = (String)boxLoaiXeDKVThang.getSelectedItem();
                int areaId = boxKhuGuiDKVThang.getSelectedIndex()+1;
                if(vehicleId.equals("") && vehicleType == ticketType ){
                database.connectDb();
                if(homeNv.updateTicketMonth(ticketId,vehicleId,cusName,cusPhone,ticketType,vehicleName,areaId)){
                    JOptionPane.showMessageDialog(null,"Cập nhật thông tin thành công!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                    resetTicket();
                    showTableTicket();
                    String ticketIdTemp= generateRandomID();
                    String ticketCode =ticketCodetmp ;
                    if(ticketCode.equals(ticketCodetmp))
                    {
                        do {
                            database.connectDb();
                            ticketCode= ticketIdTemp;
                        }while (homeNv.isTicketIdExists(ticketCode));
                        txtMaTheDKVThang.setText(ticketCode);
                    }
                    txtMaTheDKVThang.setText(ticketIdTemp);
                }}else{
                    if(!vehicleId.equals("")) {
                        JOptionPane.showMessageDialog(null, "Vui lòng để trống biển số xe vì thông tin này tương ứng với 1 mã vé duy nhất", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    }
                    if (vehicleType != ticketType){
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn đúng thông tin", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                showTableTicket();
            }
        });

        btXoaDKVThang.setBackground(new Color(102, 102, 102));
        btXoaDKVThang.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
        btXoaDKVThang.setForeground(new Color(255, 255, 255));
        btXoaDKVThang.setText("Xóa");
        btXoaDKVThang.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    btXoaDKVThangActionPerformed(evt);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            private void btXoaDKVThangActionPerformed(ActionEvent evt) throws SQLException {
                String ticketId = txtMaTheDKVThang.getText();

                if(ticketId.equals("")){
                    JOptionPane.showMessageDialog(null ,"Vui lòng nhập mã vé để xoá ", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    database.connectDb();;
                    if (homeNv.deleteTicket(ticketId)){
                        JOptionPane.showMessageDialog(null,"Xoá vé thành công");
                        resetTicket();
                        String ticketIdTemp= generateRandomID();
                        String ticketCode =ticketCodetmp ;
                        if(ticketCode.equals(ticketCodetmp))
                        {
                            do {
                                database.connectDb();
                                ticketCode= ticketIdTemp;
                            }while (homeNv.isTicketIdExists(ticketCode));
                            txtMaTheDKVThang.setText(ticketCode);
                        }
                        txtMaTheDKVThang.setText(ticketIdTemp);
                        showTableTicket();
                    }
                }
            }
        });

        tableVeThang.setModel(new DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã thẻ", "Tên khách hàng", "Số điện thoại", "Biển số xe", "Loại xe", "Loại vé", "Ngày đăng ký", "Tiền"
            }
        ) {
            Class[] types = new Class [] {
                String.class, String.class, String.class, String.class, String.class, String.class, String.class, Float.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableVeThang);

        GroupLayout jPanel9Layout = new GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
                .addContainerGap())
        );

        GroupLayout plVeThangLayout = new GroupLayout(plVeThang);
        plVeThang.setLayout(plVeThangLayout);
        plVeThangLayout.setHorizontalGroup(
            plVeThangLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(plVeThangLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jPanel8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addGroup(plVeThangLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(btDangKyVeThang, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btSuaDKVThang, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btXoaDKVThang, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
            .addComponent(jPanel9, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        plVeThangLayout.setVerticalGroup(
            plVeThangLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(plVeThangLayout.createSequentialGroup()
                .addComponent(jLabel10, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                .addGroup(plVeThangLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(plVeThangLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGroup(plVeThangLayout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(btDangKyVeThang, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btSuaDKVThang, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btXoaDKVThang, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25)
                .addComponent(jPanel9, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Show.add(plVeThang, "card5");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Menu, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(Show, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(Menu, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Show, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btLogOutActionPerformed(ActionEvent evt) {//GEN-FIRST:event_btLogOutActionPerformed
        this.dispose();
        new My().setVisible(true);
    }//GEN-LAST:event_btLogOutActionPerformed

    private void btVeThangActionPerformed(ActionEvent evt) {//GEN-FIRST:event_btVeThangActionPerformed
        showTableTicket();
        switchPanel(plVeThang);

    }//GEN-LAST:event_btVeThangActionPerformed

    private void btXeRAActionPerformed(ActionEvent evt) {//GEN-FIRST:event_btXeRAActionPerformed
        showTableXeRa();
        switchPanel(plXeRa);

    }//GEN-LAST:event_btXeRAActionPerformed

    private void btXeVaoActionPerformed(ActionEvent evt) {//GEN-FIRST:event_btXeVaoActionPerformed
        switchPanel(plXeVao);
        showTable();
    }//GEN-LAST:event_btXeVaoActionPerformed

    private void btThongKe1ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_btThongKe1ActionPerformed

        switchPanel(plThongKe);
    }//GEN-LAST:event_btThongKe1ActionPerformed

    private static String generateRandomID() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder(6);
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < 6; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }
        return sb.toString();
    }
    private String ticketCodetmp= generateRandomID() ;
    public static void setTextFieldReadOnly(JTextField textField) {
        textField.setEditable(false); // Đặt thuộc tính editable của text field thành false
        textField.setFocusable(false); // Loại bỏ khả năng focus vào text field
        textField.setBackground(null); // Loại bỏ màu nền của text field
        textField.setBorder(null); // Loại bỏ border của text field
    }


    private void btVaoActionPerformed(ActionEvent evt) throws SQLException {//GEN-FIRST:event_btVaoActionPerformed
    String vehicle_id = textBienSo.getText();
    String vehicleName = (String) boxLuaChonLoaiXe.getSelectedItem();
    int vehicleType =0;
    if(vehicleName.equals("xe máy")) vehicleType=1;
    if(vehicleName.equals("ô tô")) vehicleType=2;
    if(vehicleName.equals("xe đạp")) vehicleType=3;
    String ticket_type_item = (String) boxLuaChonLoaiVe.getSelectedItem();
    int ticketType = 0;
    if (ticket_type_item.equals("Vé xe máy")) ticketType = 1;
    if (ticket_type_item.equals("Vé ô tô")) ticketType = 3;
    if (ticket_type_item.equals("Vé xe máy tháng")) ticketType = 2;
    if (ticket_type_item.equals("vé ô tô tháng")) ticketType = 4;
    if(vehicle_id.equals(""))
    {
        JOptionPane.showMessageDialog(null,"Biển số xe đang trống, vui lòng điền thông tin này ","Thông báo",JOptionPane.INFORMATION_MESSAGE);
    }
    else {
        database.connectDb();
        if(( ticketType==2 && vehicleType ==1) ||( ticketType==4 && vehicleType ==2)){
            if(homeNv.vehicleIdExist(vehicle_id)) {
                JOptionPane.showMessageDialog(null, "Thêm xe thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                textBienSo.setText("");
            }else{
                JOptionPane.showMessageDialog(null, "Vehicle ID không tồn tại", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        } else if(( ticketType==1 && vehicleType ==1) ||( ticketType==3 && vehicleType ==2)){
            if (homeNv.vehicleIdExist(vehicle_id)){
                String ticketIdTemp= generateRandomID();
                String ticketCode =ticketCodetmp ;

                if(ticketCode == ticketCodetmp)
                {
                    do {
                        database.connectDb();
                        ticketCode= ticketIdTemp;
                    }while (homeNv.isTicketIdExists(ticketCode));
                    txtMaThe.setText(ticketCode);
                }
                Timestamp time = new Timestamp(System.currentTimeMillis());
                Timestamp timeIn = time;

                int areaId =  boxLuaChonKhu.getSelectedIndex()+1;

                try {
                    database.connectDb();
                    homeNv.insertTicketInfo(ticketCode, timeIn, ticketType, areaId, vehicle_id);
                    JOptionPane.showMessageDialog(null,"Thêm xe thành công!");
                    txtBienSo.setText("");
                    txtMaThe.setText(ticketIdTemp);
                    textBienSo.setText("");
                    Timestamp time1 = new Timestamp(System.currentTimeMillis());
                    textGioVao.setText(String.valueOf(time1));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else {
                String ticketIdTemp= generateRandomID();
                String ticketCode =ticketCodetmp ;

                if(ticketCode == ticketCodetmp)
                {
                    do {
                        database.connectDb();
                        ticketCode= ticketIdTemp;
                    }while (homeNv.isTicketIdExists(ticketCode));
                    txtMaThe.setText(ticketCode);
                }
                Timestamp time = new Timestamp(System.currentTimeMillis());
                Timestamp timeIn = time;

                int areaId =  boxLuaChonKhu.getSelectedIndex()+1;

                try {
                    database.connectDb();
                    homeNv.insertVehicleInfo(vehicle_id,vehicleName,areaId);
                    homeNv.insertTicketInfo(ticketCode, timeIn,ticketType,areaId,vehicle_id);
                    JOptionPane.showMessageDialog(null,"Thêm xe thành công!");
                    txtBienSo.setText("");
                    txtMaThe.setText(ticketIdTemp);
                    textBienSo.setText("");
                    Timestamp time1 = new Timestamp(System.currentTimeMillis());
                    textGioVao.setText(String.valueOf(time1));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

        }else {
            JOptionPane.showMessageDialog(null, "Loại vé và loại xe không phù hợp", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }


    }

    showTable();
    }//GEN-LAST:event_btVaoActionPerformed

    private void textBienSoActionPerformed(ActionEvent evt) {//GEN-FIRST:event_textBienSoActionPerformed
        String url = dotenv.PostgreUrl;
        String username = dotenv.name;
        String password = dotenv.password;

        String bienSo = textBienSo.getText();
        int ticketTypeId = 0;

        String sql = "select ticket.vehicle_id, ticket.ticket_type_id\n" +
                "from ticket \n" +
                "where (ticket_type_id = 2 or ticket_type_id = 4) and (vehicle_id = '" + bienSo + "');";

        String bienSoMonth = "ngon";
        System.out.println(sql);
        String ticketIdTemp= generateRandomID();

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();
            System.out.println("success connect to db");
            ResultSet resultSet = st.executeQuery(sql);
            while (resultSet.next()) {
                bienSoMonth = resultSet.getString(1);
                ticketTypeId = resultSet.getInt(2);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(bienSoMonth + " " + ticketTypeId);

        if (!bienSoMonth.equals("ngon")) {
            txtBienSo.setText(bienSoMonth);
            String currentTime = java.sql.Timestamp.from(Instant.now()).toString();
            textGioVao.setText(currentTime);
            if (ticketTypeId == 2) {
                boxLuaChonLoaiXe.setSelectedItem("xe máy");
                boxLuaChonLoaiXe.setSelectedItem("Vé xe máy tháng");
                try {
                    Connection con = DriverManager.getConnection(url, username, password);
                    Statement st = con.createStatement();
                    System.out.println("success connect to db");
                    System.out.println(currentTime);
                    sql = "update ticket \n" +
                            "set time_out = null, time_in = '" + currentTime + "'\n" +
                            "where vehicle_id = '" + bienSoMonth + "'";
                    st.executeUpdate(sql);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                JOptionPane.showMessageDialog(null, "Xe đã đăng kí vé tháng vào thẳng", "Alert", JOptionPane.INFORMATION_MESSAGE);
                txtBienSo.setText("");
                txtMaThe.setText(ticketIdTemp);
                textBienSo.setText("");
                Timestamp time1 = new Timestamp(System.currentTimeMillis());
                textGioVao.setText(String.valueOf(time1));
                showTableXeRa();
            } else {
                boxLuaChonLoaiXe.setSelectedItem("ô tô");
                boxLuaChonLoaiXe.setSelectedItem("vé ô tô tháng");
                JOptionPane.showMessageDialog(null, "Xe đã đăng kí vé tháng vào thẳng", "Alert", JOptionPane.INFORMATION_MESSAGE);
                try {
                    Connection con = DriverManager.getConnection(url, username, password);
                    Statement st = con.createStatement();
                    System.out.println("success connect to db");
                    System.out.println(currentTime);
                    sql = "update ticket \n" +
                            "set time_out = null, time_in = '" + currentTime + "'\n" +
                            "where vehicle_id = '" + bienSoMonth + "'";
                    st.executeUpdate(sql);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                txtBienSo.setText("");
                txtMaThe.setText(ticketIdTemp);
                textBienSo.setText("");
                Timestamp time1 = new Timestamp(System.currentTimeMillis());
                textGioVao.setText(String.valueOf(time1));
                showTableXeRa();
            }

        } else {
            updateDateTime(textBienSo, textGioVao);
            txtBienSo.setText(bienSo);
        }

    }//GEN-LAST:event_textBienSoActionPerformed

    private void boxLuaChonLoaiXeActionPerformed(ActionEvent evt) {//GEN-FIRST:event_boxLuaChonLoaiXeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boxLuaChonLoaiXeActionPerformed

    private void txtMaTheActionPerformed(ActionEvent evt) {//GEN-FIRST:event_txtMaTheActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaTheActionPerformed

    private void txtBienSoActionPerformed(ActionEvent evt) {//GEN-FIRST:event_txtBienSoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBienSoActionPerformed

    private void textGioVaoActionPerformed(ActionEvent evt) {//GEN-FIRST:event_textGioVaoActionPerformed
        
    }//GEN-LAST:event_textGioVaoActionPerformed

    private void boxLuaChonLoaiVeActionPerformed(ActionEvent evt) {//GEN-FIRST:event_boxLuaChonLoaiVeActionPerformed

//        boxLuaChonLoaiVe.addItem();
    }//GEN-LAST:event_boxLuaChonLoaiVeActionPerformed

    private void txtMaTheXRActionPerformed(ActionEvent evt) {//GEN-FIRST:event_txtMaTheXRActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaTheXRActionPerformed

    private void txtBienSoXRActionPerformed(ActionEvent evt) {//GEN-FIRST:event_txtBienSoXRActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBienSoXRActionPerformed

    private void textGioVaoXRActionPerformed(ActionEvent evt) {//GEN-FIRST:event_textGioVaoXRActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textGioVaoXRActionPerformed

    private void textGioRaXRActionPerformed(ActionEvent evt) {//GEN-FIRST:event_textGioRaXRActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textGioRaXRActionPerformed

    private void btTKLuotGuiActionPerformed(ActionEvent evt) {//GEN-FIRST:event_btTKLuotGuiActionPerformed
        this.dispose();
        try {
            new LuotGuiXeNv().setVisible(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }//GEN-LAST:event_btTKLuotGuiActionPerformed

    private void btTKLuotDKVeThangActionPerformed(ActionEvent evt) {//GEN-FIRST:event_btTKLuotDKVeThangActionPerformed
        this.dispose();
        try {
            new LuotDKVeThang().setVisible(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }//GEN-LAST:event_btTKLuotDKVeThangActionPerformed

    private void txtMaTheDKVThangActionPerformed(ActionEvent evt) {//GEN-FIRST:event_txtMaTheDKVThangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaTheDKVThangActionPerformed

    private void txtBienSoDKVThangActionPerformed(ActionEvent evt) {//GEN-FIRST:event_txtBienSoDKVThangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBienSoDKVThangActionPerformed

    private void txtTenKhachHangActionPerformed(ActionEvent evt) {//GEN-FIRST:event_txtTenKhachHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenKhachHangActionPerformed

    private void txtSdtDKVThangActionPerformed(ActionEvent evt) {//GEN-FIRST:event_txtSdtDKVThangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSdtDKVThangActionPerformed

    private void btTimKiemXRActionPerformed(ActionEvent evt) throws SQLException {//GEN-FIRST:event_btTimKiemXRActionPerformed
//        updateDateTime(txtTimKiemXR, textGioRaXR);
        String search = txtTimKiemXR.getText();
        int op = boxLuaChonTimKiemXR.getSelectedIndex();
        if(!(search.equals("")))
        {
//            var res = homeNv.searchByTicketId(search);
            if(op == 0)
            {
                database.connectDb();
                homeNv.searchByTicketId(search);
                var res = homeNv.searchByTicketId(search);

                if(res.get("id").equals("")){
                    JOptionPane.showMessageDialog(null,"Vui lòng nhập lại mã vé");
                }else{
                    String ticket_id = res.get("id");
                    txtMaTheXR.setText(ticket_id);
                    String vehicle_id = res.get("vehicle_id");
                    txtBienSoXR.setText(vehicle_id);
//                    Timestamp time_in = Timestamp.valueOf(res.get("time_in"));
//                    textGioVao.setText(String.valueOf(time_in));
                    Timestamp time = new Timestamp(System.currentTimeMillis());
                    String time_out = String.valueOf(time);
                    String time_in = res.get("time_in");
                    System.out.println(time_in);
                    textGioVaoXR.setText(time_in);
                    textGioRaXR.setText(time_out);
                    String ticket_type = res.get("name");
                    txtLoaiVeXR.setText(ticket_type);
                    String vehicle_type = res.get("vehicle_name");
                    txtLoaiXeXR.setText(vehicle_type);
                    String area_id = res.get("area_id");
                    txtKhuGuiXR.setText(area_id);
                    String cost = res.get("cost");
                    txtTienXR.setText(cost);

                    DefaultTableModel tableModel = new DefaultTableModel();
                    String [] colsName = {"Mã thẻ", "Biển số xe", "Loại xe", "Loại vé", "Khu gửi", "Giờ vào", "Giờ ra","Tiền"};
                    tableModel.setColumnIdentifiers(colsName);
                    String row[] = new String[8];
                    row[0] = res.get("id");
                    row[1] = res.get("vehicle_id");
                    row[2]= res.get("vehicle_name");
                    row[3] = res.get("name");
                    row[4] = res.get("area_id");
                    row[5] = res.get("time_in");
                    row[6] = res.get("time_out");
                    row[7] = res.get("cost");
                    tableModel.addRow(row);
                    tableXR.setModel(tableModel);
                }
            }
            if (op ==1)
            {
                database.connectDb();
                var res = homeNv.searchByVehicleId(search);

                if(res.get("vehicle_id").equals(""))
                {
                    JOptionPane.showMessageDialog(null,"Vui lòng nhập lại mã xe!");
                }else{
                    String ticket_id = res.get("id");
                    txtMaTheXR.setText(ticket_id);
                    String vehicle_id = res.get("vehicle_id");
                    txtBienSoXR.setText(vehicle_id);
//                    Timestamp time_in = Timestamp.valueOf(res.get("time_in"));
//                    textGioVao.setText(String.valueOf(time_in));
                    Timestamp time = new Timestamp(System.currentTimeMillis());
                    String time_out = String.valueOf(time);
                    textGioRaXR.setText(time_out);
                    textGioVaoXR.setText(res.get("time_in"));
                    String ticket_type = res.get("name");
                    txtLoaiVeXR.setText(ticket_type);
                    String vehicle_type = res.get("vehicle_name");
                    txtLoaiXeXR.setText(vehicle_type);
                    String area_id = res.get("area_id");
                    txtKhuGuiXR.setText(area_id);
                    String cost = res.get("cost");
                    txtTienXR.setText(cost);

                    DefaultTableModel tableModel = new DefaultTableModel();
                    String [] colsName = {"Mã thẻ", "Biển số xe", "Loại xe", "Loại vé", "Khu gửi", "Giờ vào", "Giờ ra","Tiền"};
                    tableModel.setColumnIdentifiers(colsName);
                    String row[] = new String[8];
                    row[0] = res.get("id");
                    row[1] = res.get("vehicle_id");
                    row[2]= res.get("vehicle_name");
                    row[3] = res.get("name");
                    row[4] = res.get("area_id");
                    row[5] = res.get("time_in");
                    row[6] = res.get("time_out");
                    row[7] = res.get("cost");
                    tableModel.addRow(row);
                    tableXR.setModel(tableModel);
                }
            }


        }else{
            JOptionPane.showMessageDialog(null,"Nhập để tìm kiếm");
        }
    }//GEN-LAST:event_btTimKiemXRActionPerformed


    private void btThoatCTrinhActionPerformed(ActionEvent evt) {//GEN-FIRST:event_btThoatCTrinhActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btThoatCTrinhActionPerformed
    
    public void updateDateTime(JTextField txt, JTextField tt){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date now = new Date();
        if (txt.getText() != null){
            tt.setText(dateFormat.format(now));
            tt.setEditable(false);
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HomeForNV.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(HomeForNV.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(HomeForNV.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(HomeForNV.class.getName()).log(Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomeForNV().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JPanel Menu;
    private JPanel Show;
    private JComboBox<String> boxKhuGuiDKVThang;
    private JComboBox<String> boxLoaiVeDKVThang;
    private JComboBox<String> boxLoaiXeDKVThang;
    private JComboBox<String> boxLuaChonKhu;
    private JComboBox<String> boxLuaChonLoaiVe;
    private JComboBox<String> boxLuaChonLoaiXe;
    private JComboBox<String> boxLuaChonTimKiemXR;
    private JButton btDangKyVeThang;
    private JButton btLogOut;
    private JButton btRaXR;
    private JButton btSuaDKVThang;
    private JButton btTKLuotDKVeThang;
    private JButton btTKLuotGui;
    private JButton btThoatCTrinh;
    private JButton btThongKe1;
    private JButton btTimKiemXR;
    private JButton btVao;
    private JButton btVeThang;
    private JButton btXeRA;
    private JButton btXeVao;
    private JButton btXoaDKVThang;
    private ButtonGroup buttonGroup1;
    private JLabel jLabel1;
    private JLabel jLabel10;
    private JLabel jLabel11;
    private JLabel jLabel15;
    private JLabel jLabel16;
    private JLabel jLabel17;
    private JLabel jLabel18;
    private JLabel jLabel19;
    private JLabel jLabel2;
    private JLabel jLabel20;
    private JLabel jLabel21;
    private JLabel jLabel22;
    private JLabel jLabel23;
    private JLabel jLabel24;
    private JLabel jLabel25;
    private JLabel jLabel26;
    private JLabel jLabel27;
    private JLabel jLabel28;
    private JLabel jLabel29;
    private JLabel jLabel3;
    private JLabel jLabel30;
    private JLabel jLabel31;
    private JLabel jLabel32;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JLabel jLabel9;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JPanel jPanel4;
    private JPanel jPanel5;
    private JPanel jPanel6;
    private JPanel jPanel7;
    private JPanel jPanel8;
    private JPanel jPanel9;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JScrollPane jScrollPane6;
    public   JPanel plThongKe;
    private JPanel plVeThang;
    private JPanel plXeRa;
    private JPanel plXeVao;
    private JTable tableVeThang;
    private JTable tableXR;
    private JTable tableXevao;
    private JTextField textBienSo;
    private JTextField textGioRaXR;
    private JTextField textGioVao;
    private JTextField textGioVaoXR;
    private JTextField txtBienSo;
    private JTextField txtBienSoDKVThang;
    private JTextField txtBienSoXR;
    private JTextField txtKhuGuiXR;
    private JTextField txtLoaiVeXR;
    private JTextField txtLoaiXeXR;
    private JTextField txtMaThe;
    private JTextField txtMaTheDKVThang;
    private JTextField txtMaTheXR;
    private JTextField txtSdtDKVThang;
    private JTextField txtTenKhachHang;
    private JTextField txtTienDKVThang;
    private JTextField txtTienXR;
    private JTextField txtTimKiemXR;
    // End of variables declaration//GEN-END:variables
}
