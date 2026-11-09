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
}