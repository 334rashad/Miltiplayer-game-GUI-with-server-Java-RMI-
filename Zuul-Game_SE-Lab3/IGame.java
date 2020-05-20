import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;

public interface IGame extends Remote{
	public void addPlayer(IPlayer p) throws RemoteException;

	public static IGame getGame() throws RemoteException {
		return null;
	}

	public HashMap<String, IPlayer> getPlayers() throws RemoteException;

	public void exitRoom(IPlayer player) throws RemoteException;

	public void enterRoom(IPlayer player) throws RemoteException;

	public String deletePlayer(IPlayer player) throws RemoteException;
}
