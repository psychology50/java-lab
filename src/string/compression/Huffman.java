package string.compression;

import java.util.PriorityQueue;

public class Huffman {
    private static final int R = 256; // ASCII 문자

    public void compress() {
        String s = BinaryStdIn.readString(); // 입력 문자열을 s에 저장
        char[] input = s.toCharArray(); // 문자열을 문자 배열로 변환 후, 한 문자씩 처리
        int[] freq = new int[R]; // 각 문자에 대한 발생 빈도수를 배열에 저장

        for (int i = 0; i < input.length; i++) {
            freq[input[i]]++;
        }

        Node root = buildTree(freq); // 허프만 트리 생성
        String[] st = new String[R]; // 각 문자에 대한 이진 코드를 저장할 배열
        buildCode(st, root, ""); // 허프만 코드 생성
        writeTrie(root); // 허프만 트리를 출력

        BinaryStdOut.write(input.length); // 원본 문자열의 길이 출력

        for (char c : input) {
            String code = st[c]; // 문자에 대한 허프만 코드
            for (int j = 0; j < code.length(); j++) {
                // 0이면 false 출력
                BinaryStdOut.write(code.charAt(j) == '1'); // 1이면 true 출력
            }
        }
        BinaryStdOut.close();
    }

    public void expand() {
        Node root = readTree(); // 허프만 트리를 읽음
        int N = BinaryStdIn.readInt(); // 원본 문자열의 길이를 읽음

        for (int i = 0; i < N; i++) { // 원본 문자열의 길이만큼 반복
            Node x = root; // 이진 트리를 루트부터 순회하면서
            while (!x.isLeaf()) {
                if (BinaryStdIn.readBoolean()) x = x.right; // true이면 오른쪽 자식으로 이동
                else x = x.left; // false이면 왼쪽 자식으로 이동
            }
            BinaryStdOut.write(x.ch, 8); // 리프 노드의 문자 출력
        }
        BinaryStdOut.close();
    }

    private Node readTree() {
        if (BinaryStdIn.readBoolean()) {
            return new Node(BinaryStdIn.readChar(), 0, null, null); // 리프 노드
        }
        return new Node('\0', 0, readTree(), readTree()); // 내부 노드
    }

    private Node buildTree(int[] freq) {
        // Node.freq를 기준으로 오름차순으로 정렬하는 우선순위 큐
        PriorityQueue<Node> pq = new PriorityQueue<>();

        for (char c = 0; c < R; c++) {
            if (freq[c] > 0) {
                pq.add(new Node(c, freq[c], null, null)); // 모든 노드가 leaf
            }
        }

        while (pq.size() > 1) {
            Node left = pq.remove(); // freq가 가장 작은 두 개의 노드 삭제
            Node right = pq.remove();

            // 두 개의 노드를 자식으로 하는 부모 노드 생성 후, 우선순위 큐에 추가
            Node parent = new Node('\0', left.freq + right.freq, left, right);
            pq.add(parent);
        }

        return pq.remove(); // 우선순위 큐에 하나의 노드(루트 노드)만 남았을 때, 해당 노드 반환
    }

    private void buildCode(String[] st, Node x, String s) { // 코드 구축
        if (!x.isLeaf()) {
            buildCode(st, x.left, s + '0'); // 왼쪽 자식은 prefix 0
            buildCode(st, x.right, s + '1'); // 오른쪽 자식은 prefix 1
        } else {
            st[x.ch] = s; // 리프 노드에서 테이블에 코드 등록
        }
    }

    private void writeTrie(Node x) { // 트리 구조를 출력
        if (x.isLeaf()) {
            BinaryStdOut.write(true); // 리프 노드는 접두사가 1
            BinaryStdOut.write(x.ch, 8); // 리프 노드의 문자(8비트) 출력
            return;
        }

        BinaryStdOut.write(false); // non-leaf 노드는 접두사가 0
        writeTrie(x.left); // preorder
        writeTrie(x.right);
    }

    private class Node implements Comparable<Node> {
        private char ch;
        private int freq;
        private final Node left, right;

        public Node(char ch, int freq, Node left, Node right) {
            this.ch = ch;
            this.freq = freq;
            this.left = left;
            this.right = right;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }

        @Override
        public int compareTo(Node that) {
            return this.freq - that.freq;
        }
    }
}
