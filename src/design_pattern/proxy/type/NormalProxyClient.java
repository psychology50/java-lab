package design_pattern.proxy.type;

interface Subject {
    void request();
}

class RealSubject implements Subject {
    @Override
    public void request() {
        System.out.println("RealSubject.request()");
    }
}

class Proxy implements Subject {
    private RealSubject realSubject;

    public Proxy(RealSubject realSubject) {
        this.realSubject = realSubject;
    }

    @Override
    public void request() {
        realSubject.request();
        System.out.println("Proxy.request()");
    }
}

public class NormalProxyClient {
    public static void main(String[] args) {
        Subject subject = new Proxy(new RealSubject());
        subject.request();
    }
}
