/**
 * Implementation of the 'help' user command.
 */
public class HelpCommand extends Command
{
    private CommandWords commandWords;
    
    /**
     * Constructor for objects of class HelpCommand
     */
    public HelpCommand(CommandWords words)
    {
        commandWords = words;
    }
    
    /**
     * Print out some help information. Here we print some stupid, 
     * cryptic message and a list of the command words.
     * Returns always false.
     */
    public boolean execute(Player player)
    {
        player.println("You are lost. You are alone. You wander");
        player.println("around at the university.");
        player.println("");
        player.println("Your command words are:");
        player.println(commandWords.toString());
        return false;
    }

	@Override
	public boolean execute(String s) {
		// TODO Auto-generated method stub
		return false;
	}
}
