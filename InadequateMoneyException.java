/**
 * Project 4 Exception: InadequateMoneyException class
 * Creating an exception for when the user attempts to withdraw more mone than
 * what they have.
 * @author Lane Woods
 */
public class InadequateMoneyException extends Exception{
    //Variables for our exception
    private final Double shortFall;

    /**
     * Constructor for InadequateMoneyException class.Takes in a double for the
     * amount of money that is the shortfall and a string for the output message.
     * @param shortFall Amount of the money that is the shortfall
     * @param message The message to be displayed
     */
    public InadequateMoneyException(Double shortFall, String message){
        super(message);
        this.shortFall = shortFall;
    }

    /**
     * Basic getter for the shortfall variable
     * @return The shortfall amount
     */
    public double getShortfall(){
        return shortFall;
    }
}
