import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IPlayer extends Remote {

	void setCurrentRoom(IRoom startRoom) throws RemoteException;

	void println(String string) throws RemoteException;

	String getName() throws RemoteException;

	IRoom getCurrentRoom() throws RemoteException;

}
