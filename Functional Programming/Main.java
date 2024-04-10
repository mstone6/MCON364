package Assignment9;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.function.ToIntFunction;

public class Main {
    public static void main(String[]args){
        //Creating a new hashmap
        ArrayListHashMap9<String, Integer> myMap = new ArrayListHashMap9<String, Integer>();

        //Now processing and calculating the hashes for all the words in my hashmap
        processWords(myMap);

        Scanner scanner = new Scanner(System.in);

        //Now giving the user a choice of what to do
        int choice;
        do {
            System.out.println("\n** MAIN MENU: Please enter your choice: **");
            System.out.println("1. Choose your Hash Function.");
            System.out.println("2. Check your word count and the length of your lists.");
            System.out.println("3. View all your words in descending order.");
            System.out.println("4. Get a report on the internal structure of the Hash Table.");
            System.out.println("0. To end the Program.");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    //They chose to hash a function
                    System.out.println("You have selected to choose your Hash Function." +
                            "\n1. Choose to get a NAIVE Hash Function." +
                            "\n2. Choose to get a SOPHISTICATED Hash Function. ");
                    int function = scanner.nextInt();
                    scanner.nextLine();
                    switch (function) {
                        //There are two hashes, one naive and one sophisticated
                        case 1:
                            //Now in the naive hash function

                            //This is a lambda: key is the word to be hashed and key.chars converts the string into chars
                            //then the chars get evaluated and the total sum is given
                            ToIntFunction<String> naiveHash = key -> key.chars().sum();

                            System.out.println("You chose a naive Hash Function" +
                                    "\nEnter a word to hash: ");
                            String word = scanner.nextLine(); // Read the word
                            if (myMap.containsKey(word)) {
                                int naiveHashValue = naiveHash.applyAsInt(word);
                                System.out.println("Naive hash value: " + naiveHashValue);
                            } else {
                                System.out.println("'" + word + "' was not found in the map. Please enter a word that exists in the map.");
                                break;
                            }
                            break;
                        case 2:
                            //Now in the sophisticated function

                            //This is the LAMBDA: it does the same as the other one with converting
                            //Then the initial val is 0 but for each char it updates its hash val by multiplying the current hashval * 31
                            // and adding the ASCII val of the char
                            ToIntFunction<String> sophisticatedHash = key ->
                                    key.chars().reduce(0, (hash, c) -> 31 * hash + c);

                            System.out.println("You chose a sophisticated Hash Function" +
                                    "\nEnter a word to hash: ");
                            String word2 = scanner.nextLine(); // Read the word
                            //Checks if the word they want to hash is in the hashmap
                            if (myMap.containsKey(word2)) {
                                int sophisticatedHashVal = sophisticatedHash.applyAsInt(word2);
                                System.out.println("Sophisticated hash value: " + sophisticatedHashVal);
                            } else {
                                //The word they entered is not in the hashmap
                                System.out.println("'" + word2 + "' was not found in the map. Please enter a word that exists in the map.");
                                break;
                            }

                            break;
                        default:
                            System.out.println("");
                    }
                    break;
                case 2:
                    //Now checking for a words count
                    System.out.println("Enter the word to check its count:");
                    String wordToCheck = scanner.nextLine().toLowerCase();
                    if (myMap.containsKey(wordToCheck)) {
                        //if the map contains the word they want to check
                        int count = myMap.getOrDefault(wordToCheck, 0);
                        System.out.println("Word count for '" + wordToCheck + "': " + count);
                        //Now calculating the length of the list
                        int lengthOfList = myMap.getLengthOfList(wordToCheck);
                        System.out.println("Length of the list for '" + wordToCheck + "': " + lengthOfList);
                    } else {
                        System.out.println("'" + wordToCheck + "' was not found in the map.");
                    }
                    break;


                case 3:
                    // View all words in descending order
                    printDescOrder(myMap);
                    break;
                case 4:
                    //Calling the method that generates a hash report
                    hashReportInternalStructure(myMap);
                    break;
                case 0:
                    System.out.println("Now exiting the program! Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option: ");
            }
        } while (choice != 0);


    }


    static void processWords(ArrayListHashMap9<String, Integer> myMap){
        //This method processes words from a file and calculates their hashes
        //Then stores them in myMap as words and their counts
        String fileName = "bookOutput.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    System.out.println("Processing the word: " + word);
                    word = word.replaceAll("[^a-zA-Z]", "").toLowerCase();
                    if (!word.isEmpty()) {
                        myMap.put(word, myMap.getOrDefault(word, 0) + 1);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    private static void printDescOrder(ArrayListHashMap9<String, Integer> myMap){
        //This method prints all words in desc order based on their usage
        processWords(myMap);

        System.out.println("\nViewing all words in descending order of usage:");

        PriorityQueue<MapEntry9<String, Integer>> queue = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());;

        //Now creating a new iterator to iterate through our map
        Iterator<MapEntry9<String, Integer>> iterator = myMap.iterator();
        while(iterator.hasNext()){
            MapEntry9<String, Integer> entry = iterator.next();
            queue.offer(entry);
        }

        //Now getting and displaying the key value pairs
        while(!queue.isEmpty()){
            MapEntry9<String, Integer> entry = queue.poll();
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }


    static void hashReportInternalStructure(ArrayListHashMap9<String, Integer> myMap) {
        //This method gereates a hash report on the internal structure of the map
        System.out.println("You chose to get a Hash Report.");

        // Lets Calculate how many words per book
        int totalWords = myMap.size();
        System.out.println("\n***Now calculating the total number of words in the book.***");
        System.out.println("Total number of words in the book: " + totalWords);

        // Printing out each slot
        System.out.println("\n***Printing contents of each slot: ***");

        // Now we Iterate through each bucket
        for (int i = 0; i < myMap.buckets.size(); i++) {
            List<MapEntry9<String, Integer>> bucket = myMap.buckets.get(i);
            System.out.println("Slot " + i + ": " + bucket);
        }

        // Now we Get The size of the array
        int arraySize = myMap.buckets.size();
        System.out.println("\n***Size of the array: " + arraySize + " ***");

        // Now lets Get The length of the linked list for each hash code
        System.out.println("\n***Length of the linked list for each hash code: ***");
        for (int i = 0; i < myMap.buckets.size(); i++) {
            List<MapEntry9<String, Integer>> bucket = myMap.buckets.get(i);
            System.out.println("Hash code " + i + ": " + bucket.size());
        }

        // Now lets Get The number of unused array slots
        System.out.println("\n***Now getting the number of unused array slots.***");
        int unusedSlots = 0;
        for (List<MapEntry9<String, Integer>> bucket : myMap.buckets) {
            if (bucket.isEmpty()) {
                unusedSlots++;
            }
        }
        //Now printing out the number of unused array slots
        System.out.println("Number of unused array slots: " + unusedSlots);

    }



}