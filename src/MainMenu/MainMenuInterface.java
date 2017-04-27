package MainMenu;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//import needed libraries and classes
import javax.swing.JFrame;



public class MainMenuInterface extends JFrame implements ActionListener {
private static GridBagConstraints constraints; //the grid constraints variable
private static final long serialVersionUID = 1L;

	public MainMenuInterface() { //the constructor
		setLayout(new GridBagLayout()); //use the gridBag layout
		constraints = new GridBagConstraints(); //init our constraints
		createLayout(); //set up our layout
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit the program on window close
		setResizable(true); //allow it to be resized
		setTitle("SWORD");
		setSize(getPreferredSize()); //set the default size
		setVisible(true);
	}
	private void createLayout() {
		//color the background black
		getContentPane().setBackground(Color.BLACK);
		
		//allow components to resize
		constraints.weightx = 0.5;
		constraints.weighty = 0.5; 
		constraints.fill = GridBagConstraints.BOTH;
		
		//add padding
		constraints.ipadx = 5;
		constraints.ipady = 5;
	
}
	public static void main(String[] args) {
		//let's get MOOOOVING
		new MainMenuInterface();
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
