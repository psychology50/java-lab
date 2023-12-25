package design_pattern.proxy.design_machine;

import java.io.Serializable;

public interface State extends Serializable {
    void insertCoin();
    void ejectCoin();
    void turnCrank();
    void dispense();
}
