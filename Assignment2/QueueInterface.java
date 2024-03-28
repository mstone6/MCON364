package Assignment2;

public interface QueueInterface <T>{

    // Adds an item to the end of the queue
    void enqueue(T item);

    // Removes and returns the item from the front of the queue
    T dequeue();

    // Returns the item at the front of the queue without removing it
    T peek();

    // Returns true if the queue is empty, false otherwise
    boolean isEmpty();

    // Returns the number of items in the queue
    int size();


}


