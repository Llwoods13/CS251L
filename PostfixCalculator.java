import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Project 5 Postfix Calculator Class.
 * Class that implements the Calculator interface and uses inner classes to help
 * evaluate the postfix expression. I created additional operators to play with
 * and test. They come out working pretty well.
 * @author Lane Woods
 */
public class PostfixCalculator{
    /* Variables of my class that creates a new DoubleStack list and a map of
    operators including its symbols and its shortened names
     */
    private final DoubleStack stack = new DoubleStack();
    private final Map<String, Operator> operatorMap = new HashMap<>();

    /**
     * Constructor for PostfixCalculator class. It puts all the different operators
     * needed into Hashmap.
     */
    public PostfixCalculator(){
        operatorMap.put("+", new add());
        operatorMap.put("add", new add());
        operatorMap.put("-", new sub());
        operatorMap.put("sub", new sub());
        operatorMap.put("*", new multi());
        operatorMap.put("multi", new multi());
        operatorMap.put("/", new div());
        operatorMap.put("div", new div());
        operatorMap.put("print", new Print());
        operatorMap.put("=", new Print());
        operatorMap.put("sqrt", new Sqrt());
        //Past this point is operators I made and played with.
        operatorMap.put("%", new Mod());
        operatorMap.put("mod", new Mod());
        operatorMap.put("^", new Exp());
        operatorMap.put("exp", new Exp());
        //It goes base then the number raised to. i.e 5 25 log = 2.0.
        operatorMap.put("log", new Log());
    }


    /**
     * Pushes an operand into my stack
     * @param operand Value to be pushed
     */
    public void storeOperand (double operand) {
        stack.push(operand);
    }

    /**
     * Creates a linked list using the key from my map to evaluate the operator.
     * It uses the key and number of arguments to evaluate what the operator
     * is supposed to do. It then pops the numbers from the stack and adds to
     * a list which is later evaluated and pushed back onto the stack.
     * @param operator String of the operator to be evaluated
     */
    public void evaluateOperator(String operator){
        LinkedList<Double> result = new LinkedList<>();
        /* This is for my understanding of how this loop works.
         * This loop gets the operator using the key and then uses the number of
         * arguments from that key to determine how many times it should pop an
         * element from the stack and add it to the list.
         * The final result is then pushed back into the stack as the key determines
         * what the operator should do and the eval(result) uses the key to
         * determine what should happen to the numbers/arguments in the list.
         */
        for (int i = 0; i < operatorMap.get(operator).numArgs(); i++) {
            result.add(stack.pop());
        }
        //Pushes the evaluated result back onto the stack
        stack.push(operatorMap.get(operator).eval(result));
    }

    /**
     * Nested class Add that implements the Operator interface and adds two
     * number from a list using the eval method.
     */
    private class add implements Operator {
        /**
         * Number of arguments the operator needs.
         * @return 2 arguments needed
         */
        @Override
        public int numArgs() {
            return 2;
        }

        /**
         * Evaluates the operator by adding the first number to the second number.
         * @param args List of arguments
         * @return The result of the addition
         */
        @Override
        public double eval(List<Double> args) {
            return args.get(1) + args.get(0);
        }
    }

    /**
     * Nested class Sub that implements the Operator interface and subtracts two
     * numbers from a list using the eval method.
     */
    private class sub implements Operator {

        /**
         * Number of arguments the operator needs.
         * @return 2 arguments needed
         */
        @Override
        public int numArgs() {
            return 2;
        }

        /**
         * Evaluates the operator by subtracting the second number from the first
         * number.
         * @param args List of arguments
         * @return The result of the subtraction
         */
        @Override
        public double eval(List<Double> args) {
            return args.get(1) - args.get(0);
        }
    }

    /**
     * Nested class multi that implements the Operator interface and multiplies two
     * numbers from a list using the eval method.
     */
    private class multi implements Operator {

        /**
         * Number of arguments the operator needs.
         * @return 2 arguments needed
         */
        @Override
        public int numArgs() {
            return 2;
        }

        /**
         * Evaluates the operator by multiplying the first number by the second
         * number.
         * @param args List of arguments
         * @return The result of the multiplication
         */
        @Override
        public double eval(List<Double> args) {
            return args.get(1) * args.get(0);
        }
    }

    /**
     * Nested class div that implements the Operator interface and divides two
     * numbers from a list using the eval method.
     */
    private class div implements Operator {
        /**
         * Number of arguments the operator needs.
         * @return 2 arguments needed
         */
        @Override
        public int numArgs() {
            return 2;
        }

        /**
         * Evaluates the operator by dividing the first number by the second
         * number.
         * @param args List of arguments
         * @return The result of the division
         */
        @Override
        public double eval(List<Double> args) {
            return args.get(1) / args.get(0);
        }
    }

    /**
     * Nested class print that implements the Operator interface and prints the
     * result of the first number in the list using the eval method.
     */
    private class Print implements Operator{

        /**
         * Number of arguments the operator needs.
         * @return 1 argument needed
         */
        @Override
        public int numArgs() {
            return 1;
        }

        /**
         * Prints the result of the first number in the list.
         * @param args List of arguments
         * @return The first number in the list
         */
        @Override
        public double eval(List<Double> args) {
            System.out.println(args.getFirst());
            return args.getFirst();
        }
    }

    /**
     * Nested class sqrt that implements the Operator interface and calculates
     * the square root of the first number in the list using the eval method.
     */
    private static class Sqrt implements Operator{

        /**
         * Number of arguments the operator needs.
         * @return 1 argument needed
         */
        @Override
        public int numArgs() {
            return 1;
        }

        /**
         * Calculates the square root of the first number in the list.
         * @param args Argument list.
         * @return The square root of the first number in the list.
         */
        @Override
        public double eval(List<Double> args) {
            return Math.sqrt(args.getFirst());
        }
    }

    private class Mod implements Operator{

        /**
         * Return the number of arguments needed.
         * @return 2 arguments needed
         */
        @Override
        public int numArgs() {
            return 2;
        }

        /**
         * Makes sure you're not dividing by 0 and if so will get the mod/remainder
         * of your first mod argument by your second mod argument.
         * @param args Argument list.
         * @return The remainder of the division.
         */
        @Override
        public double eval(List<Double> args) {
            if(args.get(1) != 0) {
                return args.get(1) % args.get(0);
            }
            else {
                return 0;
            }
        }
    }

    private class Exp implements Operator{

        /**
         * The number of arguments needed.
         * @return Arguments needed
         */
        @Override
        public int numArgs() {
            return 2;
        }

        /**
         * Grabs the first argument as the base, then uses the second argument
         * as the exponent to raise to.
         * @param args Argument list.
         * @return result of base raised to exponent
         */
        @Override
        public double eval(List<Double> args) {
            return Math.pow(args.get(1), args.get(0));
        }
    }

    private class Log implements Operator{

        /**
         * The number of arguments needed.
         * @return Arguments needed
         */
        @Override
        public int numArgs() {
            return 2;
        }

        /**
         * This works in a way that the first argument is the base and the
         * second argument is the number that it is raised to. It's a bit tricky,
         * but it works like this. 5 25 log = 2.0
         * @param args Argument list.
         * @return The result of the log
         */
        @Override
        public double eval(List<Double> args) {
            return Math.log(args.get(0)) / Math.log(args.get(1));
        }
    }
}
