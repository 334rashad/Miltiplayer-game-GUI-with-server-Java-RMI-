import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * This class represents players in the game and is the class 
 * to instantiate. 
 * Each player has a current location in the game provided by the game class.
 * In the beginning, the player registers with the game environment.
 */

public class Player extends UnicastRemoteObject implements IPlayer,  Serializable
{
    private String name;
    private IRoom currentRoom;
    private IGame game;
    private GUI gui;
    private boolean gameStarted;

    /**
     * Constructor for objects of class Player
     */
    public Player(String name, IGame game) throws RemoteException
    {
        this.name= name;
        gui= new GUI(this, game);
        gameStarted= false;
        this.game = game;
    }

	public void startGame() throws RemoteException
    {
        if (!gameStarted){
            game.addPlayer(this);
            gameStarted= true;
            notifyOthers();
        }
    }
	
    private void notifyOthers() throws RemoteException {
    	if(game.getPlayers().size() != 0) {
	    	game.getPlayers().forEach((name, player) -> {
	        	if(this != player) {
	            	try {
						player.println(this.getName() + " entered the game");
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            }
	        });	
    	}
	}

    public String getName()
    {
        return name;
    }

    public void println(String s)
    {
        gui.println(s);
    }

    /**
     * Return the current room for this player.
     */
    public IRoom getCurrentRoom()
    {
        return currentRoom;
    }

    /**
     * Set the current room for this player.
     */
    public void setCurrentRoom(IRoom room)
    {
        currentRoom = room;
    }

    /**
     * Try to walk in a given direction. If there is a door
     * this will change the player's location.
     * @throws RemoteException 
     */
    public void walk(String direction) throws RemoteException
    {
        // Try to leave current room.
        IRoom nextRoom = null;
        if (currentRoom!= null) nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            println("There is no door!");
        }
        else {
        	game.exitRoom(this);
            setCurrentRoom(nextRoom);
            println(nextRoom.getLongDescription());
            game.enterRoom(this);
        }
    }

    public void quit() throws RemoteException
    {
    	game.getPlayers().forEach((name, player) -> {
        	if(this != player) {
            	try {
					player.println(this.getName() + " exited the game");
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
        game.deletePlayer(this);
    }

    private static void usage()
    {
        System.out.println("Usage of commandline arguments:");
        System.out.println("Player [PlayerName]");  
    }
 

//    public static void main(String [] argv)
//    {
//        usage();
//        String name= "Professor";
//        if(argv.length>0) name= argv[0];
//        Player p = new Player(name);	
//    }
}
