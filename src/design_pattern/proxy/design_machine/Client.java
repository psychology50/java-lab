package design_pattern.proxy.design_machine;

import java.rmi.Naming;

public class Client {
    public static void main(String[] args) {
        String location = "rmi://127.0.0.1/DesignMachine";
        try {
            MachineRemote service = (MachineRemote) Naming.lookup(location);
            Monitor monitor = new Monitor(service);
            monitor.report();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
