/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2progra1;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @autor Eidan Alexandre Picado Leiva
 * @autor Cristian Chinchilla Fonseca
 *
 * Class ReportGenerator - Generates and displays sales reports using Java Swing and JFreeChart.
 */
public class ReportGenerator {

    private final SalesManager salesManager;

    /**
     * Constructor initializes the report generator with a reference to the SalesManager.
     * @param salesManager The SalesManager instance containing the sales data.
     */
    public ReportGenerator(SalesManager salesManager) {
        this.salesManager = salesManager;
    }

    /**
     * Displays the main report interface using a Swing JFrame.
     */
    public void displayReportInterface() {
        JFrame frame = new JFrame("Sales Report Generator");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Sales Report Generator", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        frame.add(titleLabel, BorderLayout.NORTH);

        JTextArea reportArea = new JTextArea();
        reportArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(reportArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton totalSalesButton = new JButton("Show Total Sales");
        JButton productBreakdownButton = new JButton("Show Product Breakdown");
        JButton chartButton = new JButton("Show Sales Chart");
        JButton closeButton = new JButton("Close");

        buttonPanel.add(totalSalesButton);
        buttonPanel.add(productBreakdownButton);
        buttonPanel.add(chartButton);
        buttonPanel.add(closeButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        totalSalesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reportArea.setText(generateTotalSalesReport());
            }
        });

        productBreakdownButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reportArea.setText(generateProductBreakdownReport());
            }
        });

        chartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displaySalesChart();
            }
        });

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        frame.setVisible(true);
    }

    /**
     * Generates a report showing total sales across all products and channels.
     * @return The formatted report as a String.
     */
    private String generateTotalSalesReport() {
        int totalSales = salesManager.getTotalSales();
        return "=== Total Sales Report ===\nTotal Sales Across All Products and Channels: " + totalSales;
    }

    /**
     * Generates a detailed breakdown report for each product and channel.
     * @return The formatted report as a String.
     */
    private String generateProductBreakdownReport() {
        StringBuilder report = new StringBuilder("=== Product Breakdown Report ===\n");
        int[][] salesData = salesManager.getSalesData();
        for (int i = 0; i < salesData.length; i++) {
            report.append("Product ").append(i).append(": ");
            report.append("Physical Store = ").append(salesData[i][0]).append(", ");
            report.append("Online Store = ").append(salesData[i][1]).append("\n");
        }
        return report.toString();
    }

    /**
     * Displays a bar chart representing the sales data for all products across all channels.
     * Displays the chart in a new JFrame.
     */
    private void displaySalesChart() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        int[][] salesData = salesManager.getSalesData();

        for (int i = 0; i < salesData.length; i++) {
            dataset.addValue(salesData[i][0], "Physical Store", "Product " + i);  
            dataset.addValue(salesData[i][1], "Online Store", "Product " + i);     
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Sales by Product and Channel",  
                "Product",                      
                "Sales",                        
                dataset,                        
                org.jfree.chart.plot.PlotOrientation.VERTICAL,
                true,                           
                true,                           
                false                           
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));

        JFrame chartFrame = new JFrame("Sales Chart");
        chartFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        chartFrame.add(chartPanel);
        chartFrame.pack();
        chartFrame.setVisible(true);
    }
}