package application2;
import java.util.Stack;


public class StackModel {
    private Stack<Integer> stack;

    public StackModel() {
        stack = new Stack<>();
    }

    public void push(int element) {
        stack.push(element);
    }

    public int pop() {
        return stack.pop();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public int size() {
        return stack.size();
    }

    // Other methods as needed
}
