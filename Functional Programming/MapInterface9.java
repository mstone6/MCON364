package Assignment9;


public interface MapInterface9<K, V> extends Iterable<MapEntry9<K,V>> {
    // Inserts or updates an entry in the map with key k and value v.
    // Returns the previous value associated with key k, or null if no such entry exists.
    V put(K k, V v);

    // Retrieves the value associated with key k.
    // Returns the value if the key exists, otherwise returns null.
    V get(K k);

    // Removes the entry with key k from the map.
    // Returns the value associated with key k if it exists, otherwise returns null.
    // Throws UnsupportedOperationException if not supported.
    V remove(K k);

    // Checks if an entry with key k exists in the map.
    // Returns true if the entry exists, otherwise returns false.
    boolean contains(K k);

    // Checks if the map is empty.
    // Returns true if the map is empty, otherwise returns false.
    boolean isEmpty();

    // Checks if the map is full.
    // Returns true if the map is full, otherwise returns false.
    boolean isFull();

    // Returns the number of entries in the map.
    int size();
}
