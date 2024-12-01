package proyecto2progra1;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.io.IOException;

/**
 * @autor Eidan Alexandre Picado Leiva
 * @autor Cristian Chinchilla Fonseca
 *
 * Class SaleAnalysisSystem - Entry point for the sales analysis project with Java Swing.
 */
public class SaleAnalysisSystem {

    public static void main(String[] args) {

        SalesManager salesManager = new SalesManager(5);
        FileHandler fileHandler = new FileHandler("sales_data");
        ReportGenerator reportGenerator = new ReportGenerator(salesManager);
        TrendAnalyzer trendAnalyzer = new TrendAnalyzer(salesManager);
        GUIHelper guiHelper = new GUIHelper();
        SwingUtilities.invokeLater(() -> createMainMenu(salesManager, fileHandler, reportGenerator, trendAnalyzer, guiHelper));
    }

    /**
     * Creates and displays the main menu using Swing.
     *
     * @param salesManager SalesManager instance.
     * @param fileHandler FileHandler instance.
     * @param reportGenerator ReportGenerator instance.
     * @param trendAnalyzer TrendAnalyzer instance.
     * @param guiHelper GUIHelper instance.
     */
    private static void createMainMenu(SalesManager salesManager, FileHandler fileHandler,
                                       ReportGenerator reportGenerator, TrendAnalyzer trendAnalyzer,
                                       GUIHelper guiHelper) {
        JFrame mainFrame = guiHelper.createFrame("Sales Management System", 400, 300);

        JPanel buttonPanel = guiHelper.createGridPanel(3, 2, 10, 10);
        JButton registerSaleButton = guiHelper.createButton("Register Sale", 150, 50);
        JButton saveSalesButton = guiHelper.createButton("Save Sales Data", 150, 50);
        JButton loadSalesButton = guiHelper.createButton("Load Sales Data", 150, 50);
        JButton showReportsButton = guiHelper.createButton("Show Reports", 150, 50);
        JButton analyzeTrendsButton = guiHelper.createButton("Analyze Trends", 150, 50);
        JButton exitButton = guiHelper.createButton("Exit", 150, 50);

        buttonPanel.add(registerSaleButton);
        buttonPanel.add(saveSalesButton);
        buttonPanel.add(loadSalesButton);
        buttonPanel.add(showReportsButton);
        buttonPanel.add(analyzeTrendsButton);
        buttonPanel.add(exitButton);

        registerSaleButton.addActionListener(e -> openRegisterSaleDialog(mainFrame, salesManager, guiHelper));
        saveSalesButton.addActionListener(e -> saveSalesData(mainFrame, fileHandler, salesManager, guiHelper));
        loadSalesButton.addActionListener(e -> loadSalesData(mainFrame, fileHandler, salesManager, guiHelper));
        showReportsButton.addActionListener(e -> reportGenerator.displayReportInterface());

        analyzeTrendsButton.addActionListener(e -> {
            
            String trendsReport = trendAnalyzer.generateTrendsReport(); 
            
           
            JTextArea textArea = guiHelper.createTextArea(15, 30, false);
            textArea.setText(trendsReport);
            
           
            JOptionPane.showMessageDialog(null, new JScrollPane(textArea), "Sales Trends Report", JOptionPane.INFORMATION_MESSAGE);
        });

        exitButton.addActionListener(e -> System.exit(0));

        mainFrame.add(guiHelper.createLabel("Main Menu", 20, SwingConstants.CENTER), BorderLayout.NORTH);
        mainFrame.add(buttonPanel, BorderLayout.CENTER);

        mainFrame.setVisible(true);
    }

    /**
     * Opens a dialog that allows the user to register a sale by entering the
     * product index, sales channel, and quantity. If the user clicks OK, the
     * sale is registered using the SalesManager. If the input is invalid, an
     * error message is shown.
     *
     * @param parentFrame The parent frame for the dialog.
     * @param salesManager The SalesManager instance for registering the sale.
     * @param guiHelper The GUIHelper instance to create and manage components.
     */
    private static void openRegisterSaleDialog(JFrame parentFrame, SalesManager salesManager, GUIHelper guiHelper) {
        JPanel panel = guiHelper.createGridPanel(4, 2, 5, 5);
        JTextField productField = new JTextField();
        JTextField channelField = new JTextField();
        JTextField quantityField = new JTextField();

        panel.add(guiHelper.createLabel("Product Index (0-4):", 14, SwingConstants.LEFT));
        panel.add(productField);
        panel.add(guiHelper.createLabel("Sales Channel (0 = Store, 1 = Online):", 14, SwingConstants.LEFT));
        panel.add(channelField);
        panel.add(guiHelper.createLabel("Quantity Sold:", 14, SwingConstants.LEFT));
        panel.add(quantityField);

        int result = JOptionPane.showConfirmDialog(parentFrame, panel, "Register Sale", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            try {
                int productIndex = Integer.parseInt(productField.getText());
                int channel = Integer.parseInt(channelField.getText());
                int quantity = Integer.parseInt(quantityField.getText());
                salesManager.registerSale(productIndex, channel, quantity);
                guiHelper.showSuccessDialog(parentFrame, "Sale registered successfully!");
            } catch (Exception e) {
                guiHelper.showErrorDialog(parentFrame, "Error registering sale: " + e.getMessage());
            }
        }
    }

    /**
     * Opens a dialog where the user can input the month to save the sales data.
     * The sales data is saved using the FileHandler instance. If an error
     * occurs, an error message is displayed.
     *
     * @param parentFrame The parent frame for the dialog.
     * @param fileHandler The FileHandler instance for saving the data.
     * @param salesManager The SalesManager instance containing the sales data.
     * @param guiHelper The GUIHelper instance to manage components.
     */
    private static void saveSalesData(JFrame parentFrame, FileHandler fileHandler, SalesManager salesManager, GUIHelper guiHelper) {
        String month = JOptionPane.showInputDialog(parentFrame, "Enter the month to save data (e.g., January):");
        if (month != null) {
            try {
                fileHandler.saveSalesData(salesManager, month);
                guiHelper.showSuccessDialog(parentFrame, "Sales data saved successfully for " + month + ".");
            } catch (IOException e) {
                guiHelper.showErrorDialog(parentFrame, "Error saving data: " + e.getMessage());
            }
        }
    }

    /**
     * Opens a dialog where the user can input the month to load the sales data.
     * The sales data is loaded using the FileHandler instance. If an error
     * occurs, an error message is displayed.
     *
     * @param parentFrame The parent frame for the dialog.
     * @param fileHandler The FileHandler instance for loading the data.
     * @param salesManager The SalesManager instance to update with the loaded
     * data.
     * @param guiHelper The GUIHelper instance to manage components.
     */
    private static void loadSalesData(JFrame parentFrame, FileHandler fileHandler, SalesManager salesManager, GUIHelper guiHelper) {
        String month = JOptionPane.showInputDialog(parentFrame, "Enter the month to load data (e.g., January):");
        if (month != null) {
            try {
                fileHandler.loadSalesData(salesManager, month);
                guiHelper.showSuccessDialog(parentFrame, "Sales data loaded successfully for " + month + ".");
            } catch (IOException e) {
                guiHelper.showErrorDialog(parentFrame, "Error loading data: " + e.getMessage());
            }
        }
    }
}
