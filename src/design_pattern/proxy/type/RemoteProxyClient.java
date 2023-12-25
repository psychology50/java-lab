package design_pattern.proxy.type;

import java.net.MalformedURLException;
import java.rmi.*;

interface MyRemote extends Remote {
    String sayHello() throws RemoteException;
}

class MyMonitor {
    private final MyRemote service;

    MyMonitor(MyRemote service) {
        this.service = service;
    }

    public void report() {
        try {
            System.out.println(service.sayHello());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}

public class RemoteProxyClient {
    public static void main(String[] args) {
        String url = "rmi://어쩌구저쩌구.com/RemoteHello";
        try {
            MyRemote service = (MyRemote) Naming.lookup(url);
            MyMonitor monitor = new MyMonitor(service);
            monitor.report();
        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            e.printStackTrace();
        }
    }
}
