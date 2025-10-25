/**
 * A simple class to demonstrate parsing command line arguments.
 */
public class ArgSum {

    /**
     * Program takes two numeric arguments and tells the user their sum.
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        // A tiny bit of error checking to make sure the correct
        // number of arguments are used.
        if(args.length != 2) {
            // wrong number of arguments, so complain about it.
            System.err.println("Expected 2 arguments, but got " + args.length);
        } else {
            // Arguments are Strings, so use static method in Integer
            // class to convert to numbers.
            int firstVal = Integer.parseInt(args[0]);
            int secondVal = Integer.parseInt(args[1]);

            int total = firstVal + secondVal;

            System.out.println("The sum of "
                    + firstVal + " and " + secondVal
                    + " is " + total);
        }
    }
}