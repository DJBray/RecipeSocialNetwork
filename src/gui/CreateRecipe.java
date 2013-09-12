package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import jdbc_storage.DataStorage;
import jdbc_storage.Recipe;



/**
 * @author Nguyen
 */
public class CreateRecipe extends JPanel {
	public CreateRecipe() {
		initComponents();
	}

	public CreateRecipe(String username, DataStorage ds, SuperGUI myFrame) {
		this.username = username;
		this.ds = ds;
		this.myFrame = myFrame;
		initComponents();
	}

	/**
	 * Gets the top level JPanel for createRecipe containing all the buttons, 
	 * labels and additional components
	 * 
	 * @return Top level JPanel for createRecipe
	 */
	public JPanel getScreen(){
		return CreateRep;
	}

	private void SubmitActionPerformed(ActionEvent e) {
		File image = new File(uploadBox.getText());
		Calendar rightNow = Calendar.getInstance();
		Recipe r = new Recipe(ds.getuid(), RecipeName.getText(), Description.getText(), Ingredient.getText(), 
			Steps.getText(), 3, 0, image.getName(), rightNow.get(Calendar.YEAR)*10000
				+ rightNow.get(Calendar.MONTH) *100 + rightNow.get(Calendar.DATE));
		ds.insertImage(image.getName(), image.getAbsolutePath());
		if(!ds.addRecipe(r)){
			JOptionPane.showMessageDialog(null, "Recipe already exists with that name");
			return;
		}
		myFrame.getCenter().removeAll();
		ViewAllRep single_viewAll = new ViewAllRep(username,ds, myFrame);
		myFrame.getCenter().getCard().add(single_viewAll.getScreen(), ViewAllRep.NAME);
		myFrame.getCenter().setScreen(ViewAllRep.NAME);
		myFrame.getCenter().getCard().revalidate();
	}

	private void CancelActionPerformed(ActionEvent e) {
		myFrame.getCenter().setScreen(ViewAllRep.NAME);
	}

	private void BrowseActionPerformed(ActionEvent e) {
		JFileChooser fc = new JFileChooser();
		int result = fc.showOpenDialog(myFrame);
		if (result == JFileChooser.APPROVE_OPTION) {
            		File file = fc.getSelectedFile();
            		uploadBox.setText(file.getAbsolutePath());
      		} 
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		CreateRep = new JPanel();
		Title = new JLabel();
		RecipeName = new JTextField();
		scrollPane1 = new JScrollPane();
		Ingredient = new JTextArea();
		scrollPane2 = new JScrollPane();
		Steps = new JTextArea();
		scrollPane3 = new JScrollPane();
		Description = new JTextArea();
		RepNameLB = new JLabel();
		IngLB = new JLabel();
		IngLB2 = new JLabel();
		StepLB = new JLabel();
		hSpacer1 = new JPanel(null);
		uploadBox = new JTextField();
		Browse = new JButton();
		pictureLB = new JLabel();
		Submit = new JButton();
		Cancel = new JButton();
		vSpacer1 = new JPanel(null);

		//======== CreateRep ========
		{
			CreateRep.setLayout(null);

			//---- Title ----
			Title.setText("Create Recipe");
			Title.setHorizontalAlignment(SwingConstants.CENTER);
			Title.setFont(Title.getFont().deriveFont(Title.getFont().getSize() + 10f));
			Title.setForeground(Color.red);
			CreateRep.add(Title);
			Title.setBounds(55, 10, 340, 40);
			CreateRep.add(RecipeName);
			RecipeName.setBounds(105, 60, 315, 30);

			//======== scrollPane1 ========
			{
				scrollPane1.setViewportView(Ingredient);
			}
			CreateRep.add(scrollPane1);
			scrollPane1.setBounds(105, 210, 315, 90);

			//======== scrollPane2 ========
			{
				scrollPane2.setViewportView(Steps);
			}
			CreateRep.add(scrollPane2);
			scrollPane2.setBounds(105, 310, 315, 90);

			//======== scrollPane3 ========
			{
				scrollPane3.setViewportView(Description);
			}
			CreateRep.add(scrollPane3);
			scrollPane3.setBounds(105, 110, 315, 90);

			//---- RepNameLB ----
			RepNameLB.setText("Recipe Name");
			RepNameLB.setFont(RepNameLB.getFont().deriveFont(RepNameLB.getFont().getStyle() | Font.BOLD, RepNameLB.getFont().getSize() + 1f));
			RepNameLB.setHorizontalAlignment(SwingConstants.TRAILING);
			CreateRep.add(RepNameLB);
			RepNameLB.setBounds(-30, 60, 125, 30);

			//---- IngLB ----
			IngLB.setText("Ingredients");
			IngLB.setFont(IngLB.getFont().deriveFont(IngLB.getFont().getStyle() | Font.BOLD, IngLB.getFont().getSize() + 1f));
			IngLB.setHorizontalAlignment(SwingConstants.TRAILING);
			CreateRep.add(IngLB);
			IngLB.setBounds(-30, 220, 125, 30);

			//---- IngLB2 ----
			IngLB2.setText("Description");
			IngLB2.setFont(IngLB2.getFont().deriveFont(IngLB2.getFont().getStyle() | Font.BOLD, IngLB2.getFont().getSize() + 1f));
			IngLB2.setHorizontalAlignment(SwingConstants.TRAILING);
			CreateRep.add(IngLB2);
			IngLB2.setBounds(-30, 120, 125, 30);

			//---- StepLB ----
			StepLB.setText("Steps");
			StepLB.setFont(StepLB.getFont().deriveFont(StepLB.getFont().getStyle() | Font.BOLD, StepLB.getFont().getSize() + 1f));
			StepLB.setHorizontalAlignment(SwingConstants.TRAILING);
			CreateRep.add(StepLB);
			StepLB.setBounds(-30, 325, 125, 30);
			CreateRep.add(hSpacer1);
			hSpacer1.setBounds(420, 60, 60, 30);
			CreateRep.add(uploadBox);
			uploadBox.setBounds(105, 440, 225, 30);

			//---- Browse ----
			Browse.setText("Browse");
			Browse.setFont(Browse.getFont().deriveFont(Browse.getFont().getStyle() & ~Font.BOLD));
			Browse.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					BrowseActionPerformed(e);
				}
			});
			CreateRep.add(Browse);
			Browse.setBounds(340, 440, 80, 30);

			//---- pictureLB ----
			pictureLB.setText("Picture");
			pictureLB.setFont(pictureLB.getFont().deriveFont(pictureLB.getFont().getStyle() | Font.BOLD, pictureLB.getFont().getSize() + 1f));
			pictureLB.setHorizontalAlignment(SwingConstants.TRAILING);
			CreateRep.add(pictureLB);
			pictureLB.setBounds(-25, 440, 125, 30);

			//---- Submit ----
			Submit.setText("Submit");
			Submit.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					SubmitActionPerformed(e);
				}
			});
			CreateRep.add(Submit);
			Submit.setBounds(140, 480, 100, 33);

			//---- Cancel ----
			Cancel.setText("Cancel");
			Cancel.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					CancelActionPerformed(e);
				}
			});
			CreateRep.add(Cancel);
			Cancel.setBounds(250, 480, 100, 33);
			CreateRep.add(vSpacer1);
			vSpacer1.setBounds(370, 470, vSpacer1.getPreferredSize().width, 70);

			{ // compute preferred size
				Dimension preferredSize = new Dimension();
				for(int i = 0; i < CreateRep.getComponentCount(); i++) {
					Rectangle bounds = CreateRep.getComponent(i).getBounds();
					preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
					preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
				}
				Insets insets = CreateRep.getInsets();
				preferredSize.width += insets.right;
				preferredSize.height += insets.bottom;
				CreateRep.setMinimumSize(preferredSize);
				CreateRep.setPreferredSize(preferredSize);
			}
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JPanel CreateRep;
	private JLabel Title;
	private JTextField RecipeName;
	private JScrollPane scrollPane1;
	private JTextArea Ingredient;
	private JScrollPane scrollPane2;
	private JTextArea Steps;
	private JScrollPane scrollPane3;
	private JTextArea Description;
	private JLabel RepNameLB;
	private JLabel IngLB;
	private JLabel IngLB2;
	private JLabel StepLB;
	private JPanel hSpacer1;
	private JTextField uploadBox;
	private JButton Browse;
	private JLabel pictureLB;
	private JButton Submit;
	private JButton Cancel;
	private JPanel vSpacer1;

	private SuperGUI myFrame;
	private String username;
	private DataStorage ds;
	// JFormDesigner - End of variables declaration  //GEN-END:variables

	public static final String NAME = "Create Recipe";
}
