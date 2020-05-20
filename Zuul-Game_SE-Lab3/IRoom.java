import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRoom extends Remote{

	String getLongDescription() throws RemoteException;

	IRoom getExit(String direction) throws RemoteException;

	String getShortDescription() throws RemoteException;

}
