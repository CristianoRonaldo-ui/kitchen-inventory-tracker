import java.util.HashMap;

public class KitchenInventory {

    // Maps ingredient name -> quantity in stock
    private HashMap<String, Integer> stock;

    public KitchenInventory() {
        stock = new HashMap<>();
    }

    public void addStock(String item, int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive.");
        }
        int current = stock.getOrDefault(item, 0);
        stock.put(item, current + amount);
    }

    public int getQuantity(String item) {
        return stock.getOrDefault(item, 0);
    }

    public void useStock(String item, int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive.");
        }
        if (!stock.containsKey(item)) {
            throw new IllegalArgumentException("Item not found: " + item);
        }
        int current = stock.get(item);
        if (amount > current) {
            throw new IllegalArgumentException(
                    "Not enough " + item + ". Available: " + current + ", requested: " + amount);
        }
        stock.put(item, current - amount);
    }

    public void printLowStockReport(int threshold) {
        System.out.println("Low stock items (below " + threshold + "):");
        boolean found = false;

        for (String item : stock.keySet()) {
            int quantity = stock.get(item);
            if (quantity < threshold) {
                System.out.println("  - " + item + ": " + quantity);
                found = true;
            }
        }

        if (!found) {
            System.out.println("  (none)");
        }
    }
}