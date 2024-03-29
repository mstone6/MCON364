package Assignment2;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Iterator;

public class BinarySearchTreeTest {

    // Test for Preorder traversal iterator
    @Test
    public void testPreorderIterator() {
        // Creating a binary search tree and add elements
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.add(50);
        tree.add(30);
        tree.add(70);
        tree.add(20);
        tree.add(40);
        tree.add(60);
        tree.add(80);

        // Getting the iterator for Preorder traversal
        Iterator<Integer> iterator = tree.getIterator(BSTInterface.Traverse.Preorder);

        // Test the order of elements returned by the iterator
        assertEquals(Integer.valueOf(50), iterator.next());
        assertEquals(Integer.valueOf(30), iterator.next());
        assertEquals(Integer.valueOf(20), iterator.next());
        assertEquals(Integer.valueOf(40), iterator.next());
        assertEquals(Integer.valueOf(70), iterator.next());
        assertEquals(Integer.valueOf(60), iterator.next());
        assertEquals(Integer.valueOf(80), iterator.next());
        assertFalse(iterator.hasNext()); // Making sure no more elements
    }

    // Test for Inorder traversal iterator
    @Test
    public void testInorderIterator() {
        // Creating a binary search tree and add elements
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.add(50);
        tree.add(30);
        tree.add(70);
        tree.add(20);
        tree.add(40);
        tree.add(60);
        tree.add(80);

        // Getting the iterator for Inorder traversal
        Iterator<Integer> iterator = tree.getIterator(BSTInterface.Traverse.Inorder);

        // Testing the order of elements returned by the iterator
        assertEquals(Integer.valueOf(20), iterator.next());
        assertEquals(Integer.valueOf(30), iterator.next());
        assertEquals(Integer.valueOf(40), iterator.next());
        assertEquals(Integer.valueOf(50), iterator.next());
        assertEquals(Integer.valueOf(60), iterator.next());
        assertEquals(Integer.valueOf(70), iterator.next());
        assertEquals(Integer.valueOf(80), iterator.next());
        assertFalse(iterator.hasNext()); // Making sure no more elements
    }

    // Test for Postorder traversal iterator
    @Test
    public void testPostorderIterator() {
        // Now creating a binary search tree and adding elements
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.add(50);
        tree.add(30);
        tree.add(70);
        tree.add(20);
        tree.add(40);
        tree.add(60);
        tree.add(80);

        // Get the iterator for Postorder traversal
        Iterator<Integer> iterator = tree.getIterator(BSTInterface.Traverse.Postorder);

        // Test the order of elements returned by the iterator
        assertEquals(Integer.valueOf(20), iterator.next());
        assertEquals(Integer.valueOf(40), iterator.next());
        assertEquals(Integer.valueOf(30), iterator.next());
        assertEquals(Integer.valueOf(60), iterator.next());
        assertEquals(Integer.valueOf(80), iterator.next());
        assertEquals(Integer.valueOf(70), iterator.next());
        assertEquals(Integer.valueOf(50), iterator.next());
        assertFalse(iterator.hasNext()); // Ensure no more elements
    }

    // Testing for an empty iterator
    @Test
    public void testEmptyIterator() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        Iterator<Integer> iterator = tree.getIterator(BSTInterface.Traverse.Preorder);
        assertFalse(iterator.hasNext()); // Ensure iterator is empty
    }

    // Test for checking UnsupportedOperationException when calling remove on iterator
    @Test
    public void testRemoveUnsupportedOperation() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.add(50);
        Iterator<Integer> iterator = tree.getIterator(BSTInterface.Traverse.Inorder);
        assertThrows(UnsupportedOperationException.class, iterator::remove); // Ensure UnsupportedOperationException is thrown
    }

    // Test for checking if iterator has next element
    @Test
    public void testIteratorHasNext() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.add(50);
        Iterator<Integer> iterator = tree.getIterator(BSTInterface.Traverse.Inorder);
        assertTrue(iterator.hasNext()); // Making sure iterator has next element
    }
}
