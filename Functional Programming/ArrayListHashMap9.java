package Assignment9;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.ToIntFunction;

public class ArrayListHashMap9<K, V> implements MapInterface9<K, V> {
    //Now creating a list of all lists to store all the key value pairs
    protected List<List<MapEntry9<K, V>>> buckets;

    // Default number of buckets
    private static final int DEFAULT_CAPACITY = 16;

    public ArrayListHashMap9() {
        this(DEFAULT_CAPACITY);
    } //This is our constructor but with a default capacity

    public ArrayListHashMap9(int initCapacity) {
        //This is our constructor with the specific initial capacity
        if (initCapacity <= 0)
            throw new IllegalArgumentException("Invalid initial capacity: " + initCapacity);

        //Now intializing the buckets with the specific initial capacity
        buckets = new ArrayList<>(initCapacity);
        for (int i = 0; i < initCapacity; i++) {
            //Now adding empty lists to the buckets to get filled
            buckets.add(new ArrayList<>());
        }
    }

    @Override
    public V put(K k, V v) {
        //This method adds a key value pair to the hash map
        if (k == null)
            throw new IllegalArgumentException("Null keys cannot be used in Maps.");

        //Now calculating the bucket index for the key
        int bucketIndex = getBucketIndex(k);

        //Now getting the bucket for the corresponding index
        List<MapEntry9<K, V>> bucket = buckets.get(bucketIndex);

        //Lets iterate through all entries in the bucket
        for (MapEntry9<K, V> entry : bucket) {
            if (entry.getKey().equals(k)) {
                //If the key is there we update the value
                V oldValue = entry.getValue();
                entry.setValue(v);
                return oldValue;
            }
        }

        //If we got here it means the key doesnt exist
        //Now adding a new entry
        bucket.add(new MapEntry9<>(k, v));
        return null;
    }

    @Override
    public V get(K k) {
        //This method gets the value assigned to a specific key
        if (k == null)
            throw new IllegalArgumentException("Null keys cannot be used in Maps.");

        //Now calculating the bucket index for the key
        int bucketIndex = getBucketIndex(k);

        //Lets get the bucket that goes with that index
        List<MapEntry9<K, V>> bucket = buckets.get(bucketIndex);

        for (MapEntry9<K, V> entry : bucket) {
            //Now iterating through entries in the bucket
            if (entry.getKey().equals(k)) {
                //The key exists so we are returning the value
                return entry.getValue();
            }
        }
        //The key doesnt exist - so returning null
        return null;
    }

    @Override
    public V remove(K k) { // Removes the entry associated with the specified key
        if (k == null)
            throw new IllegalArgumentException("Null keys cannot be used in Maps.");

        int bucketIndex = getBucketIndex(k); // Calculates the bucket index for the key
        List<MapEntry9<K, V>> bucket = buckets.get(bucketIndex); // Gets the bucket corresponding to the index

        Iterator<MapEntry9<K, V>> iterator = bucket.iterator(); // Initializes iterator for the bucket
        while (iterator.hasNext()) { // Iterates through entries in the bucket
            MapEntry9<K, V> entry = iterator.next();
            if (entry.getKey().equals(k)) { // If key exists, remove the entry
                iterator.remove();
                return entry.getValue();
            }
        }

        return null; // If key does not exist, return null
    }

    @Override
    public boolean contains(K k) { // Checks if the map contains the specified key
        if (k == null)
            throw new IllegalArgumentException("Null keys cannot be used in Maps.");

        int bucketIndex = getBucketIndex(k); // Calculates the bucket index for the key
        List<MapEntry9<K, V>> bucket = buckets.get(bucketIndex); // Gets the bucket corresponding to the index

        for (MapEntry9<K, V> entry : bucket) { // Iterates through entries in the bucket
            if (entry.getKey().equals(k)) { // If key exists, return true
                return true;
            }
        }

        return false; // If key does not exist, return false
    }

    @Override
    public boolean isEmpty() { // Checks if the map is empty
        for (List<MapEntry9<K, V>> bucket : buckets) { // Iterates through all buckets
            if (!bucket.isEmpty()) { // If any bucket is not empty, return false
                return false;
            }
        }
        return true; // If all buckets are empty, return true
    }

    @Override
    public boolean isFull() {
        // This method Checks if the map is full
        return false; // The map can never be full with dynamic resizing
    }

    @Override
    public int size() { // Returns the number of entries in the map
        int size = 0;
        for (List<MapEntry9<K, V>> bucket : buckets) {
            // Now Iterating through all buckets
            size += bucket.size(); // Adds the size of each bucket to the total size
        }
        return size; // Returns the total size
    }

    @Override
    public Iterator<MapEntry9<K, V>> iterator() {
        // This method returns an iterator over the entries in the map
        List<MapEntry9<K, V>> entries = new ArrayList<>(); // Initializes a list to store all entries
        for (List<MapEntry9<K, V>> bucket : buckets) {
            // Now Iterating through all buckets
            entries.addAll(bucket); // Adds all entries in each bucket to the list
        }
        return entries.iterator(); // Returns an iterator over the list of entries
    }

    // Helper method to calculate the bucket index for a given key
    private int getBucketIndex(K key) {
        int hashCode = key.hashCode(); // Calculates the hash code for the key
        return Math.abs(hashCode) % buckets.size(); // Maps the hash code to a bucket index
    }



    public V getOrDefault(K key, V defaultValue) {
        // This method gets the value associated with the specified key, or a default value if key is not found
        if (key == null)
            throw new IllegalArgumentException("Null keys cannot be used in Maps.");

        int bucketIndex = getBucketIndex(key); // Calculates the bucket index for the key
        List<MapEntry9<K, V>> bucket = buckets.get(bucketIndex); // Gets the bucket corresponding to the index

        for (MapEntry9<K, V> entry : bucket) {
            // Now Iterating through entries in the bucket
            if (entry.getKey().equals(key)) {
                // If key exists, return the value
                return entry.getValue();
            }
        }

        return defaultValue; // If key does not exist, return the default value


    }

    public int getLengthOfList(K key) {
        //This method gets the length of the list
        if (key == null)
            throw new IllegalArgumentException("Null keys cannot be used in Maps.");

        int bucketIndex = getBucketIndex(key);
        List<MapEntry9<K, V>> bucket = buckets.get(bucketIndex);

        for (MapEntry9<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                return bucket.size();
            }
        }

        return 0; // Key not found, so the length of the list is 0
    }
    public boolean containsKey(K key) {
        //This method checks if the key is used in the map and has a value
        if (key == null) {
            throw new IllegalArgumentException("Null keys cannot be used in Maps.");
        }
        int bucketIndex = getBucketIndex(key);
        List<MapEntry9<K, V>> bucket = buckets.get(bucketIndex);
        for (MapEntry9<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

}
