/**
 * Project 3 Inheritance Candy class
 * Candy class that extends from Dessert
 * @author Lane Woods
 */
public class Candy extends Dessert {
    //Initialization of our variables
    private final double weight;
    private final double price;

    /**
     * Constructor for Candy class
     * @param name Name of the candy
     * @param weight Weight of the candy
     * @param price Price of the candy
     */
    public Candy(String name, double weight, double price) {
        super(name);
        this.weight = weight;
        this.price = price;
    }

    /**
     * Gets the weight of the candy in pounds
     * @return Weight of the candy in pounds
     */
    public double getWeightInPounds() {
        return weight;
    }

    /**
     * Gets the price per pound of candy
     * @return Price per pound of candy
     */
    public double getPricePerPound() {
        return price;
    }

    /**
     * Gets the price of the candy by multiplying the weight by the price
     * @return Price of the candy
     */
    @Override
    public double getPrice() {
        return weight * price;
    }

}
