
package proyecto2progra1;;

/**
 * @autor Eidan Alexandre Picado Leiva
 * @autor Cristian Chinchilla Fonseca
 *
 * Class TrendAnalyzer - Analyzes sales trends including growth, decline, stability, peaks, and seasonality.
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
     * Analyzes and returns a comprehensive sales trend report for a specific product.
     * This includes growth, decline, stability, peaks, and potential seasonality.
     * @param productIndex The index of the product to analyze.
     * @return A string summarizing the detected trend for the product.
     */
    public String analyzeSalesTrend(int productIndex) {
        int[] productSales = salesManager.getSalesData()[productIndex];
        
        // Check for basic trends (increasing, decreasing, stable)
        boolean increasing = isIncreasing(productSales, 0);
        boolean decreasing = isDecreasing(productSales, 0);
        boolean stable = isStable(productSales);

        // Check for seasonal patterns and peaks
        String seasonality = detectSeasonality(productSales);
        String peaks = detectSalesPeaks(productSales);

        String trend = "Product " + productIndex + ": ";

        if (increasing) {
            trend += "Sales are steadily increasing. ";
        } else if (decreasing) {
            trend += "Sales are steadily decreasing. ";
        } else if (stable) {
            trend += "Sales are stable. ";
        } else {
            trend += "Sales are fluctuating. ";
        }

        // Add information about seasonality and peaks
        trend += seasonality;
        trend += peaks;

        return trend;
    }

    /**
     * Recursively checks if the sales data is increasing.
     * @param sales The array of sales data for the product.
     * @param index The current index to compare.
     * @return True if sales are increasing, false otherwise.
     */
    private boolean isIncreasing(int[] sales, int index) {
        if (index >= sales.length - 1) {
            return true;
        }
        return sales[index] <= sales[index + 1] && isIncreasing(sales,
                index + 1);
    }

    /**
     * Recursively checks if the sales data is decreasing.
     * @param sales The array of sales data for the product.
     * @param index The current index to compare.
     * @return True if sales are decreasing, false otherwise.
     */
    private boolean isDecreasing(int[] sales, int index) {
        if (index >= sales.length - 1) {
            return true;
        }
        return sales[index] >= sales[index + 1] && isDecreasing(sales,
                index + 1);
    }

    /**
     * Checks if the sales data is stable (within a defined threshold).
     * @param sales The array of sales data for the product.
     * @return True if sales are stable, false otherwise.
     */
    private boolean isStable(int[] sales) {
        int threshold = 5; // Example threshold for stability (sales should not fluctuate by more than 5 units)
        for (int i = 1; i < sales.length; i++) {
            if (Math.abs(sales[i] - sales[i - 1]) > threshold) {
                return false;
            }
        }
        return true;
    }

    /**
     * Detects seasonality patterns in the sales data. For simplicity, it checks for recurring patterns over time.
     * @param sales The array of sales data for the product.
     * @return A string describing potential seasonality.
     */
    private String detectSeasonality(int[] sales) {
        // Example of detecting simple seasonality (like higher sales in the first quarter)
        int sumFirstQuarter = 0, sumLastQuarter = 0;
        int quarterLength = sales.length / 4;
        
        for (int i = 0; i < quarterLength; i++) {
            sumFirstQuarter += sales[i];
            sumLastQuarter += sales[sales.length - 1 - i];
        }

        if (sumFirstQuarter > sumLastQuarter) {
            return "There seems to be higher sales in the first quarter"
                    + " (potential seasonality). ";
        }
        return "No significant seasonality detected. ";
    }

    /**
     * Detects sales peaks (significant increases in sales over short periods).
     * @param sales The array of sales data for the product.
     * @return A string describing any detected peaks.
     */
    private String detectSalesPeaks(int[] sales) {
        StringBuilder peaks = new StringBuilder();
        int threshold = 10; // Threshold for a peak (e.g., if sales increase by more than 10 units from one day to the next)

        for (int i = 1; i < sales.length; i++) {
            if (sales[i] - sales[i - 1] > threshold) {
                peaks.append("Sales peak detected on day ").append(i).append(
                        " (increase of ").append(
                                sales[i] - sales[i - 1]).append(" units). ");
            }
        }

        return peaks.length() > 0 ? peaks.toString() :
                "No significant sales peaks detected. ";
    }

    /**
     * Generates a detailed report for the sales trends of all products.
     * @return A string containing the sales trends report for all products.
     */
    public String generateTrendsReport() {
        StringBuilder report = new StringBuilder(
                "=== Sales Trends Report ===\n");
        int[][] salesData = salesManager.getSalesData();
        
        for (int i = 0; i < salesData.length; i++) {
            report.append(analyzeSalesTrend(i)).append("\n");
        }
        return report.toString();
    }
}
