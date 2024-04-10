package Assignment9;

    public class MapEntry9<K, V> {
        private final K key;
        private V value;

        public MapEntry9(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Key: " + key + ", Value: " + value;
        }
    }



