package string.search;

public class KMP {
    private final int R; // 기수
    private final String pattern; // 패턴
    private final int[][] dfa; // DFA

    public KMP(String pattern) {
        this.R = 256;
        this.pattern = pattern;

        int M = pattern.length();
        dfa = new int[R][M];
        dfa[pattern.charAt(0)][0] = 1; // 초기 상태의 match condition

        for (int X=0, j=1; j<M; ++j) {
            for (int c=0; c<R; ++c) {
                dfa[c][j] = dfa[c][X]; // 이전 X 상태의 모든 이동 규칙들을 복사
            }
            dfa[pattern.charAt(j)][j] = j+1; // match일 경우 다음으로 이동
            X = dfa[pattern.charAt(j)][X]; // 현재 상태가 이전의 어디까지 동일한가?
        }
    }

    public int search(String txt) {
        int i=0, j=0, N = txt.length(), M = pattern.length();

        for (i=0; i<N && j<M; ++i) {
            j = dfa[txt.charAt(i)][j]; // no backup
        }

        if (j==M) return i-M; // pattern이 시작되는 text 위치
        else return N; // not found
    }
}
