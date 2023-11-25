package functional_interface.closure;

import java.util.Comparator;
import java.util.function.IntUnaryOperator;

public class AccumulateClosure {
//    private int value = 0;

//    private IntUnaryOperator createAccumulator() {
//        return (x) -> {
//            value += x;
//            return value;
//        };
//    }
//
//    public static void main(String[] args) {
//        AccumulateClosure closure = new AccumulateClosure();
//
//        IntUnaryOperator accumulator = closure.createAccumulator();
//
//        int res = accumulator.applyAsInt(10);
//        System.out.println(res); // 10
//        System.out.println(closure.value); // 10
//
//        res = accumulator.applyAsInt(20);
//        System.out.println(res); // 30
//        System.out.println(closure.value); // 30
//    }


    public IntUnaryOperator createAccumulator() {
        var value = new int[]{0};
        return (x) -> { value[0] += x; return value[0]; };
    }

    public static void main(String[] args) {
        AccumulateClosure closure = new AccumulateClosure();

        IntUnaryOperator accumulator = closure.createAccumulator();

        int res = accumulator.applyAsInt(10);
        System.out.println(res); // 10

        res = accumulator.applyAsInt(20);
        System.out.println(res); // 30

        Comparator<String> comparator = (a, b) -> a.compareTo(b);
        System.out.println(comparator.compare("hello", "world")); // -15
    }
}
