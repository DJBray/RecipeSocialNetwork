package gui;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import jdbc_storage.DataStorage;
import jdbc_storage.Recipe;



/**
 * @author Nguyen
 */
public class SearchPane  {
	public static final int SEARCH_LIMIT = 100;

	public SearchPane(String username,DataStorage ds){
		this.username = username;
		this.ds = ds;
		initComponents(0);
	}

	public JPanel getScreen(){
		return SearchPage;
	}

	private void AddActionPerformed(ActionEvent e) {
		//ds.addFriend(friend);
	}

	private void ViewActionPerformed(ActionEvent e) {
		// TODO add your code here
	}

	private void SearchPPActionPerformed(ActionEvent e) {
		SeachPanel.removeAll();
		SeachPanel.revalidate();
		{
			SearchScroll.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			SearchScroll.setInheritsPopupMenu(true);
			SearchScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			
			

			//======== SeachPanel ========



			{
				SeachPanel.setLayout(new GridLayout(0, 1));


				// ======= If people click Seach people button 
				HashSet<String> searchResults = new HashSet<String>();

				searchResults.addAll(ds.findUser(PeopleSeach.getText(), DataStorage.STYPE_UID));
				searchResults.addAll(ds.findUser(PeopleSeach.getText(), DataStorage.STYPE_LAST_NAME));
				searchResults.addAll(ds.findUser(PeopleSeach.getText(), DataStorage.STYPE_FIRST_NAME));
				SearchScroll.setPreferredSize(new Dimension(240, searchResults.size()*109));
				SeachPanel.setPreferredSize(new Dimension(240, 109));
				ArrayList<String> list = new ArrayList<String>();
				list.addAll(searchResults);
				for(int i = 0; i<list.size() && i<SEARCH_LIMIT; i++){
					String person = list.get(i);
					People = new JPanel();
					PeopleName = new JLabel();
					PhotoP = new JLabel();
					Add = new JButton();
					separator1 = new JSeparator();

					//======== People ========
					{
						People.setBorder(null);
						People.setPreferredSize(new Dimension(109, 71));
						People.setAutoscrolls(true);

						//---- PeopleName ----
						PeopleName.setText(person);
						PeopleName.setHorizontalAlignment(SwingConstants.CENTER);
						PeopleName.setFont(PeopleName.getFont().deriveFont(PeopleName.getFont().getStyle() | Font.BOLD));

						//---- PhotoP ----
						File pic = ds.getImageforProfile(person);
						BufferedImage profilePic = null;
						try {
							if(pic == null)
								profilePic = ImageIO.read(new File("profilepic.jpg"));
							else
								profilePic = ImageIO.read(pic);
						} catch (IOException e1) {
							//Auto-generated catch block
							e1.printStackTrace();
						}
						PhotoP.setIcon(new ImageIcon(SuperGUI.convertSize(profilePic, 50)));
						PhotoP.setHorizontalAlignment(SwingConstants.CENTER);

						//---- Add ----
						Add.setText("Add");
						Add.addActionListener(new AddListener(person));

						GroupLayout PeopleLayout = new GroupLayout(People);
						PeopleLayout.setHonorsVisibility(false);
						People.setLayout(PeopleLayout);
						PeopleLayout.setHorizontalGroup(
								PeopleLayout.createParallelGroup()
								.addGroup(PeopleLayout.createSequentialGroup()
										.addGap(5, 5, 5)
										.addComponent(PeopleName, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(PhotoP, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
										.addGap(203, 203, 203)
										.addComponent(Add, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
										.addGap(42, 42, 42))
										.addGroup(PeopleLayout.createSequentialGroup()
												.addContainerGap()
												.addComponent(separator1, GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)
												.addGap(5, 5, 5))
								);
						PeopleLayout.setVerticalGroup(
								PeopleLayout.createParallelGroup()
								.addGroup(PeopleLayout.createSequentialGroup()
										.addGap(5, 5, 5)
										.addGroup(PeopleLayout.createParallelGroup()
												.addComponent(Add, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
												.addGroup(PeopleLayout.createSequentialGroup()
														.addGroup(PeopleLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
																.addComponent(PhotoP, GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
																.addComponent(PeopleName, GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE))
																.addGap(1, 1, 1)))
																.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(separator1, GroupLayout.PREFERRED_SIZE, 5, GroupLayout.PREFERRED_SIZE)
																.addGap(109, 109, 109))
								);
					}
					SeachPanel.add(People);
				}


			}

			SearchScroll.setViewportView(SeachPanel);

		}
		SearchScroll.revalidate();

		// TODO add your code here
	}

	private void SearchRPActionPerformed(ActionEvent e) {
		SeachPanel.removeAll();
		SeachPanel.revalidate();
		{
			SearchScroll.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			SearchScroll.setInheritsPopupMenu(true);
			SearchScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

			//======== SeachPanel ========
			SeachPanel.setLayout(new GridLayout(0, 1));
			
			HashSet<Recipe> searchResults = new HashSet<Recipe>();
			searchResults.addAll(ds.findRecipe(RecipeSearch.getText(), DataStorage.STYPE_RECIPE_NAME));
			searchResults.addAll(ds.findRecipe(RecipeSearch.getText(), DataStorage.STYPE_RECIPE_INGREDIANTS));
			searchResults.addAll(ds.findRecipe(RecipeSearch.getText(), DataStorage.STYPE_RECIPE_DESCRIPTION));
			searchResults.addAll(ds.findRecipe(RecipeSearch.getText(), DataStorage.STYPE_RECIPE_STEPS));
			SearchScroll.setPreferredSize(new Dimension(240, searchResults.size()*109));
			SeachPanel.setPreferredSize(new Dimension(240, searchResults.size()*109));
			ArrayList<Recipe> list = new ArrayList<Recipe>();
			list.addAll(searchResults);
			for(int i = 0; i < list.size() && i < SEARCH_LIMIT; i++){
				Recipe found = list.get(i);
				Recipe = new JPanel();
				RecipeName = new JLabel();
				PhotoR = new JLabel();
				View = new JButton();
				separator2 = new JSeparator();
				//======== Recipe ========
				{
					Recipe.setBorder(null);
					Recipe.setPreferredSize(new Dimension(109, 71));
					Recipe.setAutoscrolls(true);

					//---- RecipeName ----
					RecipeName.setText(found.get_recipeName());
					RecipeName.setHorizontalAlignment(SwingConstants.CENTER);
					RecipeName.setFont(RecipeName.getFont().deriveFont(RecipeName.getFont().getStyle() | Font.BOLD));

					//---- PhotoR ----
					File pic = ds.getImageForRecipe(found.get_uid(), found.get_recipeName());
					BufferedImage profilePic = null;
					try {
						if(pic == null)
							profilePic = ImageIO.read(new File("profilepic.jpg"));
						else
							profilePic = ImageIO.read(pic);
					} catch (IOException e2) {
						//Auto-generated catch block
						e2.printStackTrace();
					}
					PhotoR.setIcon(new ImageIcon(SuperGUI.convertSize(profilePic, 50)));
					PhotoR.setHorizontalAlignment(SwingConstants.CENTER);

					//---- View ----
					View.setText("View");
					View.addActionListener(new ViewListener(found));

					GroupLayout RecipeLayout = new GroupLayout(Recipe);
					RecipeLayout.setHonorsVisibility(false);
					Recipe.setLayout(RecipeLayout);
					RecipeLayout.setHorizontalGroup(
							RecipeLayout.createParallelGroup()
							.addGroup(RecipeLayout.createSequentialGroup()
									.addGap(5, 5, 5)
									.addComponent(RecipeName, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addComponent(PhotoR, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
									.addGap(203, 203, 203)
									.addComponent(View, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
									.addGap(38, 38, 38))
									.addGroup(GroupLayout.Alignment.TRAILING, RecipeLayout.createSequentialGroup()
											.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(separator2, GroupLayout.PREFERRED_SIZE, 433, GroupLayout.PREFERRED_SIZE)
											.addContainerGap())
							);
					RecipeLayout.setVerticalGroup(
							RecipeLayout.createParallelGroup()
							.addGroup(RecipeLayout.createSequentialGroup()
									.addGap(5, 5, 5)
									.addGroup(RecipeLayout.createParallelGroup()
											.addComponent(View, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
											.addGroup(RecipeLayout.createSequentialGroup()
													.addGroup(RecipeLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
															.addComponent(PhotoR, GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
															.addComponent(RecipeName, GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE))
															.addGap(1, 1, 1)))
															.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
															.addComponent(separator2, GroupLayout.PREFERRED_SIZE, 5, GroupLayout.PREFERRED_SIZE)
															.addGap(109, 109, 109))
							);
				}
				SeachPanel.add(Recipe);
			}
			SearchScroll.setViewportView(SeachPanel);

		}
		SearchScroll.revalidate();
		// TODO add your code here
	}

	private void initComponents(int number) {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		SearchPage = new JPanel();
		Title = new JLabel();
		PeopleLB = new JLabel();
		RecipeLB = new JLabel();
		PeopleSeach = new JTextField();
		RecipeSearch = new JTextField();
		SearchPP = new JButton();
		SearchRP = new JButton();
		//SearchScroll = new JScrollPane();
		//SeachPanel = new JPanel();



		//======== SearchPage ========
		{

			//======== SearchScroll ========

			//---- Title ----
			Title.setText("Search Page");
			Title.setHorizontalAlignment(SwingConstants.CENTER);
			Title.setFont(Title.getFont().deriveFont(Title.getFont().getStyle() | Font.BOLD, Title.getFont().getSize() + 13f));
			Title.setForeground(Color.red);

			//---- PeopleLB ----
			PeopleLB.setText("People");
			PeopleLB.setFont(PeopleLB.getFont().deriveFont(PeopleLB.getFont().getStyle() | Font.BOLD));

			//---- RecipeLB ----
			RecipeLB.setText("Recipe");
			RecipeLB.setFont(RecipeLB.getFont().deriveFont(RecipeLB.getFont().getStyle() | Font.BOLD));

			//---- SearchPP ----
			SearchPP.setText("Search");
			SearchPP.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					SearchPPActionPerformed(e);
				}
			});

			//---- SearchRP ----
			SearchRP.setText("Seach");
			SearchRP.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					SearchRPActionPerformed(e);
				}
			});

			GroupLayout SearchPageLayout = new GroupLayout(SearchPage);
			SearchPage.setLayout(SearchPageLayout);
			SearchPageLayout.setHorizontalGroup(
					SearchPageLayout.createParallelGroup()
					.addGroup(SearchPageLayout.createSequentialGroup()
							.addGap(106, 106, 106)
							.addComponent(Title, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
							.addGroup(GroupLayout.Alignment.TRAILING, SearchPageLayout.createSequentialGroup()
									.addContainerGap(80, Short.MAX_VALUE)
									.addGroup(SearchPageLayout.createParallelGroup()
											.addGroup(SearchPageLayout.createSequentialGroup()
													.addComponent(PeopleLB, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
													.addComponent(PeopleSeach, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
													.addComponent(SearchPP, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
													.addGroup(SearchPageLayout.createSequentialGroup()
															.addComponent(RecipeLB, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
															.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
															.addComponent(RecipeSearch, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE)
															.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
															.addComponent(SearchRP, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)))
															.addGap(62, 62, 62))
															.addGroup(SearchPageLayout.createSequentialGroup()
																	.addContainerGap()
																	.addComponent(SearchScroll, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
																	.addContainerGap())
					);
			SearchPageLayout.setVerticalGroup(
					SearchPageLayout.createParallelGroup()
					.addGroup(GroupLayout.Alignment.TRAILING, SearchPageLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(Title)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(SearchPageLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
									.addComponent(PeopleLB, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
									.addComponent(PeopleSeach, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(SearchPP))
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addGroup(SearchPageLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
											.addComponent(RecipeLB, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
											.addComponent(RecipeSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addComponent(SearchRP))
											.addGap(18, 18, 18)
											.addComponent(SearchScroll, GroupLayout.PREFERRED_SIZE, 413, GroupLayout.PREFERRED_SIZE)
											.addContainerGap())
					);
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JPanel SearchPage;
	private JScrollPane SearchScroll = new JScrollPane();
	private JPanel SeachPanel = new JPanel();
	private JPanel People;
	private JLabel PeopleName;
	private JLabel PhotoP;
	private JButton Add;
	private JSeparator separator1;
	private JPanel Recipe;
	private JLabel RecipeName;
	private JLabel PhotoR;
	private JButton View;
	private JSeparator separator2;
	private JLabel Title;
	private JLabel PeopleLB;
	private JLabel RecipeLB;
	private JTextField PeopleSeach;
	private JTextField RecipeSearch;
	private JButton SearchPP;
	private JButton SearchRP;
	private DataStorage ds;
	private String username;


	public static final String NAME = "Search Panel";
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public class ViewListener implements ActionListener{
		public Recipe recipe;
		public ViewListener(Recipe recipe){
			this.recipe = recipe;
		}
		public void actionPerformed(ActionEvent e){
			//go to recipe
		}
	}
	
	public class AddListener implements ActionListener{
		public String name;
		public AddListener(String name){
			this.name = name;
		}
		public void actionPerformed(ActionEvent e){
			ds.addFriend(name);
			JOptionPane.showMessageDialog(null, "Friend added!");
		}
	}
}
