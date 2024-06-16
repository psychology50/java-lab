package string.compression;

import java.io.BufferedInputStream;

public class BinaryStdIn {
    static BufferedInputStream in = new BufferedInputStream(System.in);
    static final int EOF = -1;
    static int buffer; // 한 바이트를 읽어들이는 버퍼
    static int n; // 버퍼에 남아있는 비트 수

    public static boolean isEmpty() {
        return buffer == EOF;
    }

    public static boolean readBoolean() {
        if (isEmpty()) throw new RuntimeException("Reading from empty input stream");
        n--;
        boolean bit = ((buffer >> n) & 1) == 1; // 버퍼의 가장 왼쪽 비트부터 읽어들인다.
        if (n == 0) fillBuffer(); // 버퍼가 비었으면 다시 채워넣는다.
        return bit;
    }

    public static char readChar() {
        if (isEmpty()) throw new RuntimeException("Reading from empty input stream");

        // 8비트를 읽어들여 16비트로 확장한다.
        char x = (char) buffer;
        fillBuffer();

        if (isEmpty()) throw new RuntimeException("Reading from empty input stream");

        // 8비트를 읽어들여 16비트로 확장한다.
        x = (char) ((x << 8) | buffer);
        fillBuffer();

        return x;
    }

    public static char readChar(int R) {
        if (R <= 1 || R >= 256) throw new IllegalArgumentException("Illegal value of R");

        if (isEmpty()) throw new RuntimeException("Reading from empty input stream");

        int logR = 32 - Integer.numberOfLeadingZeros(R); // R의 비트 수
        int bits = 0;
        for (int i = 0; i < logR; i++) {
            bits = (bits << 1) | (readBoolean() ? 1 : 0);
        }

        return (char) bits;
    }

    public static byte readByte() {
        char x = readChar();
        return (byte) (x & 0xff);
    }

    public static int readInt() {
        int x = 0;
        for (int i = 0; i < 4; i++) {
            char c = readChar();
            x = (x << 8) | c;
        }

        return x;
    }

    public static int readInt(int R) {
        if (R <= 1 || R >= 256) throw new IllegalArgumentException("Illegal value of R");

        int logR = 32 - Integer.numberOfLeadingZeros(R); // R의 비트 수
        int bits = 0;
        for (int i = 0; i < logR; i++) {
            bits = (bits << 1) | (readBoolean() ? 1 : 0);
        }

        return bits;
    }

    public static long readLong() {
        long x = 0;
        for (int i = 0; i < 8; i++) {
            char c = readChar();
            x = (x << 8) | c;
        }

        return x;
    }

    public static String readString() {
        if (isEmpty()) throw new RuntimeException("Reading from empty input stream");

        StringBuilder sb = new StringBuilder();
        while (!isEmpty()) {
            sb.append(readChar());
        }

        return sb.toString();
    }

    private static void fillBuffer() {
        try {
            buffer = in.read();
            n = 8;
        } catch (Exception e) {
            System.out.println("EOF");
            buffer = EOF;
            n = -1;
        }
    }
}
