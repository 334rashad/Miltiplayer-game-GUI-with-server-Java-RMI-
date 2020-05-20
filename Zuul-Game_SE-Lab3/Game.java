import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

/**
 *  This is text based adventure game.  Users 
 *  can walk around some scenery.
 * 
 *  This class creates the game environment consisting 
 *  of a simple labyrinth of rooms and returns its Instance to the player.
 */

public class Game extends UnicastRemoteObject implements IGame,  Serializable
{
    private static Game game;
    private HashMap<String, IPlayer> players;  // multiplayer
	Room startRoom;

    /**
     * Create the game and initialise its internal map.
     */
    private Game() throws RemoteException 
    {
        startRoom= createRooms();
        players = new HashMap<String, IPlayer>();
    }

    public static Game getGame() throws RemoteException 
    {
        if(game == null){
            game = new Game();
        }
        return game; 
    }
    
    public HashMap<String, IPlayer> getPlayers() {
		return players;
	}

    /**
     * Create all the rooms and link their exits together.
     */
    private Room createRooms() throws RemoteException
    {
        Room outside, theatre, pub, lab, office, Mensa, ProfRenzBuro, Toilet, PauseHalle, PCPool, SchlaffZimmer, TreffPunk;

        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theatre = new Room("in a lecture theatre");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        Mensa = new Room("wo Wenn Man Hunger hat");
        ProfRenzBuro = new Room("in the Prof. Renz office");
        Toilet = new Room("wo im sehr dringend Notfall");
        PauseHalle = new Room("in voll gšnnen es lonht sich");
        PCPool = new Room("wo wenn um spa§ geht");
        SchlaffZimmer = new Room("wo wenn alle anders nicht geht");
        TreffPunk = new Room("wo Man ohne Ende quetschen kann");

        // initialise room exits
        outside.setExit("east", theatre);
        outside.setExit("south", lab);
        outside.setExit("west", pub);
        outside.setExit("NorthEast", Toilet);
        outside.setExit("SouthEast", Mensa);
        outside.setExit("NorthWest", SchlaffZimmer);
        outside.setExit("Southwest", ProfRenzBuro);
        theatre.setExit("west", outside);
        SchlaffZimmer.setExit("NorthWest", Toilet);
        pub.setExit("east", outside);
        lab.setExit("north", outside);
        lab.setExit("east", office);
        office.setExit("west", lab);
        Mensa.setExit("NorthEast", SchlaffZimmer);
        ProfRenzBuro.setExit("SoutEast", PCPool);
        Toilet.setExit("SouthWest", lab);
        PauseHalle.setExit("east", outside);
        PCPool.setExit("NorthWest", SchlaffZimmer);
        TreffPunk.setExit("SouthWest", Toilet);
        
        return outside;
    }

    /**
     * Print out the opening message for the player.
     * @throws RemoteException 
     */
    private void welcome(IPlayer player) throws RemoteException
    {
        player.setCurrentRoom(startRoom);
        player.println("");
        player.println("Welcome to The World of Zuul!");
        player.println("The World of Zuul is a new, incredibly boring adventure game.");
        player.println("Type 'help' if you need help.");
        player.println("");
        player.println(player.getCurrentRoom().getLongDescription());
    }

    private void farewell(IPlayer p) throws RemoteException
    {
        p.println("Thank you for playing.  Good bye.");
    }

    public void addPlayer(IPlayer player) throws RemoteException
    {
        //player = p; // overwrites an existing player
    	IPlayer p = (IPlayer) player;
    	System.out.println(p.getName() +  "started game");
    	players.put(p.getName(), p);
        welcome(p);
    }

    public String deletePlayer(IPlayer p) throws RemoteException
    {
    	farewell(p);
    	return (String) players.remove(p.getName()).getName();
    	 
//        if(p.equals(player)){
//            farewell(player);
//            player= null;
//        }
    }
    
    public void exitRoom(IPlayer p) {
	    this.players.forEach((name, player) -> {
	    	if(p != player) {
	    		try {
					if(p.getCurrentRoom().getShortDescription().equals(player.getCurrentRoom().getShortDescription())) {
						player.println(p.getName() + 
								" exited the room: " + 
								p.getCurrentRoom().getShortDescription());
					}
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	    });
    }
    
    public void enterRoom(IPlayer p) {
        this.players.forEach((name, player) -> {
        	if(p != player) {
            	try {
					if(p.getCurrentRoom().getShortDescription().equals(player.getCurrentRoom().getShortDescription())) {
						try {
							p.println(player.getName() + 
									" is in this room: " + 
									p.getCurrentRoom().getShortDescription());
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						player.println(p.getName() + 
								" just entered the room: " + 
								p.getCurrentRoom().getShortDescription());
					}
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });    	
    }
}


