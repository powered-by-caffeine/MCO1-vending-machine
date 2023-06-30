import java.util.*;

public class VendingMachine{
    private String machineName;
    private ChangeDispenser changeDispenser;
    private ArrayList<Item> items = new ArrayList<Item>();
    private int paymentReserve = 0;

    private ArrayList<Item> startingInventory = new ArrayList<>();

    

    final String SUCCESS_ITEM_ADD = "Item added successfully.";

    /**
     * Default constructor for the vending machine. Creates a drink vending machine with preset, stocked items and a stocked change dispenser that has 10 of each denomination
     */
    public VendingMachine()
    {
        machineName = "Drink Vending Machine";
        Item Coke = new Item("Coca-Cola", 43, 139, 10);
        Item CokeZero = new Item("Coke Zero", 34, 0, 10);
        Item Pepsi = new Item("Pepsi", 43, 150, 10);
        Item Sprite = new Item("Sprite", 37, 146, 10);
        Item Royal = new Item("Royal", 34, 76, 10);
        Item MountainDew = new Item("Mountain Dew", 37, 170, 10);
        Item Monster = new Item("Monster", 99, 101, 10);
        Item RedBull = new Item("Red Bull", 86, 168, 10);

        items.add(Coke);
        items.add(CokeZero);
        items.add(Pepsi);
        items.add(Sprite);
        items.add(Royal);
        items.add(MountainDew);
        items.add(Monster);
        items.add(RedBull);


        changeDispenser = new ChangeDispenser(10);
    }


    /**
     * Constructor for the Vending Machine. The change dispenser for the vending machine has to be manually stocked
     * @param machineName This names the machine
     */
    public VendingMachine(String machineName){
        this.machineName = machineName;
        changeDispenser = new ChangeDispenser(0);
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
            if(tempStock < 8){
                System.out.println("Item Stock is below minimum, please add more stock.");
            }
        }

        Item newItem = new Item(tempName, tempPrice, tempCalorie, tempStock);
        
        items.add(newItem);
        System.out.println(SUCCESS_ITEM_ADD);

        input.close();
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
        startingInventory.add(newItem);
        System.out.println(SUCCESS_ITEM_ADD);
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
        startingInventory.add(newItem);
        System.out.println(SUCCESS_ITEM_ADD);
    }

    /**
     * Used to add an existing item into the machine. Primarily used for adding items
     * @param newItem an already existing item to be added into the list of items the machine currently holds
     */
    public void createItem(Item newItem)
    {
        items.add(newItem);
        startingInventory.add(newItem);
        System.out.println(SUCCESS_ITEM_ADD);
    }

    /**
     * Stocks the item given the index, the minimum stock detection is done over in the Item.java file
     * @param Index used to locate the proper location of the item
     * @param stockAmount the amount of stock to be added to the item 
     */
    public void stockItem(int Index, int stockAmount){
        items.get(Index).stockItem(stockAmount);
    }

    

    public void stockChange(){
        changeDispenser.StockChange();
    }

    /**
     * Returns the amount of items that the machine has
     * @return number of items the machine currently has
     */
    public int getItemAmount(){
        return items.size();
    }

    /**
     * Dispenses the item 
     * @param index 
     * @param moneyAmount the payment received by the machine
     */
    public void dispenseItem(int index, int moneyAmount){
        ArrayList<Denomination> change = new ArrayList<>();

        if (moneyAmount < items.get(index).getItemPrice()) //payment is less than the item price
        {
            System.out.println("Transaction failed.Payment received is less than the item price.");
        }
        else if (moneyAmount == items.get(index).getItemPrice()) //payment is equal to the item price
        {
            System.out.println("Dispensing " + items.get(index).getItemName() + "...");
            items.get(index).dispenseItem();
            System.out.println(items.get(index).getItemStock() + " stock remaining for item named: " + items.get(index).getItemName());
        }
        else //payment requires change
        {
            change = changeDispenser.dispenseChange(moneyAmount - items.get(index).getItemPrice());

            if (change.isEmpty()) //change is empty if change dispenser does not have enough change stock
            {
                System.out.println("Transaction failed. Not enough change to dispense.");
            }
            else
            {
                //Collect payment
                addPayment(moneyAmount - items.get(index).getItemPrice());

                // Dispense Item
                System.out.println("Dispensing " + items.get(index).getItemName() + "...");
                items.get(index).dispenseItem();
                System.out.println(items.get(index).getItemStock() + " stock remaining for item named: " + items.get(index).getItemName());

                // Dispense change
                System.out.println("Dispensing change:");
                for (Denomination denomination : change)
                {
                    System.out.println("Dispensing " + denomination.getValue() + " PHP...");
                }
            }
        }
    }

    /**
     * Code used to print all the items that the machine has, used for menus for customers or to inform the machine creator what it has
     */
    public void displayAllItems(){
        int i = 0;
        while(i < this.items.size()){
            System.out.println("[" + i + "] " + this.items.get(i).getItemName() + " | " + this.items.get(i).getItemCalorie() + " Calorie(s)");
            System.out.println(this.items.get(i).getItemStock() + " piece(s) left.");
            i++;
        }
    }

    /**
     * Adds the received payment to the payment reserve
     * @param paymentReceived payment received by the machine from last transaction
     */
    public void addPayment(int paymentReceived)
    {
        paymentReserve += paymentReceived;
    }

    /**
     * Collects the payments received by the machine and resets the payment reserve.
     * @return the payments received by the machine since last collection
     */
    public int collectPayment()
    {
        int paymentsReceived = paymentReserve;

        paymentReserve = 0; //resets payment reserve

        return paymentsReceived;
    }

    /**
     * gets the payments received by the machine
     * @return
     */
    public int getPaymentReserve() {
        return paymentReserve;
    }

    /**
     * gets the starting inventory since last reset
     */
    public ArrayList<Item> getStartingInventory() {
        return startingInventory;
    }

    public void updateStartingInventory(Item newItem) {
        startingInventory.add(newItem);
    }

    public void resetStartingInventory() {
        startingInventory.clear();
    }
}