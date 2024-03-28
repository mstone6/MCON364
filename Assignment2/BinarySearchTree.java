package Assignment2;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Stack;

public class BinarySearchTree<T extends Comparable<T>> implements BSTInterface {
    //The code for this is on page 462
    private Node<T> rootNode;    //This is the root node of the binary tree
    protected Comparator<T> comparator;     //This is our specified comparator for comparing all of our elements

    protected boolean found; //This is our boolean for telling us wether or not the node we are looking for is found.
    private int counter = 0; //This is our counter variable, ready to be incremented.

    public Node<T> getRoot () {
        return rootNode;
    }
    public BinarySearchTree() {
        //This is the constructor, instantiated root node to null and also setting the comparator to sort in natural order
        rootNode = null;
        comparator = (Comparator<T>) Comparator.naturalOrder();
        counter = 0;
    }

    public BinarySearchTree(Comparator<T> comp) {
        //This is constructor number two - in this one the comparator is customized.
        rootNode = null;
        this.comparator = comp;
        counter = 0;
    }

    public boolean isFull() {
        //This method checks if the tree is full, will always return false.
        return false;
    }

    public boolean isEmpty() {
        //This method checks if the tree is empty
        if(rootNode == null){
            return true;
        }return false;
    }

    @Override
    public T minimum() {
        //This method searches for the minimum element in the tree and returns it
        System.out.println("\n-----------------------------------------------------------");
        System.out.println("Now calling the method to find smallest element on the tree.");
        //Calls a recusrive method that returns either the min or null
        return findTheMinimum(rootNode);
    }

    private T findTheMinimum(Node<T> node){
        //This is a recursive method that finds the minimum element starting from the given node
        System.out.println("\n-----------------------------------------------------------");
        if(node==null){
            System.out.println("Your rootNode is null.");
            return null;
        }else if(node.getLeft() != null){
            System.out.println("The rootNode has left children." +
                    "\nNow moving left...");
            node = node.getLeft();
        }
            System.out.println("The current node has no left children.");
            System.out.println("Found the smallest element on tree. + " +
                    "\nNow getting the minimum nodes information...");
        return node.getInfo();
    }


    @Override
    public T maximum(){
        //This method searches for and returns the maximum element on the tree
        System.out.println("\n-----------------------------------------------------------");
        System.out.println("Now calling the method to find the largest method on the tree.");
        //Now calling a recursive method to return either max or null
        return findTheMaximum(rootNode);
    }

    public T findTheMaximum(Node<T> node) {
        //This is a recursive method to find the max element starting from a supplied node
        System.out.println("\n-----------------------------------------------------------");
        System.out.println("Now finding the largest element on the tree.");
        if(node==null){
            System.out.println("Your rootNode is null.");
            return null;
        }else if(node.getRight() != null){
            System.out.println("The rootNode has right children." +
                    "\nNow moving right...");
            node = node.getRight();
        }
        System.out.println("The current node has no right children.");
        System.out.println("Found the largest element on tree. + " +
                "\nNow getting the maximum nodes information...");
        return node.getInfo();
    }


    public int size() {
        //This is a method to return the amount of nodes in the tree
        System.out.println("\n-----------------------------------------------------------");
        System.out.println("\nNow calculating how many elements there are in the tree.");
        return counter;
    }

    public boolean contains(T target) {
        //This method checks if the tree has a node
        System.out.println("\n-----------------------------------------------------------");
        //This method uses recursion to check if the target node is contained in the tree
        System.out.println("Now checking if the node we are searching for is contained in the tree using recursion.");
        //Now calling that recursive method to check if node is contained in the tree
        return recContains(target, rootNode);
    }

    private boolean recContains(T target, Node<T> newNode) {
        // Recursive method to check if the given node is contained in the tree
        System.out.println("\n-----------------------------------------------------------");
        System.out.println("Now in the recursive method.");

        // If the current node is not null
        if (newNode != null) {

            // Compare the target with the data in the current node
            int comparatorResult = comparator.compare(target, newNode.getInfo());

            // If the target is less than the current node's data, move left down the tree
            if (comparatorResult < 0) {
                System.out.println("The target is less than the current node's data." +
                        "\nRecursively moving left down the tree to the next node.");
                return recContains(target, newNode.getLeft());
            }
            // If the target is greater than the current node's data, move right down the tree
            else if (comparatorResult > 0) {
                System.out.println("The target is greater than the current node's data." +
                        "\nRecursively moving right down the tree to the next node.");
                return recContains(target, newNode.getRight());
            }
            // If the target is equal to the current node's data, it's found in the tree
            else {
                System.out.println("The target node is found in the tree.");
                return true;
            }
        } else {
            // If the current node is null, the target node was not found in the tree
            System.out.println("The current node is null." +
                    "\nThe target node was not found in the tree.");
            return false;
        }
    }



    public T get(T target) {
        //This method calls a recursive method to return the information of where that node is stored in the tree
        System.out.println("\n-----------------------------------------------------------");
        System.out.println("\nNow checking if the node we are searching for is contained in the tree and returning its information." +
                "\n Now calling the recursive method.");
        return recGet(target, rootNode);
    }

    private T recGet(T target, Node<T> newNode) {
        //This is the recursive method that returns the info if the node exists
        System.out.println("\n-----------------------------------------------------------");
        while (newNode != null) {
            int comprtr = comparator.compare(target, newNode.getInfo());
            System.out.println("Now creating a comparator between the node entered and the current node. ");
            if (comprtr < 0) {
                System.out.println("The comparator is less then zero." +
                        "\nThe node we are trying to find is less then the one we currently have." +
                        "\nNow recursively calling to move left down the tree to the next node.");
                return recGet(target, newNode.getLeft());
            } else if (comprtr > 0) {
                System.out.println("\"The comparator is greater then zero." +
                        "\nThe node we are trying to find is greater then the one we currently have." +
                        "\nNow recursively calling to move right down the tree to the next node.");
                return recGet(target, newNode.getRight());

            } else {
                System.out.println("The node we are comparing the new node too are equal to each other." +
                        "\nThe node entered was found in the tree, now returning its information.");
                return newNode.getInfo();

            }
        }
        System.out.println("The target element was not found in the tree because it was null.");
        return null;
    }

    @Override
    public Iterator <T> getIterator(Traverse orderType) {
        //This method gets an iterator based on a certain traversal order
        System.out.println("\n-----------------------------------------------------------");
        final Queue<T> infoQueue = new Queue<T>();
        //Now traversing tree based on the specific order and enqueues elements onto the queue
        if (orderType == BSTInterface.Traverse.Preorder) {
            System.out.println("Iterator Type = Pre Order. ");
            preOrder(rootNode, infoQueue);
        }
        else if (orderType == BSTInterface.Traverse.Inorder) {
            System.out.println("Iterator Type = In Order. ");
            inOrder(rootNode, infoQueue);
        }
        else if (orderType == BSTInterface.Traverse.Postorder) {
            System.out.println("Iterator Type = Post Order. ");
            postOrder(rootNode, infoQueue);
        }
        //Now returning a new iterator for the queue
        return new Iterator<T>() {
            public boolean hasNext() {
                return !infoQueue.isEmpty();
            }

            public T next() {
                if (!hasNext())
                    throw new IndexOutOfBoundsException("illegal invocation of next "
                            + " in BinarySearchTree iterator.\n");
                return infoQueue.dequeue();
            }
            public void remove() {
                throw new UnsupportedOperationException("Unsupported remove attempted on BinarySearchTree iterator.");
            }

        };
    }

    private void inOrder(Node<T> ioNode, Queue<T> queue) {
        // Recursive method that traverses the tree in order and enqueues elements into the queue
        System.out.println("\n-----------------------------------------------------------");
        System.out.println("Now enqueueing the BST in order into a Queue.");

        // Base case: If the current node is null, there's nothing left in the tree
        if (ioNode == null) {
            System.out.println("\nThere is nothing left in the tree.");
            return;
        }

        // Traverse the left subtree recursively
        System.out.println("Now moving through the left part of the tree recursively.");
        inOrder(ioNode.getLeft(), queue);

        // Enqueue the current node's information
        System.out.println("Now enqueuing the current node's information.");
        queue.enqueue(ioNode.getInfo());

        // Traverse the right subtree recursively
        System.out.println("Now moving through the right part of the tree recursively.");
        inOrder(ioNode.getRight(), queue);
    }

    // Overloaded method to start in-order traversal from the root node
    public void inOrder(Queue<T> queue) {
        // Call the recursive inOrder method starting from the root node
        inOrder(rootNode, queue);
    }

    //This method performs the in order traversal of the tree and enqueues the elements


    private void preOrder(Node<T> proNode, Queue<T> proQueue){
        //This is a recursive method that traverses the tree in a preorder way and enqueues nodes onto the queue
        System.out.println("\n-----------------------------------------------------------");
        if(proNode!=null){
            System.out.println("Now enqueing the current nodes info in a PRE ORDER way.");
            proQueue.enqueue(proNode.getInfo());

            System.out.println("Now moving through the left part of the tree recursively.");
            preOrder(proNode.getLeft(), proQueue);
            System.out.println("Now moving through the right part of the tree recursively.");
            preOrder(proNode.getRight(), proQueue);

        }

    }

    private void postOrder(Node<T> poNode, Queue <T> poQueue){
        //This is a recursive method that traverses the tree in a postorder way and enqueues the nodes onto the queue
        System.out.println("\n-----------------------------------------------------------");
        if (poNode != null) {
            System.out.println("The current node is not null." +
                    "\n Now enqueuing the BST in a POST ORDER way.");
            System.out.println("Now moving through the left part of the tree recursively.");
            postOrder(poNode.getLeft(), poQueue);
            System.out.println("Now moving through the right part of the tree recursively.");
            postOrder(poNode.getRight(), poQueue);

            poQueue.enqueue(poNode.getInfo());
        }
    }

    public void add(T element){
    //This is a method that adds elements onto the tree
        System.out.println("\n-----------------------------------------------------------");
        System.out.println("Now in the adding method.");
            //Now calling the recursive method to add the element to the tree
        if(rootNode == null) {
            rootNode = new Node<T>(element);
        } else {
            rootNode = recAdd(element, rootNode);
        }


        }
    private Node<T> recAdd(T element, Node<T> node) {
        // Recursive method to add elements to the tree
        // If the tree is empty, create a new root node with the given element
        if (node == null) {
            System.out.println("Creating a new root node with the given element.");
            return new Node<>(element);
        }

        // Compare the element to be inserted with the data in the current node
        int compare = comparator.compare(element, node.getInfo());

        // If the element is smaller or equal to the data in the current node
        if (compare <= 0) {
            // If the left child of the current node is null, create a new left child with the element
            if (node.getLeft() == null) {
                System.out.println("Creating a new left child with the given element.");
                node.setLeft(new Node<>(element));
            } else {
                // If the left child is not null, recursively call recAdd with the left child as the new node
                System.out.println("Moving to the left child.");
                recAdd(element, node.getLeft());
            }
        } else { // If the element is greater than the data in the current node
            // If the right child of the current node is null, create a new right child with the element
            if (node.getRight() == null) {
                System.out.println("Creating a new right child with the given element.");
                node.setRight(new Node<>(element));
            } else {
                // If the right child is not null, recursively call recAdd with the right child as the new node
                System.out.println("Moving to the right child.");
                recAdd(element, node.getRight());
            }
        }
        // Return the root node of the binary tree
        System.out.println("Returning the root node of the binary tree.");
        return node;
    }


    public boolean remove(T target) {
    //This is the method that removes elements from the tree
        System.out.println("\n-----------------------------------------------------------");
        System.out.println("Now in the removing method.");
        //Now calling the recursive method to remove elements from the tree
        rootNode = recRemove(target, rootNode);
        return true;
    }

    private Node<T> recRemove(T target, Node<T> node) {
    //This is the recursive method to remove elements from the tree
        System.out.println("\n-----------------------------------------------------------");
        System.out.println("We are now in the REC removing method.");
        if (node == null){
            System.out.println("The node you want to remove is null." +
                    "\nOr we have hit the end of the tree.");
            return null;
        }
        System.out.println("Now initializing a Comparator to make things easier.");
        int comprtr = comparator.compare(target, node.getInfo());
        if (comprtr < 0) {
            System.out.println("The comparator was less then zero." +
                    "\nNow moving down to the LEFT part of the tree.");
            node.setLeft(recRemove(target, node.getLeft()));
        }
        else if (comprtr > 0) {
            System.out.println("The comparator was greater then zero." +
                    "\nNow moving down to the RIGHT part of the tree.");
            node.setRight(recRemove(target, node.getRight()));
        }else {
            System.out.println("The node you want to remove matches the current node." +
                    "\nNow going to remove current node.");
            node = removeNode(node);
        }
        System.out.println("Now returning current node to be removed.");
        return node;
    }

    private Node<T> removeNode(Node<T> node) {
    //This method ACTUALLY removes the node from the tree
        System.out.println("\n-----------------------------------------------------------");
        T data;
        System.out.println("Now in the ACTUAL remove method.");
        if (node.getLeft() == null){
            System.out.println("There are no nodes left in the left children. Now moving right.");
        return node.getRight();
        }
        else if (node.getRight() == null) {
            System.out.println("There are no nodes left in the right children. Now moving right.");
            return node.getLeft();
        }
        else {
            System.out.println("Left and Right Children exist in the BST." +
                    "\nNow transfering to your left child node and storing it.");
            data = node.getLeft().getInfo();
            System.out.println("Now setting your node informtion to have 'empty data'.");
            node.setInfo(data);
            node.setLeft(recRemove(data, node.getLeft()));
            return node;
        }
    }



    public void breadthFirstSearch(){
    //This method performs a breadth first search traversal of the tree
        System.out.println("\n-----------------------------------------------------------");
        if(rootNode!= null){
            System.out.println("Now in Breadth First Search Method. " +
                    "\nRoot node is not null."+ "\nNow creating a new queue.");
            Queue<Node<T>> queue = new Queue<>();
            System.out.println("Now adding your root node to the queue.");
            queue.enqueue(rootNode);
            while(!queue.isEmpty()){
                System.out.println("The queue is not empty." +
                                "\nNow popping the current node off the queue.");
                Node<T> node = queue.dequeue();
                System.out.println(node.getInfo());
                if(node.getLeft() != null){
                    System.out.println("The BST has additional nodes. Now enqueuing the left children");
                    queue.enqueue(node.getLeft());
                }
                if(node.getRight() != null){
                    System.out.println("The BST has additional nodes. Now enqueuing the right children");
                    queue.enqueue(node.getRight());
                }
            }
        }
    }

    public void depthFirstSearch(){
        //This method performs a depth first search traversal of the tree
        System.out.println("\n-----------------------------------------------------------");
        if(rootNode != null){
            System.out.println("Now in the Depth First Search Method." +
                    "\nRoot node is not null." +
                    "\nNow creating a new stack.");
            Stack<Node<T>> stack = new Stack<>();
            System.out.println("Now pushing the rootnode to the stack.");
            stack.push(rootNode);
            while(!stack.isEmpty()){
                System.out.println("Now popping the node of the stack.");
                Node<T> node = stack.pop();
                System.out.println(node.getInfo());
                if(node.getRight()!=null){
                    System.out.println("The BST has additional nodes. Now enqueuing the right children");
                    stack.push(node.getRight());
                }
                if(node.getLeft()!=null){
                    System.out.println("The BST has additional nodes. Now enqueuing the left children");
                    stack.push(node.getLeft());
                }
            }
        }
    }


    @Override
    public Iterator iterator() {
    //This is the method to get the iterator
        return null;
    }
}
