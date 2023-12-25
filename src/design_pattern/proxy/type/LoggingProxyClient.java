package design_pattern.proxy.type;

import java.time.LocalDateTime;

class LoggingProxy implements Subject {
    private RealSubject realSubject;

    LoggingProxy(RealSubject realSubject) {
        this.realSubject = realSubject;
    }

    @Override
    public void request() {
        System.out.println("RealSubject 접근 감지");
        realSubject.request();
        System.out.println("RealSubject 실행 완료");
        System.out.println(LocalDateTime.now());
    }
}

public class LoggingProxyClient {
    public static void main(String[] args) {
        Subject subject = new LoggingProxy(new RealSubject());
        subject.request();
    }
}
