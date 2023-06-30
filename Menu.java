import java.util.Scanner;
import java.util.ArrayList;

public class Menu 
{
    final String PROJECT_TITLE = "MCO1 VENDING MACHINE FACTORY SIMULATOR";
    final String PROGRAM_EXIT = "Exiting program...";
    final String INPUT_ERROR = "Invalid Input. Please try again.";
    final String SUCCESS_STOCK = "Item stocked successfully.";

    final int MIN_ITEMS = 8;
    final int MIN_ITEM_STOCK = 10;

    final int MAX_ITEMS = 10;
    final int MAX_ITEM_STOCK = 15;

    final int DIVIDER_LENGTH = 50;

    VendingMachine currentMachine;

    Scanner scanner = new Scanner(System.in);

    /**
     * Displays a divider for better readability in terminal
     */
    private void displayDivider()
    {
        for (int i = 0; i < DIVIDER_LENGTH; i++)
        {
            System.out.print("-");
        }

        System.out.println();
    }

    /**
     * creates items for the vending machine using presets
     */
    private void createPresets()
    {
        ArrayList<Item> presets = new ArrayList<>();

        //PRESETS GO HERE
        Item Coke = new Item("Coca-Cola", 43, 139);
        Item CokeZero = new Item("Coke Zero", 34, 0);
        Item Pepsi = new Item("Pepsi", 43, 150);
        Item Sprite = new Item("Sprite", 37, 146);
        Item Royal = new Item("Royal", 34, 76);
        Item MountainDew = new Item("Mountain Dew", 37, 170);
        Item Monster = new Item("Monster", 99, 101);
        Item RedBull = new Item("Red Bull", 86, 168);
        Item Lays = new Item("Lay's Chips", 157, 553);
        Item Pringles = new Item("Pringles", 84, 600);
        Item Doritos = new Item("Doritos", 249, 900);
        Item Piknik = new Item("Piknik", 94, 513);
        Item Ruffles = new Item("Ruffles", 165, 160);

        //ADD PRESETS HERE
        presets.add(Coke);
        presets.add(CokeZero);
        presets.add(Pepsi);
        presets.add(Sprite);
        presets.add(Royal);
        presets.add(MountainDew);
        presets.add(Monster);
        presets.add(RedBull);
        presets.add(Lays);
        presets.add(Pringles);
        presets.add(Doritos);
        presets.add(Piknik);
        presets.add(Ruffles);

        int i = 0;
        int choice;

        Item newItem;
        int newStock;

        //displays all of the presets available
        displayDivider();
        for (Item item : presets)
        {
            System.out.println("[" + i + "] " + item.getItemName() + " | " + item.getItemCalorie() + " Calorie(s)");
            i++;
        }
        System.out.println();

        while(true)
        {

            System.out.println("Enter the number of the preset you'd like to add as an item: ");    
            choice = scanner.nextInt();

            if (-1 < choice && choice < presets.size())
            {
                newItem = presets.get(choice);

                while (true)
                {
                    System.out.println("How much do you want to stock? (10-15): ");
                    newStock = scanner.nextInt();
                  
                    if (newItem.stockItem(newStock) == true)
                    {
                        currentMachine.createItem(newItem);
                        currentMachine.updateStartingInventory(newItem);

                        System.out.println(SUCCESS_STOCK);
                        break;
                    }
                }

                break;
            }
            else
            {
                System.out.println("Item not found. Try again.");
            }
        }
    }

    /**
     * Creates a regular vending machine
     */
    private void createRegularVendingMachine()
    {
        int input = 0;

        scanner.nextLine(); //consumes the newline character leftover from last nextInt() call

        displayDivider();
        System.out.println("What do you want to do?");
        System.out.println("[1] Create Preset Vending Machine (Drink Vending Machine)");
        System.out.println("[2] Create Vending Machine Manually");
        System.out.println("[3] Exit");
        System.out.println();
        System.out.print("Input: ");

        switch(scanner.nextInt())
        {
            case 1:
                currentMachine = new VendingMachine();
                System.out.println("Successfully created vending machine!");
                break;
            
            case 2:
                scanner.nextLine();

                System.out.print("Enter machine name: ");
                currentMachine = new VendingMachine(scanner.nextLine()); //assigns user input as machine name

                while (true) 
                {
                    displayDivider();
                    System.out.println("Current items in vending machine: ");
                    currentMachine.displayAllItems(); //gives visual feedback on how many items there are
                    System.out.println();
                    System.out.println(MIN_ITEMS + " - " + MAX_ITEMS + " items need to be created for the Vending Machine to dispense.");
                    System.out.println("[1] Create Item Manually");
                    System.out.println("[2] Create Item using Preset");
                    System.out.println("[3] Exit");
                    System.out.println();
                    System.out.print("Input: ");

                    input = scanner.nextInt();

                    if (currentMachine.getItemAmount() == MAX_ITEMS)
                    {
                        break; //ends loop when item limit is reached
                    }
                    else if (input == 1)
                    {
                        currentMachine.createItem();
                    }
                    else if (input == 2)
                    {
                        createPresets();
                    }
                    else if (input == 3)
                    {
                        if (currentMachine.getItemAmount() >= 8)
                        {
                            break; //ends loop
                        }
                        else
                        {
                            displayDivider();
                            System.out.println("Vending Machine has less than 8 items.");
                        }
                    }         
                    else
                    {
                        System.out.println(INPUT_ERROR);
                    }
                    
                }        
                break;

            case 3:
                break;

            default:
                System.out.println(INPUT_ERROR);
                break;
        }


        
    }

    /**
     * Creates a vending machine based on user preference
     */
    private void createVendingMachine()
    {
        int input = 0;

        while (input != 3)
        {
            displayDivider();
            System.out.println("What type of vending machine would you like to create?");
            System.out.println();
            System.out.println("[1] Regular Vending Machine");
            System.out.println("[2] Special Vending Machine");
            System.out.println("[3] Exit");   
            System.out.println();
            System.out.print("Input: ");
            
            input = scanner.nextInt();

            switch (input)
            {
                case 1: 
                    createRegularVendingMachine();
                    break;
                
                case 2:
                    //createSpecialVendingMachine();
                    break;

                case 3:
                    break;

                default:
                    System.out.println(INPUT_ERROR);
                    break;

            }
        }
    }

    /**
     * tests the newest vending machine created
     */
    private void testVendingMachine()
    {
        int tempItem = 0, tempMoney = 0;

        ArrayList<Integer> denominations = new ArrayList<>();

        int payment = 0;
        
        if (currentMachine == null)
        {
            displayDivider();
            System.out.println("Please create a vending machine first.");
        }
        else
        {
            displayDivider();

            // Accept Payment in denominations
            while(true)
            {
                System.out.println("Keep entering the denominations you wish to insert into the machine. Enter 0 to finish entering denominations.");
                System.out.print("Input: ");
                tempMoney = scanner.nextInt();   
                
                if (tempMoney == 5 || tempMoney == 10 || tempMoney == 20 || tempMoney == 50 || tempMoney == 100 || tempMoney == 500)
                {
                    denominations.add(tempMoney); //for keeping track of what user inserted in case they cancel purchase
                    payment += tempMoney;
                }
                else if (tempMoney == 0)
                {
                    break;
                }
                else
                {
                    System.out.println("Invalid denomination. Try again.");
                }
            }

            // Choose the item to purchase
            System.out.println("Enter the number of the item you wish to purchase. To cancel your purchase, input -1.");
            currentMachine.displayAllItems();
            System.out.println();
            System.out.print("Input: ");
            tempItem = scanner.nextInt();

            if (tempItem == -1) //user cancels
            {
                System.out.println("Dispensing your change: ");
                for (int denomination : denominations)
                {
                    System.out.println("Dispensing " + denomination + " PHP...");
                }
            }
            else
            {
                currentMachine.dispenseItem(tempItem, payment);
            }
                
        }
        
    }

    /**
     * Performs maintenance on the current vending machine
     */
    private void maintainVendingMachine()
    {
        int input = 0;

        int tempItem = 0, tempStock = 0;

        if (currentMachine == null)
        {
            displayDivider();
            System.out.println("Please create a vending machine first.");
        }
        else
        {
            displayDivider();
            System.out.println("Performing maintenance on newest machine created: " + currentMachine.getMachineName());   

            while (input != 5)
            {
                System.out.println("What do you want to do?");
                System.out.println();
                System.out.println("[1] Restock Items");
                System.out.println("[2] Restock Change");
                System.out.println("[3] Print Transaction Summary");
                System.out.println("[4] Collect Payment");
                System.out.println("[5] Exit");
                System.out.println();
                System.out.print("Input: ");

                input = scanner.nextInt();

                switch (input)
                {
                    case 1:
                        displayDivider();
                        System.out.println("Which item do you want to restock?");
                        currentMachine.displayAllItems();
                        System.out.println();
                        System.out.print("Input: ");
                        tempItem = scanner.nextInt();
                        System.out.println();

                        System.out.println("How much will you add to the stock?");
                        System.out.print("Input: ");
                        tempStock = scanner.nextInt();
                        System.out.println();

                        currentMachine.stockItem(tempItem, tempStock);
                        System.out.println(SUCCESS_STOCK);
                        break;
                    
                    case 2:
                        displayDivider();
                        currentMachine.stockChange();
                        break;
                    
                    case 3:
                        int i = 0;

                        System.out.println("Starting Inventory since last restocking: ");
                        for (Item item : currentMachine.getStartingInventory())
                        {
                            System.out.println("[" + i + "] " + item.getItemName() + " | " + item.getItemCalorie() + " Calorie(s) | Stock: " + item.getItemStock());
                            i++;
                        }

                        System.out.println("Ending Inventory since last restocking: ");
                        currentMachine.displayAllItems();

                        break;

                    case 4:
                        displayDivider();
                        int paymentsReceived = currentMachine.collectPayment();

                        System.out.println(paymentsReceived + " amount collected from sales.");
                        break;

                    case 5: //exit
                        break;

                    default:
                        System.out.println(INPUT_ERROR);
                    
                }
            }
        }
        

    }

    public void mainMenu()
    {
        int input;
        Boolean programEnded = false;

        while (programEnded == false)
        {
            displayDivider();
            System.out.println(PROJECT_TITLE);
            System.out.println();
            System.out.println("[1] Create a Vending Machine");
            System.out.println("[2] Test a Vending Machine");
            System.out.println("[3] Maintain a Vending Machine");
            System.out.println("[4] Exit");   
            System.out.println();
            System.out.print("Input: ");

            input = scanner.nextInt();

            switch (input)
            {
                case 1:
                    createVendingMachine();
                    break;

                case 2:
                    testVendingMachine();
                    break;
                
                case 3:
                    maintainVendingMachine();
                    break;

                case 4:
                    displayDivider();
                    System.out.println(PROGRAM_EXIT);
                    programEnded = true;
                    break;

                default:
                    System.out.println(INPUT_ERROR);
                    break;
            }
        }

    }

    public static void main (String[] args)
    {
        Menu m = new Menu();

        m.mainMenu();
    }
}
