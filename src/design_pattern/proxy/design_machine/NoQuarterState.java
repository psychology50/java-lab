package design_pattern.proxy.design_machine;

public class NoQuarterState implements State {
    private static final long serialVersionUID = 2L;
    transient Machine machine;

    public NoQuarterState(Machine machine) {
        this.machine = machine;
    }

    @Override
    public void insertCoin() {
        System.out.println("동전을 넣으셨습니다.");
//        machine.setState(machine.getHasQuarterState());
    }

    @Override
    public void ejectCoin() {
        System.out.println("동전을 넣어주세요.");
    }

    @Override
    public void turnCrank() {
        System.out.println("동전을 넣어주세요.");
    }

    @Override
    public void dispense() {
        System.out.println("동전을 넣어주세요.");
    }
}
