package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import jdbc_storage.DataStorage;

public class SuperGUI extends JFrame{
	
	private String username;
	private DataStorage ds;
	
	private HeaderPane header;
	private CentralPanel centerPanel;
	private EastPanel east;
	private WestPanel west;
	
	public static void main(String args[]){
		new SuperGUI();
	}
	
	public SuperGUI(){
		super("Recipe App");
		init();
		
		Container contentPanel = this.getContentPane();
		
		contentPanel.add(header.getHeaderPanel(), BorderLayout.NORTH);
		contentPanel.add(centerPanel.getScreen(), BorderLayout.CENTER);
		contentPanel.add(east.getScreen(), BorderLayout.EAST);
		contentPanel.add(west.getScreen(), BorderLayout.WEST);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(1200, 650));
		this.pack();
		this.setVisible(true);
		
	}
	
	/**
	 * Initializes all components
	 */
	public void init(){
		
		ds = new DataStorage();
		header = new HeaderPane(this);
		centerPanel = new CentralPanel("", ds, this);
		west = new WestPanel(this);
		east = new EastPanel(this);
		
	}
	
	/**
	 * Allows the SuperGUI instance to access the instance of the center panel
	 * @return Central Panel
	 */
	public CentralPanel getCenter(){
		return centerPanel;
	}
	
	/**
	 * Allows the SuperGUI instance to access the instance of the Header
	 * @return HeaderPane
	 */
	public HeaderPane getHeader(){
		return header;
	}
	
	/**
	 * Allows the SuperGUI instance to access the instance of the East Panel
	 * @return East Panel
	 */
	public EastPanel getEast(){
		return east;
	}
	
	/**
	 * Allows the SuperGUI instance to access the instance of the West Panel
	 * @return West Panel
	 */
	public WestPanel getWest(){
		return west;
	}
	
	static public BufferedImage convertSize(BufferedImage originalPic, int size){
		int type = originalPic.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalPic.getType();
		BufferedImage resizedImage = new BufferedImage(size, size, type);
		
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalPic, 0, 0, size, size, null);
		g.dispose();
		
		return resizedImage;
	}
}
