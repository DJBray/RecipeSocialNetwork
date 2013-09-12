package gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;

import jdbc_storage.DataStorage;



/**
 * @author Nguyen
 */
public class ComposeMsg  {
	public ComposeMsg(String username, DataStorage ds,SuperGUI frame) {
		this.username = username;
		myFrame = frame;
		this.ds = ds;
		initComponents();
	}
	
	public void send(ActionEvent e){
		String to = ToTextField.getText();
		String content = ContentArea.getText();
		Calendar c = Calendar.getInstance();
		ds.sendMessage(to, "message", content, c.get(Calendar.YEAR)*10000 + c.get(Calendar.MONTH)*100 + c.get(Calendar.DATE));
		myFrame.getCenter().setScreen(ViewAllMessage.NAME);
	}
	
	public void cancel(ActionEvent e){
		myFrame.getCenter().setScreen(ViewAllMessage.NAME);
	}

	public JPanel getScreen(){
		return ComposeMessage;
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		ComposeMessage = new JPanel();
		TItle = new JLabel();
		ToTextField = new JTextField();
		ToLB = new JLabel();
		scrollPane1 = new JScrollPane();
		ContentArea = new JTextArea();
		ContentLB = new JLabel();
		Send = new JButton();
		Cancel = new JButton();

		//======== ComposeMessage ========
		{
			ComposeMessage.setPreferredSize(new Dimension(515, 575));

			//---- TItle ----
			TItle.setText("Compose Message");
			TItle.setHorizontalAlignment(SwingConstants.CENTER);
			TItle.setFont(TItle.getFont().deriveFont(TItle.getFont().getStyle() | Font.BOLD, TItle.getFont().getSize() + 4f));
			TItle.setForeground(Color.red);

			//---- ToLB ----
			ToLB.setText("TO");
			ToLB.setHorizontalAlignment(SwingConstants.CENTER);
			ToLB.setFont(ToLB.getFont().deriveFont(ToLB.getFont().getStyle() | Font.BOLD, ToLB.getFont().getSize() + 4f));

			//======== scrollPane1 ========
			{
				scrollPane1.setViewportView(ContentArea);
			}

			//---- ContentLB ----
			ContentLB.setText("Content");
			ContentLB.setHorizontalAlignment(SwingConstants.CENTER);
			ContentLB.setFont(ContentLB.getFont().deriveFont(ContentLB.getFont().getStyle() | Font.BOLD, ContentLB.getFont().getSize() + 4f));

			//---- Send ----
			Send.setText("Send");
			Send.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					send(e);
				}
			});
			Send.setFont(Send.getFont().deriveFont(Send.getFont().getStyle() | Font.BOLD, Send.getFont().getSize() + 3f));

			//---- Cancel ----
			Cancel.setText("Cancel");
			Cancel.setFont(Cancel.getFont().deriveFont(Cancel.getFont().getStyle() | Font.BOLD, Cancel.getFont().getSize() + 3f));
			Cancel.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					cancel(e);
				}
			});
			GroupLayout ComposeMessageLayout = new GroupLayout(ComposeMessage);
			ComposeMessage.setLayout(ComposeMessageLayout);
			ComposeMessageLayout.setHorizontalGroup(
				ComposeMessageLayout.createParallelGroup()
					.addGroup(GroupLayout.Alignment.TRAILING, ComposeMessageLayout.createSequentialGroup()
						.addGap(28, 28, 28)
						.addGroup(ComposeMessageLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
							.addComponent(ToLB, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
							.addComponent(ContentLB, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18)
						.addGroup(ComposeMessageLayout.createParallelGroup()
							.addGroup(GroupLayout.Alignment.TRAILING, ComposeMessageLayout.createSequentialGroup()
								.addComponent(TItle, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
								.addGap(167, 167, 167))
							.addGroup(ComposeMessageLayout.createSequentialGroup()
								.addGroup(ComposeMessageLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
									.addComponent(scrollPane1, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
									.addComponent(ToTextField, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 259, GroupLayout.PREFERRED_SIZE))
								.addGap(127, 127, 127))))
					.addGroup(ComposeMessageLayout.createSequentialGroup()
						.addGap(148, 148, 148)
						.addComponent(Send, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
						.addGap(31, 31, 31)
						.addComponent(Cancel, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(148, Short.MAX_VALUE))
			);
			ComposeMessageLayout.setVerticalGroup(
				ComposeMessageLayout.createParallelGroup()
					.addGroup(ComposeMessageLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(ComposeMessageLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
							.addComponent(ToLB)
							.addGroup(ComposeMessageLayout.createSequentialGroup()
								.addComponent(TItle, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18)
								.addComponent(ToTextField, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)))
						.addGroup(ComposeMessageLayout.createParallelGroup()
							.addGroup(ComposeMessageLayout.createSequentialGroup()
								.addGap(18, 18, 18)
								.addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))
							.addGroup(ComposeMessageLayout.createSequentialGroup()
								.addGap(30, 30, 30)
								.addComponent(ContentLB)))
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(ComposeMessageLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(Send, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
							.addComponent(Cancel, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(49, Short.MAX_VALUE))
			);
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JPanel ComposeMessage;
	private JLabel TItle;
	private JTextField ToTextField;
	private JLabel ToLB;
	private JScrollPane scrollPane1;
	private JTextArea ContentArea;
	private JLabel ContentLB;
	private JButton Send;
	private JButton Cancel;
	private DataStorage ds;
	private String username;
	private SuperGUI myFrame;


	public static final String NAME = "Compose Message";
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
