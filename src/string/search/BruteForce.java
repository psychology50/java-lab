package string.search;

public class BruteForce {
    public static int search(String txt, String pat) {
        int N = txt.length();
        int M = pat.length();
        for (int i = 0; i <= N - M; i++) {
            int j;
            for (j = 0; j < M; j++) {
                if (txt.charAt(i + j) != pat.charAt(j)) {
                    break;
                }
            }
            if (j == M) {
                return i; // 패턴을 찾은 경우 text 위치 반환
            }
        }
        return N; // Not Found
    }

    public static void main(String[] args) {
        String txt = "ABACADABRAC";
        String pat = "ABRA";
        System.out.println(search(txt, pat));
    }
}
