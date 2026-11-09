public class InventoryApp {

    public static void main(String[] args) {
        KitchenInventory inventory = new KitchenInventory();

        inventory.addStock("rice", 20);
        inventory.addStock("fish sauce", 5);
        inventory.addStock("rice", 10);

        System.out.println("Rice: " + inventory.getQuantity("rice"));
        System.out.println("Fish sauce: " + inventory.getQuantity("fish sauce"));
        System.out.println("Noodles: " + inventory.getQuantity("noodles"));
    }
}