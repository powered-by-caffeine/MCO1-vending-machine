import java.util.*;

public class VendingMachine{
    private String machineName;
    private int itemNum;
    //private ChangeDispenser change;
    private Item[] items;

    /**
     * Constructor for the Vending Machine
     * @param machineName This names the machine
     * @param itemNum This adds how many Items the machine holds, with the minimum of 8 **(Needs to be edited to be more user friendly)**
     */
    public VendingMachine(String machineName, int itemNum){
        this.machineName = machineName;
        if(itemNum < 8){
            System.out.println("Number of items inputted is less than the minimum");
            System.out.println("Defaulting to 8");
            this.itemNum = 8;
            items = new Item[itemNum];
        }
        this.itemNum = itemNum;
        items = new Item[itemNum];
    }

    /**
     * Used to manually create Items for the machine, Requires User Input.
     */
    public void createItem(){
        Scanner input = new Scanner(System.in);
        String tempName;
        int tempPrice = 0;
        int tempStock = 0;

        System.out.println("Please enter Item name: ");
        tempName = input.nextLine();
        System.out.println("Please enter Item price: ");
        tempPrice = input.nextInt();
        while(tempStock < 10){
            System.out.println("Please enter Item stock: ");
            tempStock = input.nextInt();
            if(tempStock < 8){
                System.out.println("Item Stock is below minimum, please add more stock.");
            }
        }

        Item newItem = new Item(tempName, tempPrice, tempStock);
        
        int lowestIdx = -1;
        for(int i = 0; i < itemNum; i++){
            if(this.items[i] == null){
                lowestIdx = i;
                this.items[lowestIdx] = newItem;
                System.out.println("Successfuly made an Item."); 
                break;
            }
        }

        if(lowestIdx == -1){
            System.out.println("Vending Machine is already full of items.");
        }
    }

    /**
     * Used to pre-program an item for the machine, Overloaded, one requires stock while the other does not
     * @param itemName adds the name to the item
     * @param itemPrice add a price to the item
     */
    public void createItem(String itemName, int itemPrice){
        Item newItem = new Item(itemName, itemPrice);
        int lowestIdx = -1;
        for(int i = 0; i < itemNum; i++){
            if(this.items[i] == null){
                lowestIdx = i;
                this.items[lowestIdx] = newItem;
                System.out.println("Successfuly made an Item."); 
                break;
            }
        }
    }

    /**
     * Used to pre-program an item for the machine, Overloaded, one requires stock while the other does not
     * @param itemName adds a name to the item
     * @param itemPrice adds a price to the item
     * @param itemStock adds a stock to the item **(Has nothing that checks for the minimum stock required, either to be recoded or as it is pre-programmed up to user discretion)**
     */
    public void createItem(String itemName, int itemPrice, int itemStock){
        Item newItem = new Item(itemName, itemPrice, itemStock);
        int lowestIdx = -1;
        for(int i = 0; i < itemNum; i++){
            if(this.items[i] == null){
                lowestIdx = i;
                this.items[lowestIdx] = newItem;
                System.out.println("Successfuly made an Item."); 
                break;
            }
        }
    }

    /**
     * Stocks the item given the index, the minimum stock detection is done over in the Item.java file
     * @param Index used to locate the proper location of the item
     * @param stockAmount the amount of stock to be added to the item 
     */
    public void stockItem(int Index, int stockAmount){
        this.items[Index].StockItem(stockAmount);
    }

    /**
     * Used to dispense the item.
     * @param index Requires index to locate the proper item
     * **STILL NEEDS THE CODE FOR THE CHANGE**
     */
    public void dispenseItem(int index){
        System.out.println("Dispensing " + items[index].getItemName());
        items[index].dispenseItem();
        /*
         * Enter Change here
         */
        System.out.println("There are " +items[index].getItemStock() + " " + items[index].getItemName() + " left");
    }

    /**
     * Code used to print all the items that the machine has, used for menus for customers or to inform the machine creator what it has
     */
    public void displayAllItems(){
        int i = 0;
        while(this.items[i] != null){
            System.out.println("[" + i + "] " + this.items[i].getItemName() + " | " + this.items[i].getItemStock() + " pieces left.");
            i++;
        }
    }
}