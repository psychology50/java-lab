package string.search;

import java.math.BigInteger;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RabinKarp {
    private final String pattern;
    private final long patternHash;
    private final int M, R = 256;
    private final long Q;
    private final long RM; // R^(M-1) % Q

    public RabinKarp(String pattern) {
        this.pattern = pattern;
        this.M = pattern.length();
        this.Q = BigInteger.probablePrime(31, ThreadLocalRandom.current()).longValue();

        long tmp = 1;
        for (int i = 1; i <= M-1; i++) {
            tmp = (R * tmp) % Q;
        }
        RM = tmp;

        patternHash = hash(pattern, M);
    }

    public int search(String txt) {
        int N = txt.length();
        if (N < M) return N;

        long txtHash = hash(txt, M);

        if (patternHash == txtHash && isSame(txt, 0)) return 0;

        for (int i = M; i < N; i++) {
            txtHash = (txtHash + Q - RM * txt.charAt(i - M) % Q) % Q;
            txtHash = (txtHash * R + txt.charAt(i)) % Q;

            if (patternHash == txtHash && isSame(txt, i - M + 1)) return i - M + 1;
        }

        return N; // Not Found
    }

    private long hash(String key, int M) { // key[0..M-1]의 해시값 계산
        long h = 0;
        for (int j = 0; j < M; j++) {
            h = (R * h + key.charAt(j)) % Q;
        }

        return h;
    }

    private boolean isSame(String txt, int i) { // Las Vegas 방식
        for (int j = 0; j < M; j++) {
            if (pattern.charAt(j) != txt.charAt(i + j)) return false;
        }
        return true; // Monte Carlo 방식인 경우, true 반환
    }
}
