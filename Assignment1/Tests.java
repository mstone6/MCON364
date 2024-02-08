package Assignment1;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

class Tests {

	@Test
	void testAutomaticSortMethod() {
		//Tests that the randomly generated grades are sorted correctly
		Main.automaticSortMethod();
	}
	
	@Test
	public void testCompareToMethod() {
		//Create 2 student mock objects
		Student firstStudent = new Student("Mushka", 99);
		Student secondStudent = new Student("Sara", 98);
		
		//Test to see if first student grade is higher then second student
		assertTrue(firstStudent.compareTo(secondStudent) > 0);
	}
	
	@Test
	void testQuickSort() {
		//Tests that quick sort sorts correctly
		
		//Lets create a mock array to test
		Integer[] mockUnsortedArray = {78, 69, 26, 98, 25, 100};
		
		//Lets create a mock quicksort object to test
		QuickSort<Integer> mockQS = new QuickSort<>();
		
		//Now lets call the sorting to get a sorted list
		mockUnsortedArray = mockQS.quickSort(mockUnsortedArray, 0, mockUnsortedArray.length - 1);
		
		assertArrayEquals(new Integer[] {25, 26, 69, 78, 98, 100}, mockUnsortedArray);
		
	}
	
	@Test
	void testMergeSort() {
		//Tests that merge sort sorts correctly
		//Lets create a mock array to test
		Integer[] mockUnsortedArray = {78, 69, 26, 98, 25, 100};
		
		//Lets create a mock quicksort object to test
		MergeSort<Integer> mockMS = new MergeSort<>();
		
		//Now lets call the sorting to get a sorted list
		mockUnsortedArray = mockMS.mergeSort(mockUnsortedArray);
		
		assertArrayEquals(new Integer[] {25, 26, 69, 78, 98, 100}, mockUnsortedArray);
		
	}
}
