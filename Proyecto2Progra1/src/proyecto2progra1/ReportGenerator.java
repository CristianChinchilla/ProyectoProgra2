/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2proga1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @autor Eidan Alexandre Picado Leiva
 * @autor Cristian Chinchilla Fonseca
 *
 * Class ReportGenerator - Generates and displays sales reports using Java Swing.
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

        // Title Label
        JLabel titleLabel = new JLabel("Sales Report Generator", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        frame.add(titleLabel, BorderLayout.NORTH);

        // Report Area
        JTextArea reportArea = new JTextArea();
        reportArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(reportArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        JButton totalSalesButton = new JButton("Show Total Sales");
        JButton productBreakdownButton = new JButton("Show Product Breakdown");
        JButton closeButton = new JButton("Close");

        buttonPanel.add(totalSalesButton);
        buttonPanel.add(productBreakdownButton);
        buttonPanel.add(closeButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Button Actions
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
}
