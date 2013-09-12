package gui;
import jdbc_storage.DataStorage;
import java.awt.CardLayout;
import javax.swing.JPanel;

public class EastPanel {
	
	private JPanel cards = new JPanel(new CardLayout());
	private CardLayout east = (CardLayout)(cards.getLayout());
	
	private SuperGUI myFrame;
	//Current visible state of the East Panel, invisible by default 
	private boolean visState = false; 
	
	//GUI Cards
	private FeedPane feed;
	
	//database variables
	private String username;
	private DataStorage ds;
	
	public EastPanel(SuperGUI frame){
		//TODO
		myFrame = frame;
		
	}
	
	/**
	 * Set whether or not the east Panel should be seen
	 * @param vis viewable or not (true/false) state
	 */
	public void setVis(boolean vis){
		visState = vis;
	}
	
	/**
	 * Check to see if the east panel is visible
	 * @return Current visible state of the East Panel
	 */
	public boolean getVis(){
		return visState;
	}
	
	/**
	 * Initializes components of east panel.
	 */
	/*private void init(){
		//TODO
		feed = new FeedPane(ds, 4);
		
		cards.add(feed.getFeed(), FeedPane.NAME );
	}*/
	
	/**
	 * Gets the JPanel containing the different possible views
	 * 
	 * @return JPanel 
	 */
	public JPanel getScreen(){
		if (visState){
			cards.setVisible(visState);
			return cards;
		}
		cards.setVisible(visState);
		return cards;
	}
	
	/**
	 * Sets the show of the card layout to the id. Changes which 
	 * JPanel is viewable
	 * @param id Which JPanel to set to show
	 */
	public void setScreen(String id){
		east.show(cards, id);
	}
}
