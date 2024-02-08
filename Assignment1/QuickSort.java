
package Assignment1;
/* Implement QuickSort and MergeSort: Implement generic versions of QuickSort and MergeSort that can sort any list
        of objects that implement the Comparable Interface The sorting algorithms should not modify the original list,
        but instead return a new sorted list.
*/
public class QuickSort<T extends Comparable<T>>{
    public void swap(T[] array, int i, int j){
        System.out.println("We are now in the swap method in quick SOrt, this method swaps two elements in the array.");
        T placeHolder = array[i];
        array[i] = array[j];
        array[j] = placeHolder;
    }

    public int partition(T[] array, int low, int high){
        System.out.println("We are now in the partition method in quick sort." +
                "\n This method chooses a pivot and makes sure that all smaller then it are on the left and larger then it are on the right.");
        T pivot = array[high];

        int i = (low -1);
        for(int j = low; j <= high - 1; j++){
            if(array[j].compareTo(pivot)<0){
                i++;
                swap(array, i, j);
            }
        }
        System.out.println("This method calls swap recursively until all elements are in their place.");
        swap(array, i + 1, high);
        return(i + 1);
    }

     public T[] quickSort(T[]array, int low, int high){
        System.out.println("We are now in the quicksort method in quicksort." +
                "\n This method calls partition recursively until all elements are on the correct side of the pivot and swapped properly.");
        if(low<high){
            int pivotIndex = partition(array, low, high);


            quickSort(array, low, pivotIndex-1);
            quickSort(array, pivotIndex + 1, high);
        }
        System.out.println("The quick sorted results are now being transferred to main.........");
        return array;
    }



}
