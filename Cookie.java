/**
 * Project 3 Inheritance Cookie class
 * Cookie class that extends from Dessert
 * @author Lane Woods
 */
public class Cookie extends Dessert{
    //Initialization of our variables
    private final double pricePerDozen;
    private final int numCookies;

    /**
     * Constructor for Cookie class
     * @param name Name of the cookie
     * @param numCookies Number of cookies
     * @param pricePerDozen Price per dozen of cookies
     */
    public Cookie(String name, int numCookies, double pricePerDozen) {
        super(name);
        this.pricePerDozen = pricePerDozen;
        this.numCookies = numCookies;
    }

    /**
     * Gets the price per dozen of cookies
     * @return Price per dozen of cookies
     */
    public double getPricePerDozen() {
        return pricePerDozen;
    }

    /**
     * Gets the number of cookies
     * @return Number of cookies
     */
    public int getNumCookies() {
        return numCookies;
    }

    /**
     * Gets the price of the cookies by dividing the number of cookies by a dozen
     * (12) and multiplying it by the price per dozen
     * @return Price of the cookies
     */
    @Override
    public double getPrice() {
        return ((double) numCookies / 12) * pricePerDozen;
    }
}
