package string.introduction;

public class MSD {
    private static final int M = 15; // 삽입 정렬을 위한 임계값
    private static final int R = 256; // 기수
    private static String[] aux;

    public static void sort(String[] a) {
        int N = a.length;
        aux = new String[N];
        sort(a, 0, N-1, 0);
    }

    private static void sort(String[] a, int lo, int hi, int d) {
        if (hi <= lo + M) { // cutoff : M = 15, 삽입 정렬이 더 빠름
            Insertion.sort(a, lo, hi, d);
            return;
        }
        System.out.println("lo: " + lo + ", hi: " + hi + ", d: " + d);

        int[] count = new int[R+2]; // R: 문자열의 길이, R+2: 앞에서 저장 & EOS 공간 필요
        for (int i = lo; i <= hi; i++) {
            count[charAt(a[i], d) + 2]++; // count[1]: EOS(End of String) 공간
        }

        for (int r = 0; r < R+1; r++) {
            count[r+1] += count[r];
        }

        for (int i = lo; i <= hi; i++) {
            aux[count[charAt(a[i], d) + 1]++] = a[i];
        }

        for (int i = lo; i <= hi; i++) {
            a[i] = aux[i - lo]; // aux는 전역 배열로 한 번만 할당
        }

//        for (int i : count) {
//            System.out.print(i + " ");
//        }
//        System.out.println("-----");

        for (int r = 0; r < R; r++) {
            sort(a, lo + count[r], lo + count[r+1] - 1, d+1); // R개의 부분 배열에 대해 정렬
        }
    }

    private static int charAt(String s, int d) {
        if (d < s.length()) {
            return s.charAt(d);
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {
        String[] a = {"she", "sells", "seashells", "by", "the", "sea", "shore", "the", "shells", "she", "sells", "are", "surely", "seashells"};
        sort(a);
        for (String s : a) {
            System.out.println(s);
        }
    }
}

class Insertion {
    public static void sort(String[] a, int lo, int hi, int d) {
        for (int i = lo; i <= hi; i++) {
            for (int j = i; j > lo && less(a[j], a[j-1], d); j--) {
                exch(a, j, j-1);
            }
        }
    }

    private static boolean less(String v, String w, int d) {
        return v.substring(d).compareTo(w.substring(d)) < 0;
    }

    private static void exch(String[] a, int i, int j) {
        String temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
