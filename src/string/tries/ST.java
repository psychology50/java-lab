package string.tries;

public class ST<K, V> {
    public ST() {
    }

    public void put(K key, V val) {
    }

    public V get(K key) {
        return null;
    }

    public void delete(K key) {
        put(key, null);
    }

    public boolean contains(K key) {
        return get(key) != null;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return 0;
    }

    public Iterable<K> keys() {
        return null;
    }

    public static void main(String[] args) {
        ST<String, Integer> st = new ST<>();
        String[] a = {"she", "sells", "seashells", "by", "the", "sea", "shore", "the", "shells", "she", "sells", "are", "surely", "seashells"};
        for (int i = 0; i < a.length; i++) {
            st.put(a[i], i);
        }

        for (String s : st.keys()) {
            System.out.println(s + " " + st.get(s));
        }
    }
}
