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
import java.util.ArrayList;
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
        String[] colsName = {"Mã vé", "Biển số xe", "Khu gửi","Loại xe", "Loại vé", "Giờ vào"};
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
                String row[] = new String[6];
                row[0] = resultSet.getString(1);
                row[1] = resultSet.getString(6);
                row[2] = resultSet.getString(7);
                row[3] = resultSet.getString(10);
                row[4] = resultSet.getString(3);
                row[5] = resultSet.getString(3);
//                row[5] = resultSet.getString(4);
//                row[5] = resultSet.getString(9);
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
        String[] colsName = {"Mã vé", "Biển số xe", "Khu gửi","Loại xe","Giờ vào"};
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
                String row[] = new String[5];
                row[0] = resultSet.getString(1);
                row[1] = resultSet.getString(6);
                row[2] = resultSet.getString(10);
                row[3] = resultSet.getString(9);
                row[4] = resultSet.getString(3);
//                row[5] = resultSet.getString(8);
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
                "ticket_type.name AS ticket_name, time_in, ticket.ticket_cost AS cost " +
                "FROM ticket " +
                "inner join ticket_type on ticket_type.id = ticket.ticket_type_id " +
                "INNER JOIN vehicle ON vehicle.id = vehicle_id " +
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
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        Menu = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btXeVao = new javax.swing.JButton();
        btXeRA = new javax.swing.JButton();
        btThongKe1 = new javax.swing.JButton();
        btVeThang = new javax.swing.JButton();
        btLogOut = new javax.swing.JButton();
        btThoatCTrinh = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        Show = new javax.swing.JPanel();
        plXeVao = new javax.swing.JPanel();
        btVao = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        boxLuaChonLoaiXe = new javax.swing.JComboBox<>();
        textBienSo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtMaThe = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtBienSo = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        textGioVao = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        boxLuaChonLoaiVe = new javax.swing.JComboBox<>();
        boxLuaChonKhu = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tableXevao = new javax.swing.JTable();
        plXeRa = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtMaTheXR = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtBienSoXR = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        textGioVaoXR = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        textGioRaXR = new javax.swing.JTextField();
        txtLoaiVeXR = new javax.swing.JTextField();
        txtKhuGuiXR = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtLoaiXeXR = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txtTienXR = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        boxLuaChonTimKiemXR = new javax.swing.JComboBox<>();
        txtTimKiemXR = new javax.swing.JTextField();
        btTimKiemXR = new javax.swing.JButton();
        btRaXR = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableXR = new javax.swing.JTable();
        plThongKe = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        btTKLuotGui = new javax.swing.JButton();
        btTKLuotDKVeThang = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        plVeThang = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txtMaTheDKVThang = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        txtBienSoDKVThang = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txtTenKhachHang = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        txtSdtDKVThang = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        txtTienDKVThang = new javax.swing.JTextField();
        boxLoaiVeDKVThang = new javax.swing.JComboBox<>();
        boxLoaiXeDKVThang = new javax.swing.JComboBox<>();
        boxKhuGuiDKVThang = new javax.swing.JComboBox<>();
        btDangKyVeThang = new javax.swing.JButton();
        btSuaDKVThang = new javax.swing.JButton();
        btXoaDKVThang = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableVeThang = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        Menu.setBackground(new java.awt.Color(51, 51, 51));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 50)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Nhân viên");

        btXeVao.setBackground(new java.awt.Color(51, 51, 51));
        btXeVao.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        btXeVao.setForeground(new java.awt.Color(255, 255, 255));
        btXeVao.setText("Xe vào");
        btXeVao.setBorder(null);
        btXeVao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btXeVaoActionPerformed(evt);
            }
        });

        btXeRA.setBackground(new java.awt.Color(51, 51, 51));
        btXeRA.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        btXeRA.setForeground(new java.awt.Color(255, 255, 255));
        btXeRA.setText("Xe ra");
        btXeRA.setBorder(null);
        btXeRA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btXeRAActionPerformed(evt);
            }
        });

        btThongKe1.setBackground(new java.awt.Color(51, 51, 51));
        btThongKe1.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        btThongKe1.setForeground(new java.awt.Color(255, 255, 255));
        btThongKe1.setText("Filter");
        btThongKe1.setBorder(null);
        btThongKe1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btThongKe1ActionPerformed(evt);
            }
        });

        btVeThang.setBackground(new java.awt.Color(51, 51, 51));
        btVeThang.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        btVeThang.setForeground(new java.awt.Color(255, 255, 255));
        btVeThang.setText("Vé tháng");
        btVeThang.setBorder(null);
        btVeThang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVeThangActionPerformed(evt);
            }
        });

        btLogOut.setBackground(new java.awt.Color(51, 51, 51));
        btLogOut.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        btLogOut.setForeground(new java.awt.Color(255, 255, 255));
        btLogOut.setText("Đăng Xuất");
        btLogOut.setBorder(null);
        btLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLogOutActionPerformed(evt);
            }
        });

        btThoatCTrinh.setBackground(new java.awt.Color(51, 51, 51));
        btThoatCTrinh.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        btThoatCTrinh.setForeground(new java.awt.Color(255, 255, 255));
        btThoatCTrinh.setText("Thoát");
        btThoatCTrinh.setBorder(null);
        btThoatCTrinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btThoatCTrinhActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(51, 51, 51));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Đánh giá");
        jButton1.setBorder(null);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout MenuLayout = new javax.swing.GroupLayout(Menu);
        Menu.setLayout(MenuLayout);
        MenuLayout.setHorizontalGroup(
            MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuLayout.createSequentialGroup()
                .addGroup(MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btLogOut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btThoatCTrinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(MenuLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btVeThang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btThongKe1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btXeVao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btXeRA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        MenuLayout.setVerticalGroup(
            MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addComponent(btXeVao, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btXeRA, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btThongKe1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btVeThang, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btLogOut, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btThoatCTrinh, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        Show.setLayout(new java.awt.CardLayout());

        btVao.setBackground(new java.awt.Color(102, 102, 102));
        btVao.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
        btVao.setForeground(new java.awt.Color(255, 255, 255));
        btVao.setText("Vào");
        btVao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
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

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin xe", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18))); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Loại xe");

        boxLuaChonLoaiXe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "xe máy", "ô tô", "xe đạp" }));
        boxLuaChonLoaiXe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxLuaChonLoaiXeActionPerformed(evt);
            }
        });

        textBienSo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textBienSoActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("Biển số xe");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(boxLuaChonLoaiXe, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(textBienSo, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textBienSo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(boxLuaChonLoaiXe, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                .addContainerGap(68, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 50)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Xe vào");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin thẻ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18))); // NOI18N

        jLabel4.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Mã thẻ");

        txtMaThe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaTheActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel15.setText("Biển số xe");

        txtBienSo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBienSoActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel16.setText("Giờ vào");

        textGioVao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textGioVaoActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel17.setText("Loại vé");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel18.setText("Khu gửi");

        boxLuaChonLoaiVe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Vé xe máy", "Vé ô tô", "Vé xe máy tháng", "vé ô tô tháng" }));
        boxLuaChonLoaiVe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxLuaChonLoaiVeActionPerformed(evt);
            }
        });

        boxLuaChonKhu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Khu A", "Khu B", "Khu C" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtMaThe, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtBienSo, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(20, 20, 20))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(boxLuaChonLoaiVe, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(boxLuaChonKhu, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textGioVao, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaThe, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(txtBienSo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textGioVao, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(boxLuaChonKhu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(boxLuaChonLoaiVe)))
                .addGap(16, 16, 16))
        );

        tableXevao.setModel(new javax.swing.table.DefaultTableModel(
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
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane6.setViewportView(tableXevao);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout plXeVaoLayout = new javax.swing.GroupLayout(plXeVao);
        plXeVao.setLayout(plXeVaoLayout);
        plXeVaoLayout.setHorizontalGroup(
            plXeVaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, plXeVaoLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                .addComponent(btVao, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        plXeVaoLayout.setVerticalGroup(
            plXeVaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plXeVaoLayout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGroup(plXeVaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(plXeVaoLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(plXeVaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(plXeVaoLayout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(btVao, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Show.add(plXeVao, "card2");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 50)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Xe Ra");

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin thẻ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18))); // NOI18N

        jLabel7.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Mã thẻ");

        txtMaTheXR.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaTheXR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaTheXRActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel19.setText("Biển số xe");

        txtBienSoXR.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtBienSoXR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBienSoXRActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel20.setText("Giờ vào");

        textGioVaoXR.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textGioVaoXR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textGioVaoXRActionPerformed(evt);
                setTextFieldReadOnly(textGioVao);
            }
        });

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel21.setText("Loại vé");

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel22.setText("Khu gửi");

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel23.setText("Giờ ra");

        textGioRaXR.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textGioRaXR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textGioRaXRActionPerformed(evt);
            }
        });

        txtLoaiVeXR.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtKhuGuiXR.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel24.setText("Loại xe");

        txtLoaiXeXR.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel25.setText("Thành tiền");

        txtTienXR.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(36, 36, 36)
                                    .addComponent(txtMaTheXR, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtBienSoXR, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(textGioVaoXR, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(53, 53, 53)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtLoaiXeXR, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtKhuGuiXR, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtLoaiVeXR, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtTienXR, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(32, 32, 32))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(textGioRaXR, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtMaTheXR, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtBienSoXR, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtLoaiVeXR))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtLoaiXeXR, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(textGioVaoXR, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtKhuGuiXR, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textGioRaXR, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(39, 39, 39))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTienXR, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel8.setText("Tìm kiếm thông tin:");

        boxLuaChonTimKiemXR.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        boxLuaChonTimKiemXR.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã thẻ", "Biển số xe" }));

        btTimKiemXR.setBackground(new java.awt.Color(102, 102, 102));
        btTimKiemXR.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btTimKiemXR.setForeground(new java.awt.Color(255, 255, 255));
        btTimKiemXR.setText("Tìm kiếm");
        btTimKiemXR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    btTimKiemXRActionPerformed(evt);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        btRaXR.setBackground(new java.awt.Color(102, 102, 102));
        btRaXR.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btRaXR.setForeground(new java.awt.Color(255, 255, 255));
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
                Timestamp timeOut = time;
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
                                    homeNv.update_ticket_cost(ticketId);
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


        tableXR.setModel(new javax.swing.table.DefaultTableModel(
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
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tableXR);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout plXeRaLayout = new javax.swing.GroupLayout(plXeRa);
        plXeRa.setLayout(plXeRaLayout);
        plXeRaLayout.setHorizontalGroup(
            plXeRaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, plXeRaLayout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addGroup(plXeRaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(plXeRaLayout.createSequentialGroup()
                        .addComponent(btTimKiemXR, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btRaXR, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(plXeRaLayout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(boxLuaChonTimKiemXR, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(txtTimKiemXR, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 655, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        plXeRaLayout.setVerticalGroup(
            plXeRaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plXeRaLayout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(plXeRaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(plXeRaLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(plXeRaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(boxLuaChonTimKiemXR, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(txtTimKiemXR, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addGroup(plXeRaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btTimKiemXR, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btRaXR, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(13, 13, 13))
        );

        Show.add(plXeRa, "card3");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 50)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Thống Kê");

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        btTKLuotGui.setBackground(new java.awt.Color(102, 102, 102));
        btTKLuotGui.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btTKLuotGui.setForeground(new java.awt.Color(255, 255, 255));
        btTKLuotGui.setText("Lượt gửi xe");
        btTKLuotGui.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTKLuotGuiActionPerformed(evt);
            }
        });

        btTKLuotDKVeThang.setBackground(new java.awt.Color(102, 102, 102));
        btTKLuotDKVeThang.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btTKLuotDKVeThang.setForeground(new java.awt.Color(255, 255, 255));
        btTKLuotDKVeThang.setText("Lượt đăng ký vé tháng");
        btTKLuotDKVeThang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTKLuotDKVeThangActionPerformed(evt);
            }
        });

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        jLabel12.setIcon(new javax.swing.ImageIcon("./img/parking-area.png")); // NOI18N

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));

        jLabel13.setIcon(new javax.swing.ImageIcon("./img/car (1).png")); // NOI18N

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(258, 258, 258))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(btTKLuotGui, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btTKLuotDKVeThang, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(105, 105, 105))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(148, 148, 148)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btTKLuotGui, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btTKLuotDKVeThang, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(196, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout plThongKeLayout = new javax.swing.GroupLayout(plThongKe);
        plThongKe.setLayout(plThongKeLayout);
        plThongKeLayout.setHorizontalGroup(
            plThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        plThongKeLayout.setVerticalGroup(
            plThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plThongKeLayout.createSequentialGroup()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Show.add(plThongKe, "card4");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 50)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Đăng ký vé tháng");

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin thẻ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18))); // NOI18N

        jLabel11.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel11.setText("Mã thẻ:");

        txtMaTheDKVThang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaTheDKVThang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaTheDKVThangActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel26.setText("Biển số xe:");

        txtBienSoDKVThang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtBienSoDKVThang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBienSoDKVThangActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel27.setText("Tên khách hàng:");

        txtTenKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTenKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenKhachHangActionPerformed(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel28.setText("Loại vé:");

        jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel29.setText("Khu gửi:");

        jLabel30.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel30.setText("Số điện thoại:");

        txtSdtDKVThang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSdtDKVThang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSdtDKVThangActionPerformed(evt);
            }
        });

        jLabel31.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
        jLabel31.setText("Loại xe:");

        jLabel32.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel32.setText("Thành tiền:");

        txtTienDKVThang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        boxLoaiVeDKVThang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Vé tháng xe máy", "Vé tháng ô tô" }));

        boxLoaiXeDKVThang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Xe máy", "Ô tô" }));

        boxKhuGuiDKVThang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Khu A", "Khu B", "Khu C" }));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtMaTheDKVThang, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtBienSoDKVThang, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtTenKhachHang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSdtDKVThang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTienDKVThang)
                            .addComponent(boxLoaiVeDKVThang, 0, 137, Short.MAX_VALUE))
                        .addGap(18, 18, 18))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(boxKhuGuiDKVThang, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(boxLoaiXeDKVThang, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaTheDKVThang, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(boxLoaiVeDKVThang, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(boxLoaiXeDKVThang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtBienSoDKVThang, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(boxKhuGuiDKVThang, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(txtSdtDKVThang, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTienDKVThang, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        btDangKyVeThang.setBackground(new java.awt.Color(102, 102, 102));
        btDangKyVeThang.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btDangKyVeThang.setForeground(new java.awt.Color(255, 255, 255));
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

        btSuaDKVThang.setBackground(new java.awt.Color(102, 102, 102));
        btSuaDKVThang.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btSuaDKVThang.setForeground(new java.awt.Color(255, 255, 255));
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

        btXoaDKVThang.setBackground(new java.awt.Color(102, 102, 102));
        btXoaDKVThang.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btXoaDKVThang.setForeground(new java.awt.Color(255, 255, 255));
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

        tableVeThang.setModel(new javax.swing.table.DefaultTableModel(
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
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableVeThang);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout plVeThangLayout = new javax.swing.GroupLayout(plVeThang);
        plVeThang.setLayout(plVeThangLayout);
        plVeThangLayout.setHorizontalGroup(
            plVeThangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(plVeThangLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addGroup(plVeThangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btDangKyVeThang, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btSuaDKVThang, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btXoaDKVThang, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        plVeThangLayout.setVerticalGroup(
            plVeThangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plVeThangLayout.createSequentialGroup()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(plVeThangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(plVeThangLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(plVeThangLayout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(btDangKyVeThang, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btSuaDKVThang, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btXoaDKVThang, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25)
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Show.add(plVeThang, "card5");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Menu, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(Show, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Show, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                homeNv.update_ticket_cost(search);
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
                    String [] colsName = {"Mã thẻ", "Biển số xe", "Loại xe", "Loại vé", "Khu gửi", "Giờ vào","Tiền"};
                    tableModel.setColumnIdentifiers(colsName);
                    String row[] = new String[7];
                    row[0] = res.get("id");
                    row[1] = res.get("vehicle_id");
                    row[2]= res.get("vehicle_name");
                    row[3] = res.get("name");
                    row[4] = res.get("area_id");
                    row[5] = res.get("time_in");
                    row[6] = res.get("cost");
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
        new DanhGia().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

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
    private javax.swing.JPanel Menu;
    private javax.swing.JPanel Show;
    private javax.swing.JComboBox<String> boxKhuGuiDKVThang;
    private javax.swing.JComboBox<String> boxLoaiVeDKVThang;
    private javax.swing.JComboBox<String> boxLoaiXeDKVThang;
    private javax.swing.JComboBox<String> boxLuaChonKhu;
    private javax.swing.JComboBox<String> boxLuaChonLoaiVe;
    private javax.swing.JComboBox<String> boxLuaChonLoaiXe;
    private javax.swing.JComboBox<String> boxLuaChonTimKiemXR;
    private javax.swing.JButton btDangKyVeThang;
    private javax.swing.JButton btLogOut;
    private javax.swing.JButton btRaXR;
    private javax.swing.JButton btSuaDKVThang;
    private javax.swing.JButton btTKLuotDKVeThang;
    private javax.swing.JButton btTKLuotGui;
    private javax.swing.JButton btThoatCTrinh;
    private javax.swing.JButton btThongKe1;
    private javax.swing.JButton btTimKiemXR;
    private javax.swing.JButton btVao;
    private javax.swing.JButton btVeThang;
    private javax.swing.JButton btXeRA;
    private javax.swing.JButton btXeVao;
    private javax.swing.JButton btXoaDKVThang;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JPanel plThongKe;
    private javax.swing.JPanel plVeThang;
    private javax.swing.JPanel plXeRa;
    private javax.swing.JPanel plXeVao;
    private javax.swing.JTable tableVeThang;
    private javax.swing.JTable tableXR;
    private javax.swing.JTable tableXevao;
    private javax.swing.JTextField textBienSo;
    private javax.swing.JTextField textGioRaXR;
    private javax.swing.JTextField textGioVao;
    private javax.swing.JTextField textGioVaoXR;
    private javax.swing.JTextField txtBienSo;
    private javax.swing.JTextField txtBienSoDKVThang;
    private javax.swing.JTextField txtBienSoXR;
    private javax.swing.JTextField txtKhuGuiXR;
    private javax.swing.JTextField txtLoaiVeXR;
    private javax.swing.JTextField txtLoaiXeXR;
    private javax.swing.JTextField txtMaThe;
    private javax.swing.JTextField txtMaTheDKVThang;
    private javax.swing.JTextField txtMaTheXR;
    private javax.swing.JTextField txtSdtDKVThang;
    private javax.swing.JTextField txtTenKhachHang;
    private javax.swing.JTextField txtTienDKVThang;
    private javax.swing.JTextField txtTienXR;
    private javax.swing.JTextField txtTimKiemXR;
    // End of variables declaration//GEN-END:variables
}
