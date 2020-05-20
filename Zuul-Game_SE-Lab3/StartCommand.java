import java.rmi.RemoteException;

/**
 * Implementation of the 'quit' user command.
 */
public class StartCommand extends Command
{
    /**
     * Constructor for objects of class StartCommand
     */
    public StartCommand()
    {
    }

    /**
     * "Quit" was entered. Check the argument to see whether
     * we really quit the game. Return true, if we should quit, false
     * otherwise.
     */
    public boolean execute(Player player)
    {
        if(getSecondWord() == null) {
            try {
				player.startGame();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            return false;
        }
        else {
            player.println("I cannot start that...");
            return false;
        }
    }

	@Override
	public boolean execute(String s) {
		// TODO Auto-generated method stub
		return false;
	}

}
