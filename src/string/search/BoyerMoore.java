package string.search;

public class BoyerMoore {
    private final String pattern;
    private final int[] right;

    public BoyerMoore(String pattern) {
        this.pattern = pattern;

        int M = pattern.length();
        int R = 256;
        right = new int[R];

        for (int c = 0; c < R; c++) {
            right[c] = -1;
        }

        for (int j = 0; j < M; j++) {
            right[pattern.charAt(j)] = j;
        }
    }

    public int search(String txt) {
        int M = pattern.length();
        int N = txt.length();
        int skip;

        for (int i = 0; i <= N - M; i += skip) {
            skip = 0;
            for (int j = M - 1; j >= 0; j--) {
                if (pattern.charAt(j) != txt.charAt(i + j)) { // 패턴 불일치
                    skip = Math.max(1, j - right[txt.charAt(i + j)]);
                    break;
                }
            }
            if (skip == 0) return i; // 패턴을 찾은 경우 text 위치 반환
        }

        return N; // Not Found
    }
}
