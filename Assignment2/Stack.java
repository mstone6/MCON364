package Assignment2;



import java.util.EmptyStackException;

/**
 * @author Mushka Stone
 * Create a generic Stack ADT (Abstract Data Type) using a linked list implementation.
 * The stack should support the following operations:

push(T element): Adds the specified element to the top of the stack.
pop(): Removes and returns the element from the top of the stack.
peek(): Returns the element from the top of the stack without removing it.
 * @param <T>

 */

public class Stack<T> {

    private class Node{
        //This class is used to implement the linked list
        T value;
        Node next;
        Node(T val, Node n){
            value = val;
            next = n;
        }
    }
    //This is the top of the stack
    private Node top = null;

    public boolean empty() {
        //This test the stack to see if its empty, returns true if yes but false if not
        return top == null;
    }

    public void push(T s) {
        //This allows for a node to be pushed onto the stack
        top = new Node(s, top);
    }

    public T pop() {
        //This method removes the element at the top of the stack
        //And then returns the element

        //If the stack is empty we throw this exception
        if(empty()) {
            throw new EmptyStackException();
        }else {
            T returnValue = top.value;
            top= top.next;
            return returnValue;
        }
    }
    public T peek() {
        //This peek method returns the top value on the stack
        if(empty()) {
            throw new EmptyStackException();
        }else {
            return top.value;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node p = top;
        while(p != null) {
            sb.append(p.value);
            p = p.next;
            if(p != null)
                sb.append("\n");
        }
        return sb.toString();
    }


}

