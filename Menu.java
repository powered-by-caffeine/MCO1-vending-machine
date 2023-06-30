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

    private void displayDivider()
    {
        for (int i = 0; i < DIVIDER_LENGTH; i++)
        {
            System.out.print("-");
        }

        System.out.println();
    }

    private void createPresets()
    {
        ArrayList<Item> presets = new ArrayList<>();

        //PRESETS GO HERE
        Item Coke = new Item("Coca-Cola", 43, 139);
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
        Boolean found = true;

        Item newItem;

        //displays all of the presets available
        displayDivider();
        for (Item item : presets)
        {
            System.out.println("[" + i + "] " + item.getItemName() + " | " + item.getItemCalorie() + " Calorie(s)");
            System.out.println(item.getItemStock() + " piece(s) left.");
            i++;
        }
        System.out.println();

        while(true)
        {

            System.out.println("Enter the number of the preset you'd like to add as an item: ");    
            choice = scanner.nextInt();

            if (-1 < choice && choice < presets.size())
            {
                newItem = presets.get(i);

                while (true)
                {
                    System.out.println("How much do you want to stock? (0-15): ");
                    newItem.stockItem(scanner.nextInt()); 

                    
                }
            }
            else
            {
                System.out.println("Item not found. Try again.");
            }
        }

        while(true)
        {
            
        }
        

    }

    private void createRegularVendingMachine()
    {
        int input = 0;

        scanner.nextLine(); //consumes the newline character leftover from last nextInt() call

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
    }

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

    private void testVendingMachine()
    {
        int input = 0;

        int tempItem = 0, tempStock = 0, tempMoney = 0;
        
        if (currentMachine == null)
        {
            displayDivider();
            System.out.println("Please create a vending machine first.");
        }
        else
        {
            displayDivider();
            System.out.println("Testing the newest Machine created: " + currentMachine.getMachineName());

            while (input != 4)
            {
                System.out.println("What do you want to do?");
                System.out.println();
                System.out.println("[1] Restock Items");
                System.out.println("[2] Restock Change");
                System.out.println("[3] Use Vending Machine");
                System.out.println("[4] Exit");
                System.out.println();
                System.out.print("Input: ");

                switch (input)
                {
                    case 1:
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
                        currentMachine.stockChange();
                        break;
                    
                    case 3:
                        System.out.println("Which item do you want to purchase?");
                        currentMachine.displayAllItems();
                        System.out.println();
                        System.out.print("Input: ");
                        tempItem = scanner.nextInt();
                        System.out.println();

                        System.out.println("Enter the amount of money you wish to insert into the machine.");
                        System.out.print("Input: ");
                        tempMoney = scanner.nextInt();

                        currentMachine.dispenseItem(tempItem, tempMoney);
                        break;
                    
                    case 4: //Exit
                        break;

                    default:
                        System.out.println(INPUT_ERROR);
                        break;
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
            System.out.println("[3] Exit");   
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
