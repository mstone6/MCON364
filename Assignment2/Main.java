package Assignment2;

import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        Iterator<Character> iterator = null;

        //Now creating a new binary search tree
        System.out.println("Creating a binary search tree...");
        BinarySearchTree<Character> letters = new BinarySearchTree<>();
        System.out.println("Now adding nodes to your tree.");
        letters.add('M');
        letters.add('U');
        letters.add('S');
        letters.add('H');
        letters.add('K');
        letters.add('A');
        letters.add('S');
        letters.add('T');
        letters.add('O');
        letters.add('N');
        letters.add('E');
        letters.add('X');
        letters.add('Y');
        letters.add('Z');

        while (true) {
            //Three options: Choose automated sorting with random, user inserted sorting, or exit program.
            System.out.println("\nMenu: Please enter your choice: ");
            System.out.println("1. Automated Adding and Printing");
            System.out.println("2. User Friendly, Add a node to the tree.");
            System.out.println("3. User Friendly, Remove a node from the tree.");
            System.out.println("4. User Friendly, Find a node in the tree.");
            System.out.println("5. Print tree elements using the In Order Traversal.");
            System.out.println("6. Print Tree elements using Pre Order Traversal.");
            System.out.println("7. Print tree elements using the Post Order Traversal.");
            System.out.println("0. To end the Program.");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    automatic(letters);
                    break;
                case 2:
                    addNode(letters);
                    break;
                case 3:
                    removeNode(letters);
                    break;
                case 4:
                    searchNode(letters);
                    break;
                case 5:
                    printInOrder(letters);
                    break;
                case 6:
                    printPreOrder(letters);
                    break;
                case 7:
                    printPostOrder(letters);
                    break;
                case 0:
                    System.out.println("Now exiting the program! Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a valid option: ");
            }



        }
    }

    public static void automatic(BinarySearchTree<Character> letters) {

        System.out.println("\nNow printing your tree nodes in order: ");
        Iterator<Character> iter;
        System.out.print("Inorder:   ");
        iter = letters.getIterator(BSTInterface.Traverse.Inorder);
        while (iter.hasNext())
            System.out.println(iter.next());

        System.out.println("\nNow printing your tree nodes in Pre Order: ");
        Iterator<Character> iter2;
        System.out.print("Pre Order:   ");
        iter2 = letters.getIterator(BSTInterface.Traverse.Preorder);
        while (iter2.hasNext())
            System.out.println(iter2.next());

        //Now printing your nodes in post order
        System.out.println("\nNow printing your tree nodes Post Order: ");
        Iterator<Character> iter3;
        System.out.print("Post Order:   ");
        iter3 = letters.getIterator(BSTInterface.Traverse.Postorder);
        while (iter3.hasNext()) {
            System.out.println(iter3.next());
        }
    }

    public static void addNode(BinarySearchTree<Character> letters) {
        //This method adds a node to the tree
        System.out.println("You chose to add a node.");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a new character to add: ");
        char addChar = scanner.next().charAt(0);
        letters.add(addChar);

        System.out.println("The node you entered was added successfully.");

    }

    public static void removeNode(BinarySearchTree<Character> letters) {
        //This method removes a node from the tree
        System.out.println("You chose to remove a node.");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a character to remove: ");
        char remChar = scanner.next().charAt(0);
        Boolean removeChar = letters.remove(remChar);

        if (removeChar != null) {
            System.out.println("Character " + remChar + " was removed from the tree.");
        } else {
            System.out.println("Character " + remChar + " was not found in the tree.");
        }
    }

    public static void searchNode(BinarySearchTree<Character> letters) {
        //This method searches for a node in the tree
        System.out.println("You chose to search for a node.");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a character to search: ");
        char searchChar = scanner.next().charAt(0);
        boolean found = letters.contains(searchChar);

        System.out.println("Now searching...");

        System.out.println("\nWas " + searchChar + " found in the tree? " + found);
    }


    public static void printInOrder(BinarySearchTree<Character> letters) {
        //Now printing your nodes in order
        System.out.println("\nNow printing your tree nodes in order: ");
        Iterator<Character> iter;
        System.out.print("Inorder:   ");
        iter = letters.getIterator(BSTInterface.Traverse.Inorder);
        while (iter.hasNext())
            System.out.println(iter.next());

    }

    public static void printPreOrder (BinarySearchTree < Character > letters) {
        System.out.println("\nNow printing your tree nodes in Pre Order: ");
        Iterator<Character> iter;
        System.out.print("Pre Order:   ");
        iter = letters.getIterator(BSTInterface.Traverse.Preorder);
        while (iter.hasNext())
            System.out.println(iter.next());

    }

    public static void printPostOrder (BinarySearchTree < Character > letters) {
        //Now printing your nodes in post order
        System.out.println("\nNow printing your tree nodes Post Order: ");
        Iterator<Character> iter;
        System.out.print("Post Order:   ");
        iter = letters.getIterator(BSTInterface.Traverse.Postorder);
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
    }





}


