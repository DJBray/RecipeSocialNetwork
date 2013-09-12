/**
 * Class: MySqlConnector
 * @author Daniel J Bray
 * 
 * Description:
 * 		This class is used to establish a connection to a MySql database and perform
 * and handle queries. Only one MySqlConnection should be present per 
 * client at a time.
 * 		All methods should be self explanatory in the javadoc.
 */

package jdbc_storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.StringTokenizer;



public class MySqlConnector {
	public static final String ROW_DELIM = "^&";
	public static final String COL_DELIM = "@#";
	
	private String database;
	private String username;
	private String password;
	
	private Connection conn;
		//Ignore this:
		//
		//This could be used for logins?
		//JPasswordField jpf = new JPasswordField();
		//JOptionPane.showConfirmDialog(null, jpf,
		//		"Enter Password:", JOptionPane.OK_CANCEL_OPTION);

		//jpf.getPassword();
		//char [] pass = jpf.getPassword();
		//password = new String(pass);
	
	/**
	 * MySqlConnector constructor
	 * 
	 * Specifies the jdbc driver and initializes the database, username, and password
	 * variables to some default variables.
	 * 
	 * For convenience, change these to your own database, username, and password
	 */
	public MySqlConnector(){
		//To run this make sure you are forwarding 3307 to localhost:3307 using ssh
		
		//Change these for your own db if you want the default constructor
		database = "braydj";
		username = "braydj";
		password = "pqidjf";

		try {
			Class.forName("com.mysql.jdbc.Driver"); //You need to have Connector J in your build path
		}
		catch (ClassNotFoundException e) {
			System.out.println("Cannot find driver");
		}
	}
	
	/**
	 * MySqlConnector constructor
	 * 
	 * Specifies the jdbc driver and initializes the database, username, and password
	 * variables to specified parameter values.
	 * 
	 * @param database 
	 * 			the database name
	 * @param username
	 * 			your mysql username
	 * @param password
	 * 			your mysql password
	 */
	public MySqlConnector(String database, String username, String password){
		//To run this make sure you are forwarding 3307 to localhost:3307 using ssh
		this.database = database;
		this.username = username;
		this.password = password;

		try {
			Class.forName("com.mysql.jdbc.Driver"); //You need to have Connector J in your build path
		}
		catch (ClassNotFoundException e) {
			System.out.println("Cannot find driver");
		}
	}
		
	/**
	 * connect
	 * 
	 * Call connect() to connect to the MySql database specified by the database, username,
	 * and password variables for the class. Only call connect when you are ready to establish
	 * a connection
	 * 
	 * PRECONDITION: the port you will be using is 3307 and Putty is active with port fowarding.
	 * 
	 * @throws SQLException
	 * 				throws an exception if a connection could not be established.
	 */
	public void connect() throws SQLException{
		if(conn != null)
			return;
		else{ //if(conn == null || conn.isClosed()){
			try{
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/"+database,username,password);
			}
			catch(SQLException e){
				throw new SQLException("Error: Cannot connect. If database/username/password " +
						"are all correct check to see if port 3307 is already in use.\n" + e.getMessage());
			}
		}
	}
	
	public void insertImage(String uid, String iid, String imgPath){
		try{
			File image = new File(imgPath);
			String statement = "INSERT INTO IMAGES VALUES(?,?,?,?);";
			PreparedStatement psmt = conn.prepareStatement(statement);
			psmt.setString(1, uid);
			psmt.setString(2, iid);
			psmt.setString(3, image.length()+"");
			
			FileInputStream fis = new FileInputStream(image);
			psmt.setBinaryStream(4, (InputStream)fis, (int)image.length());
			
			int s = psmt.executeUpdate();
			if(s>0) 
				System.out.println("Uploaded successfully !");
			else 
				System.out.println("unsucessfull to upload image.");
		}
		catch(FileNotFoundException e){
			System.err.println("Image not found.\n" + e.getMessage());
		}
		catch(SQLException e){
			System.err.println("Image Insertion error.\n" + e.getMessage());
		}
	}
	
	public File getImage(String uid, String iid){
		try{
			String select = "SELECT * FROM IMAGES where uid = '" + uid + "' AND iid = '" +
					iid + "';";
			Statement stmt = conn.prepareStatement(select);
			ResultSet rs = stmt.executeQuery(select);
			
			File image = new File(iid);
			FileOutputStream fos = new FileOutputStream(image);
			while(rs.next()){
				int imageLength = rs.getInt(3);
				InputStream is2 = rs.getBinaryStream(4);
				//System.out.println(is2.available());
				byte[] buff = new byte[imageLength];
				is2.read(buff);
				fos.write(buff);
				fos.flush();
			}
			fos.close();
			return image;
		}
		catch(IOException e){
			System.out.println(e.getMessage());
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	/**
	 * iud = InsertUpdateDelete
	 * 
	 * This method can be used for any insert, update, or delete call on the 
	 * mySQL database. Specify the call with the String insertion.
	 * 
	 * Example method call:
	 * 	String insertion = "INSERT INTO USERS VALUES ('Dan', 'test');";
	 * 	iud(insertion);
	 * 
	 * @param insertion
	 * 				the String value for the insert, update, or delete call.
	 * @throws SQLException
	 */
	public void iud(String insertion) throws SQLException{
		Statement stmt = conn.createStatement();
		stmt.executeUpdate(insertion);
		stmt.close();
	}
	
	/**
	 * query 
	 * 
	 * This method should be called anytime the user wants to execute
	 * a query for MySQL. Specify the query and the number of columns
	 * being returned by the query. 
	 * 
	 * The method then returns the values returned by the query
	 * as a String. Each column is separated by a comma
	 * as a delimiter. Likewise each row is separated by a new line
	 * character ("\n") as a delimiter. Every line is ended with
	 * a comma and a new line delimiter to mark the end of the String.
	 * 
	 * Example usage:
	 * 	String query = "SELECT uid, pwd FROM USERS;";
	 * 	String results = query(query, 2);
	 * 
	 * Note that 2 is used because we wanted to select the uid
	 * and the pwd from the database.
	 * 
	 * @param query
	 * 		the query to be placed 
	 * @param columns
	 * 		number of columns to be returned by the query
	 * @return
	 * 		the results of the query with comma's separating columns
	 * and '\n' characters separating rows.
	 * 
	 * @throws SQLException
	 * 		throws an exception if a connection error occurs
	 */
	public String query(String query, int columns) throws SQLException{
		String result = "";
		Statement stmt = conn.createStatement();
		ResultSet resSet= stmt.executeQuery(query);
		while(resSet.next()){
			for(int i = 1; i <= columns; i++){
				//Each column is separated by a delimiter 
				result += resSet.getString(i) + COL_DELIM;
			}
			//Each row is separated by a delimiter 
			//NOTE: each line is ended by the delimiters
			//in column-row order
			result += ROW_DELIM;
		}
		stmt.close();
		return result;
	}
	
	/**
	 * tokenizeResults
	 * 
	 * A static method used to take a string and break it into parts
	 * with each part stored as an element in an arraylist.
	 * 
	 * This method is designed to be used in conjunction with query()
	 * to break down the query into individual elements.
	 * 
	 * @param str    The string to be tokenized
	 * @param delim  The delimiter for the string
	 * 				 (ie. what separates each token)
	 * @return	the string broken into parts stored in an arraylist.
	 */
	
	public static ArrayList<String> tokenizeResults(String str, String delim){
		ArrayList<String> results = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(str, delim);
		while(st.hasMoreTokens()){
			results.add(st.nextToken());
		}
		return results;
	}
	
	/**
	 * close
	 * 
	 * Close should be called at the end of usage for the MySqlConnector
	 * class. close() closes the connection currently open with the
	 * MySQL database.
	 * 
	 * @throws SQLException
	 * 				throws a MySQLException if a connection problem occurs.
	 */
	public void close() throws SQLException{
		if(!conn.isClosed())
			conn.close();
	}
	
	/**
	 * isClosed
	 * 
	 * isClosed is used to determine if a connection is currently open with
	 * the database on this machine.
	 * 
	 * @return true if the connection is closed
	 * 		   false if the connection is still open
	 */
	public boolean isClosed(){
		if(conn == null)
			return true;
		try{
			return conn.isClosed();
		}
		catch(SQLException e){
			return true;
		}
	}
	
	/*public static void main(String[] args){
		try{
			MySqlConnector conn = new MySqlConnector();
			conn.connect();
			conn.insertImage("braydj", "Red_turbin.jpg", "Red_turbin.jpg");
			String update = "UPDATE PROFILE SET iid = 'Red_turbin.jpg' WHERE uid = 'braydj';";
			conn.iud(update);
			File f = conn.getImage("braydj", "Red_turbin.jpg");
		}
		catch(SQLException e){
			System.err.println("ERROR: " + e.getMessage());
		}
	}*/
}
