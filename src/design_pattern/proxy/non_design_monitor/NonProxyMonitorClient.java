package design_pattern.proxy.non_design_monitor;

class Machine {
    String location;
    Integer count;
    Integer state;

    Machine(String location, Integer count, Integer state) {
        this.location = location;
        this.count = count;
        this.state = state;
    }

    public void getLocation() {
        System.out.println(location);
    }

    public void getCount() {
        System.out.println(count);
    }

    public void getState() {
        System.out.println(state);
    }
}

class LegacyMonitor {
    private final Machine machine;

    LegacyMonitor(Machine machine) {
        this.machine = machine;
    }

    public void report() {
        System.out.println("Location: " + machine.location);
        System.out.println("Count: " + machine.count);
        System.out.println("State: " + machine.state);
    }
}

public class NonProxyMonitorClient {
    public static void main(String[] args) {
        Machine machine = new Machine("서울", 10, 1);
        LegacyMonitor monitor = new LegacyMonitor(machine);
        monitor.report();
    }
}
