package gui;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import jdbc_storage.DataStorage;
import jdbc_storage.ProfileInfo;
import jdbc_storage.Recipe;



/*
 * Created by JFormDesigner on Mon Dec 05 19:28:12 EST 2011
 */



/**
 * @author Nguyen
 */
public class ProfileTab {
	public ProfileTab() {
		initComponents();
	}

	public ProfileTab(String username1, DataStorage ds, SuperGUI frame){
		this.username1 = username1;
		this.ds = ds;
		this.frame = frame;
		initComponents();
	}

	private void ViewFriendActionPerformed(ActionEvent e) {
		frame.getCenter().setScreen(FriendPane.NAME);
	}
	
	public JPanel getScreen(){
		
		return ProfileInformation;
		
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		ProfileInformation = new JPanel();
		PicturePanel = new JPanel();
		username = new JLabel();
		picture = new JLabel();
		InfoPanel = new JPanel();
		Name = new JTextField();
		Bday = new JTextField();
		Info = new JTextArea();
		ViewFriend = new JButton();

		//======== ProfileInformation ========
		{

			//======== PicturePanel ========
			{
				PicturePanel.setBorder(new EtchedBorder(Color.lightGray, null));

				//---- username ----
				username.setText(username1);
				username.setHorizontalTextPosition(SwingConstants.CENTER);
				username.setFont(username.getFont().deriveFont(username.getFont().getStyle() | Font.BOLD, username.getFont().getSize() + 5f));
				username.setHorizontalAlignment(SwingConstants.CENTER);

				//---- picture ----
				File f = ds.getImageforProfile(ds.getuid());
				BufferedImage profilePic = null;
				try {
					if(f == null)
						profilePic = ImageIO.read(new File("profilepic.jpg"));
					else
						profilePic = ImageIO.read(f);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
								
				picture = new JLabel(new ImageIcon(SuperGUI.convertSize(profilePic,150)));
				picture.setHorizontalAlignment(SwingConstants.CENTER);
				picture.addMouseListener(new MouseListener(){
					public void mouseClicked(MouseEvent e){
						JFileChooser fc = new JFileChooser();
						int result = fc.showOpenDialog(frame);
						if (result == JFileChooser.APPROVE_OPTION) {
				            File file = fc.getSelectedFile();
				            String s = (file.getAbsolutePath());
				            File image = new File(s);
				    		ds.insertImageForProfile(image.getName(), image.getAbsolutePath());
				    		frame.getCenter().removeAll();
				    		ViewAllRep single_viewAll = new ViewAllRep(ds.getuid(),ds, frame);
				    		frame.getCenter().getCard().add(single_viewAll.getScreen(), ViewAllRep.NAME);
				    		frame.getCenter().setScreen(ViewAllRep.NAME);
				    		frame.getCenter().getCard().revalidate();
				      	} 
					}
					
					public void mouseEntered(MouseEvent e){}

					@Override
					public void mouseExited(MouseEvent arg0) {}

					@Override
					public void mousePressed(MouseEvent arg0) {}

					@Override
					public void mouseReleased(MouseEvent arg0) {}
				});
				

				GroupLayout PicturePanelLayout = new GroupLayout(PicturePanel);
				PicturePanel.setLayout(PicturePanelLayout);
				PicturePanelLayout.setHorizontalGroup(
					PicturePanelLayout.createParallelGroup()
						.addGroup(PicturePanelLayout.createSequentialGroup()
							.addGroup(PicturePanelLayout.createParallelGroup()
								.addGroup(PicturePanelLayout.createSequentialGroup()
									.addContainerGap()
									.addComponent(username, GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE))
								.addGroup(PicturePanelLayout.createSequentialGroup()
									.addGap(72, 72, 72)
									.addComponent(picture, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)))
							.addContainerGap())
				);
				PicturePanelLayout.setVerticalGroup(
					PicturePanelLayout.createParallelGroup()
						.addGroup(PicturePanelLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(username, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(picture, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
							.addContainerGap())
				);
			}

			//======== InfoPanel ========
			{
				InfoPanel.setBorder(new CompoundBorder(
					new TitledBorder("Information"),
					new EmptyBorder(5, 5, 5, 5)));
				InfoPanel.setLayout(null);

				//---- Name ----
				ProfileInfo profile1 = ds.getProfileInfo(username1);
				
				String info1 = profile1.get_firstName() + " " + profile1.get_lastName();
				Name.setText(info1);
				Name.setBorder(new CompoundBorder(
					new TitledBorder(null, "Name", TitledBorder.LEADING, TitledBorder.TOP),
					new EmptyBorder(5, 5, 5, 5)));
				Name.setBackground(new Color(240, 240, 240));
				Name.setEditable(false);
				Name.setHorizontalAlignment(SwingConstants.CENTER);
				InfoPanel.add(Name);
				Name.setBounds(15, 25, 250, 55);

				//---- Bday ----
				String info2 = ProfileInfo.parseBirthday(profile1.get_birthday());
				Bday.setText(info2);
				Bday.setBorder(new CompoundBorder(
					new TitledBorder(null, "Birthday", TitledBorder.LEADING, TitledBorder.TOP),
					new EmptyBorder(5, 5, 5, 5)));
				Bday.setBackground(new Color(240, 240, 240));
				Bday.setEditable(false);
				Bday.setHorizontalAlignment(SwingConstants.CENTER);
				InfoPanel.add(Bday);
				Bday.setBounds(15, 85, 250, 55);

				//---- Info ----
				String info3 = profile1.get_info();
				Info.setText(info3);
				Info.setBorder(new CompoundBorder(
					new TitledBorder(null, "Info", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, null, Color.black),
					new EmptyBorder(5, 5, 5, 5)));
				Info.setBackground(new Color(240, 240, 240));
				Info.setDragEnabled(true);
				Info.setDoubleBuffered(true);
				Info.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
				Info.setLineWrap(true);
				Info.setEditable(false);
				InfoPanel.add(Info);
				Info.setBounds(15, 145, 248, 118);

				//---- ViewFriend ----
				ViewFriend.setText("View Friends");
				ViewFriend.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						ViewFriendActionPerformed(e);
					}
				});
				InfoPanel.add(ViewFriend);
				ViewFriend.setBounds(35, 275, 205, 35);

				{ // compute preferred size
					Dimension preferredSize = new Dimension();
					for(int i = 0; i < InfoPanel.getComponentCount(); i++) {
						Rectangle bounds = InfoPanel.getComponent(i).getBounds();
						preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
						preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
					}
					Insets insets = InfoPanel.getInsets();
					preferredSize.width += insets.right;
					preferredSize.height += insets.bottom;
					InfoPanel.setMinimumSize(preferredSize);
					InfoPanel.setPreferredSize(preferredSize);
				}
			}

			GroupLayout ProfileInformationLayout = new GroupLayout(ProfileInformation);
			ProfileInformation.setLayout(ProfileInformationLayout);
			ProfileInformationLayout.setHorizontalGroup(
				ProfileInformationLayout.createParallelGroup()
					.addGroup(ProfileInformationLayout.createSequentialGroup()
						.addGroup(ProfileInformationLayout.createParallelGroup()
							.addComponent(InfoPanel, GroupLayout.PREFERRED_SIZE, 275, GroupLayout.PREFERRED_SIZE)
							.addComponent(PicturePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addContainerGap())
			);
			ProfileInformationLayout.setVerticalGroup(
				ProfileInformationLayout.createParallelGroup()
					.addGroup(ProfileInformationLayout.createSequentialGroup()
						.addComponent(PicturePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(InfoPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(12, 12, 12))
			);
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JPanel ProfileInformation;
	private JPanel PicturePanel;
	private JLabel username;
	private JLabel picture;
	private JPanel InfoPanel;
	private JTextField Name;
	private JTextField Bday;
	private JTextArea Info;
	private JButton ViewFriend;
	private String username1;
	private DataStorage ds;
	private SuperGUI frame;
	
	public static final String NAME = "Profile Tab";
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
