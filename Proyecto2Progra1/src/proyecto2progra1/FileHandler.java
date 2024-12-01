/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2progra1;


import java.io.*;
import java.util.Scanner;
import proyecto2progra1.SalesManager;

/**
 * @autor Eidan Alexandre Picado Leiva
 * @autor Cristian Chinchilla Fonseca
 *
 * Class FileHandler - Manages data persistence by saving and loading sales data to and from files.
 */
public class FileHandler {

    private final String directoryPath;

    /**
     * Constructor initializes the directory for saving sales files.
     * @param directoryPath The directory path where sales files will be stored.
     */
    public FileHandler(String directoryPath) {
        this.directoryPath = directoryPath;
        createDirectoryIfNotExists();
    }

    /**
     * Creates the directory if it does not exist.
     */
    private void createDirectoryIfNotExists() {
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    /**
     * Saves sales data to a file for a specific month.
     * @param salesManager The SalesManager instance containing the sales data.
     * @param month The month to save the data for.
     * @throws IOException If an error occurs while writing the file.
     */
    public void saveSalesData(SalesManager salesManager, String month) throws IOException {
        File file = new File(directoryPath + "/" + month + ".txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            int[][] data = salesManager.getSalesData();
            for (int i = 0; i < data.length; i++) {
                writer.write("Product " + i + ": " + data[i][0] + "," + data[i][1]);
                writer.newLine();
            }
        }
    }

    /**
 * Loads sales data from a file for a specific month into the provided SalesManager instance.
 * @param salesManager The SalesManager instance to populate with sales data.
 * @param month The month to load data for.
 * @throws IOException If an error occurs while reading the file.
 */
public void loadSalesData(SalesManager salesManager, String month) throws IOException {
    File file = new File(directoryPath + "/" + month + ".txt");
    if (!file.exists()) {
        throw new FileNotFoundException("File for the specified month does not exist.");
    }
    int[][] loadedData = new int[salesManager.getSalesData().length][2];
    try (Scanner scanner = new Scanner(file)) {
        int productIndex = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(": ")[1].split(",");
            loadedData[productIndex][0] = Integer.parseInt(parts[0]);
            loadedData[productIndex][1] = Integer.parseInt(parts[1]);
            productIndex++;
        }
    }
    // Replace the existing data
    salesManager.setSalesData(loadedData);
}


    /**
     * Displays all saved files in the directory.
     */
    public void listSavedFiles() {
        File directory = new File(directoryPath);
        File[] files = directory.listFiles();
        if (files != null && files.length > 0) {
            System.out.println("Saved files:");
            for (File file : files) {
                System.out.println(file.getName());
            }
        } else {
            System.out.println("No files found.");
        }
    }

    /**
     * Deletes a sales file for a specific month.
     * @param month The month of the file to delete.
     * @return True if the file was successfully deleted, false otherwise.
     */
    public boolean deleteSalesFile(String month) {
        File file = new File(directoryPath + "/" + month + ".txt");
        return file.exists() && file.delete();
    }
}
