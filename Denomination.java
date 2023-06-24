public class Denomination 
{
    private final int value;
    private int stock;

    public Denomination (int value, int stock)
    {
        this.value = value;
        this.stock = stock;
    }

    public int getValue() 
    {
        return value;
    }

    public int getStock() 
    {
        return stock;
    }

    public void addStock (int amount)
    {
        this.stock += amount;
    }

    public void reduceStock (int amount)
    {
        this.stock -= amount;
    }

    
}
