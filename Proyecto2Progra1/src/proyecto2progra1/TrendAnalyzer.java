/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2progra1;;

/**
 * @autor Eidan Alexandre Picado Leiva
 * @autor Cristian Chinchilla Fonseca
 *
 * Class TrendAnalyzer - Analyzes sales trends and calculates averages using recursive methods.
 */
public class TrendAnalyzer {

    private final SalesManager salesManager;

    /**
     * Constructor initializes the TrendAnalyzer with a reference to the SalesManager.
     * @param salesManager The SalesManager instance containing the sales data.
     */
    public TrendAnalyzer(SalesManager salesManager) {
        this.salesManager = salesManager;
    }

    /**
     * Calculates the average sales over a specific product and channel recursively.
     * @param productIndex The index of the product to analyze.
     * @param channel The channel to analyze (0 for physical store, 1 for online store).
     * @return The average sales for the product and channel.
     */
    public double calculateAverageSales(int productIndex, int channel) {
        int[] sales = salesManager.getSalesData()[productIndex];
        int totalSales = sumRecursively(sales, 0);
        return totalSales / (double) sales.length;
    }

    /**
     * Recursively calculates the sum of an array of integers.
     * @param array The array to sum.
     * @param index The current index to process.
     * @return The sum of the array.
     */
    private int sumRecursively(int[] array, int index) {
        if (index >= array.length) {
            return 0;
        }
        return array[index] + sumRecursively(array, index + 1);
    }

    /**
     * Detects sales trends (increasing or decreasing) for a specific product across all channels.
     * @param productIndex The index of the product to analyze.
     * @return A string describing the detected trend.
     */
    public String detectSalesTrend(int productIndex) {
        int[][] salesData = salesManager.getSalesData();
        int[] productSales = salesData[productIndex];
        boolean increasing = isIncreasing(productSales, 0);
        boolean decreasing = isDecreasing(productSales, 0);

        if (increasing) {
            return "Product " + productIndex + ": Sales are increasing.";
        } else if (decreasing) {
            return "Product " + productIndex + ": Sales are decreasing.";
        } else {
            return "Product " + productIndex + ": No clear trend detected.";
        }
    }

    /**
     * Recursively checks if an array of sales is strictly increasing.
     * @param sales The array to check.
     * @param index The current index to compare.
     * @return True if the sales are increasing, false otherwise.
     */
    private boolean isIncreasing(int[] sales, int index) {
        if (index >= sales.length - 1) {
            return true;
        }
        return sales[index] <= sales[index + 1] && isIncreasing(sales, index + 1);
    }

    /**
     * Recursively checks if an array of sales is strictly decreasing.
     * @param sales The array to check.
     * @param index The current index to compare.
     * @return True if the sales are decreasing, false otherwise.
     */
    private boolean isDecreasing(int[] sales, int index) {
        if (index >= sales.length - 1) {
            return true;
        }
        return sales[index] >= sales[index + 1] && isDecreasing(sales, index + 1);
    }

    /**
     * Analyzes trends for all products and generates a comprehensive report.
     * @return A string summarizing the trends for all products.
     */
    public String generateTrendsReport() {
        StringBuilder report = new StringBuilder("=== Sales Trends Report ===\n");
        int[][] salesData = salesManager.getSalesData();
        for (int i = 0; i < salesData.length; i++) {
            report.append(detectSalesTrend(i)).append("\n");
        }
        return report.toString();
    }
}
