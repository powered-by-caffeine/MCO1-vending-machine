import java.util.*;
import java.util.ArrayList;

public class VendingMachine{
    private String machineName;
    private ChangeDispenser change;
    private ArrayList<Item> items = new ArrayList<Item>();

    

    /**
     * Constructor for the Vending Machine
     * @param machineName This names the machine
     * @param itemNum This adds how many Items the machine holds, with the minimum of 8 **(Needs to be edited to be more user friendly)**
     */
    public VendingMachine(String machineName){
        this.machineName = machineName;
    }

    /**
     * Returns the machine name
     * @return the name of the vending machine
     */
    public String getMachineName() {
        return machineName;
    }

    /**
     * Used to manually create Items for the machine, Requires User Input.
     */
    public void createItem(){
        Scanner input = new Scanner(System.in);
        String tempName;
        int tempPrice = 0;
        int tempStock = 0;
        int tempCalorie = 0;

        System.out.println("Please enter Item name: ");
        tempName = input.nextLine();
        System.out.println("Please enter Item price: ");
        tempPrice = input.nextInt();
        System.out.println("Please enter Item calorie: ");
        tempCalorie = input.nextInt();
        while(tempStock < 10){
            System.out.println("Please enter Item stock: ");
            tempStock = input.nextInt();
            if(tempStock < 10){
                System.out.println("Item Stock is below minimum, please add more stock.");
            }
        }

        Item newItem = new Item(tempName, tempPrice, tempCalorie, tempStock);
        
        items.add(newItem);
        System.out.println("Successfully made an Item.");
    }

    /**
     * Used to pre-program an item for the machine, Overloaded, one requires stock while the other does not
     * @param itemName adds the name to the item
     * @param itemPrice adds a price to the item
     * @param itemCalorie adds the calorie to the item
     */
    public void createItem(String itemName, int itemPrice, int itemCalorie){
        Item newItem = new Item(itemName, itemPrice, itemCalorie);
        items.add(newItem);
        System.out.println("Succesfully made an Item.");
    }

    /**
     * Used to pre-program an item for the machine, Overloaded, one requires stock while the other does not
     * @param itemName adds a name to the item
     * @param itemPrice adds a price to the item
     * @param itemCalorie adds the calorie to the item
     * @param itemStock adds a stock to the item **(Has nothing that checks for the minimum stock required, either to be recoded or as it is pre-programmed up to user discretion)**
     */
    public void createItem(String itemName, int itemPrice, int itemCalorie, int itemStock){
        Item newItem = new Item(itemName, itemPrice, itemCalorie, itemStock);
        items.add(newItem);
        System.out.println("Succesfully made an Item.");
    }

    /**
     * Stocks the item given the index, the minimum stock detection is done over in the Item.java file
     * @param Index used to locate the proper location of the item
     * @param stockAmount the amount of stock to be added to the item 
     */
    public void stockItem(int Index, int stockAmount){
        items.get(Index).StockItem(stockAmount);
    }

    public void stockChange(){
        change.StockChange();
    }

    /**
     * Returns the amount of items that the machine has
     * @return number of items the machine currently has
     */
    public int getItemAmount(){
        return items.size();
    }
    
    public ArrayList<Item> getItems(){
        return items;
    }

    /**
     * Used to dispense the item.
     * @param index Requires index to locate the proper item
     * **STILL NEEDS THE CODE FOR THE CHANGE**
     */
    public void dispenseItem(int index, int moneyAmount){
        System.out.println("Dispensing " + items.get(index).getItemName());
        /*
         * CHANGE HERE
         */
        items.get(index).dispenseItem();
        System.out.println("There are " +items.get(index).getItemStock() + " " + items.get(index).getItemName() + " left");
    }

    /**
     * Code used to print all the items that the machine has, used for menus for customers or to inform the machine creator what it has
     */
    public void displayAllItems(){
        int i = 0;
        if (items.size() == 0)
        {
            System.out.println("No items to display.");
        }
        else
        {
            while(i < this.items.size()){
                System.out.println("[" + i + "] " + this.items.get(i).getItemName() + " | " + this.items.get(i).getItemCalorie() + " Calorie(s)");
                System.out.println(this.items.get(i).getItemStock() + " piece(s) left.");
                i++;
            }
        }
    }

}