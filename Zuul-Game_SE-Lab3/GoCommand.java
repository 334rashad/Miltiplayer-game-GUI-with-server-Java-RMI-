import java.rmi.RemoteException;

/**
 * Implementation of the 'go' user command.
 */
public class GoCommand extends Command
{
    /**
     * Constructor for objects of class GoCommand
     */
    public GoCommand()
    {
    }

    /** 
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message. Returns always 'false'.
     * @throws RemoteException 
     */
    public boolean execute(Player player) throws RemoteException
    {
        if(hasSecondWord()) {
            String direction = getSecondWord();
            player.walk(direction);
        }
        else {
            // if there is no second word, we don't know where to go...
            player.println("Go where?");
        }
        return false;
    }

	@Override
	public boolean execute(String s) {
		// TODO Auto-generated method stub
		return false;
	}
}
