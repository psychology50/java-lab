package design_pattern.proxy.design_machine;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MachineRemote extends Remote {
    int getCount() throws RemoteException;
    String getLocation() throws RemoteException;
    State getState() throws RemoteException;
}
