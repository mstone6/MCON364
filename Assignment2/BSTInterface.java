package Assignment2;

import java.util.Iterator;
public interface BSTInterface<T> extends Iterable<T>{
    public enum Traverse{Inorder, Preorder, Postorder};

    T minimum(); //This returns the smallest item on the tree
                 //In the case that the tree is empty will return null

    T maximum(); //This returns the largest element on tree
                 //IN the case that the tree is empty then will return null

    public Iterator<T> getIterator(Traverse orderType);
    //This iterator returns a traversal of a blob of the tree in use, in the order
    // specified by the order type.
}
