package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/*
 * Created by JFormDesigner on Mon Dec 05 16:07:56 EST 2011
 */

import jdbc_storage.DataStorage;



/**
 * @author Nguyen
 */
public class RegisterScreen extends JPanel {
	public RegisterScreen(SuperGUI frame, DataStorage ds) {
		this.frame = frame;
		this.ds = ds;
		initComponents();
	}

	public JPanel getScreen(){
		return this;
	}
	
	private void SubmitActionPerformed(ActionEvent e) {
		String u_name = username.getText();
		String pwd = String.copyValueOf(password.getPassword());
		String pwd2 = String.copyValueOf(repassword.getPassword());
		String bday = birthday.getText();
		String bday2 = birthday2.getText();
		String fname = First.getText();
		String lname = Last.getText();
		String info = Info.getText();
		int bdayInt;
		//assert pwd and bday are the same
		if(u_name.trim().isEmpty() || pwd.trim().isEmpty() || bday.trim().isEmpty() || fname.trim().isEmpty() || lname.trim().isEmpty()){
			JOptionPane.showMessageDialog(null, "1 or more entries are filled out.");
			return;
		}
		if(u_name.length() > DataStorage.UID_LENGTH || pwd.length() > DataStorage.PWD_LENGTH ||
				fname.length() > DataStorage.FNAME_LENGTH || lname.length() > DataStorage.LNAME_LENGTH ||
				info.length() > DataStorage.INFO_LENGTH){
			JOptionPane.showMessageDialog(null, "You have exceeded the text limit in 1 or more fields");
			return;
		}
		if(!pwd.equals(pwd2) || !bday.equals(bday2)){
			JOptionPane.showMessageDialog(null, "Passwords do not match");
			return;
		}
		
		//assert bday is the correct int
		try{
			bdayInt = Integer.parseInt(bday);
			if(bday.length() != 8)
				throw new NumberFormatException("Invalid birthday");
		}
		catch(NumberFormatException ex){
			JOptionPane.showMessageDialog(null, "You entered an invalid birthday.\nIt must be in the format YYYYMMDD\n\n" + 
						"For example: 20101205 = December 5th, 2010");
			return;
		}
		
		boolean createSuccess = DataStorage.createAccount(u_name, pwd2);
		if(!createSuccess){
			JOptionPane.showMessageDialog(null, "Username not available");
			return;
		}
		
		ds.setuid(u_name);
		ds.setpwd(pwd);
		ds.login();
		ds.createProfile(u_name, fname, lname, info, bdayInt);
		
		RecipePanel single_recipe = new RecipePanel(ds.getuid(), ds, frame);
		ProfileTab single_profile = new ProfileTab(ds.getuid(), ds, frame);
		FeedPane single_feed = new FeedPane(ds, 6);
		
		
		frame.getCenter().getCard().add(single_recipe.getScreen(), RecipePanel.NAME);
		frame.getCenter().setScreen(RecipePanel.NAME);
		
		
		//System.out.println(frame.getWest().getVis());
		frame.getWest().setVis(true);
		frame.getWest().getScreen().add(single_profile.getScreen(), ProfileTab.NAME);
		frame.getWest().setScreen(ProfileTab.NAME);
		
		frame.getEast().setVis(true);
		frame.getEast().getScreen().add(single_feed.getScreen(), FeedPane.NAME);
		frame.getEast().setScreen(FeedPane.NAME);
		
		frame.getHeader().setSignIOaction();
		frame.setVisible(true);
	}

	private void CancelActionPerformed(ActionEvent e) {
		frame.getCenter().setScreen(LoginScreen.NAME);
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		RegisterPage = new JPanel();
		Title = new JLabel();
		username = new JTextField();
		usernameLB = new JLabel();
		password = new JPasswordField();
		passwordLB = new JLabel();
		repassword = new JPasswordField();
		repasswordLB = new JLabel();
		birthday = new JTextField();
		birthday2 = new JTextField();
		First = new JTextField();
		Last = new JTextField();
		scrollPane1 = new JScrollPane();
		Info = new JTextArea();
		birthdayLB = new JLabel();
		birthday2LB = new JLabel();
		FirstLB = new JLabel();
		LastLB = new JLabel();
		InfoLB = new JLabel();
		Submit = new JButton();
		Cancel = new JButton();
		hSpacer1 = new JPanel(null);
		vSpacer1 = new JPanel(null);

		//======== this ========
		setLayout(null);

		//---- Title ----
		Title.setText("Register Page");
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		Title.setFont(Title.getFont().deriveFont(Title.getFont().getSize() + 10f));
		Title.setForeground(Color.red);
		add(Title);
		Title.setBounds(85, 20, 340, 40);
		add(username);
		username.setBounds(200, 85, 245, 30);

		//---- usernameLB ----
		usernameLB.setText("UserName");
		usernameLB.setHorizontalAlignment(SwingConstants.CENTER);
		usernameLB.setFont(usernameLB.getFont().deriveFont(usernameLB.getFont().getStyle() | Font.BOLD, usernameLB.getFont().getSize() + 5f));
		add(usernameLB);
		usernameLB.setBounds(90, 85, 95, 30);
		add(password);
		password.setBounds(200, 125, 245, 30);

		//---- passwordLB ----
		passwordLB.setText("Password");
		passwordLB.setHorizontalAlignment(SwingConstants.CENTER);
		passwordLB.setFont(passwordLB.getFont().deriveFont(passwordLB.getFont().getStyle() | Font.BOLD, passwordLB.getFont().getSize() + 5f));
		add(passwordLB);
		passwordLB.setBounds(90, 125, 95, 30);
		add(repassword);
		repassword.setBounds(200, 165, 245, 30);

		//---- repasswordLB ----
		repasswordLB.setText("Re-Enter");
		repasswordLB.setHorizontalAlignment(SwingConstants.CENTER);
		repasswordLB.setFont(repasswordLB.getFont().deriveFont(repasswordLB.getFont().getStyle() | Font.BOLD, repasswordLB.getFont().getSize() + 5f));
		add(repasswordLB);
		repasswordLB.setBounds(90, 165, 95, 30);
		add(birthday);
		birthday.setBounds(200, 205, 245, 30);
		add(birthday2);
		birthday2.setBounds(200, 245, 245, 30);
		add(First);
		First.setBounds(200, 285, 245, 30);
		add(Last);
		Last.setBounds(200, 325, 245, 30);

		//======== scrollPane1 ========
		{
			scrollPane1.setViewportView(Info);
		}
		add(scrollPane1);
		scrollPane1.setBounds(200, 365, 245, 135);

		//---- birthdayLB ----
		birthdayLB.setText("birthday");
		birthdayLB.setHorizontalAlignment(SwingConstants.CENTER);
		birthdayLB.setFont(birthdayLB.getFont().deriveFont(birthdayLB.getFont().getStyle() | Font.BOLD, birthdayLB.getFont().getSize() + 5f));
		add(birthdayLB);
		birthdayLB.setBounds(90, 205, 95, 30);

		//---- birthday2LB ----
		birthday2LB.setText("Re-Enter");
		birthday2LB.setHorizontalAlignment(SwingConstants.CENTER);
		birthday2LB.setFont(birthday2LB.getFont().deriveFont(birthday2LB.getFont().getStyle() | Font.BOLD, birthday2LB.getFont().getSize() + 5f));
		add(birthday2LB);
		birthday2LB.setBounds(90, 245, 95, 30);

		//---- FirstLB ----
		FirstLB.setText("First Name");
		FirstLB.setHorizontalAlignment(SwingConstants.CENTER);
		FirstLB.setFont(FirstLB.getFont().deriveFont(FirstLB.getFont().getStyle() | Font.BOLD, FirstLB.getFont().getSize() + 5f));
		add(FirstLB);
		FirstLB.setBounds(90, 285, 95, 30);

		//---- LastLB ----
		LastLB.setText("Last Name");
		LastLB.setHorizontalAlignment(SwingConstants.CENTER);
		LastLB.setFont(LastLB.getFont().deriveFont(LastLB.getFont().getStyle() | Font.BOLD, LastLB.getFont().getSize() + 5f));
		add(LastLB);
		LastLB.setBounds(90, 325, 95, 30);

		//---- InfoLB ----
		InfoLB.setText("Info");
		InfoLB.setHorizontalAlignment(SwingConstants.CENTER);
		InfoLB.setFont(InfoLB.getFont().deriveFont(InfoLB.getFont().getStyle() | Font.BOLD, InfoLB.getFont().getSize() + 5f));
		add(InfoLB);
		InfoLB.setBounds(95, 365, 95, 30);

		//---- Submit ----
		Submit.setText("Submit");
		Submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SubmitActionPerformed(e);
			}
		});
		add(Submit);
		Submit.setBounds(160, 520, 100, 33);

		//---- Cancel ----
		Cancel.setText("Cancel");
		Cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CancelActionPerformed(e);
			}
		});
		add(Cancel);
		Cancel.setBounds(270, 520, 100, 33);
		add(hSpacer1);
		hSpacer1.setBounds(445, 95, 70, hSpacer1.getPreferredSize().height);
		add(vSpacer1);
		vSpacer1.setBounds(385, 500, vSpacer1.getPreferredSize().width, 75);

		{ // compute preferred size
			Dimension preferredSize = new Dimension();
			for(int i = 0; i < getComponentCount(); i++) {
				Rectangle bounds = getComponent(i).getBounds();
				preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
				preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
			}
			Insets insets = getInsets();
			preferredSize.width += insets.right;
			preferredSize.height += insets.bottom;
			setMinimumSize(preferredSize);
			setPreferredSize(preferredSize);
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	private SuperGUI frame;
	private DataStorage ds;
	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JPanel RegisterPage;
	private JLabel Title;
	private JTextField username;
	private JLabel usernameLB;
	private JPasswordField password;
	private JLabel passwordLB;
	private JPasswordField repassword;
	private JLabel repasswordLB;
	private JTextField birthday;
	private JTextField birthday2;
	private JTextField First;
	private JTextField Last;
	private JScrollPane scrollPane1;
	private JTextArea Info;
	private JLabel birthdayLB;
	private JLabel birthday2LB;
	private JLabel FirstLB;
	private JLabel LastLB;
	private JLabel InfoLB;
	private JButton Submit;
	private JButton Cancel;
	private JPanel hSpacer1;
	private JPanel vSpacer1;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public static final String NAME = "Register";
}
