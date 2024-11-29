/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2progra1;

/**
 * The Sale class represents an individual sales transaction.
 * It contains information about the product sold, date of sale,
 * sales channel, and quantity sold.
 * 
 * @author 
 */
public class Sale {
    
    private Product product;
    private String date; // Format: "DD-MM-YYYY"
    private SalesChannel channel;
    private int quantity;
    
    /**
     * Constructs a new Sale with the specified product, date, channel, and quantity.
     *
     * @param product  The product that was sold.
     * @param date     The date of the sale in the format "DD-MM-YYYY".
     * @param channel  The channel through which the sale was made.
     * @param quantity The quantity of the product sold.
     */
    public Sale(Product product, String date, SalesChannel channel, int quantity) {
        this.product = product;
        this.date = date;
        this.channel = channel;
        this.quantity = quantity;
    }
    
    /**
     * Retrieves the product sold in this sale.
     *
     * @return The product sold.
     */
    public Product getProduct() {
        return product;
    }
    
    /**
     * Sets the product sold in this sale.
     *
     * @param product The new product to be set.
     */
    public void setProduct(Product product) {
        this.product = product;
    }
    
    /**
     * Retrieves the date of the sale.
     *
     * @return The date of the sale in "DD-MM-YYYY" format.
     */
    public String getDate() {
        return date;
    }
    
    /**
     * Sets the date of the sale.
     *
     * @param date The new date of the sale in "DD-MM-YYYY" format.
     */
    public void setDate(String date) {
        this.date = date;
    }
    
    /**
     * Retrieves the sales channel of the sale.
     *
     * @return The sales channel.
     */
    public SalesChannel getChannel() {
        return channel;
    }
    
    /**
     * Sets the sales channel of the sale.
     *
     * @param channel The new sales channel.
     */
    public void setChannel(SalesChannel channel) {
        this.channel = channel;
    }
    
    /**
     * Retrieves the quantity of the product sold.
     *
     * @return The quantity sold.
     */
    public int getQuantity() {
        return quantity;
    }
    
    /**
     * Sets the quantity of the product sold.
     *
     * @param quantity The new quantity sold.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    /**
     * Calculates the total amount for this sale.
     *
     * @return The total amount (price * quantity).
     */
    public double getAmount() {
        return product.getPrice() * quantity;
    }
    
    /**
     * Returns a string representation of the sale.
     *
     * @return A string containing the product name, date, channel, quantity, and total amount.
     */
    @Override
    public String toString() {
        return "Sale [Product=" + product.getName() + ", Date=" + date + ", Channel=" + channel 
                + ", Quantity=" + quantity + ", Amount=$" + getAmount() + "]";
    }
}
