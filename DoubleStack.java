import java.util.LinkedList;

/**
 * Project 5 Postfix Calculator Double Stack Class
 * Double Stack class that implements the Stack interface. It creates a linked
 * list of doubles to store the values of the stack. The values can be pushed,
 * popped, peeked at, and checked if the stack is empty.
 *
 * @author Lane Woods
 */
public class DoubleStack implements Stack<Double> {
    LinkedList<Double> Numbers = new LinkedList<>();

    /**
     * Checks if the stack is empty.
     *
     * @return True if the stack is empty or False if it is not
     */
    @Override
    public boolean isEmpty() {
        return Numbers.isEmpty();
    }

    /**
     * Pushes a value onto the stack.
     *
     * @param val Value to be pushed onto the stack
     */
    @Override
    public void push(Double val) {
        Numbers.push(val);
    }

    /**
     * Pops a value off the stack.
     *
     * @return Value popped off the stack or -1 if the stack is empty
     */
    @Override
    public Double pop() {
        if (Numbers.isEmpty()) {
            return -1.0;
        } else {
            return Numbers.pop();
        }
    }

    /**
     * Peeks at the top of the stack without removing it.
     *
     * @return Value at the top of the stack or -1 if the stack is empty
     */
    @Override
    public Double peek() {
        if (!Numbers.isEmpty()) {
            return Numbers.peek();
        }
        return -1.0;
    }
}
