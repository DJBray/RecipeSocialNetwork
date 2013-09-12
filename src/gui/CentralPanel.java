package gui;
import java.awt.CardLayout;

import javax.swing.JPanel;

import jdbc_storage.DataStorage;


public class CentralPanel extends JPanel {
	

	private JPanel cards = new JPanel(new CardLayout());
	private CardLayout center = (CardLayout)(cards.getLayout());
	
	private SuperGUI myFrame;
	
	//GUI cards
	private CreateRecipe newRecipe;
	private LoginScreen login;
	private RegisterScreen register;
	private RecipePanel single_recipe;
	
	private boolean visState = true; 
	
	private String username;
	private DataStorage ds;
	
	public CentralPanel(String username, DataStorage ds, SuperGUI frame){
		myFrame = frame;
		this.ds = ds;
		this.username = username;
		init();
	}
	
	public JPanel getCard(){
		return cards;
	}

	private void init(){

		login = new LoginScreen(myFrame, ds);
		CreateRecipe single_createRep = new CreateRecipe(username,ds,myFrame);
		cards.add(single_createRep.getScreen(), CreateRecipe.NAME);
		register = new RegisterScreen(myFrame, ds);		
		
		//cards.add(newRecipe.getScreen(), CreateRecipe.NAME);
		cards.add(login.getScreen(), LoginScreen.NAME);
		cards.add(register.getScreen(), RegisterScreen.NAME);
		
		
		setScreen(LoginScreen.NAME);
		
	}
	
	public JPanel getScreen(){
		if (visState){
			cards.setVisible(visState);
			return cards;
		}
		cards.setVisible(visState);
		return cards;
	}
	
	public void setScreen(String id){
		center.show(cards, id);
	}

}
