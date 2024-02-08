
package Assignment1;

import java.util.Arrays;
/* Implement QuickSort and MergeSort: Implement generic versions of QuickSort and MergeSort that can sort any list
        of objects that implement the Comparable Interface The sorting algorithms should not modify the original list,
        but instead return a new sorted list.
*/
public class MergeSort<T extends Comparable<T>>{

    public T[] sort(T[] unsortedArray) {
        /* This method Splits the Array for sorting
            - Creates a variable to hold the arrays size,
            - Validates that the array has numbers inside it via an if statement
            - Creates middleIndex variable to hold the middle of the array
            - Creates the leftPart array to hold the left half of the og array
            - Creates the rightPart array to hold the right half of the og array
            - The first for loop fills the leftPart array from the beginning until the middle index of the og array
            - The second for loop fill the rightPart array from the middle index until the end of the og array
            - Then to actually sort the arrays we recursively call this function with both the left and right parts
            - Once everything is sorted we can call the merge method
         */
            System.out.println("We are now in the mergeSort's sort method.");
            T[] sortedArray = Arrays.copyOf(unsortedArray, unsortedArray.length);
            mergeSort(sortedArray);
            return sortedArray;
        }
        public T[] mergeSort(T[] array){
            System.out.println("We are now in the official mergeSort method. Students will be sorted recursively.");
            int arraySize = array.length;

            if (arraySize < 2) {
                //Validation, if array is empty then just return nothing
                return array;
            }

            int middleIndex = arraySize / 2;
            System.out.println("The array is now being split into two halves to be sorted.");
            T[] leftPart = Arrays.copyOfRange(array, 0, middleIndex);
            T[] rightPart = Arrays.copyOfRange(array, middleIndex, arraySize);

            mergeSort(leftPart);
            mergeSort(rightPart);

            return merge(array, leftPart, rightPart);
        }


    private T[] merge(T[] array, T[] leftPart, T[] rightPart){
        /* This method takes the leftPart and rightPart, compares them, sorts them again,
        and then puts them back together in one array.
            - First creates a variable to hold the leftPart arrays length
            - Then creates a variable to hold the rightPart arrays length
            - Three iterator variables are created and each one is used differently.
            - i will be the iterator for the leftPart
            - j will be the iterator for the rightPart
            - k will be the iterator for the merged array
            - Then the while loop uses an if else, to compare that the leftpart is smaller then the right part
            and once thats confirmed it gets added to the new array
            However if its not smaller then right part, the right one gets added to the array, etc.
            Then the actual merged array iterator is incremented to be filled in properly. Until theres nothing left.
            - Now because we may run out of elements in one of the parts and the loop will exit, we created a 2 new while
            loops to add in all remaining numbers (if there are any).
         */
        System.out.println("We are now in the merge method of mergeSort. The two halves will now be merged back together.");
        int leftSize = leftPart.length;
        int rightSize = rightPart.length;

        int i = 0;
        int j = 0;
        int k = 0;

        while(i < leftSize && j < rightSize){
            if(leftPart[i].compareTo(rightPart[j]) <=0 ){
                array[k] = leftPart[i];
                i++;
            } else{
                array[k] = rightPart[j];
                j++;
            }

            k++;
        }

        while(i < leftSize){
            array[k] = leftPart[i];
            i++;
            k++;
        }
        while(j < rightSize){
            array[k] = rightPart[j];
            j++;
            k++;
        }
        System.out.println("The merged sorted results are now being transferred to main.........");
        return array;
    }

}

