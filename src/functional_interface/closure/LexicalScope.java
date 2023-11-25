package functional_interface.closure;

public class LexicalScope {
    private int x = 0;

    private void foo() {
        System.out.println(x);
    }

    private void bar() {
        int x = 10;
        foo();
    }

    public static void main(String[] args) {
        LexicalScope scope = new LexicalScope();
        scope.foo();
        scope.bar();
    }
}
