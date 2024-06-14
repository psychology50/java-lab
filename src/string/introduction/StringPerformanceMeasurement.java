package string.introduction;

public class StringPerformanceMeasurement {
    public static void main(String[] args) {
        // + 연산자 사용 시
        long startTime = System.currentTimeMillis();

        String str = "";
        for (int i = 0; i < 10000; i++) {
            str += "x";
        }
        long endTime = System.currentTimeMillis();
        System.out.println("String: " + (endTime - startTime) + "ms");

        // StringBuilder 사용 시
        startTime = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 10000; i++) {
            sb.append("x");
        }
        endTime = System.currentTimeMillis();
        System.out.println("StringBuilder: " + (endTime - startTime) + "ms");
    }
}
