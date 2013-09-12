package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
/*
 * Created by JFormDesigner on Wed Nov 09 16:30:04 EST 2011
 */

import jdbc_storage.DataStorage;



/**
 * @author Nguyen K Nguyen
 */
public class LoginScreen  {
	
	public static final String NAME = "Login";
	private SuperGUI myFrame;
	private DataStorage ds;
	
	public LoginScreen(SuperGUI frame, DataStorage ds){
		this.myFrame = frame;
		this.ds = ds;
		initComponents();
	}
	
	public DataStorage getDB(){
		return this.ds;
	}
	public JPanel getScreen(){
		return LoginScreen;
	}
	
	public String getuid(){
		return uid;
	}
	

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		LoginScreen = new JPanel();
		usernameField = new JTextField();
		passwordField = new JPasswordField();
		Login = new JButton();
		userNamelabel = new JLabel();
		Passwordlabel = new JLabel();
		LoginTitle = new JLabel();
		
		//======== LoginScreen ========
		{
			LoginScreen.setMaximumSize(new Dimension(570, 140));
			LoginScreen.setMinimumSize(new Dimension(570, 140));

			//---- Login ----
			Login.setText("ENTER");
			Login.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					login(usernameField, passwordField);
				}
			});

			//---- userNamelabel ----
			userNamelabel.setText("UserName");

			//---- Passwordlabel ----
			Passwordlabel.setText("Password");

			//---- LoginTitle ----
			LoginTitle.setText("Recipe Network");
			LoginTitle.setFont(LoginTitle.getFont().deriveFont(LoginTitle.getFont().getSize() + 5f));
			LoginTitle.setForeground(Color.red);
			LoginTitle.setHorizontalAlignment(SwingConstants.CENTER);

			GroupLayout LoginScreenLayout = new GroupLayout(LoginScreen);
			LoginScreen.setLayout(LoginScreenLayout);
			LoginScreenLayout.setHorizontalGroup(
				LoginScreenLayout.createParallelGroup()
					.addGroup(GroupLayout.Alignment.TRAILING, LoginScreenLayout.createSequentialGroup()
						.addGap(123, 123, 123)
						.addGroup(LoginScreenLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
							.addComponent(userNamelabel)
							.addComponent(Passwordlabel))
						.addGap(18, 18, 18)
						.addGroup(LoginScreenLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
							.addComponent(LoginTitle, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
							.addGroup(LoginScreenLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
								.addComponent(passwordField, GroupLayout.Alignment.LEADING)
								.addComponent(usernameField, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)))
						.addGap(18, 18, 18)
						.addComponent(Login, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
						.addGap(122, 122, 122))
			);
			LoginScreenLayout.setVerticalGroup(
				LoginScreenLayout.createParallelGroup()
					.addGroup(LoginScreenLayout.createSequentialGroup()
						.addComponent(LoginTitle, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(LoginScreenLayout.createParallelGroup()
							.addComponent(Login, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
							.addGroup(LoginScreenLayout.createSequentialGroup()
								.addGroup(LoginScreenLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
									.addComponent(usernameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(userNamelabel))
								.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(LoginScreenLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
									.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(Passwordlabel))))
						.addContainerGap(47, Short.MAX_VALUE))
			);
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	public void login(JTextField usernameField, JPasswordField passwordField) {
		String username = usernameField.getText();
		char[] cpwd = passwordField.getPassword();
		String password = String.copyValueOf(cpwd);
		
		ds.setuid(username);
		ds.setpwd(password);
		int isLoggedIn;
		
		isLoggedIn = ds.login();
		if(isLoggedIn == DataStorage.USER_LOGGED_IN){
			myFrame.getHeader().removeAll();
			RecipePanel single_recipe = new RecipePanel(username,ds,myFrame);
			ProfileTab single_profile = new ProfileTab(username,ds,myFrame);
			FeedPane single_feed = new FeedPane(ds, 6);
			
			ComposeMsg single_composeh = new ComposeMsg(username,ds,myFrame);
			myFrame.getCenter().getCard().add(single_composeh.getScreen(), ComposeMsg.NAME);
			
			SearchPane single_search = new SearchPane(username,ds);
			myFrame.getCenter().getCard().add(single_search.getScreen(), SearchPane.NAME);
			
			ViewAllRep single_viewAll = new ViewAllRep(username,ds, myFrame);
			myFrame.getCenter().getCard().add(single_viewAll.getScreen(), ViewAllRep.NAME);
			
			FriendPane single_FriendPane = new FriendPane(username,ds, myFrame);
			myFrame.getCenter().getCard().add(single_FriendPane.getScreen(), FriendPane.NAME);
			
			TopRep single_topRep = new TopRep(username,ds,myFrame, 6);
			myFrame.getCenter().getCard().add(single_topRep.getScreen(), TopRep.NAME);
			
			ViewAllMessage single_mesAll = new ViewAllMessage(username,ds,myFrame, 6);
			myFrame.getCenter().getCard().add(single_mesAll.getScreen(), ViewAllMessage.NAME);
			
			CreateRecipe single_createRep = new CreateRecipe(username,ds, myFrame);	
			myFrame.getCenter().getCard().add(single_createRep.getScreen(), CreateRecipe.NAME);
			
			myFrame.getCenter().getCard().add(single_recipe.getScreen(), RecipePanel.NAME);
			myFrame.getCenter().setScreen(RecipePanel.NAME);
			
			
			//System.out.println(myFrame.getWest().getVis());
			myFrame.getWest().setVis(true);
			myFrame.getWest().getScreen().add(single_profile.getScreen(), ProfileTab.NAME);
			myFrame.getWest().setScreen(ProfileTab.NAME);
			
			myFrame.getEast().setVis(true);
			myFrame.getEast().getScreen().add(single_feed.getScreen(), FeedPane.NAME);
			myFrame.getEast().setScreen(FeedPane.NAME);
			
			myFrame.getHeader().setSignIOaction();
			
			myFrame.setVisible(true);
		}
			/*
			 * Update GUI
			 */
			//myFrame.homeScreen();
		else if(isLoggedIn == DataStorage.INVALID_UID_PWD){
			JOptionPane.showMessageDialog(null, "Username/password combination not valid");
		}
		else if(isLoggedIn == DataStorage.USER_NOT_FOUND){
			int answer = JOptionPane.showConfirmDialog(null, "That user does not exist, would you like to create a new profile?");
			if(answer == JOptionPane.YES_OPTION){
				myFrame.getCenter().setScreen(RegisterScreen.NAME);
				myFrame.setVisible(true);
			}
		}
	}
	
	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JPanel LoginScreen;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JButton Login;
	private JLabel userNamelabel;
	private JLabel Passwordlabel;
	private JLabel LoginTitle;
	private static String uid;
	private static String pwd;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
