import java.rmi.Naming;

public class GameServer {
    public static void main(String [] args) throws Exception {
    	Game game = Game.getGame();
        Naming.rebind("multiplayer", game);
    }
}
