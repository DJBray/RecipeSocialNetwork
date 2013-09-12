package gui;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import jdbc_storage.DataStorage;
import jdbc_storage.Message;



/**
 * @author Nguyen
 */
public class ViewAllMessage  {

	public ViewAllMessage(String username,DataStorage ds, SuperGUI frame, int i){
		myFrame = frame;
		this.username = username;
		this.ds = ds;
		initComponents(i);
	}


	public JPanel getScreen(){
		return ViewAllMessage;
	}

	private void CreateMessageActionPerformed(ActionEvent e) {
		myFrame.getCenter().setScreen(ComposeMsg.NAME);
		// TODO add your code here
	}

	private void initComponents(int number) {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		ArrayList<Message> messages = ds.getMessages();
		ViewAllMessage = new JPanel();
		MessageScroll = new JScrollPane();
		MessagesAll = new JPanel();
		CreateMessage = new JButton();
		if(messages == null){
			messages = new ArrayList<Message>();
		}
		for (int i = 0; i < number && i < messages.size(); i++ ){
			Message m = messages.get(i);
			MessagePanel = new JPanel();
			From = new JLabel();
			scrollPane1 = new JScrollPane();
			RepDescription = new JTextArea();
			RepDescription.setText(m.get_message());
			Photo = new JLabel();
			separator1 = new JSeparator();


			//======== ViewAllMessage ========
			{

				//======== MessageScroll ========
				{

					//======== MessagesAll ========
					{
						MessagesAll.setLayout(new GridLayout(0, 1));

						//======== MessagePanel ========
						{
							MessagePanel.setBorder(new EtchedBorder());
							MessagePanel.setMinimumSize(new Dimension(484, 109));
							MessagePanel.setMaximumSize(new Dimension(484, 109));
							MessagePanel.setPreferredSize(new Dimension(484, 109));

							//---- From ----
							From.setText("From " + m.get_fromUser());
							From.setHorizontalAlignment(SwingConstants.CENTER);
							From.setFont(From.getFont().deriveFont(From.getFont().getStyle() | Font.BOLD));
							From.addMouseListener(new MouseAdapter() {
								@Override
								public void mouseClicked(MouseEvent e) {
									//to DO
								}
							});

							//======== scrollPane1 ========
							{

								//---- RepDescription ----
								RepDescription.setEditable(false);
								scrollPane1.setViewportView(RepDescription);
							}

							//---- Photo ----
							File f = ds.getImageforProfile(m.get_fromUser());
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
							Photo.setHorizontalAlignment(SwingConstants.CENTER);
							Photo.setIcon(new ImageIcon(profilePic));

							GroupLayout MessagePanelLayout = new GroupLayout(MessagePanel);
							MessagePanel.setLayout(MessagePanelLayout);
							MessagePanelLayout.setHorizontalGroup(
									MessagePanelLayout.createParallelGroup()
									.addGroup(GroupLayout.Alignment.TRAILING, MessagePanelLayout.createSequentialGroup()
											.addGroup(MessagePanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
													.addGroup(MessagePanelLayout.createSequentialGroup()
															.addContainerGap()
															.addComponent(separator1, GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE))
															.addGroup(MessagePanelLayout.createSequentialGroup()
																	.addGroup(MessagePanelLayout.createParallelGroup()
																			.addComponent(From, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
																			.addGroup(MessagePanelLayout.createSequentialGroup()
																					.addGap(10, 10, 10)
																					.addComponent(Photo, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)))
																					.addGap(10, 10, 10)
																					.addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)))
																					.addContainerGap())
									);
							MessagePanelLayout.setVerticalGroup(
									MessagePanelLayout.createParallelGroup()
									.addGroup(MessagePanelLayout.createSequentialGroup()
											.addGroup(MessagePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
													.addGroup(MessagePanelLayout.createSequentialGroup()
															.addComponent(From, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
															.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
															.addComponent(Photo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
															.addGroup(MessagePanelLayout.createSequentialGroup()
																	.addContainerGap()
																	.addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)))
																	.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
																	.addComponent(separator1, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
																	.addGap(125, 125, 125))
									);
						}
						MessagesAll.add(MessagePanel);
					}
					MessageScroll.setViewportView(MessagesAll);
				}
			}
		}
			//---- CreateMessage ----
			CreateMessage.setText("Compose Message");
			CreateMessage.setFont(CreateMessage.getFont().deriveFont(CreateMessage.getFont().getStyle() | Font.BOLD, CreateMessage.getFont().getSize()));
			CreateMessage.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					CreateMessageActionPerformed(e);
				}
			});

			GroupLayout ViewAllMessageLayout = new GroupLayout(ViewAllMessage);
			ViewAllMessage.setLayout(ViewAllMessageLayout);
			ViewAllMessageLayout.setHorizontalGroup(
					ViewAllMessageLayout.createParallelGroup()
					.addGroup(ViewAllMessageLayout.createParallelGroup()
							.addGroup(ViewAllMessageLayout.createSequentialGroup()
									.addContainerGap()
									.addComponent(MessageScroll, GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
									.addContainerGap()))
									.addGroup(ViewAllMessageLayout.createSequentialGroup()
											.addGap(140, 140, 140)
											.addComponent(CreateMessage, GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
											.addContainerGap(50, Short.MAX_VALUE))
					);
			ViewAllMessageLayout.setVerticalGroup(
					ViewAllMessageLayout.createParallelGroup()
					.addGroup(ViewAllMessageLayout.createParallelGroup()
							.addGroup(GroupLayout.Alignment.TRAILING, ViewAllMessageLayout.createSequentialGroup()
									.addGap(62, 62, 62)
									.addComponent(MessageScroll, GroupLayout.PREFERRED_SIZE, 452, GroupLayout.PREFERRED_SIZE)
									.addContainerGap()))
									.addGroup(ViewAllMessageLayout.createSequentialGroup()
											.addContainerGap()
											.addComponent(CreateMessage, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
											.addContainerGap(477, Short.MAX_VALUE))
					);
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JPanel ViewAllMessage;
	private JScrollPane MessageScroll;
	private JPanel MessagesAll;
	private JPanel MessagePanel;
	private JLabel From;
	private JScrollPane scrollPane1;
	private JTextArea RepDescription;
	private JLabel Photo;
	private JSeparator separator1;
	private JButton CreateMessage;
	private DataStorage ds;
	private String username;
	private SuperGUI myFrame;

	public static final String NAME = "All Message";

	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
