package design_pattern.proxy.design_machine;

public class Monitor {
    MachineRemote machineRemote;

    public Monitor(MachineRemote machineRemote) {
        this.machineRemote = machineRemote;
    }

    public void report() {
        try {
            System.out.println("뽑기 기계");
            System.out.println("현재 재고: " + machineRemote.getCount() + "개");
            System.out.println("현재 위치: " + machineRemote.getLocation());
            System.out.println("현재 상태: " + machineRemote.getState());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
