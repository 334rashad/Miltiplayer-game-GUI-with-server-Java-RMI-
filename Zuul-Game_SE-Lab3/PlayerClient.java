import java.rmi.Naming;

public class PlayerClient {
    public static void main(String [] argv) throws Exception {
    	IGame game =
                (IGame) Naming.lookup(
                    "rmi://127.0.0.1/multiplayer") ;
    	
    	String name = "cubu";
    	if(argv.length>0) name = argv[0];
    	IPlayer player = new Player(name, game);
    	
    }
}