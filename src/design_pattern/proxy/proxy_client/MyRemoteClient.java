package design_pattern.proxy.proxy_client;

import design_pattern.proxy.proxy_server.MyRemote;

public class MyRemoteClient {
    public static void main(String[] args) {
        new MyRemoteClient().go();
    }

    public void go() {
        try {
            MyRemote service = (MyRemote) java.rmi.Naming.lookup("rmi://127.0.0.1/RemoteHello");
            String s = service.sayHello();
            System.out.println(s);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
