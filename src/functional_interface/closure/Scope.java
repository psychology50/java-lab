package functional_interface.closure;

public class Scope {
    private static String global = "global";

    public static void test() {
//        System.out.println(local); // compile error
    }

    public static void main(String[] args) {
        String global = "local";
        System.out.println(global); // local
    }
}
