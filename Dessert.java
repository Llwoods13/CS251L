/** Base class for Dessert types. */
public abstract class Dessert {

    /** Name of the dessert item.
     *
     * Note that the protected access modifier allows derived classes
     * to read this member variable, while the final modifier prevents
     * it from being reassigned after it is initialized in the dessert
     * constructor.
     */
    protected final String name;

    /**
     * Constructs a new dessert item.
     * @param name Name of the dessert.
     */
    public Dessert(String name) {
        this.name = name;
    }

    /**
     * Get name of the dessert.
     *
     * Note that the final modifier prevents subclasses from
     * overriding this method
     *
     * @return Dessert name
     */
    public final String getName() {
        return name;
    }

    /**
     * Get the price of the dessert.
     *
     * This method is abstract, so subclasses will have to implement it.
     *
     * @return Dessert price
     */
    public abstract double getPrice();
}