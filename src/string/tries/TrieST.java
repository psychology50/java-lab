package string.tries;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class TrieST<V> implements StringST<V> {
    private static int R = 256; // 기본적으로 ASCII 문자셋을 사용한다.
    private Node root;
    private int N; // trie 안의 key 개수. size() 함수에서 사용하기 위함.

    private static class Node { // Node에 문자는 저장하지 않는다.
        private Object val; // val이 null이 아니면, 그곳까지의 key가 존재한다.
        private Node[] next = new Node[R];
    }

    @Override
    public void put(String key, V val) {
        if (val == null) {
            delete(key);
        } else {
            root = put(root, key, val, 0);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public V get(String key) {
        Node x = get(root, key, 0);

        if (x == null) {
            return null;
        }

        return (V) x.val;
    }

    @Override
    public void delete(String key) {
        root = delete(root, key, 0);
    }

    @Override
    public boolean contains(String key) {
        return get(key) != null;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public Iterable<String> keysWithPrefix(String pre) {
        Queue<String> q = new LinkedList<>();
        Node x = get(root, pre, 0);
        collect(x, new StringBuilder(pre), q);
        return q;
    }

    @Override
    public String longestPrefixOf(String s) {
        int length = search(root, s, 0, 0);
        return s.substring(0, length);
    }

    @Override
    public Iterable<String> keysThatMatch(String target) {
        Queue<String> q = new LinkedList<>();
        collect(root, new StringBuilder(), target, q);
        return q;
    }

    @Override
    public int size() {
        return N;
    }

    @Override
    public Iterable<String> keys() {
        return keysWithPrefix("");
    }

    private Node put(Node x, String key, V val, int d) {
        if (x == null) {
            x = new Node(); // null link인 경우 새로운 노드를 생성한다.
        }

        if (d == key.length()) {
            if (x.val == null) {
                N++; // 새로운 key를 추가하는 경우에만 N을 증가시킨다.
            }

            x.val = val; // key의 끝에 도달하면 val을 저장한다. (else면 단순 값 변경)
            return x;
        }

        char c = key.charAt(d); // d번째 문자를 이용해 다음으로 이동한다.
        x.next[c] = put(x.next[c], key, val, d+1);
        return x;
    }

    private Node get(Node x, String key, int d) {
        if (x == null) {
            return null;
        }

        if (d == key.length()) {
            return x;
        }

        char c = key.charAt(d); // d번째 문자를 이용해 다음으로 이동한다.
        return get(x.next[c], key, d+1);
    }

    private Node delete(Node x, String key, int d) {
        if (x == null) {
            return null;
        }

        if (d == key.length()) { // 삭제할 노드 도착
            if (x.val != null) {
                N--; // key를 삭제하는 경우에만 N을 감소시킨다.
            }

            x.val = null; // key의 끝에 도달하면 val을 null로 설정한다.
        } else { // 삭제할 노드 도착하지 않은 경우 탐색
            char c = key.charAt(d); // d번째 문자를 이용해 다음으로 이동한다.
            x.next[c] = delete(x.next[c], key, d+1);
        }

        // link 삭제 여부 판단 (현재 노드의 val이 null이고, 자식이 없는 node인 경우)
        if (x.val != null) {
            return x; // x.val이 null이 아니면, x를 반환한다. (삭제 불가)
        }

        for (int c = 0; c < R; c++) {
            if (x.next[c] != null) {
                return x; // null이 아닌 자식이 존재하면 x를 반환한다. (삭제 불가)
            }
        }

        return null; // x.val과 x.next[r]이 모두 null이면, null을 반환한다. (삭제)
    }

    private void collect(Node x, StringBuilder pre, String target, Queue<String> q) {
        if (x == null) {
            return;
        }

        int d = pre.length();
        if (d == target.length() && x.val != null) {
            q.add(pre.toString());
        }

        if (d == target.length()) {
            return;
        }

        char c = target.charAt(d);
        if (c == '.') {
            for (char ch = 0; ch < R; ch++) {
                pre.append(ch);
                collect(x.next[ch], pre, target, q);
                pre.deleteCharAt(pre.length() - 1);
            }
        } else {
            pre.append(c);
            collect(x.next[c], pre, target, q);
            pre.deleteCharAt(pre.length() - 1);
        }
    }

    private void collect(Node x, StringBuilder pre, Queue<String> q) {
        if (x == null) {
            return;
        }

        if (x.val != null) {
            q.add(pre.toString());
        }

        for (char c = 0; c < R; c++) {
            if (x.next[c] == null) {
                continue;
            }

            pre.append(c);
            collect(x.next[c], pre, q);
            pre.deleteCharAt(pre.length() - 1);
        }
    }

    private int search(Node x, String s, int d, int length) {
        if (x == null) {
            return length;
        }

        if (x.val != null) {
            length = d;
        }

        if (d == s.length()) {
            return length;
        }

        char c = s.charAt(d);
        return search(x.next[c], s, d+1, length);
    }
}
