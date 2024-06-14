package string.tries;

public class TST<V> implements StringST<V> {
    private Node<V> root;
    private int N;

    @Override
    public V get(String key) {
        Node<V> x = get(root, key, 0);

        if (x == null) {
            return null;
        }

        return x.val;
    }

    @Override
    public void put(String key, V val) {
        if (!contains(key)) {
            N++;
        }

        root = put(root, key, val, 0);
    }

    @Override
    public int size() {
        return N;
    }

    @Override
    public boolean contains(String key) {
        return get(key) != null;
    }

    private Node<V> get(Node<V> x, String key, int d) {
        if (x == null) {
            return null;
        }

        if (key.length() == 0) {
            return null;
        }

        char c = key.charAt(d);

        if (c < x.c) {
            return get(x.left, key, d);
        } else if (c > x.c) {
            return get(x.right, key, d);
        } else if (d < key.length() - 1) {
            return get(x.mid, key, d + 1);
        } else {
            return x;
        }
    }

    private Node<V> put(Node<V> x, String key, V val, int d) {
        char c = key.charAt(d);

        if (x == null) {
            x = new Node<>();
            x.c = c;
        }

        if (c < x.c) {
            x.left = put(x.left, key, val, d);
        } else if (c > x.c) {
            x.right = put(x.right, key, val, d);
        } else if (d < key.length() - 1) {
            x.mid = put(x.mid, key, val, d + 1);
        } else {
            x.val = val;
        }

        return x;
    }

    private static class Node<V> {
        char c;
        V val;
        Node<V> left, mid, right;
    }
}
