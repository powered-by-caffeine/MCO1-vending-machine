import java.util.Scanner;

public class Menu 
{
    final String PROJECT_TITLE = "MCO1 VENDING MACHINE FACTORY SIMULATOR";
    final String PROGRAM_EXIT = "Exiting program...";
    final String INPUT_ERROR = "Invalid Input. Please try again.";

    final int MIN_ITEMS = 8;
    final int MIN_ITEM_STOCK = 10;

    VendingMachine currentMachine;

    Boolean programEnded = false;

    Scanner scanner = new Scanner(System.in);

    private void createRegularVendingMachine()
    {
        int input = 0;

        System.out.print("Enter machine name: ");
        currentMachine = new VendingMachine(scanner.nextLine()); //assigns user input as machine name

        for (;;)
        {
            System.out.println("Current items in vending machine: ");
            currentMachine.displayAllItems(); //gives visual feedback on how many items there are
            System.out.println();
            System.out.println(MIN_ITEMS + "items need to be created for the Vending Machine to dispense.");
            System.out.println("[1] Create Item Manually");
            System.out.println("[2] Create Item using Preset");
            System.out.println("[3] Exit");
            System.out.println();
            System.out.print("Input: ");

            input = scanner.nextInt();

            if (input == 1)
            {
                currentMachine.createItem();
            }
            else if (input == 2)
            {
                /*
                    * PRESETS HERE
                    */
            }
            else if (input == 3)
            {
                if (currentMachine.getItemAmount() >= 8)
                {
                    break; //ends loop
                }
                else
                {
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
                
            }
        }   
    }

    public void mainMenu()
    {
        int input;

        while (programEnded == false)
        {
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
                    System.out.println(PROGRAM_EXIT);
                    break;

                default:
                    System.out.println(INPUT_ERROR);
                    break;
            }
        }

    }


}
