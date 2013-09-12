package gui;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import jdbc_storage.DataStorage;
import jdbc_storage.Recipe;
import jdbc_storage.Comment;



/**
 * @author Nguyen
 */
public class RecipePanel {
	public RecipePanel() {
		initComponents();
	}

	public JPanel getScreen(){
		return RecipePanel;
	}

	public RecipePanel(String username, DataStorage ds, SuperGUI frame){
		this.username = username;
		this.ds = ds;
		myFrame = frame;
		r = ds.getRecipes();
		initComponents();		
	}

	private void CreateRepActionPerformed(ActionEvent e) {
		myFrame.getCenter().setScreen(CreateRecipe.NAME);
		// TODO add your code here
	}

	private void SeeALlRepActionPerformed(ActionEvent e) {
		myFrame.getCenter().setScreen(ViewAllRep.NAME);
		// TODO add your code here
	}

	private void SubmitActionPerformed(ActionEvent e) {
		ds.sendComment(r.get(0).get_uid(), r.get(0).get_recipeName(), CommentAra.getText());
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		RecipePanel = new JPanel();
		CreateRep = new JButton();
		SeeALlRep = new JButton();
		RepName = new JLabel();
		scrollPane1 = new JScrollPane();
		DescriptionT = new JTextArea();
		scrollPane2 = new JScrollPane();
		IngredientT = new JTextArea();
		scrollPane3 = new JScrollPane();
		StepT = new JTextArea();
		star1 = new JRadioButton();
		star2 = new JRadioButton();
		star3 = new JRadioButton();
		star4 = new JRadioButton();
		star5 = new JRadioButton();
		Rating = new JLabel();
		CommPanel = new JPanel();
		CommList = new JScrollPane();
		CommContainer = new JPanel();
		
		separator1 = new JSeparator();
		scrollPane4 = new JScrollPane();
		CommentAra = new JTextArea();
		Submit = new JButton();
		Photo = new JLabel();
		ArrayList<JRadioButton> stars = new ArrayList<JRadioButton>();

		//======== RecipePanel ========
		{

			//---- CreateRep ----
			CreateRep.setText("Create Recipe");
			CreateRep.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					CreateRepActionPerformed(e);
				}
			});

			//---- SeeALlRep ----
			SeeALlRep.setText("All Recipe");
			SeeALlRep.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					SeeALlRepActionPerformed(e);
				}
			});

			//---- RepName ----
			String nameJDBC;
			if(r.isEmpty())
				nameJDBC = "No recipes created";
			else
				nameJDBC = r.get(0).get_recipeName(); //database for recipeName
			RepName.setText(nameJDBC);
			RepName.setHorizontalAlignment(SwingConstants.CENTER);
			RepName.setFont(RepName.getFont().deriveFont(RepName.getFont().getSize() + 8f));
			RepName.setBorder(new BevelBorder(BevelBorder.LOWERED));

			//======== scrollPane1 ========
			{

				//---- DescriptionT ----
				String descrJDBC;
				if(r.isEmpty())
					descrJDBC = "";
				else
					descrJDBC = r.get(0).get_recipeDescription(); //Queries database for a recipeDescription 
				DescriptionT.setText(descrJDBC);
				DescriptionT.setLineWrap(true);
				DescriptionT.setBorder(new CompoundBorder(
						new TitledBorder(null, "Description", TitledBorder.LEADING, TitledBorder.TOP),
						new EmptyBorder(5, 5, 5, 5)));
				DescriptionT.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
				DescriptionT.setEditable(false);
				scrollPane1.setViewportView(DescriptionT);
			}

			//======== scrollPane2 ========
			{

				//---- IngredientT ----
				String ingrJDBC;
				if(r.isEmpty())
					ingrJDBC = "";
				else
					ingrJDBC = r.get(0).get_recipeIngrediants(); //database recipeIngrediants
				JTextField ingredients = new JTextField(ingrJDBC);
				IngredientT.setText(ingrJDBC);
				IngredientT.setLineWrap(true);
				IngredientT.setBorder(new CompoundBorder(
						new TitledBorder(null, "Ingredients", TitledBorder.LEADING, TitledBorder.TOP),
						new EmptyBorder(5, 5, 5, 5)));
				IngredientT.setEditable(false);
				scrollPane2.setViewportView(IngredientT);
			}

			//======== scrollPane3 ========
			{

				//---- StepT ----
				String stepsJDBC;
				if(r.isEmpty())
					stepsJDBC = "";
				else
					stepsJDBC = r.get(0).get_recipeSteps(); //database query recipeSteps
				JTextField steps = new JTextField(stepsJDBC);
				StepT.setText(stepsJDBC);
				StepT.setBorder(new CompoundBorder(
						new TitledBorder(null, "Instruction", TitledBorder.LEADING, TitledBorder.TOP),
						new EmptyBorder(5, 5, 5, 5)));
				StepT.setEditable(false);
				scrollPane3.setViewportView(StepT);
			}

			//---- star1 ----
			star1.setText("1");

			//---- star2 ----
			star2.setText("2");

			//---- star3 ----
			star3.setText("3");

			//---- star4 ----
			star4.setText("4");

			//---- star5 ----
			star5.setText("5");

			//---- addStars ----
			stars.add(star1);
			stars.add(star2);
			stars.add(star3);
			stars.add(star4);
			stars.add(star5);

			//---- Rating ----
			int rating = r.get(0).get_rating();
			Rating.setText("Rated " + rating + "/5");

			for(JRadioButton s:stars)
				s.setEnabled(false);
			stars.get(rating-1).setSelected(true);

			Rating.setHorizontalAlignment(SwingConstants.CENTER);
			Rating.setFont(Rating.getFont().deriveFont(Rating.getFont().getStyle() | Font.BOLD));

			//======== CommPanel ========
			{
				CommPanel.setBorder(new EtchedBorder());

				//======== CommList ========
				{
					CommList.setBorder(new EtchedBorder());

					//======== CommContainer ========
					{
						CommContainer.setLayout(new GridLayout(0,1));

						ArrayList<Comment> comments = ds.getComments(r.get(0).get_uid(), r.get(0).get_recipeName());
						for (int i = 0; i < 6 && i < comments.size(); i++ ){
							Comment c = comments.get(i);
							CommentDialog = new JPanel();
							ScrollText = new JScrollPane();
							Comment = new JTextArea();
							Commenter = new JLabel();
							//======== CommentDialog ========
							{
								CommentDialog.setPreferredSize(new Dimension(150, 90));

								//======== ScrollText ========
								{

									//---- Comment ----
									Comment.setText(c.get_comment());
									Comment.setEditable(false);
									ScrollText.setViewportView(Comment);
								}

								//---- Commenter ----
								Commenter.setText(c.get_poster() + ": " + c.get_postDate());
								Commenter.setFont(Commenter.getFont().deriveFont(Commenter.getFont().getStyle() | Font.BOLD));

								GroupLayout CommentDialogLayout = new GroupLayout(CommentDialog);
								CommentDialog.setLayout(CommentDialogLayout);
								CommentDialogLayout.setHorizontalGroup(
										CommentDialogLayout.createParallelGroup()
										.addGroup(GroupLayout.Alignment.TRAILING, CommentDialogLayout.createSequentialGroup()
												.addContainerGap()
												.addGroup(CommentDialogLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
														.addComponent(separator1, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
														.addComponent(ScrollText, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
														.addComponent(Commenter, GroupLayout.Alignment.LEADING))
														.addContainerGap())
										);
								CommentDialogLayout.setVerticalGroup(
										CommentDialogLayout.createParallelGroup()
										.addGroup(CommentDialogLayout.createSequentialGroup()
												.addContainerGap()
												.addComponent(Commenter)
												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(ScrollText, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(separator1, GroupLayout.PREFERRED_SIZE, 5, GroupLayout.PREFERRED_SIZE)
												.addContainerGap(105, Short.MAX_VALUE))
										);
							}
							CommContainer.add(CommentDialog);
						}
					}
					CommList.setViewportView(CommContainer);
				}


				//======== scrollPane4 ========
				{
					scrollPane4.setViewportView(CommentAra);
				}

				//---- Submit ----
				Submit.setText("Submit");
				Submit.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						SubmitActionPerformed(e);
					}
				});

				GroupLayout CommPanelLayout = new GroupLayout(CommPanel);
				CommPanel.setLayout(CommPanelLayout);
				CommPanelLayout.setHorizontalGroup(
						CommPanelLayout.createParallelGroup()
						.addComponent(CommList, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
						.addGroup(CommPanelLayout.createSequentialGroup()
								.addGap(10, 10, 10)
								.addComponent(scrollPane4, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
								.addGap(10, 10, 10))
								.addGroup(GroupLayout.Alignment.TRAILING, CommPanelLayout.createSequentialGroup()
										.addContainerGap(34, Short.MAX_VALUE)
										.addComponent(Submit, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
										.addGap(29, 29, 29))
						);
				CommPanelLayout.setVerticalGroup(
						CommPanelLayout.createParallelGroup()
						.addGroup(CommPanelLayout.createSequentialGroup()
								.addComponent(CommList, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(scrollPane4, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(Submit)
								.addContainerGap(27, Short.MAX_VALUE))
						);
			}

			//---- Photo ----
			File pic = ds.getImageForRecipe(ds.getuid(), r.get(0).get_recipeName());
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
			
			
			Photo.setIcon(new ImageIcon(SuperGUI.convertSize(profilePic, 50)));
			Photo.setHorizontalAlignment(SwingConstants.CENTER);
			Photo.setBorder(new EtchedBorder());

			GroupLayout RecipePanelLayout = new GroupLayout(RecipePanel);
			RecipePanel.setLayout(RecipePanelLayout);
			RecipePanelLayout.setHorizontalGroup(
					RecipePanelLayout.createParallelGroup()
					.addGroup(RecipePanelLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(RecipePanelLayout.createParallelGroup()
									.addGroup(RecipePanelLayout.createSequentialGroup()
											.addGroup(RecipePanelLayout.createParallelGroup()
													.addComponent(scrollPane3, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
													.addComponent(scrollPane1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
													.addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE))
													.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
													.addComponent(CommPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
													.addContainerGap())
													.addGroup(GroupLayout.Alignment.TRAILING, RecipePanelLayout.createSequentialGroup()
															.addGroup(RecipePanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
																	.addGroup(RecipePanelLayout.createSequentialGroup()
																			.addGap(61, 61, 61)
																			.addComponent(Photo, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
																			.addGap(45, 45, 45)
																			.addComponent(Rating, GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
																			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
																			.addComponent(star1))
																			.addGroup(RecipePanelLayout.createSequentialGroup()
																					.addComponent(RepName, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
																					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
																					.addComponent(CreateRep, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)))
																					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
																					.addGroup(RecipePanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
																							.addGroup(RecipePanelLayout.createSequentialGroup()
																									.addComponent(star2)
																									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
																									.addComponent(star3)
																									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
																									.addComponent(star4)
																									.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
																									.addComponent(star5))
																									.addComponent(SeeALlRep, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
																									.addGap(20, 20, 20))))
					);
			RecipePanelLayout.setVerticalGroup(
					RecipePanelLayout.createParallelGroup()
					.addGroup(RecipePanelLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(RecipePanelLayout.createParallelGroup()
									.addGroup(RecipePanelLayout.createSequentialGroup()
											.addGroup(RecipePanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
													.addComponent(SeeALlRep, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
													.addComponent(CreateRep, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
													.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
													.addGroup(RecipePanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
															.addComponent(Rating, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
															.addGroup(RecipePanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
																	.addComponent(star1)
																	.addComponent(star2)
																	.addComponent(star3)
																	.addComponent(star4)
																	.addComponent(star5)))
																	.addGap(18, 18, 18)
																	.addComponent(CommPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
																	.addGroup(RecipePanelLayout.createSequentialGroup()
																			.addComponent(RepName, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
																			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
																			.addComponent(Photo, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
																			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
																			.addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
																			.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
																			.addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
																			.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
																			.addComponent(scrollPane3, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)))
																			.addGap(18, 18, 18))
					);
			RecipePanelLayout.linkSize(SwingConstants.VERTICAL, new Component[] {scrollPane1, scrollPane2, scrollPane3});
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JPanel RecipePanel;
	private JButton CreateRep;
	private JButton SeeALlRep;
	private JLabel RepName;
	private JScrollPane scrollPane1;
	private JTextArea DescriptionT;
	private JScrollPane scrollPane2;
	private JTextArea IngredientT;
	private JScrollPane scrollPane3;
	private JTextArea StepT;
	private JRadioButton star1;
	private JRadioButton star2;
	private JRadioButton star3;
	private JRadioButton star4;
	private JRadioButton star5;
	private JLabel Rating;
	private JPanel CommPanel;
	private JScrollPane CommList;
	private JPanel CommContainer;
	private JPanel CommentDialog;
	private JScrollPane ScrollText;
	private JTextArea Comment;
	private JLabel Commenter;
	private JSeparator separator1;
	private JScrollPane scrollPane4;
	private JTextArea CommentAra;
	private JButton Submit;
	private JLabel Photo;
	private SuperGUI myFrame;
	private String username;
	private DataStorage ds;
	private ArrayList<Recipe> r;
	// JFormDesigner - End of variables declaration  //GEN-END:variables

	public static final String NAME = "Full Recipe";
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
