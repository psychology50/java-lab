package string.compression;

import string.tries.StringST;
import string.tries.TST;

public class LZW {
    private static final int R = 256; // ASCII 문자
    private static final int W = 12; // 코드 폭
    private static final int L = 4096; // 코드 수 = 2^W

    public void compress() {
        String input = BinaryStdIn.readString(); // 압축할 문자열들을 한 번에 읽음
        StringST<Integer> st = new TST<>(); // TST를 이용해 심볼 테이블 구성

        for (int i = 0; i < 256; i++) {
            st.put("" + (char) i, i); // ASCII 문자를 심볼 테이블에 저장
        }
        int code = R + 1; // R = EOF를 표시하기 위한 코드

        while (input.length() > 0) {
            String s = st.longestPrefixOf(input); // 입력 문자열에서 가장 긴 prefix 찾기
            BinaryStdOut.write(st.get(s), W); // s의 encoding 출력
            int t = s.length(); // prefix의 길이
            if (t < input.length() && code < L) { // 심볼 테이블이 꽉 차지 않았을 때
                st.put(input.substring(0, t + 1), code++); // prefix + next char를 심볼 테이블에 추가
            }
            input = input.substring(t); // prefix를 제외한 나머지 문자열
        }
        BinaryStdOut.write(R, W); // EOF 출력
        BinaryStdOut.close();
    }

    public void expand() {
        String[] st = new String[L]; // 심볼 테이블
        int i; // 다음 사용할 코드
        for (i = 0; i < R; i++) {
            st[i] = "" + (char) i; // ASCII 문자 초기화
        }
        st[i++] = ""; // EOF

        int codeword = BinaryStdIn.readInt(W); // 첫 번째 코드
        String val = st[codeword]; // 코드에 대한 문자열
        while (true) {
            BinaryStdOut.write(val); // 출력
            codeword = BinaryStdIn.readInt(W); // 다음 코드
            if (codeword == R) { // EOF
                break;
            }
            String s = st[codeword]; // 다음 문자열
            if (i == codeword) { // special case hack
                s = val + val.charAt(0); // special case hack
            }
            if (i < L) {
                st[i++] = val + s.charAt(0); // 심볼 테이블에 추가
            }
            val = s; // 다음 문자열
        }
        BinaryStdOut.close();
    }
}
