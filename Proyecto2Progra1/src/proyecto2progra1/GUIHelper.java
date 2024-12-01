/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoproga2;

import javax.swing.*;
import java.awt.*;

/**
 * @autor Eidan Alexandre Picado Leiva
 * @autor Cristian Chinchilla Fonseca
 *
 * Class GUIHelper - Provides utility methods for creating common GUI components in Java Swing.
 */
public class GUIHelper {

    /**
     * Creates a standardized JLabel with specific text, font size, and alignment.
     * @param text The text to display on the label.
     * @param fontSize The font size of the label.
     * @param alignment The horizontal alignment of the label (SwingConstants).
     * @return The created JLabel.
     */
    public JLabel createLabel(String text, int fontSize, int alignment) {
        JLabel label = new JLabel(text, alignment);
        label.setFont(new Font("Arial", Font.PLAIN, fontSize));
        return label;
    }

    /**
     * Creates a standardized JButton with specific text and dimensions.
     * @param text The text to display on the button.
     * @param width The preferred width of the button.
     * @param height The preferred height of the button.
     * @return The created JButton.
     */
    public JButton createButton(String text, int width, int height) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(width, height));
        return button;
    }

    /**
     * Creates a standardized JPanel with a GridLayout.
     * @param rows The number of rows in the grid.
     * @param cols The number of columns in the grid.
     * @param hGap The horizontal gap between components.
     * @param vGap The vertical gap between components.
     * @return The created JPanel.
     */
    public JPanel createGridPanel(int rows, int cols, int hGap, int vGap) {
        JPanel panel = new JPanel(new GridLayout(rows, cols, hGap, vGap));
        return panel;
    }

    /**
     * Creates a standardized JScrollPane for a JTextArea.
     * @param textArea The JTextArea to embed in the JScrollPane.
     * @return The created JScrollPane.
     */
    public JScrollPane createScrollPane(JTextArea textArea) {
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        return scrollPane;
    }

    /**
     * Displays a standardized error message dialog.
     * @param parentComponent The parent component for the dialog.
     * @param message The error message to display.
     */
    public void showErrorDialog(Component parentComponent, String message) {
        JOptionPane.showMessageDialog(parentComponent, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Displays a standardized success message dialog.
     * @param parentComponent The parent component for the dialog.
     * @param message The success message to display.
     */
    public void showSuccessDialog(Component parentComponent, String message) {
        JOptionPane.showMessageDialog(parentComponent, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Creates a JFrame with standardized settings.
     * @param title The title of the frame.
     * @param width The width of the frame.
     * @param height The height of the frame.
     * @return The created JFrame.
     */
    public JFrame createFrame(String title, int width, int height) {
        JFrame frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        return frame;
    }

    /**
     * Creates a standardized JTextArea.
     * @param rows The number of rows for the text area.
     * @param cols The number of columns for the text area.
     * @param editable Whether the text area should be editable.
     * @return The created JTextArea.
     */
    public JTextArea createTextArea(int rows, int cols, boolean editable) {
        JTextArea textArea = new JTextArea(rows, cols);
        textArea.setEditable(editable);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        return textArea;
    }
}
