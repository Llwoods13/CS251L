/**
 * Creating FizzBuzz, but using numbers given through a command line argument.
 */
public class FizzBuzz {
    public static void main(String[] args) {
        /**
         * This program takes in 3 arguments. The upperbound for what number it
         * should stop at. A Fizz which is what number is the first modulus that
         * will result in Fizz. Same for Buzz this will take in the second modulus
         * which will replace the integer with a string in its place.
         * @param args This is the command line arguments.
         */
        if (args.length != 3) {
            //Ensures 3 arguments are taken. No more, no less.
            System.out.println("Expected wrong number of arguments of " +
                    args.length + " instead of 3");
        }//Makes sure the upperbound is not 0
        else if (args[0].equals("0")){
            System.out.println("Upperbound has to be greater than 0");
        }
        //Preventing dividing by 0
        else if (args[1].equals("0") || args[2].equals("0")) {
            System.out.println("Cannot divide by zero");
        }
        else {
            //Variables that are assigned when entering 3 numbers into the
            //command console.
            int upperBound = Integer.parseInt(args[0]);
            int Fizz = Integer.parseInt(args[1]);
            int Buzz = Integer.parseInt(args[2]);
            //
            for (int i = 1; i <= upperBound; i++) {
                if (i % Fizz == 0 && i % Buzz == 0) {
                    System.out.println("FizzBuzz");
                } else if (i % Fizz == 0) {
                    System.out.println("Fizz");
                } else if (i % Buzz == 0) {
                    System.out.println("Buzz");
                } else {
                    System.out.println(i);
                }
            }
        }
    }
}
