package gui;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import jdbc_storage.DataStorage;
import jdbc_storage.Recipe;



/**
 * @author Nguyen
 */
public class TopRep  {
	public TopRep(String username,DataStorage ds, SuperGUI frame, int i){
		this.username = username;
		this.ds = ds;
		this.frame = frame;
		initComponents(i);
	}

	public JScrollPane getScreen(){
		return RecipeScroll;
	}

	private void RepNameMouseClicked(MouseEvent e) {
		// TODO add your code here
	}

	private void initComponents(int number) {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		RecipeScroll = new JScrollPane();
		RecipeAll = new JPanel();
		ArrayList<Recipe> topReps = ds.getTopRecipes(number);

		for (int i = 0; i < number && i < topReps.size(); i++ ){
			Recipe recipe = topReps.get(i);
			FoodPanel = new JPanel();
			RepName = new JLabel();
			scrollPane1 = new JScrollPane();
			RepDescription = new JTextArea();
			FoodPhoto = new JLabel();
			separator1 = new JSeparator();
			Rating = new JLabel();

			//======== RecipeScroll ========
			{
				RecipeScroll.setMaximumSize(new Dimension(240, 32767));
				RecipeScroll.setMinimumSize(new Dimension(240, 565));
				RecipeScroll.setPreferredSize(new Dimension(240, 565));
				//======== RecipeAll ========
				{
					RecipeAll.setLayout(new GridLayout(0, 1));

					//======== FoodPanel ========
					{
						FoodPanel.setBorder(new EtchedBorder());
						FoodPanel.setMinimumSize(new Dimension(240, 109));
						FoodPanel.setMaximumSize(new Dimension(240, 109));
						FoodPanel.setPreferredSize(new Dimension(240, 109));

						//---- RepName ----
						RepName.setText(recipe.get_recipeName());
						RepName.setHorizontalAlignment(SwingConstants.CENTER);
						RepName.setFont(RepName.getFont().deriveFont(RepName.getFont().getStyle() | Font.BOLD));
						RepName.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								RepNameMouseClicked(e);
							}
						});

						//======== scrollPane1 ========
						{

							//---- RepDescription ----
							RepDescription.setEditable(false);
							RepDescription.setText("Posted by: " + recipe.get_uid() + "\n" + recipe.get_recipeDescription());
							scrollPane1.setViewportView(RepDescription);
						}

						//---- FoodPhoto ----
						File pic = ds.getImageForRecipe(recipe.get_uid(), recipe.get_recipeName());
						BufferedImage profilePic = null;
						try {
							if(pic == null)
								profilePic = ImageIO.read(new File("profilepic.jpg"));
							else
								profilePic = ImageIO.read(pic);
						} catch (IOException e) {
							//Auto-generated catch block
							e.printStackTrace();
						}
						FoodPhoto.setHorizontalAlignment(SwingConstants.CENTER);
						FoodPhoto.setIcon(new ImageIcon(SuperGUI.convertSize(profilePic, 50)));
						

						//---- Rating ----
						Rating.setText("Rating: " + recipe.get_rating() + "/5");
						Rating.setHorizontalAlignment(SwingConstants.CENTER);

						GroupLayout FoodPanelLayout = new GroupLayout(FoodPanel);
						FoodPanel.setLayout(FoodPanelLayout);
						FoodPanelLayout.setHorizontalGroup(
								FoodPanelLayout.createParallelGroup()
								.addGroup(FoodPanelLayout.createSequentialGroup()
										.addGroup(FoodPanelLayout.createParallelGroup()
												.addGroup(GroupLayout.Alignment.TRAILING, FoodPanelLayout.createSequentialGroup()
														.addGroup(FoodPanelLayout.createParallelGroup()
																.addComponent(RepName, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
																.addGroup(FoodPanelLayout.createSequentialGroup()
																		.addGap(29, 29, 29)
																		.addComponent(FoodPhoto, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
																		.addGroup(GroupLayout.Alignment.TRAILING, FoodPanelLayout.createSequentialGroup()
																				.addContainerGap()
																				.addComponent(Rating, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
																				.addGap(8, 8, 8)))
																				.addGap(10, 10, 10)
																				.addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE))
																				.addGroup(GroupLayout.Alignment.TRAILING, FoodPanelLayout.createSequentialGroup()
																						.addContainerGap()
																						.addComponent(separator1, GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE)))
																						.addContainerGap())
								);
						FoodPanelLayout.setVerticalGroup(
								FoodPanelLayout.createParallelGroup()
								.addGroup(FoodPanelLayout.createSequentialGroup()
										.addGroup(FoodPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
												.addGroup(FoodPanelLayout.createSequentialGroup()
														.addComponent(RepName, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
														.addGap(3, 3, 3)
														.addComponent(Rating)
														.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
														.addComponent(FoodPhoto, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
														.addGroup(FoodPanelLayout.createSequentialGroup()
																.addContainerGap()
																.addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)))
																.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(separator1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																.addContainerGap(436, Short.MAX_VALUE))
								);
					}
					RecipeAll.add(FoodPanel);
				}
				RecipeScroll.setViewportView(RecipeAll);
			}
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JScrollPane RecipeScroll;
	private JPanel RecipeAll;
	private JPanel FoodPanel;
	private JLabel RepName;
	private JScrollPane scrollPane1;
	private JTextArea RepDescription;
	private JLabel FoodPhoto;
	private JSeparator separator1;
	private JLabel Rating;
	private DataStorage ds;
	private String username;
	private SuperGUI frame;

	public static final String NAME = "Top Recipe";
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
