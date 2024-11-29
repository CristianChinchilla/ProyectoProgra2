/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2progra1;

/**
 *
 * @author eidan
 */

/**
 * The SalesChannel enum represents the channels through which sales can occur.
 * It includes options like PHYSICAL_STORE and ONLINE.
 * 
 * @author Cristian Chinchilla Fonseca
 */
public enum SalesChannel {
    
    PHYSICAL_STORE,
    ONLINE
    
    
    /**
     * Returns a user-friendly string representation of the sales channel.
     *
     * @return A string representing the sales channel.
     */
    @Override
    public String toString() {
        switch(this) {
            case PHYSICAL_STORE:
                return "Physical Store";
            case ONLINE:
                return "Online";
            default:
                return "Unknown Channel";
        }
    }
}
