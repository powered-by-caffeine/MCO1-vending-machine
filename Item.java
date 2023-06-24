public class Item {
    private String itemName;
    private int itemPrice;
    private int itemStock;

    /** 
     * Constructor for Items, overloaded so that one requires stock and the other does not
     */
    public Item(String itemName, int itemPrice, int itemStock){
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemStock = itemStock;
    }
    /** 
     * Constructor for Items, overloaded so that one requires stock and the other does not
     */
    public Item(String itemName, int itemPrice){
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemStock = 0;
    }

    /**
     * Used to stock the item
     * @param stock adds to the stock of the item
     * @return either true when item is succesfuly stocked and false when not
     */
    public boolean StockItem(int stock){
        if(itemStock + stock < 10){
            System.out.println("Item Stock is below minimum, please add more stock.");
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
     * gets the item stock
     * @return the item stock
     */
    public int getItemStock() {
        return itemStock;
    }

    /**
     * Subtracts 1 from item stock as it dispenses it
     * @return the amount it has left after it dispense
     */
    public int dispenseItem(){
        this.itemStock-=1;

        return this.itemStock;
    }
}
