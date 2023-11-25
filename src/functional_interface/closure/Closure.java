package functional_interface.closure;

import java.util.function.Function;

public class Closure {
    private Integer global = 10;

    private Function<Integer, Integer> foo(Integer b) {
//        b = 10; // compile error
        return (a) -> a + b + global;
    }

    public static void main(String[] args) {
        Closure closure = new Closure();

        Function<Integer, Integer> function = closure.foo(20);
        System.out.println(function.apply(30)); // 60
    }
}
