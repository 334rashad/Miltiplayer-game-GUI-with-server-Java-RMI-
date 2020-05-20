import java.io.Serializable;
import java.rmi.RemoteException;

// SE Lab3
// Add second player to the game

public class AddPlayer extends Command implements  Serializable{
	IGame game;

	public AddPlayer(IGame game) {
		this.game = game;
	}

	public boolean execute(Player player) throws RemoteException
    {
		Player p2 = new Player("secondPlayer", game);
		return true;
    }

	@Override
	public boolean execute(String s) throws RemoteException{
		Player p2 = new Player(s, game);
		return true;
	}
}
