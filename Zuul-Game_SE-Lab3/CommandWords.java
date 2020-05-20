import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;

/**
 * This class holds a collection of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 */

public class CommandWords implements Serializable
{
    private HashMap<String, Command> commands;
    IGame game;

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords(IGame game)
    {
        commands = new HashMap<String, Command>();
        commands.put("go", new GoCommand());
        commands.put("help", new HelpCommand(this));
        commands.put("start", new StartCommand());
        commands.put("quit", new QuitCommand());
        commands.put("add player", new AddPlayer(game));
    }

    /**
     * Given a command word, find and return the matching command object.
     * Return null if there is no command with this name.
     */
    public Command get(String word)
    {
        return (Command)commands.get(word);
    }

    /**
     * Print all valid commands to System.out.
     */
    public String toString() 
    {
        StringBuffer buf= new StringBuffer();
        for(Iterator i = commands.keySet().iterator(); i.hasNext(); ) {
            buf.append(i.next());
            buf.append("  ");
        }
        buf.append("\n");
        return new String(buf);
    }
}
