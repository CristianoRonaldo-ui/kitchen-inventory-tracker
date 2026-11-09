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
    }
}