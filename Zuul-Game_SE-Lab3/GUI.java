import java.awt.*;
import java.awt.event.*;
import java.rmi.RemoteException;

import javax.swing.*;

/**
 * Most simple graphical user interface class.
 * It can be used as an alternative to the console text interface.
 * Buttons allow the player to walk to a neighboring room.
 * The text area gives feedback to the user.
 */
public class GUI
{
    private Player player;
    private CommandWords commands;
    private JTextArea textArea;
    private JTextField txtInput;
    private IGame game;

    /**
     * Constructor for objects of class GUI
     */
    public GUI(Player p, IGame game) throws RemoteException
    {
        player= p;
        commands= new CommandWords(game);
        createFrame(p.getName(), 20, 40);
        this.game = game;
    }

    private void createFrame(String name, int len, int wid) throws RemoteException
    {
        JFrame frame= new JFrame(name);

        Panel goPanel= new Panel();
        goPanel.setLayout(new BoxLayout(goPanel,BoxLayout.Y_AXIS));
        goPanel.add(goButton("north"));
        goPanel.add(goButton("east"));
        goPanel.add(goButton("south"));
        goPanel.add(goButton("west"));
        goPanel.add(goButton("NorthEast"));
        goPanel.add(goButton("NorthWest"));
        goPanel.add(goButton("SouthEast"));
        goPanel.add(goButton("SouthWest"));
                
        Panel gamePanel= new Panel();
        gamePanel.setLayout(new BoxLayout(gamePanel,BoxLayout.Y_AXIS));
        gamePanel.add(gameButton("start"));
        gamePanel.add(gameButton("help"));
        gamePanel.add(gameButton("quit"));
        gamePanel.add(gameButton("add player"));
        gamePanel.add(usrInput());

        textArea= new JTextArea(len, wid);

        JMenuBar menuBar= new JMenuBar();
        JMenu menu= new JMenu("Menus not yet available");
        menuBar.add(menu);

        JLabel versionLabel= new JLabel(
                "Game version 0.2 by Wolfgang Renz, 2019 May 23");

        Container contentPane= frame.getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(new JScrollPane(textArea), BorderLayout.CENTER);
        contentPane.add(menuBar, BorderLayout.NORTH);
        contentPane.add(goPanel, BorderLayout.EAST);
        contentPane.add(versionLabel, BorderLayout.SOUTH);
        contentPane.add(gamePanel, BorderLayout.WEST);
        frame.pack();
        frame.setVisible(true);
    }
    
    private JTextField usrInput() {
    	txtInput = new JTextField("Type new username here..");
    	txtInput.setSize(150,20);
    	txtInput.setFont(new Font("SansSerif", Font.BOLD, 12));
    	txtInput.setForeground(Color.blue);
        txtInput.addMouseListener(new MouseListener() {
        	public void mouseClicked(MouseEvent e) {
        		txtInput.setText("");
            }
            public void mousePressed(MouseEvent e) {
            	txtInput.setText("");
            }
            public void mouseReleased(MouseEvent e) {
            	txtInput.setText("");
            }
            public void mouseEntered(MouseEvent e) {
            }
            public void mouseExited(MouseEvent e) {
            }
        });
        return txtInput; 
    }
    
    private JButton gameButton(String command) throws RemoteException
    {
        JButton button = new JButton(command);
        button.addActionListener(new ActionListener()
                // anonymous class definition implementing the ActionListener Interface
            {
                public void actionPerformed(ActionEvent e)
                {
                    Command cmd = commands.get(command);
                    if(command == "add player") {
                    	if(txtInput.getText().isEmpty() || txtInput.getText().equals("Type new username here..") ) println("please enter new name") ;
                    	else {
                    		try {
								cmd.execute(txtInput.getText());
							} catch (RemoteException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
                    		txtInput.setText("");
                    	}
                    } else
						try {
							cmd.execute(player);
						} catch (RemoteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
                }
            }
        );
        return button;
    }

    private JButton goButton(String direction)
    {
        JButton button = new JButton(direction);
        button.addActionListener(new ActionListener()
                // anonymous class definition implementing the ActionListener Interface
            {   
                public void actionPerformed(ActionEvent e)
                {//button.getLabel()
                    //Command cmd= new GoCommand();
                    Command cmd= commands.get("go");
                    cmd.setSecondWord(direction);
                    try {
						cmd.execute(player);
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                }
            }  
        );
        return button;
    }

    public void println(String s)
    {
        textArea.append(s+ "\n");
    }
}
