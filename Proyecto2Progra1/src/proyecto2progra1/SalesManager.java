/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2progra1;

/**
 * The SalesManager class handles the storage and management of sales records.
 * It uses a fixed-size array to store Sale objects and provides methods to
 * add new sales and retrieve existing sales.
 * 
 * @author Alexandre Picado Leiva
 * @author Cristian Chinchilla Fonseca
 */
public class SalesManager {
    
    // Maximum number of sales that can be stored
    private final int MAX_SALES = 1000;
    
    // Array to store Sale objects
    private Sale[] salesArray;
    
    // Current count of sales stored
    private int salesCount;
    
    /**
     * Constructs a new SalesManager with an empty sales array.
     */
    public SalesManager() {
        salesArray = new Sale[MAX_SALES];
        salesCount = 0;
    }
    
    /**
     * Adds a new Sale to the sales array.
     *
     * @param sale The Sale object to be added.
     * @return true if the sale was added successfully, false if the array is full.
     */
    public boolean addSale(Sale sale) {
        if (isFull()) {
            System.out.println("Cannot add more sales. The sales array is full.");
            return false;
        }
        salesArray[salesCount] = sale;
        salesCount++;
        return true;
    }
    
    /**
     * Retrieves all sales stored in the sales array.
     *
     * @return An array containing all Sale objects up to the current sales count.
     */
    public Sale[] getAllSales() {
        Sale[] currentSales = new Sale[salesCount];
        for (int i = 0; i < salesCount; i++) {
            currentSales[i] = salesArray[i];
        }
        return currentSales;
    }
    
    /**
     * Checks if the sales array is full.
     *
     * @return true if the array is full, false otherwise.
     */
    public boolean isFull() {
        return salesCount >= MAX_SALES;
    }
    
    /**
     * Returns the current number of sales stored.
     *
     * @return The number of sales.
     */
    public int getSalesCount() {
        return salesCount;
    }
    
    /**
     * Displays all sales stored in the sales array.
     * Useful for debugging and testing purposes.
     */
    public void displayAllSales() {
        if (salesCount == 0) {
            System.out.println("No sales to display.");
            return;
        }
        for (int i = 0; i < salesCount; i++) {
            System.out.println(salesArray[i].toString());
        }
    }
}