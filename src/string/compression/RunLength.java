package string.compression;

public class RunLength {
    public static void compress() {
        char count = 0;
        boolean old = false; // 0부터 압축
        while (!BinaryStdIn.isEmpty()) {
            boolean bit = BinaryStdIn.readBoolean();
            if (bit != old) { // 새로운 run 시작
                BinaryStdOut.write(count);
                count = 0;
                old = !old; // 현재 run을 기억
            }
            else {
                if (count == 255) { // run 길이 제한
                    BinaryStdOut.write(count);
                    count = 0; // 길이 0인 run 출력
                    BinaryStdOut.write(count);
                }
            }
        }
        BinaryStdOut.write(count);
        BinaryStdOut.close();
    }

    public static void expand() {
        boolean bit = false; // 0부터 시작
        while (!BinaryStdIn.isEmpty()) {
            char count = BinaryStdIn.readChar(); // run 길이가 0~255이므로 8비트씩 read
            for (int i = 0; i < count; i++) {
                BinaryStdOut.write(bit);
            }
            bit = !bit; // 0과 1을 toggling
        }
        BinaryStdOut.close();
    }
}
