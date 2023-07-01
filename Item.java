public class Item {
    private String itemName;
    private int itemPrice;
    private int itemStock;
    private int itemCalorie;

    final int MIN_ITEMS = 8;
    final int MIN_ITEM_STOCK = 10;

    final int MAX_ITEMS = 10;
    final int MAX_ITEM_STOCK = 15;

    /**
     * Constructs an item with a name, price, calorie amount, and stock amount
     * @param itemName the item's name
     * @param itemPrice the item's price
     * @param itemCalorie the item's calorie amount
     * @param itemStock the item's stock amount
     */
    public Item(String itemName, int itemPrice, int itemCalorie, int itemStock){
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemCalorie = itemCalorie;
        this.itemStock = itemStock;
    }
    
    /**
     * Constructs an item with a name, price, and calorie amount
     * @param itemName the item's name
     * @param itemPrice the item's price
     * @param itemCalorie the item's calorie amount
     */
    public Item(String itemName, int itemPrice, int itemCalorie){
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemCalorie = itemCalorie;
        this.itemStock = 0;
    }

    /**
     * Used to stock the item
     * @param stock adds to the stock of the item
     * @return TRUE when the item is successfully stocked, FALSE otherwise
     */
    public boolean stockItem(int stock){
        if(itemStock + stock < MIN_ITEM_STOCK){
            System.out.println("Item Stock is below minimum, please add more stock.");
            return false;
        }
        else if(itemStock + stock > MAX_ITEM_STOCK){
            System.out.println("Item Stock is above the item limit, please reduce the stock.");
            return false;
        }


        itemStock += stock;
        return true;
    }

    /**
     * gets item name
     * @return the item name
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * gets the item price
     * @return the item price
     */
    public int getItemPrice() {
        return itemPrice;
    }

    /**
     * gets the item stock
     * @return the item stock
     */
    public int getItemStock() {
        return itemStock;
    }

    /**
     * Gets the item calorie
     * @return the item calorie
     */
    public int getItemCalorie() {
        return itemCalorie;
    }
    /**
     * Subtracts 1 from item stock as it dispenses it
     * @return the amount it has left after it dispense
     */
    public void dispenseItem(){
        this.itemStock-=1;
    }
    
    /**
     * Sets the item's price
     * @param itemPrice
     */
    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }
}
