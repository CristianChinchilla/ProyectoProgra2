/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2progra1;

/**
 * @autor Eidan Alexandre Picado Leiva
 * @autor Cristian Chinchilla Fonseca
 *
 * Class SalesManager - Handles storage and basic management of sales data using
 * arrays.
 */
public class SalesManager {

    // Arrays for sales data: [products][channels (0 = physical store, 1 = online store)]
    private int[][] salesData;

    /**
     * Constructor initializes the sales data array for a predefined number of
     * products and channels.
     */
    public SalesManager(int numberOfProducts) {
        salesData = new int[numberOfProducts][2];
    }

    /**
     * Registers a sale for a specific product and channel.
     *
     * @param productIndex The index of the product (0-based).
     * @param channel The sales channel (0 for physical store, 1 for online
     * store).
     * @param quantity The quantity sold.
     * @throws IllegalArgumentException if invalid channel or quantity.
     */
    public void registerSale(int productIndex, int channel, int quantity) {
        if (channel < 0 || channel > 1) {
            throw new IllegalArgumentException("Invalid channel. Use 0 for physical store or 1 for online store.");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }
        salesData[productIndex][channel] += quantity;
    }

    /**
     * Calculates the total sales for a specific product across all channels.
     *
     * @param productIndex The index of the product (0-based).
     * @return The total sales for the specified product.
     */
    public int getTotalSalesByProduct(int productIndex) {
        return calculateTotalSales(salesData[productIndex], 0);
    }

    /**
     * Recursively calculates the sum of sales for a specific array (product
     * sales).
     *
     * @param sales Array of sales for a product.
     * @param index Current index in the array.
     * @return The sum of sales.
     */
    private int calculateTotalSales(int[] sales, int index) {
        if (index >= sales.length) {
            return 0;
        }
        return sales[index] + calculateTotalSales(sales, index + 1);
    }

    /**
     * Retrieves the total sales across all products and channels.
     *
     * @return Total sales.
     */
    public int getTotalSales() {
        return calculateGrandTotal(salesData, 0);
    }

    /**
     * Recursively calculates the total sales across all products.
     *
     * @param data Array representing sales data for all products.
     * @param productIndex Current product being processed.
     * @return Total sales across all products and channels.
     */
    private int calculateGrandTotal(int[][] data, int productIndex) {
        if (productIndex >= data.length) {
            return 0;
        }
        return getTotalSalesByProduct(productIndex) + calculateGrandTotal(data, productIndex + 1);
    }

    /**
     * Displays the sales data in a readable format (for debugging purposes).
     */
    public void displaySalesData() {
        System.out.println("Sales Data:");
        for (int i = 0; i < salesData.length; i++) {
            System.out.println("Product " + i + ": Physical Store = " + salesData[i][0] + ", Online Store = " + salesData[i][1]);
        }
    }

    /**
     * Returns the sales data array.
     *
     * @return The bidimensional array containing sales data.
     */
    public int[][] getSalesData() {
        return salesData;
    }

}
