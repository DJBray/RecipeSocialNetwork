package gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import jdbc_storage.DataStorage;
import jdbc_storage.ProfileInfo;
import jdbc_storage.Recipe;



/**
 * @author Nguyen
 */
public class FeedPane  {
	
	/**
	 * Creates the Feed of updates from the Database based on how many are desired
	 * 
	 * @param ds Current DataStorage being used
	 * @param i Number of updates displayed in the feed
	 */
	public FeedPane(DataStorage ds, int i){
		this.ds = ds;
		initComponents(i);
	}

	/**
	 * Gets the Panel of the whole feed
	 * 
	 * @return The whole Feed
	 */
	public JScrollPane getScreen(){
		return FeedPane;
	}
	
	private void RepNameMouseClicked(MouseEvent e) {
		// TODO add your code here
	}

	private ArrayList<Recipe> getFeedData(int number){
		return ds.findRecentRecipes(ds.getuid(), number);
	}
	
	private void initComponents(int number) {
		ArrayList<Recipe> feedData = getFeedData(number);
		
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		FeedPane = new JScrollPane();
		FeedPanek2 = new JPanel();

		for (int i = 0; i < number && i < feedData.size(); i++ ){
			Recipe userRecipe = feedData.get(i);
			Feed = new JPanel();
			PostedUser = new JLabel();
			RepName = new JLabel();
			scrollPane1 = new JScrollPane();
			Description = new JTextArea();

			//======== FeedPane ========
			{
				FeedPane.setMaximumSize(new Dimension(240, 32767));
				FeedPane.setMinimumSize(new Dimension(240, 565));
				FeedPane.setPreferredSize(new Dimension(240, 565));

				//======== FeedPanek2 ========
				{
					FeedPanek2.setLayout(new GridLayout(0, 1));

					//======== Feed ========
					{
						Feed.setMinimumSize(new Dimension(205, 140));
						Feed.setMaximumSize(new Dimension(205, 140));
						Feed.setPreferredSize(new Dimension(170, 175));

						//---- PostedUser ----
						PostedUser.setText("Posted by " + userRecipe.get_uid() + ":" + ProfileInfo.parseBirthday(userRecipe.get_dateMade()));
						PostedUser.setHorizontalAlignment(SwingConstants.CENTER);
						PostedUser.setFont(PostedUser.getFont().deriveFont(PostedUser.getFont().getStyle() | Font.BOLD, PostedUser.getFont().getSize() + 2f));
						PostedUser.setBorder(new LineBorder(Color.lightGray, 1, true));

						//---- RepName ----
						RepName.setText(userRecipe.get_recipeName());
						RepName.setFont(RepName.getFont().deriveFont(RepName.getFont().getStyle() | Font.BOLD, RepName.getFont().getSize() + 4f));
						RepName.setHorizontalAlignment(SwingConstants.CENTER);
						RepName.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								RepNameMouseClicked(e);
							}
						});

						//======== scrollPane1 ========
						{

							//---- Description ----
							Description.setText(userRecipe.get_recipeDescription());
							Description.setLineWrap(true);
							Description.setEditable(false);
							scrollPane1.setViewportView(Description);
						}

						GroupLayout FeedLayout = new GroupLayout(Feed);
						Feed.setLayout(FeedLayout);
						FeedLayout.setHorizontalGroup(
							FeedLayout.createParallelGroup()
								.addGroup(FeedLayout.createSequentialGroup()
									.addContainerGap()
									.addGroup(FeedLayout.createParallelGroup()
										.addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
										.addComponent(RepName, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
										.addComponent(PostedUser, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
									.addGap(43, 43, 43))
						);
						FeedLayout.setVerticalGroup(
							FeedLayout.createParallelGroup()
								.addGroup(FeedLayout.createSequentialGroup()
									.addContainerGap()
									.addComponent(PostedUser)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addComponent(RepName, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
									.addContainerGap(404, Short.MAX_VALUE))
						);
					}
					FeedPanek2.add(Feed);
				}
				FeedPane.setViewportView(FeedPanek2);
			}
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JScrollPane FeedPane;
	private JPanel FeedPanek2;
	private JPanel Feed;
	private JLabel PostedUser;
	private JLabel RepName;
	private JScrollPane scrollPane1;
	private JTextArea Description;
	private JPanel fullFeed = null;
	private DataStorage ds;
	
	// JFormDesigner - End of variables declaration  //GEN-END:variables

	public static final String NAME = "Feed";
}
