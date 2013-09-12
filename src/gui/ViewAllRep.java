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
import jdbc_storage.Recipe;



/**
 * @author Nguyen
 */
public class ViewAllRep  {
	public ViewAllRep(String username,DataStorage ds, SuperGUI frame){
		this.username = username;
		this.ds = ds;
		this.frame = frame;
		initComponents();
	}

	public JPanel getScreen(){
		return HomePage;
	}
	
	public void update(){
		HomePage.removeAll();
		initComponents();
		HomePage.revalidate();
	}

	private void CreateRepActionPerformed(ActionEvent e) {
		frame.getCenter().setScreen(CreateRecipe.NAME);
	}

	private void initComponents() {
		ArrayList<Recipe> recipes = ds.getRecipes();
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		HomePage = new JPanel();
		RecipeScroll = new JScrollPane();
		RecipeAll = new JPanel();
		CreateRep = new JButton();
		

		for (int i = 0; i < recipes.size(); i++ ){
			Recipe myRecipe = recipes.get(i);
			File pic = ds.getImageForRecipe(myRecipe.get_uid(), myRecipe.get_recipeName());
			
			FoodPanel = new JPanel();
			RepName = new JLabel();
			scrollPane1 = new JScrollPane();
			RepDescription = new JTextArea();
			RepDescription.setText(myRecipe.get_recipeDescription());
			FoodPhoto = new JLabel();
			separator1 = new JSeparator();


			//======== HomePage ========
			{

				//======== RecipeScroll ========
				{

					//======== RecipeAll ========
					{
						RecipeAll.setLayout(new GridLayout(0, 1));
						RecipeAll.setPreferredSize(new Dimension(350, recipes.size()*109));

						//======== FoodPanel ========
						{
							FoodPanel.setBorder(new EtchedBorder());
							FoodPanel.setMinimumSize(new Dimension(350, 109));
							FoodPanel.setMaximumSize(new Dimension(350, 150));
							FoodPanel.setPreferredSize(new Dimension(350, 109));

							//---- RepName ----
							RepName.setText(myRecipe.get_recipeName());
							RepName.setHorizontalAlignment(SwingConstants.CENTER);
							RepName.setFont(RepName.getFont().deriveFont(RepName.getFont().getStyle() | Font.BOLD));
							RepName.addMouseListener(new MouseAdapter() {
								@Override
								public void mouseClicked(MouseEvent e) {
									
								}
							});

							//======== scrollPane1 ========
							{

								//---- RepDescription ----
								RepDescription.setEditable(false);
								scrollPane1.setViewportView(RepDescription);
							}

							//---- FoodPhoto ----
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

							GroupLayout FoodPanelLayout = new GroupLayout(FoodPanel);
							FoodPanel.setLayout(FoodPanelLayout);
							FoodPanelLayout.setHorizontalGroup(
									FoodPanelLayout.createParallelGroup()
									.addGroup(GroupLayout.Alignment.TRAILING, FoodPanelLayout.createSequentialGroup()
											.addGroup(FoodPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
													.addGroup(FoodPanelLayout.createSequentialGroup()
															.addContainerGap()
															.addComponent(separator1, GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE))
															.addGroup(FoodPanelLayout.createSequentialGroup()
																	.addGroup(FoodPanelLayout.createParallelGroup()
																			.addComponent(RepName, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
																			.addGroup(FoodPanelLayout.createSequentialGroup()
																					.addGap(10, 10, 10)
																					.addComponent(FoodPhoto, 50, 50,50)))
																					.addGap(10, 10, 10)
																					.addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)))
																					.addContainerGap())
									);
							FoodPanelLayout.setVerticalGroup(
									FoodPanelLayout.createParallelGroup()
									.addGroup(FoodPanelLayout.createSequentialGroup()
											.addGroup(FoodPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
													.addGroup(FoodPanelLayout.createSequentialGroup()
															.addComponent(RepName, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
															.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
															.addComponent(FoodPhoto, 50, 50, 50))
															.addGroup(FoodPanelLayout.createSequentialGroup()
																	.addContainerGap()
																	.addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)))
																	.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
																	.addComponent(separator1, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
																	.addGap(125, 125, 125))
									);
						}
						RecipeAll.add(FoodPanel);
					}
					RecipeScroll.setViewportView(RecipeAll);
				}
			}

			//---- CreateRep ----
			CreateRep.setText("Create Recipe");
			CreateRep.setFont(CreateRep.getFont().deriveFont(CreateRep.getFont().getStyle() | Font.BOLD, CreateRep.getFont().getSize()));
			CreateRep.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					CreateRepActionPerformed(e);
				}
			});

			GroupLayout HomePageLayout = new GroupLayout(HomePage);
			HomePage.setLayout(HomePageLayout);
			HomePageLayout.setHorizontalGroup(
					HomePageLayout.createParallelGroup()
					.addGroup(HomePageLayout.createParallelGroup()
							.addGroup(HomePageLayout.createSequentialGroup()
									.addContainerGap()
									.addComponent(RecipeScroll, GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
									.addContainerGap()))
									.addGroup(HomePageLayout.createSequentialGroup()
											.addGap(140, 140, 140)
											.addComponent(CreateRep, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE)
											.addContainerGap(145, Short.MAX_VALUE))
					);
			HomePageLayout.setVerticalGroup(
					HomePageLayout.createParallelGroup()
					.addGroup(HomePageLayout.createParallelGroup()
							.addGroup(GroupLayout.Alignment.TRAILING, HomePageLayout.createSequentialGroup()
									.addContainerGap(62, Short.MAX_VALUE)
									.addComponent(RecipeScroll, GroupLayout.PREFERRED_SIZE, 452, GroupLayout.PREFERRED_SIZE)
									.addContainerGap()))
									.addGroup(HomePageLayout.createSequentialGroup()
											.addContainerGap()
											.addComponent(CreateRep, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
											.addContainerGap(477, Short.MAX_VALUE))
					);
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JPanel HomePage;
	private JScrollPane RecipeScroll;
	private JPanel RecipeAll;
	private JPanel FoodPanel;
	private JLabel RepName;
	private JScrollPane scrollPane1;
	private JTextArea RepDescription;
	private JLabel FoodPhoto;
	private JSeparator separator1;
	private JButton CreateRep;
	private DataStorage ds;
	private String username;
	private SuperGUI frame;

	public static final String NAME = "All Recipe";
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
