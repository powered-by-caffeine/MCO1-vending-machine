import java.util.*;

public class main{


    public static int loopExitConfirm(){
        Scanner input = new Scanner(System.in);
        int temp;
        System.out.println("Are you done?");
        System.out.println("[1] Yes");
        System.out.println("[2] No");
        temp = input.nextInt();

        return temp;
    }
    public static void main(String[] args){
        Menu test = new Menu();

        test.mainMenu();

        ArrayList<VendingMachine> machine = new ArrayList<VendingMachine>();
        VendingMachine tempMachine;
        String tempName;
        Scanner input = new Scanner(System.in);
        boolean done = false;
        int menu = 0;
        int tempItem = 0, tempStock = 0, tempMoney = 0;

        while(!done){
            System.out.println("What do you want to do?");
            System.out.println("[1] Create Vending Machine");
            System.out.println("[2] Operate Vending Machine");
            System.out.println("[3] Exit");
            menu = input.nextInt();
            input.nextLine();
            switch(menu){
                case 1:
                System.out.println("Please Enter Machine Name: ");
                tempName = input.nextLine();
                tempMachine = new VendingMachine(tempName);

                for(;;){
                    System.out.println("Items need to be created for the Vending Machine to dispense.");
                    System.out.println("[1] Create Item Manually");
                    System.out.println("[2] Create Item using Preset");
                    System.out.println("[3] Exit");
                    menu = input.nextInt();
                    if(menu == 1){
                        tempMachine.createItem();
                    }
                    else if(menu == 2){
                        /*
                         * PRESETS HERE
                         */
                    }

                    else if(menu == 3)
                    if(tempMachine.getItemAmount() >= 8)
                    break;
                    else
                    System.out.println("Vending Machine has less than 8 items.");
                }
                machine.add(tempMachine);
                break;

                case 2:
                tempMachine = machine.get(machine.size() - 1);
                System.out.println("Testing the newest Machine Created " + tempMachine.getMachineName());
                for(;;){
                    System.out.println("What do you want to do");
                    System.out.println("[1] Restock Items");
                    System.out.println("[2] Restock Change");
                    System.out.println("[3] Use Vending Machine");
                    System.out.println("[4] Exit");
                    menu = input.nextInt();
                    if(menu == 1){
                        System.out.println("What do you want to restock?");
                        tempMachine.displayAllItems();
                        tempItem = input.nextInt();
                        System.out.println("How many will you add to the stock?");
                        tempStock = input.nextInt();
                        tempMachine.stockItem(tempItem, tempStock);
                    }
                    else if(menu == 2){
                        tempMachine.stockChange();
                    }
                    else if(menu == 3){
                        tempMachine.displayAllItems();
                        System.out.println("Enter the number of the Item you want.");
                        tempItem = input.nextInt();
                        System.out.println("Enter the amount of money you are paying with");
                        tempMoney = input.nextInt();
                        tempMachine.dispenseItem(tempItem, tempMoney);
                    }
                    else if(menu == 4)
                        break;
                    else
                    System.out.println("Please enter a valid number from the menu.");
                }
                break;
                case 3:
                done = true;
                break;
            }
        }

        input.close(); 
    }

   
}