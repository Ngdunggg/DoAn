/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.mytest;

import com.toedter.calendar.JDateChooser;

import java.awt.*;
import javax.swing.JLabel;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.*;
import java.sql.*;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import BackEnd.Database.database;
import BackEnd.Database.homeAdmin;
/**
 *
 * @author ASUS
 */
public class LuotGuiXeNv extends JFrame {

    /**
     * Creates new form LuotGuiXe
     */
    public LuotGuiXeNv() throws SQLException {
        initComponents();
        txtDateDau.setDateFormatString("yyyy-MM-dd");
        txtDateDau.setPreferredSize(new Dimension(150, 30));
        txtDateCuoi.setDateFormatString("yyyy-MM-dd");
        txtDateCuoi.setPreferredSize(new Dimension(150, 30));
        this.setLocationRelativeTo(null);
        showAll();
        txtTongTien.setText(homeAdmin.sumMoney("Tổng xe", 0, null, null));
        txtTongLuotGui.setText(homeAdmin.sumLuotGui("Tổng xe", 0, null, null));
    }

    public void TraCuuTongXe(Timestamp frist, Timestamp last, int option) {
        String url = "jdbc:postgresql://localhost:5432/db_do_an";
        String username = "postgres";
        String password = "123456";
        DefaultTableModel tableModel = new DefaultTableModel();
        String[] colsName = {"Mã số thẻ", "Biển số", "Loại xe", "Loại vé", "Khu gửi", "Giờ vào", "Giờ ra", "Số tiền"};
        tableModel.setColumnIdentifiers(colsName);

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();
            System.out.println("success connect to db");
            String sql = "select ticket.id, vehicle_id, vehicle.name, ticket_type.name, parking_area.name, time_in, time_out, ticket_type.cost  \n" +
                    "from ticket\n" +
                    "inner join ticket_type on ticket.ticket_type_id = ticket_type.id\n" +
                    "inner join vehicle on ticket.vehicle_id = vehicle.id\n" +
                    "inner join parking_area on ticket.area_id = parking_area.id\n" +
                    "where time_in >= '" + frist + "' and time_in <='" + last + "'";
            if(option == 1) {
                sql = "select ticket.id, vehicle_id, vehicle.name, ticket_type.name, parking_area.name, time_in, time_out, ticket_type.cost  \n" +
                        "from ticket\n" +
                        "inner join ticket_type on ticket.ticket_type_id = ticket_type.id\n" +
                        "inner join vehicle on ticket.vehicle_id = vehicle.id\n" +
                        "inner join parking_area on ticket.area_id = parking_area.id\n" +
                        "where time_in >= '" + frist + "' and time_in <='" + last + "' and vehicle.name = 'xe máy'";
            } else if (option == 2) {
                sql = "select ticket.id, vehicle_id, vehicle.name, ticket_type.name, parking_area.name, time_in, time_out, ticket_type.cost  \n" +
                        "from ticket\n" +
                        "inner join ticket_type on ticket.ticket_type_id = ticket_type.id\n" +
                        "inner join vehicle on ticket.vehicle_id = vehicle.id\n" +
                        "inner join parking_area on ticket.area_id = parking_area.id\n" +
                        "where time_in >= '" + frist + "' and time_in <='" + last + "' and vehicle.name = 'ô tô'";
            }
            System.out.println(sql);
            ResultSet resultSet = st.executeQuery(sql);
            while ((resultSet.next())) {
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

        } catch (SQLException e) {
            e.printStackTrace();
        }
        jTable1.setModel(tableModel);
    }

    public void showAll() {
        String url = "jdbc:postgresql://localhost:5432/db_do_an";
        String username = "postgres";
        String password = "123456";
        DefaultTableModel tableModel = new DefaultTableModel();
        String[] colsName = {"Mã số thẻ", "Biển số", "Loại xe", "Loại vé", "Khu gửi", "Giờ vào", "Giờ ra", "Số tiền"};
        tableModel.setColumnIdentifiers(colsName);

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();
            System.out.println("success connect to db");
            String sql = "select ticket.id, vehicle_id, vehicle.name, ticket_type.name, parking_area.name, time_in, time_out, ticket_type.cost  \n" +
                    "from ticket\n" +
                    "inner join ticket_type on ticket.ticket_type_id = ticket_type.id\n" +
                    "inner join vehicle on ticket.vehicle_id = vehicle.id\n" +
                    "inner join parking_area on ticket.area_id = parking_area.id";
            System.out.println(sql);
            ResultSet resultSet = st.executeQuery(sql);
            while ((resultSet.next())) {
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

        } catch (SQLException e) {
            e.printStackTrace();
        }
        jTable1.setModel(tableModel);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btTraCuu = new javax.swing.JButton();
        boxLuaChonXe = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtDateDau = new com.toedter.calendar.JDateChooser();
        txtDateCuoi = new com.toedter.calendar.JDateChooser();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtTongLuotGui = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btQuayLai = new javax.swing.JButton();
        btLamMoi = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btTraCuu.setBackground(new java.awt.Color(102, 102, 102));
        btTraCuu.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btTraCuu.setForeground(new java.awt.Color(255, 255, 255));
        btTraCuu.setText("Tra cứu");
        btTraCuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    btTraCuuActionPerformed(evt);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        boxLuaChonXe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tổng xe", "Xe máy", "Ô tô" }));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Từ ngày:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Đến ngày:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(boxLuaChonXe, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtDateDau, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtDateCuoi, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(btTraCuu, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btTraCuu, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(boxLuaChonXe)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDateDau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDateCuoi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã thẻ", "Biển số xe", "Loại xe", "Loại vé", "Khu gửi", "Giờ vào", "Giờ ra", "Số tiền"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Thống kê lượt gửi xe ");

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Doanh Thu", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Tổng tiền:");

        txtTongTien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("VND");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Tổng số lượt gửi:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("lượt");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTongLuotGui, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(txtTongTien)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTongLuotGui, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12))
        );

        btQuayLai.setBackground(new java.awt.Color(102, 102, 102));
        btQuayLai.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btQuayLai.setForeground(new java.awt.Color(255, 255, 255));
        btQuayLai.setText("Quay lại");
        btQuayLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btQuayLaiActionPerformed(evt);
            }
        });

        btLamMoi.setBackground(new java.awt.Color(102, 102, 102));
        btLamMoi.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btLamMoi.setForeground(new java.awt.Color(255, 255, 255));
        btLamMoi.setText("Làm mới");
        btLamMoi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAll();
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(255, 255, 255)
                        .addComponent(btLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(115, 115, 115)
                        .addComponent(btQuayLai, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btQuayLai, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    private void btTraCuuActionPerformed(ActionEvent evt) throws SQLException {                                         
        int selectedIndex = boxLuaChonXe.getSelectedIndex();
        String selectedItem = (String) boxLuaChonXe.getSelectedItem();
        Timestamp sqlDate = null;
        Timestamp sqlDateLast = null;
        Date date = txtDateDau.getDate();
        Date date_last = txtDateCuoi.getDate();

        if(date == null || date_last == null) {
            JOptionPane.showMessageDialog(null, "Chọn đầy đủ ngày bắt đầu và kết thúc", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            if (date.compareTo(date_last) < 0 ) {
                sqlDate = new Timestamp(date.getTime());
                sqlDateLast = new Timestamp(date_last.getTime());
                TraCuuTongXe(sqlDate, sqlDateLast, selectedIndex);
                database.connectDb();

                txtTongTien.setText(homeAdmin.sumMoney(selectedItem, selectedIndex, sqlDate, sqlDateLast));
                txtTongLuotGui.setText(homeAdmin.sumLuotGui(selectedItem, selectedIndex, sqlDate, sqlDateLast));
                if(txtTongLuotGui.getText().equals("")) txtTongLuotGui.setText("0");

            } else {
                JOptionPane.showMessageDialog(null, "Ngày bắt đầu phải trước ngày kết thúc", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        }

    }                                        

    private void btQuayLaiActionPerformed(ActionEvent evt) {                                          
        this.dispose();
        new HomeForNV().showPlThongKe();
    }                                         

    /**
//     * @param args the command line arguments
     */
    private void updateDate() {
        txtDateCuoi.setDate(Calendar.getInstance().getTime());
    }
    
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
            Logger.getLogger(LuotGuiXe.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(LuotGuiXe.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(LuotGuiXe.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(LuotGuiXe.class.getName()).log(Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new LuotGuiXe().setVisible(true);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JComboBox<String> boxLuaChonXe;
    private javax.swing.JButton btLamMoi;
    private javax.swing.JButton btQuayLai;
    private javax.swing.JButton btTraCuu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private com.toedter.calendar.JDateChooser txtDateCuoi;
    private com.toedter.calendar.JDateChooser txtDateDau;
    private javax.swing.JTextField txtTongLuotGui;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration                   
}
