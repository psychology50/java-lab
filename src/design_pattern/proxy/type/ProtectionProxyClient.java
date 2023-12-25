package design_pattern.proxy.type;

class ProtectionProxy implements Subject {
    private final RealSubject realSubject;
    private boolean isAuth = false;

    ProtectionProxy(RealSubject realSubject, boolean isAuth) {
        this.realSubject = realSubject;
        this.isAuth = isAuth;
    }

    @Override
    public void request() {
        if (isAuth) {
            realSubject.request();
        } else {
            System.out.println("You don't have permission to access this resource.");
        }
    }
}

public class ProtectionProxyClient {
    public static void main(String[] args) {
        Subject subject = new ProtectionProxy(new RealSubject(), false);
        subject.request();
    }
}
