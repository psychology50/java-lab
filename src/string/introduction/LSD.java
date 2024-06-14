package string.introduction;

public class LSD {
    public static void main(String[] args) {

    }

    private static void sort(String[] a, int W) { // 모든 문자 길이가 W
        int N = a.length;
        int R = 256; // 기수 = R
        String[] aux = new String[N];

        for (int d = W-1; d >= 0; d--) { // d: 문자열의 d번째 문자, LSD이므로 뒤에서부터
            int[] count = new int[R];

            for (String s : a) {
                count[s.charAt(d)]++; // d번째 문자의 빈도수 계산
            }

            for (int r = 1; r < R; r++) {
                count[r] += count[r-1]; // 누적 빈도수 계산
            }

            for (int i = N - 1; i >= 0; i--) {
                aux[--count[a[i].charAt(d)]] = a[i]; // 뒤 문자열부터 뒤에서 저장 : stable
            }

            for (int i = 0; i < N; i++) {
                a[i] = aux[i]; // 정렬된 배열로 복사
            }
        }
    }
}
