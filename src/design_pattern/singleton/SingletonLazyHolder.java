package design_pattern.singleton;

public class SingletonLazyHolder {
    private SingletonLazyHolder() {}

    private static class SingleInstanceHolder {
        private static final SingletonLazyHolder INSTANCE = new SingletonLazyHolder();
    }

    public static SingletonLazyHolder getInstance() {
        return SingleInstanceHolder.INSTANCE;
    }
}
