public class InventoryApp {

    public static void main(String[] args) {
        KitchenInventory inventory = new KitchenInventory();

        inventory.addStock("rice", 20);
        inventory.addStock("fish sauce", 5);
        inventory.addStock("rice", 10);

        System.out.println("Rice: " + inventory.getQuantity("rice"));
        System.out.println("Fish sauce: " + inventory.getQuantity("fish sauce"));
        System.out.println("Noodles: " + inventory.getQuantity("noodles"));

        System.out.println("\n--- Using stock ---");

        inventory.useStock("rice", 12);
        System.out.println("Rice after using 12: " + inventory.getQuantity("rice"));

        try {
            inventory.useStock("fish sauce", 100);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            inventory.useStock("noodles", 1);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println();
        inventory.printLowStockReport(10);

        runTests();
    }

    public static void runTests() {
        System.out.println("\n--- Running tests ---");

        // Test 1: adding the same item twice should accumulate
        KitchenInventory inv1 = new KitchenInventory();
        inv1.addStock("rice", 20);
        inv1.addStock("rice", 10);
        if (inv1.getQuantity("rice") == 30) {
            System.out.println("Test 1 PASS: addStock accumulates quantity");
        } else {
            System.out.println("Test 1 FAIL: expected 30, got " + inv1.getQuantity("rice"));
        }

        // Test 2: using stock should reduce quantity
        KitchenInventory inv2 = new KitchenInventory();
        inv2.addStock("sugar", 8);
        inv2.useStock("sugar", 3);
        if (inv2.getQuantity("sugar") == 5) {
            System.out.println("Test 2 PASS: useStock reduces quantity");
        } else {
            System.out.println("Test 2 FAIL: expected 5, got " + inv2.getQuantity("sugar"));
        }

        // Test 3: using too much should throw and leave stock unchanged
        KitchenInventory inv3 = new KitchenInventory();
        inv3.addStock("salt", 4);
        boolean thrown = false;
        try {
            inv3.useStock("salt", 10);
        } catch (IllegalArgumentException e) {
            thrown = true;
        }
        if (thrown && inv3.getQuantity("salt") == 4) {
            System.out.println("Test 3 PASS: useStock rejects overuse, stock unchanged");
        } else {
            System.out.println("Test 3 FAIL: thrown=" + thrown + ", salt=" + inv3.getQuantity("salt"));
        }
    }
}