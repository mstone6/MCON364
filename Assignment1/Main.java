package Assignment1;

import java.util.*;
/* Test your implementations: Create a list of Student objects and sort it using your QuickSort and MergeSort
        implementations.

        Generate random lists for grades the user (you can ask the user how large the list should be )
        Allow the user to input their own list of grades
        Give feedback to the user as the algorithms process on each key point of the algorithm
        Verify that the students are sorted correctly by their grades
*/
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            //Three options: Choose automated sorting with random, user inserted sorting, or exit program.
            System.out.println("Menu: Please enter your choice: ");
            System.out.println("1. Automatic Sorting for Randomly Generated Grades.");
            System.out.println("2. User Friendly, Enter Your Student's names and Grades for sorting.");
            System.out.println("0. To end the Program.");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    automaticSortMethod();
                    break;
                case 2:
                    userSortMethod();
                    break;
                case 0:
                    System.out.println("Now exciting the program! Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a valid option: ");
            }
        }
    }

        public static void automaticSortMethod(){
        //This method generates random grades for a list of 10 students and then sorts according to mergesort and quicksort
            Random random = new Random();
            Student[] grades = new Student[10];

            //This for loop is assigning the randomly generated grades to the students 1-10
            for (int i = 0; i < grades.length; i++) {
                String name = "Student" + (i + 1);
                int grade = random.nextInt(101);
                grades[i] = new Student(name, grade);
            }

            //Now printing the original array
            System.out.println("\nOriginal array: ");
            for(int i = 1; i < grades.length; i++){
                System.out.println("Student " + i + " Grade: " + grades[i].getGrade());
            }

            //Now making a copy of the og array to be used in merge sort
            Student[] gradesCopy = Arrays.copyOf(grades, grades.length);

            //Created new mergesort object
            MergeSort<Student> mergeS = new MergeSort<>();

            //Creating new mergesort array
            Student[] mergedSortedGrades = mergeS.mergeSort(gradesCopy);

            //Now printing mergesort array
            System.out.println("\nMerge Sorted array: ");
            for(int i = 1; i < mergedSortedGrades.length; i++){
                System.out.println("Student " + i + " Grade: " + mergedSortedGrades[i].getGrade());
            }

            System.out.println("\nNow sending the array to be Quick Sorted: ");

            //Created new quicksort object
            QuickSort<Student> quickS = new QuickSort<>();

            //Now calling the quicksort method in quicksort class
            quickS.quickSort(grades, 0, grades.length - 1);

            //Now printing quicksort
            System.out.println("\nQuick Sorted array: ");
            for(int i = 1; i < grades.length; i++){
                System.out.println("Student " + i + " Grade: " + grades[i].getGrade());
            }
        }

        public static void userSortMethod() {
        	Scanner scanner = new Scanner(System.in);
        	
            //Asking user to enter information
            System.out.println("How many students are there? ");
            int numOfStudents = scanner.nextInt();

            //Added input vaildation cannot sort with only one student
            if (numOfStudents < 1) {
                System.out.println("You can not have zero students.");

                System.out.println("How many students are there? ");
                numOfStudents = scanner.nextInt();
            }

            //Created new array of students
            Student[] students = new Student[numOfStudents];

            //Looping through students to ask for names and grades
            for (int i = 0; i < numOfStudents; i++) {
                System.out.println("Please enter student number " + (i + 1) + "'s name: ");
                String name = scanner.next();

                System.out.println("Please enter " + name + "'s number grade: ");
                Integer grade = scanner.nextInt();

                students[i] = new Student(name, grade);
            }

            //Printing out original inputted list
            System.out.println("\nHere are your Student Records UnSorted: ");
            for (Student student : students) {
                System.out.println("Student Name: " + student.getName() + "; Grade: " + student.getGrade());
            }

            //Creating copies of the list one for merge sort and one for quicksort
            System.out.println("\nNow creating copies of the student lists to be sorted.");
            Student[] mergedSortStudents = Arrays.copyOf(students, students.length);
            Student[] quickSortStudents = Arrays.copyOf(students, students.length);

            System.out.println("\nNow heading to MergeSort to sort your list.\n");

            //Created new mergesort object
            MergeSort<Student> mergeS = new MergeSort<>();

            //Calling the merge sort method in merge sort
            Student[] mergedSortedStudents = mergeS.sort(mergedSortStudents);

            //Displaying the merge sorted list
            System.out.println("\nThe Merged Sorted List is: ");
            for (Student student : mergedSortedStudents) {
                System.out.println("Student name: " + student.getName() + "; Grade:" + student.getGrade());
            }

            System.out.println("\nNow heading to Quicksort your list");

            //Created new quicksort object
            QuickSort<Student> quickS = new QuickSort<>();

            //Calling the quicksort method in quicksort
            Student[] quickSortedStudents = quickS.quickSort(quickSortStudents, 0, quickSortStudents.length - 1);

            //Displaying the quicksorted list
            System.out.println("\nQuick Sorted List is: ");
            for (Student student : quickSortedStudents) {
                System.out.println("Student name: " + student.getName() + "; Grade:" + student.getGrade());
            }
        }
    }


