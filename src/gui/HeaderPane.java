package gui;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class HeaderPane extends JPanel{

	private JPanel header = null;
	private JButton home;
	private JButton messages;
	private JButton topRecipes;
	private JButton search;
	private JButton signIn;
	private JButton signOut;
	private SuperGUI myFrame;
	
	private static boolean trigger = false;

	public HeaderPane(SuperGUI frame){
		myFrame = frame;
		buildHeaderPane();

	}
	private void buildHeaderPane(){

		header = new JPanel(new GridLayout(1,0));
		header.removeAll();
		header.add(menuButtons());

	}

	public JPanel getHeaderPanel(){
		return header;
	}

	/** Builds menu buttons and arranges them in a grid layout JPanel
	 * 
	 */
	private JPanel menuButtons(){
		JPanel buttonPane = new JPanel (new GridLayout(0, 5));

		home = new JButton("Home");
		home.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Home();
			}
		});
		messages = new JButton("Messages");
		messages.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				toMessages();
			}
		});
		topRecipes = new JButton("Top Recipes");
		topRecipes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				toTopRep();
			}
		});
		search = new JButton("Search");
		search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				toSearch();
			}
		});
		signIn = new JButton("Sign In");
		signIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				signIn();
			}
		});
		signOut = new JButton("Sign Out");
		signOut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				logOut();
			}
		});
		


		buttonPane.add(home);
		buttonPane.add(messages);
		buttonPane.add(topRecipes);
		buttonPane.add(search);
		if(trigger){
			buttonPane.add(signOut);

		} else {
			buttonPane.add(signIn);

		}
		//signOut.setVisible(false);

		return buttonPane;

	}
	
	public void Home() {
		// TODO Auto-generated method stub
		myFrame.getCenter().setScreen(ViewAllRep.NAME);
	}
	public void toTopRep() {
		myFrame.getCenter().setScreen(TopRep.NAME);
		// TODO Auto-generated method stub
		
	}
	public void toMessages() {
		myFrame.getCenter().setScreen(ViewAllMessage.NAME);
		// TODO Auto-generated method stub
		
	}
	public void toSearch() {
		// TODO Auto-generated method stub
		myFrame.getCenter().setScreen(SearchPane.NAME);
	}
	
	public void signIn(){
		myFrame.getCenter().setScreen(LoginScreen.NAME);
	}
	
	public void setSignIOaction(){
		myFrame.getContentPane().remove(header);
		trigger = !trigger;
		buildHeaderPane();
		myFrame.add(header, BorderLayout.NORTH);
			
		//header.remove(comp)

	}
	
	public void setTrigger(boolean i){
		trigger = i;
	}
	
	public void logOut(){
		int answer = JOptionPane.showConfirmDialog(null, "Are you sure you want to log out?");
		if(answer == JOptionPane.YES_OPTION)
			System.exit(0);
		/*myFrame.getContentPane().removeAll();
		setSignIOaction();
		buildHeaderPane();
		
		myFrame.init();
		myFrame.getContentPane().add(getHeaderPanel(), BorderLayout.NORTH);
		myFrame.getContentPane().add(myFrame.getCenter().getScreen(), BorderLayout.CENTER);
		myFrame.getContentPane().add(myFrame.getEast().getScreen(), BorderLayout.EAST);
		myFrame.getContentPane().add(myFrame.getWest().getScreen(), BorderLayout.WEST);
		
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setMinimumSize(new Dimension(1000, 650));
		myFrame.pack();
		myFrame.setVisible(true);*/
	}
}
