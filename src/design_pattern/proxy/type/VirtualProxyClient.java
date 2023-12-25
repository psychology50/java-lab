package design_pattern.proxy.type;

class VirtualProxy implements Subject {
    private RealSubject realSubject;

    VirtualProxy() {
    }

    @Override
    public void request() {
        if (realSubject == null) {
            realSubject = new RealSubject();
        }
        realSubject.request();
        System.out.println("VirtualProxy.request()");
    }
}

public class VirtualProxyClient {
    public static void main(String[] args) {
        Subject subject = new VirtualProxy();
        subject.request();
    }
}
