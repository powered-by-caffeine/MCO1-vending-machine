import java.util.ArrayList;
import java.util.*;

public class ChangeDispenser 
{
    private Denomination onePHP;
    private Denomination fivePHP;
    private Denomination tenPHP;
    private Denomination twentyPHP;
    private Denomination fiftyPHP;
    private Denomination oneHundredPHP;
    private Denomination fiveHundredPHP;

    private Denomination INVALID = new Denomination(-1, 0); //invalid denomination for error checking
    
    public ChangeDispenser(int commonStock)
    {
        onePHP = new Denomination(1, commonStock);
        fivePHP = new Denomination(5, commonStock);
        tenPHP = new Denomination(10, commonStock);
        twentyPHP = new Denomination(20, commonStock);
        fiftyPHP = new Denomination(50, commonStock);
        oneHundredPHP = new Denomination(100, commonStock);   
        fiveHundredPHP = new Denomination (500, commonStock);
    }

    public ChangeDispenser (int onePHPStock, int fivePHPStock, int tenPHPStock, int twentyPHPStock, int fiftyPHPStock, int oneHundredPHPStock, int fiveHundredPHPStock)
    {
        onePHP = new Denomination(1, onePHPStock);
        fivePHP = new Denomination(5, fivePHPStock);
        tenPHP = new Denomination(10, tenPHPStock);
        twentyPHP = new Denomination(20, twentyPHPStock);
        fiftyPHP = new Denomination(50, fiftyPHPStock);
        oneHundredPHP = new Denomination(100, oneHundredPHPStock);   
        fiveHundredPHP = new Denomination (500, fiveHundredPHPStock);
    }

    /**
     * Method for dispensing the change in denominations. Before calling this method, please compute for the change needed first (Payment - item price). 
     * @param changeNeeded the change needed. 
     * @return an empty arraylist if there is insufficient change, otherwise returns the change in denominations
     */
    public ArrayList<Denomination> dispenseChange (int changeNeeded) 
    {
        //NOTE: Do not call this method when
        ArrayList<Denomination> change = new ArrayList<>();
        int denominationsNeeded;

        Denomination[] denominations = 
        {
            fiveHundredPHP,
            oneHundredPHP,
            fiftyPHP,
            twentyPHP,
            tenPHP,
            fivePHP,
            onePHP
        };

        for (Denomination denomination : denominations)
        {
            if (changeNeeded >= denomination.getValue())
            {
                denominationsNeeded = changeNeeded / denomination.getValue();
                //calculates for the amount of denominations needed for the change needed (note that this does not necessarily mean that that's the amount that will be returned)

                for (int i = 0; i < denominationsNeeded; i++)
                {
                    if (denomination.getStock() > 0) //checks whether there is enough stock to get the denomination from
                    {
                        change.add(denomination); 
                        denomination.reduceStock(1); 
                        changeNeeded -= denomination.getValue(); //reduces the change needed for every successful transfer
                    }   
                }
            }
        }

        if (changeNeeded > 0) //this happens when there is not enough stock in the dispenser to give sufficient change
        {
            for (Denomination denomination : change)
            {
                denomination.addStock(1); //restocks the denominations by the amount previously reduced
            }

            change.clear(); //the condition for whether the transaction is successful should check if change.isEmpty()
            //hence, this function should not be called if the changeNeeded is 0
        }
        
        return change;
    }
    
    public void StockChange(){
        boolean done = false;
        int menu = 0;
        int stockNumber;
        Scanner scanner = new Scanner(System.in);

        
        while(!done){
            System.out.println("What do you want to restock?");
            System.out.println("[1] 1 Peso");
            System.out.println("[2] 5 Peso");
            System.out.println("[3] 10 Peso");
            System.out.println("[4] 20 Peso");
            System.out.println("[5] 50 Peso");
            System.out.println("[6] 100 Peso");
            System.out.println("[7] 500 Peso");
            System.out.println("[8] Exit");
            menu = scanner.nextInt();
            switch(menu){
                case 1:
                System.out.println("How many do you want to add.");
                stockNumber = scanner.nextInt();
                this.onePHP.addStock(stockNumber);
                break;

                case 2:
                System.out.println("How many do you want to add.");
                stockNumber = scanner.nextInt();
                this.fivePHP.addStock(stockNumber);
                break;

                case 3:
                System.out.println("How many do you want to add.");
                stockNumber = scanner.nextInt();
                this.tenPHP.addStock(stockNumber);
                break;

                case 4:
                System.out.println("How many do you want to add.");
                stockNumber = scanner.nextInt();
                this.twentyPHP.addStock(stockNumber);
                break;

                case 5:
                System.out.println("How many do you want to add.");
                stockNumber = scanner.nextInt();
                this.fiftyPHP.addStock(stockNumber);
                break;

                case 6:
                System.out.println("How many do you want to add.");
                stockNumber = scanner.nextInt();
                this.oneHundredPHP.addStock(stockNumber);
                break;

                case 7:
                System.out.println("How many do you want to add.");
                stockNumber = scanner.nextInt();
                this.fiveHundredPHP.addStock(stockNumber);
                break;

                case 8:
                done = true;
                break;

            }
        }

    }
}
