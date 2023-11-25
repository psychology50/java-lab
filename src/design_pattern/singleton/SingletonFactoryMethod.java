package design_pattern.singleton;

public class SingletonFactoryMethod {
    private static final SingletonFactoryMethod INSTANCE = new SingletonFactoryMethod();

    private SingletonFactoryMethod() {
        if (INSTANCE != null) { // reflection을 통한 생성 방지
            throw new IllegalStateException("Already instantiated");
        }
    }

    public static SingletonFactoryMethod getInstance() {
        return INSTANCE;
    }

    // 싱글턴임을 보장해주는 readResolve 메서드
    private Object readResolve() {
        return INSTANCE;
    }
}
