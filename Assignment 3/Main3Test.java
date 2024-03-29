package Assignment3;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


 //Unit tests for the ArrayListHashMap class.
class Main3Test {


    @Test
    public void testTotalWordsCalculation() {
        //Tests the calculation of the total words
        // Create an ArrayListHashMap and put some key-value pairs
        ArrayListHashMap<String, Integer> myMap = new ArrayListHashMap<>();
        myMap.put("word1", 1);
        myMap.put("word2", 3);
        myMap.put("word3", 2);

        // Check if the size of the map matches the expected total number of words
        assertEquals(3, myMap.size());
    }


    @Test
    public void testSizeOfArray() {
        //This test tests the resizing of the array when capacity is given
        // Create an ArrayListHashMap with default capacity
        ArrayListHashMap<String, Integer> myMap = new ArrayListHashMap<>();
        // Check if the initial size of the buckets array matches the default capacity (16)
        assertEquals(16, myMap.buckets.size());

        // Create an ArrayListHashMap with a custom capacity
        myMap = new ArrayListHashMap<>(32);
        // Check if the size of the buckets array matches the custom capacity (32)
        assertEquals(32, myMap.buckets.size());
    }


    @Test
    public void testNumberOfUnusedArraySlots() {
        //Tests the calculations of the number of unsued array slots
        // Create an ArrayListHashMap
        ArrayListHashMap<String, Integer> myMap = new ArrayListHashMap<>();
        // Check if the initial size of the buckets array matches the default capacity (16)
        assertEquals(16, myMap.buckets.size());

        // Fill some slots in the map
        myMap.put("word1", 1);
        myMap.put("word2", 3);

        // Check if the number of unused slots matches the expected count (16 - number of filled slots)
        assertEquals(14, countUnusedArraySlots(myMap));
    }

     //Helper method to count the number of unused array slots.
    private int countUnusedArraySlots(ArrayListHashMap<String, Integer> myMap) {
        int unusedSlots = 0;
        // Iterate through each bucket in the ArrayListHashMap
        for (List<MapEntry<String, Integer>> bucket : myMap.buckets) {
            // If a bucket is empty, increment the count of unused slots
            if (bucket.isEmpty()) {
                unusedSlots++;
            }
        }
        return unusedSlots;
    }


    @Test
    public void testPutAndGet() {
        //Lets test the put and get parts of the map
        // Create an ArrayListHashMap
        ArrayListHashMap<String, Integer> myMap = new ArrayListHashMap<>();

        // Put some key-value pairs into the map
        myMap.put("key1", 1);
        myMap.put("key2", 2);
        myMap.put("key3", 3);

        // Check if the size of the map matches the number of inserted elements
        assertEquals(3, myMap.size());

        // Check if values can be retrieved correctly using their corresponding keys
        assertEquals(1, myMap.get("key1").intValue());
        assertEquals(2, myMap.get("key2").intValue());
        assertEquals(3, myMap.get("key3").intValue());

        // Check for a non-existing key
        assertNull(myMap.get("non_existing_key"));
    }


    @Test
    public void testRemove() {
        //Lets test the remove aspect of the map
        // Create an ArrayListHashMap and put some key-value pairs
        ArrayListHashMap<String, Integer> myMap = new ArrayListHashMap<>();
        myMap.put("key1", 1);
        myMap.put("key2", 2);
        myMap.put("key3", 3);

        // Remove an existing key
        myMap.remove("key1");
        // Check if the removed key is no longer present in the map
        assertNull(myMap.get("key1"));
        // Check if the size of the map is updated accordingly
        assertEquals(2, myMap.size());

        // Remove a non-existing key
        assertNull(myMap.remove("non_existing_key"));
    }


    @Test
    public void testContainsKey() {
        // Create an ArrayListHashMap and put some key-value pairs
        ArrayListHashMap<String, Integer> myMap = new ArrayListHashMap<>();
        myMap.put("key1", 1);
        myMap.put("key2", 2);
        myMap.put("key3", 3);

        // Check if the map correctly identifies existing keys
        assertTrue(myMap.containsKey("key1"));
        assertTrue(myMap.containsKey("key2"));
        assertTrue(myMap.containsKey("key3"));

        // Check for a non-existing key
        assertFalse(myMap.containsKey("non_existing_key"));
    }
}
