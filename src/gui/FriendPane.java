package gui;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import jdbc_storage.DataStorage;



/**
 * @author Nguyen
 */
public class FriendPane  {

	public FriendPane(String username,DataStorage ds, SuperGUI frame){
		this.username = username;
		this.ds = ds;
		this.frame = frame;
		initComponents();
	}
	

	public JScrollPane getScreen(){
		return FriendScroll;
	}

	private void NameMouseClicked(MouseEvent e) {
		// TODO add your code here
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		FriendScroll = new JScrollPane();
		AllFriend = new JPanel();
		ArrayList<String> friends = ds.getFriends();

		for (int i = 0; i < friends.size(); i++ ){
			String friend = friends.get(i);
			Friend = new JPanel();
			Name = new JLabel();
			Photo = new JLabel();
			ViewProf = new JButton();
			Delete = new JButton();

			//======== FriendScroll ========
			{
				FriendScroll.setMaximumSize(new Dimension(240, 32767));
				FriendScroll.setMinimumSize(new Dimension(240, 565));
				FriendScroll.setPreferredSize(new Dimension(240, 565));
				//======== AllFriend ========
				{
					AllFriend.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
					AllFriend.setInheritsPopupMenu(true);
					AllFriend.setLayout(new GridLayout(0, 3));
					AllFriend.setPreferredSize(new Dimension(300, 565));

					//======== Friend ========
					{
						Friend.setBorder(new EtchedBorder());

						//---- Name ----
						Name.setText(friends.get(i));
						Name.setHorizontalAlignment(SwingConstants.CENTER);
						Name.setFont(Name.getFont().deriveFont(Name.getFont().getStyle() | Font.BOLD));
						Name.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								NameMouseClicked(e);
							}
						});

						//---- Photo ----
						Photo.setText("Photo");
						Photo.setHorizontalAlignment(SwingConstants.CENTER);
						Photo.setBorder(new LineBorder(Color.black));

						//---- ViewProf ----
						ViewProf.setText("View");
						ViewProf.addActionListener(new ViewListener(friend));

						//---- Delete ----
						Delete.setText("Del");
						Delete.addActionListener(new RemoveListener(friend,frame));

						GroupLayout FriendLayout = new GroupLayout(Friend);
						Friend.setLayout(FriendLayout);
						FriendLayout.setHorizontalGroup(
							FriendLayout.createParallelGroup()
								.addGroup(GroupLayout.Alignment.TRAILING, FriendLayout.createSequentialGroup()
									.addContainerGap()
									.addGroup(FriendLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
										.addComponent(Photo, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
										.addComponent(Name, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
										.addGroup(GroupLayout.Alignment.LEADING, FriendLayout.createSequentialGroup()
											.addComponent(ViewProf, GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
											.addGap(18, 18, 18)
											.addComponent(Delete, GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)))
									.addContainerGap())
						);
						FriendLayout.setVerticalGroup(
							FriendLayout.createParallelGroup()
								.addGroup(FriendLayout.createSequentialGroup()
									.addComponent(Name, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addComponent(Photo, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addGroup(FriendLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(ViewProf)
										.addComponent(Delete))
									.addGap(410, 410, 410))
						);
					}
					AllFriend.add(Friend);

				}
				FriendScroll.setViewportView(AllFriend);
			}
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}


	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JScrollPane FriendScroll;
	private JPanel AllFriend;
	private JPanel Friend;
	private JLabel Name;
	private JLabel Photo;
	private JButton ViewProf;
	private JButton Delete;
	private DataStorage ds;
	private String username;
	private SuperGUI frame;


	public static final String NAME = "Friend Panel";
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public class RemoveListener implements ActionListener{
		private String friend;
		private SuperGUI frame;
		public RemoveListener(String friend,SuperGUI frame){
			this.friend = friend;
			this.frame = frame;
		}
		
		public void actionPerformed(ActionEvent e){
			ds.removeFriend(friend);
			
			frame.getCenter().getCard().removeAll();
			FriendPane single_viewFriend = new FriendPane(username,ds, frame);
			frame.getCenter().getCard().add(single_viewFriend.getScreen(), FriendPane.NAME);
			frame.getCenter().getCard().revalidate();
			frame.getCenter().setScreen(FriendPane.NAME);
			frame.getCenter().getCard().revalidate();
			
		}
	}
	
	public class ViewListener implements ActionListener{
		private String friend;
		public ViewListener(String friend){
			this.friend = friend;
		}
		
		public void actionPerformed(ActionEvent e){
			//TODO
		}
	}
}
