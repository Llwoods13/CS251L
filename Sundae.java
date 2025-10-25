/**
 * Project 3 Inheritance Sundae class
 * Sundae class that extends from IceCream
 * @author Lane Woods
 */
public class Sundae extends IceCream{

    /**
     * Constructor for Sundae class that takes in an IceCream and Dessert object
     * and returns the name of the ice cream followed by what it's topped with
     * and then finally the price of the sundae. The price is calculated by
     * calling our getPrice method from our ice cream and dessert classes.
     *
     * @param iceCream Takes in an IceCream object
     * @param dessert Takes in a Dessert object
     */
    public Sundae(IceCream iceCream, Dessert dessert) {
        super(iceCream.getName() + " topped with " + dessert.getName(),
                (iceCream.getPrice() + dessert.getPrice()));
    }
}
