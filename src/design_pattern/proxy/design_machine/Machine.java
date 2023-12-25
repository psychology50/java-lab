package design_pattern.proxy.design_machine;

import java.io.Serial;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Machine extends UnicastRemoteObject implements MachineRemote {
    @Serial
    private static final long serialVersionUID = 1L;
    private String location;
    private int count;
    private State state;

    public Machine(String location, int count) throws RemoteException {
        this.location = location;
        this.count = count;
    }

    @Override
    public int getCount() throws RemoteException {
        return count;
    }

    @Override
    public String getLocation() throws RemoteException {
        return location;
    }

    @Override
    public State getState() throws RemoteException {
        return state;
    }
}
