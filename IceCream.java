/**
 * Project 3 Inheritance IceCream class
 * IceCream class that extends from Dessert
 * @author Lane Woods
 */
public class IceCream extends Dessert{
    //initialization of our variables
    private final double price;

    /**
     * Constructor for IceCream class
     * @param name Name of the ice cream
     * @param price Price of our ice cream
     */
    public IceCream(String name, double price) {
        super(name);
        this.price = price;
    }

    /**
     * Gets the price of the ice cream
     * @return price of the ice cream
     */
    @Override
    public double getPrice() {
        return price;
    }
}
