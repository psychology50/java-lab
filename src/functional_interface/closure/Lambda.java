package functional_interface.closure;

import java.util.function.Consumer;

@FunctionalInterface
interface Listener<T> {
    void onClick(T t);
}

@FunctionalInterface
interface Listener2<T> {
    String add(T a, T b);
}

public class Lambda {
    public Listener2<String> supplier() {
        return (a, b) -> a + ", " + b;
    }

    public Consumer<String> consumer() {
        return (s) -> System.out.println(s);
    }

    public static void main(String[] args) {
        Lambda lambda = new Lambda();

        String local = "local";

        Listener<String> listener = (u) -> System.out.println("clicked : " + u + local);
        listener.onClick("button"); // clicked : buttonlocal

        Listener2<String> listener2 = (a, b) -> a + ", " + b;
        System.out.println(listener2.add("hello", "world")); // hello, world

        String res = lambda.supplier().add("hello", "world");
        System.out.println(res); // hello, world

        lambda.consumer().accept("hello, world"); // hello, world
    }
}