package gui;

import java.awt.CardLayout;

import javax.swing.JPanel;
import jdbc_storage.DataStorage;

public class WestPanel {
	
	private JPanel cards = new JPanel(new CardLayout());
	private CardLayout west = (CardLayout)(cards.getLayout());
	
	//GUI management variables
	private SuperGUI myFrame;
	private boolean visState = false; //current visible state of the west panel. Invisible by default
	
	//JPanels displayed in West Panel for cards
	private ProfileTab profile;

	//database variables
	private String username;
	private DataStorage ds;
	
	public WestPanel(SuperGUI frame){
		myFrame = frame;	
	}
	
	public JPanel getCard(){
		return this.cards;
	}
	
	
	/**
	 * Initializes all possible viewable components
	 */
	/*private void init(){
		
		//profile = new ProfileTab(username, ds);
		cards.add(new JPanel());
		//cards.add(profile.getScreen(), ProfileTab.NAME);
	}*/
	
	/**
	 * Set whether or not the West Panel should be seen
	 * @param vis viewable or not (true/false) state
	 */
	public void setVis(boolean vis){
		visState = vis;
	}
	
	/**
	 * Check to see if the West panel is visible
	 * @return Current visible state of the West Panel
	 */
	public boolean getVis(){
		return visState;
	}
	/**
	 * Gets and returns the JPanel on the top of the deck 
	 * @return JPanel
	 */
	
	public JPanel getScreen(){
		if (visState){
			cards.setVisible(visState);
			return cards;
		}
		cards.setVisible(false);
		return cards;
	}
	
	/**
	 * Using CardLayouts, sets the currently viewable JPanel to the 
	 * id
	 * @param id String identifying the desired JPanel
	 */
	public void setScreen(String id){
		west.show(cards, id);
			
	}
}
