/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.mytest;

import BackEnd.Database.dotenv;
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
public class LuotGuiXe extends JFrame {

    /**
     * Creates new form LuotGuiXe
     */
    public LuotGuiXe() throws SQLException {
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
        String url = dotenv.PostgreUrl;
        String username = dotenv.name;
        String password = dotenv.password;
        DefaultTableModel tableModel = new DefaultTableModel();
        String[] colsName = {"Mã số thẻ", "Biển số", "Loại xe", "Loại vé", "Khu gửi", "Giờ vào", "Giờ ra", "Số tiền"};
        tableModel.setColumnIdentifiers(colsName);

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();
            System.out.println("success connect to db");
            String sql = "select ticket.id, vehicle_id, vehicle.name, ticket_type.name, parking_area.name, time_in, time_out, ticket.ticket_cost  \n" +
                    "from ticket\n" +
                    "inner join ticket_type on ticket.ticket_type_id = ticket_type.id\n" +
                    "inner join vehicle on ticket.vehicle_id = vehicle.id\n" +
                    "inner join parking_area on ticket.area_id = parking_area.id\n" +
                    "where time_in >= '" + frist + "' and time_in <='" + last + "'";
            if(option == 1) {
                sql = "select ticket.id, vehicle_id, vehicle.name, ticket_type.name, parking_area.name, time_in, time_out, ticket.ticket_cost  \n" +
                        "from ticket\n" +
                        "inner join ticket_type on ticket.ticket_type_id = ticket_type.id\n" +
                        "inner join vehicle on ticket.vehicle_id = vehicle.id\n" +
                        "inner join parking_area on ticket.area_id = parking_area.id\n" +
                        "where time_in >= '" + frist + "' and time_in <='" + last + "' and vehicle.name = 'xe máy'";
            } else if (option == 2) {
                sql = "select ticket.id, vehicle_id, vehicle.name, ticket_type.name, parking_area.name, time_in, time_out, ticket.ticket_cost  \n" +
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
        String url = dotenv.PostgreUrl;
        String username = dotenv.name;
        String password = dotenv.password;
        DefaultTableModel tableModel = new DefaultTableModel();
        String[] colsName = {"Mã số thẻ", "Biển số", "Loại xe", "Loại vé", "Khu gửi", "Giờ vào", "Giờ ra", "Số tiền"};
        tableModel.setColumnIdentifiers(colsName);

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();
            System.out.println("success connect to db");
            String sql = "select ticket.id, vehicle_id, vehicle.name, ticket_type.name, parking_area.name, time_in, time_out, ticket.ticket_cost  \n" +
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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new JPanel();
        jPanel2 = new JPanel();
        btTraCuu = new JButton();
        boxLuaChonXe = new JComboBox<>();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        txtDateDau = new JDateChooser();
        txtDateCuoi = new JDateChooser();
        jPanel3 = new JPanel();
        jScrollPane1 = new JScrollPane();
        jTable1 = new JTable();
        jLabel3 = new JLabel();
        jPanel4 = new JPanel();
        jLabel4 = new JLabel();
        txtTongTien = new JTextField();
        jLabel5 = new JLabel();
        jLabel6 = new JLabel();
        txtTongLuotGui = new JTextField();
        jLabel7 = new JLabel();
        btQuayLai = new JButton();
        btLamMoi = new JButton();
        btQuayLai1 = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel2.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));

        btTraCuu.setBackground(new Color(102, 102, 102));
        btTraCuu.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
        btTraCuu.setForeground(new Color(255, 255, 255));
        btTraCuu.setText("Tra cứu");
        btTraCuu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    btTraCuuActionPerformed(evt);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        boxLuaChonXe.setModel(new DefaultComboBoxModel<>(new String[] { "Tổng xe", "xe máy", "ô tô" }));

        jLabel1.setFont(new Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Từ ngày:");

        jLabel2.setFont(new Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Đến ngày:");

        GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(boxLuaChonXe, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtDateDau, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtDateCuoi, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(btTraCuu, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(btTraCuu, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(jLabel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(boxLuaChonXe)
                    .addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDateDau, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDateCuoi, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jTable1.setModel(new DefaultTableModel(
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
                String.class, String.class, String.class, String.class, String.class, String.class, String.class, Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        GroupLayout jPanel3Layout = new GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel3.setFont(new Font("Segoe UI", 1, 36)); // NOI18N
        jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel3.setText("Thống kê lượt gửi xe ");

        jPanel4.setBorder(BorderFactory.createTitledBorder(null, "Doanh Thu", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Segoe UI", 0, 18))); // NOI18N

        jLabel4.setFont(new Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Tổng tiền:");

        txtTongTien.setFont(new Font("Segoe UI", 0, 14)); // NOI18N

        jLabel5.setFont(new Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("VND");

        jLabel6.setFont(new Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Tổng số lượt gửi:");

        jLabel7.setFont(new Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("lượt");

        GroupLayout jPanel4Layout = new GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel6, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTongLuotGui, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                        .addComponent(jLabel7, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTongTien, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(txtTongTien)
                    .addComponent(jLabel5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTongLuotGui, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(jLabel7, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12))
        );

        btQuayLai.setBackground(new Color(102, 102, 102));
        btQuayLai.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
        btQuayLai.setForeground(new Color(255, 255, 255));
        btQuayLai.setText("Quay lại");
        btQuayLai.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btQuayLaiActionPerformed(evt);
            }
        });

        btLamMoi.setBackground(new Color(102, 102, 102));
        btLamMoi.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
        btLamMoi.setForeground(new Color(255, 255, 255));
        btLamMoi.setText("Làm mới");

        btQuayLai1.setBackground(new Color(102, 102, 102));
        btQuayLai1.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
        btQuayLai1.setForeground(new Color(255, 255, 255));
        btQuayLai1.setText("Trở về");
        btQuayLai1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btQuayLai1ActionPerformed(evt);
            }
        });

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(151, 151, 151)
                        .addComponent(btLamMoi, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
                        .addGap(115, 115, 115)
                        .addComponent(btQuayLai, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btQuayLai1, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
                        .addGap(115, 115, 115)))
                .addComponent(jPanel4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(btLamMoi, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                            .addComponent(btQuayLai, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                            .addComponent(btQuayLai1, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(jPanel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btTraCuuActionPerformed(ActionEvent evt) throws SQLException {//GEN-FIRST:event_btTraCuuActionPerformed
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

    }//GEN-LAST:event_btTraCuuActionPerformed

    private void btQuayLaiActionPerformed(ActionEvent evt) {//GEN-FIRST:event_btQuayLaiActionPerformed
        this.dispose();
        new Home().showPlThongKe();
    }//GEN-LAST:event_btQuayLaiActionPerformed

    private void btQuayLai1ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_btQuayLai1ActionPerformed
        this.dispose();
        new Home().showPlThongKe();
    }//GEN-LAST:event_btQuayLai1ActionPerformed

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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JComboBox<String> boxLuaChonXe;
    private JButton btLamMoi;
    private JButton btQuayLai;
    private JButton btQuayLai1;
    private JButton btTraCuu;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JPanel jPanel4;
    private JScrollPane jScrollPane1;
    private JTable jTable1;
    private JDateChooser txtDateCuoi;
    private JDateChooser txtDateDau;
    private JTextField txtTongLuotGui;
    private JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables
}
