package design_pattern.singleton;

public class SingletonDoubleCheck {
    private static volatile SingletonDoubleCheck INSTANCE;

    private SingletonDoubleCheck() {}

    public static SingletonDoubleCheck getInstance() {
        if (INSTANCE != null) // 이미 인스턴스가 생성되어 있는지 확인
            return INSTANCE;

        synchronized (SingletonDoubleCheck.class) {
            if (INSTANCE == null) // 아직 인스턴스가 생성되어 있지 않음
                INSTANCE = new SingletonDoubleCheck();
        }
        return INSTANCE;
    }
}
