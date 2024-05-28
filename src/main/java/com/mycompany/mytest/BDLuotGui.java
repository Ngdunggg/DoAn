/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mytest;

import BackEnd.Database.homeAdmin;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BDLuotGui extends JPanel
{
    private int histogramHeight = 200;
    private int barWidth = 50;
    private int barGap = 10;

    private JPanel barPanel;
    private JPanel labelPanel;

    private List<Bar> bars = new ArrayList<Bar>();

    public BDLuotGui()
    {
        setBorder( new EmptyBorder(10, 10, 10, 10) );
        setLayout( new BorderLayout() );

        barPanel = new JPanel( new GridLayout(1, 0, barGap, 0) );
        Border outer = new MatteBorder(1, 1, 1, 1, Color.BLACK);
        Border inner = new EmptyBorder(10, 10, 0, 10);
        Border compound = new CompoundBorder(outer, inner);
        barPanel.setBorder( compound );

        labelPanel = new JPanel( new GridLayout(1, 0, barGap, 0) );
        labelPanel.setBorder( new EmptyBorder(5, 10, 0, 10) );

        add(barPanel, BorderLayout.CENTER);
        add(labelPanel, BorderLayout.PAGE_END);
    }

    public void addHistogramColumn(String label, int value, Color color)
    {
        Bar bar = new Bar(label, value, color);
        bars.add( bar );
    }

    public void layoutHistogram()
    {
        barPanel.removeAll();
        labelPanel.removeAll();

        int maxValue = 1;

        for (Bar bar: bars)
            maxValue = Math.max(maxValue, bar.getValue());

        for (Bar bar: bars)
        {
            JLabel label = new JLabel(bar.getValue() + "");
            label.setHorizontalTextPosition(JLabel.CENTER);
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalTextPosition(JLabel.TOP);
            label.setVerticalAlignment(JLabel.BOTTOM);
            int barHeight = (bar.getValue() * histogramHeight) / maxValue;
            Icon icon = new ColorIcon(bar.getColor(), barWidth, barHeight);
            label.setIcon( icon );
            barPanel.add( label );

            JLabel barLabel = new JLabel( bar.getLabel() );
            barLabel.setHorizontalAlignment(JLabel.CENTER);
            labelPanel.add( barLabel );
        }
    }

    private class Bar
    {
        private String label;
        private int value;
        private Color color;

        public Bar(String label, int value, Color color)
        {
            this.label = label;
            this.value = value;
            this.color = color;
        }

        public String getLabel()
        {
            return label;
        }

        public int getValue()
        {
            return value;
        }

        public Color getColor()
        {
            return color;
        }
    }

    private class ColorIcon implements Icon
    {
        private int shadow = 3;

        private Color color;
        private int width;
        private int height;

        public ColorIcon(Color color, int width, int height)
        {
            this.color = color;
            this.width = width;
            this.height = height;
        }

        public int getIconWidth()
        {
            return width;
        }

        public int getIconHeight()
        {
            return height;
        }

        public void paintIcon(Component c, Graphics g, int x, int y)
        {
            g.setColor(color);
            g.fillRect(x, y, width - shadow, height);
            g.setColor(Color.GRAY);
            g.fillRect(x + width - shadow, y + shadow, shadow, height - shadow);
        }
    }

    public static void createAndShowGUI() throws SQLException {
        Map<String, Integer> res = null;
        String year = TKLuotGui.year_static;
        res = homeAdmin.vehiclePerMonth(year);
        Integer Jan = res.get("January");
        Integer Feb = res.get("Ferbuary");
        Integer Mar = res.get("March");
        Integer Apr = res.get("April");
        Integer May = res.get("May");
        Integer June = res.get("June");
        Integer July = res.get("July");
        Integer Aug = res.get("August");
        Integer Sep = res.get("September");
        Integer Oct = res.get("October");
        Integer Nov = res.get("November");
        Integer Dec = res.get("December");
        BDDoanhThu panel = new BDDoanhThu();
        panel.addHistogramColumn("Tháng 1", Jan, Color.red);
        panel.addHistogramColumn("Tháng 2", Feb, Color.yellow);
        panel.addHistogramColumn("Tháng 3", Mar, Color.gray);
        panel.addHistogramColumn("Tháng 4", Apr, Color.blue);
        panel.addHistogramColumn("Tháng 5", May, Color.white);
        panel.addHistogramColumn("Tháng 6", June, Color.green);
        panel.addHistogramColumn("Tháng 7", July, Color.orange);
        panel.addHistogramColumn("Tháng 8", Aug, Color.pink);
        panel.addHistogramColumn("Tháng 9", Sep, Color.darkGray);
        panel.addHistogramColumn("Tháng 10", Oct, Color.red);
        panel.addHistogramColumn("Tháng 11", Nov, Color.blue);
        panel.addHistogramColumn("Tháng 12", Dec, Color.green);
        panel.layoutHistogram();

        JFrame frame = new JFrame("Thống kê doanh thu");
        frame.add( panel );
        frame.setLocationByPlatform( true );
        frame.pack();
        frame.setVisible( true );
    }

    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                try {
                    createAndShowGUI();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
