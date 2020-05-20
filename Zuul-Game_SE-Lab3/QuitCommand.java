import java.rmi.RemoteException;

/**
 * Implementation of the 'quit' user command.
 */
public class QuitCommand extends Command
{
    /**
     * Constructor for objects of class QuitCommand
     */
    public QuitCommand()
    {
    }

    /**
     * "Quit" was entered. Check the argument to see whether
     * we really quit the game. Return true, if we should quit, false
     * otherwise.
     * @throws RemoteException 
     */
    public boolean execute(Player player) throws RemoteException
    {
        if(getSecondWord() == null) {
            player.quit();
            return true;
        }
        else {
            player.println("I cannot quit that...");
            return false;
        }
    }

	@Override
	public boolean execute( String s) {
		// TODO Auto-generated method stub
		return false;
	}

}
