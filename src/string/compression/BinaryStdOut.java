package string.compression;

public class BinaryStdOut {
    private static final int EOF = 0x1FF;
    private static int buffer; // 한 바이트를 쓰기 위한 버퍼
    private static int n; // 버퍼에 남아있는 비트 수

    public static void write(boolean x) {
        writeBit(x);
    }

    public static void write(char x) {
        if (x < 0 || x >= 256) throw new IllegalArgumentException("Illegal 8-bit char = " + x);
        for (int i = 0; i < 8; i++) {
            boolean bit = ((x >> (8 - i - 1)) & 1) == 1;
            writeBit(bit);
        }
    }

    public static void write(char x, int R) {
        if (R <= 1 || R >= 256) throw new IllegalArgumentException("Illegal value of R");
        if (x < 0 || x >= R) throw new IllegalArgumentException("Illegal " + R + "-bit char = " + x);

        int logR = 32 - Integer.numberOfLeadingZeros(R); // R의 비트 수
        for (int i = 0; i < logR; i++) {
            boolean bit = ((x >> (logR - i - 1)) & 1) == 1;
            writeBit(bit);
        }
    }

    public static void write(byte x) {
        write((char) x & 0xff);
    }

    public static void write(int x) {
        write((char) x & 0xffff);
    }

    public static void write(long x) {
        for (int i = 0; i < 8; i++) {
            write((char) (x & 0xff));
            x >>>= 8;
        }
    }

    public static void write(String s) {
        for (int i = 0; i < s.length(); i++) {
            write(s.charAt(i));
        }
    }

    public static void flush() {
        writeBuffer();
    }

    public static void close() {
        flush();
    }

    private static void writeBit(boolean bit) {
        buffer <<= 1;
        if (bit) buffer |= 1;
        n++;
        if (n == 8) writeBuffer();
    }

    private static void writeBuffer() {
        if (n == 0) return;
        if (n > 0) buffer <<= (8 - n);
        System.out.write(buffer);
        n = 0;
        buffer = 0;
    }

    public static void main(String[] args) {
        int T = 1010;
        for (int i = 0; i < T; i++) {
            BinaryStdOut.write(i);
        }
        BinaryStdOut.flush();
    }
}
