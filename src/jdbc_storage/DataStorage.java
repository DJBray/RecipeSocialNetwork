/**
 * Class: DataStorage
 * @author Daniel J Bray
 * 
 * This class is designed to be used by the our Recipe Social Network GUI.
 * This class will act as a medium between the database and the GUI to help
 * clean up function calls. In addition this class will
 * handle all the data to be stored in the database and will
 * retrieve data stored in the database.
 * 
 * PRECONDITION: For this class it is assumed that the recipe social network
 * database is being used. Otherwise, queries should be edited to fit the 
 * user's needs.
 */

package jdbc_storage;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataStorage {
	public static final int UID_LENGTH = 30;
	public static final int PWD_LENGTH = 45;
	public static final int FNAME_LENGTH = 45;
	public static final int LNAME_LENGTH = 45;
	public static final int INFO_LENGTH = 2000;
	public static final int IID_LENGTH = 30;

	public static final int USER_LOGGED_IN = 0;
	public static final int USER_NOT_FOUND = 1;
	public static final int INVALID_UID_PWD = 2;
	public static final int DB_ERROR = 3;

	public static final String TTYPE_RECIPES = "RECIPES";
	public static final String TTYPE_PROFILE = "PROFILE";

	// uid, firstName, lastName
	public static final String STYPE_UID = "uid";
	public static final String STYPE_FIRST_NAME = "firstName";
	public static final String STYPE_LAST_NAME = "lastName";

	// uid, recipeName, recipeDescription, recipeIngrediants, recipeSteps
	// uid is alread used.
	public static final String STYPE_RECIPE_NAME = "recipeName";
	public static final String STYPE_RECIPE_DESCRIPTION = "recipeDescription";
	public static final String STYPE_RECIPE_INGREDIANTS = "recipeIngrediants";
	public static final String STYPE_RECIPE_STEPS = "recipeSteps";
	public static final String STYPE_RECIPE_DATE = "dateMade";

	private String uid;
	private String pwd;
	private static MySqlConnector conn;

	/**
	 * DataStorage constructor
	 * 
	 * Initializes local uid and pwd variables to a default value.
	 */
	public DataStorage() {
		// Current default values, change accordingly for testing
		uid = "";
		pwd = "";

		// To use a different database use the constructor:
		// conn = new MySqlConnector(database, username, password);
		// with your own database name, username and password for mysql
		conn = new MySqlConnector();
		try {
			conn.connect();
		} catch (SQLException e) {
		}
	}

	/**
	 * DataStorage constructor(2)
	 * 
	 * Initializes the uid and pwd variables to the given parameter values. This
	 * will be the most commonly used constructor of the 2.
	 * 
	 * @param uid
	 *            the user id
	 * @param pwd
	 *            user's password to login
	 */
	public DataStorage(String uid, String pwd) {
		this.uid = uid;
		this.pwd = pwd;
		conn = new MySqlConnector();
	}

	/**
	 * login
	 * 
	 * Attempts to log in the user based on the current uid and pwd variable
	 * values. login() establishes a connection to the database, asserts that
	 * that uid is in the database and then checks to see if the uid and pwd
	 * matches the uid and pwd stored in the database.
	 * 
	 * If all information is put in correctly, returns true. If any information
	 * is incorrect or the user does not have an account yet, then returns
	 * false.
	 * 
	 * @return true if login succeeded. false if login failed.
	 */
	public int login() {
		try {
			// tries to connect (useful if got disconnected from bad internet)
			conn.connect();

			// Query for the user
			String result = conn.query(
					"SELECT uid, pwd FROM USERS WHERE uid = '" + uid + "';", 2);
			// Tokenize the results
			ArrayList<String> tuples = MySqlConnector.tokenizeResults(result,
					MySqlConnector.ROW_DELIM);

			// Should only have 1 user with that uid in the db since uid is a
			// primary key
			// otherwise there's a problem with our db
			if (tuples.size() > 1)
				throw new SQLException("Referential Integrity broken. "
						+ "uid is not a primary key for the database.");
			else if (tuples.size() == 0) {
				return USER_NOT_FOUND;
			} else {
				// since we asserted it only has 1 element
				ArrayList<String> loginInfo = MySqlConnector.tokenizeResults(
						tuples.get(0), MySqlConnector.COL_DELIM);

				// 2 get statements since our query had 2 returned columns
				String db_uid = loginInfo.get(0);
				String db_pwd = loginInfo.get(1);

				// Returns true if correct info is present
				if (db_uid.equals(uid) && db_pwd.equals(pwd))
					return USER_LOGGED_IN;
				// Closes the connection if not the correct info
				return INVALID_UID_PWD;
			}
		} catch (SQLException e) {
			System.out.println("Login error\n" + e.getMessage()); // or database
																	// error
		}
		// then returns false to show info is incorrect
		return DB_ERROR;
	}

	/**
	 * logout
	 * 
	 * Resets uid and pwd variables to being empty then closes the database
	 * connection.
	 */
	public void logout() {
		uid = "";
		pwd = "";
		try {
			conn.close();
		} catch (SQLException e) {
			// connection already closed so nothing more needs to be done
		}
	}

	// rate the recipe
	public void rateRecipe(Recipe recipe, int rating) {
		try {
			int newRating = ((recipe.get_rating() * recipe.get_numRatings()) + rating)
					/ (recipe.get_numRatings() + 1);
			int newNumRatings = recipe.get_numRatings() + 1;

			String update = "UPDATE RECIPES SET rating = " + newRating
					+ ", numRatings = " + newNumRatings + " WHERE uid = '"
					+ recipe.get_uid() + "' AND recipeName = '"
					+ recipe.get_recipeName() + "';";
			conn.iud(update);
		} catch (SQLException e) {
			System.err.println("SQL ERROR while rating recipe "
					+ recipe.get_recipeName());
		}
	}

	// createAccount
	public static boolean createAccount(String uid, String pwd) {
		try {
			if (conn.isClosed()) {
				conn = new MySqlConnector();
				conn.connect();
			}
			String query = "SELECT uid FROM USERS WHERE uid = '" + uid + "';";
			String result = conn.query(query, 1);
			if (!result.trim().isEmpty()) {
				return false;
			}
			String insert = "INSERT INTO USERS VALUES ('" + uid + "','" + pwd
					+ "');";
			conn.iud(insert);
			return true;
		} catch (SQLException e) {
			System.err.println("Cannot find server DataBase.");
		}
		return false;
	}

	// getProfileInfo
	public ProfileInfo getProfileInfo(String useruid) {
		try {
			String query = "SELECT firstName, lastName, info, birthday, iid FROM PROFILE WHERE uid = '"
					+ useruid + "';";
			String results = conn.query(query, 5);
			if (results.trim().isEmpty())
				return new ProfileInfo(useruid);
			ArrayList<String> rows = MySqlConnector.tokenizeResults(results,
					MySqlConnector.ROW_DELIM);
			ArrayList<String> cols = MySqlConnector.tokenizeResults(
					rows.get(0), MySqlConnector.COL_DELIM);
			return new ProfileInfo(useruid, cols.get(0), cols.get(1),
					cols.get(2), Integer.parseInt(cols.get(3)), cols.get(4));
		} catch (SQLException e) {
			System.out.println("Connection problem while getting messages.");
			return null;
		}
	}

	public void createProfile(String uid, String firstName, String lastName,
			String info, int birthday) {
		try {
			String query = "SELECT * FROM PROFILE WHERE uid = '" + uid + "';";
			String result = conn.query(query, 6);
			String iud;
			// insert
			if (result.trim().isEmpty()) {
				iud = "INSERT INTO PROFILE(uid, firstName, lastName, info, birthday)"
						+ " VALUES ('"
						+ uid
						+ "','"
						+ firstName
						+ "','"
						+ lastName + "','" + info + "'," + birthday + ");";
			}
			// update
			else {
				iud = "UPDATE PROFILE SET firstName = '" + firstName
						+ "', lastName = '" + lastName + "', info = '" + info
						+ "', birthday = " + birthday + " WHERE uid = '" + uid
						+ "';";
			}
			conn.iud(iud);
		} catch (SQLException e) {
			System.err.println("ERROR creating profile.\n" + e.getMessage());
		}
	}

	/**
	 * getMessages
	 * 
	 * This method is used to find all the messages in a user's inbox. The
	 * method queries the database for all the messages belonging to the user
	 * that matches his uid then returns them in the form of a Message.
	 * 
	 * @return an arrayList of the user's messages.
	 */
	public ArrayList<Message> getMessages() {
		try {
			ArrayList<Message> messages = new ArrayList<Message>();
			String query = "SELECT uid, messageId, fromUser, subject, dateSent, message FROM MESSAGES"
					+ " WHERE uid = '" + uid + "' ORDER BY dateSent DESC;";
			String results = conn.query(query, 6);
			ArrayList<String> lines = MySqlConnector.tokenizeResults(results,
					MySqlConnector.ROW_DELIM);
			for (String line : lines) {
				ArrayList<String> cols = MySqlConnector.tokenizeResults(line,
						MySqlConnector.COL_DELIM);
				Message m = new Message(cols.get(0), Integer.parseInt(cols
						.get(1)), cols.get(2), cols.get(3),
						Integer.parseInt(cols.get(4)), cols.get(5));
				messages.add(m);
			}
			return messages;
		} catch (SQLException e) {
			System.out.println("Connection problem while getting messages.");
			return null;
		}
	}

	// sendMessage
	public void sendMessage(String to, String subject, String content, int date) {
		try {
			conn.iud("INSERT INTO MESSAGES(uid,fromUser,subject,dateSent,message) "
					+ "VALUE("
					+ "'"
					+ to
					+ "','"
					+ uid
					+ "','"
					+ subject
					+ "'," + date + ",'" + content + "');");
		} catch (SQLException e) {
			System.out.println("Please check your input for message again");
		}
	}

	/**
	 * getFriends
	 * 
	 * This method is used to find all of a users friends. The method queries
	 * the database for all the user's friends according to his uid and then
	 * returns an arrayList of type string with each element containing the uid
	 * of one of his or her friends.
	 * 
	 * @return an arraylist of uid's of user's friends.
	 */
	public ArrayList<String> getFriends() {
		try {
			ArrayList<String> friends;
			String query = "SELECT friend FROM FRIENDS WHERE uid = '" + uid
					+ "';";
			String results = conn.query(query, 1);
			friends = MySqlConnector.tokenizeResults(results,
					MySqlConnector.COL_DELIM + MySqlConnector.ROW_DELIM);
			return friends;
		} catch (SQLException e) {
			System.out.println("Connection problem while getting friends for "
					+ uid + ".");
			return null;
		}
	}

	// addFriend
	public void addFriend(String friend) {
		try {
			String query = "SELECT uid FROM FRIENDS WHERE uid = '" + uid
					+ "' AND friend = '" + friend + "';";
			if (conn.query(query, 1).trim().isEmpty())
				conn.iud("INSERT INTO FRIENDS VALUE('" + uid + "','" + friend
						+ "');");
		} catch (SQLException e) {
			System.out.println("Connection problem while adding friend for "
					+ uid + ".");
		}

	}

	public void removeFriend(String friend) {
		try {
			String query = "SELECT uid FROM FRIENDS WHERE uid = '" + uid
					+ "' AND friend = '" + friend + "';";
			if (!conn.query(query, 1).trim().isEmpty())
				conn.iud("DELETE FROM FRIENDS WHERE uid = '" + uid
						+ "' AND friend = '" + friend + "';");
		} catch (SQLException e) {
			System.out.println("Connection problem while removing friend for "
					+ uid + ".");
		}
	}

	// findFriends
	public ArrayList<String> findUser(String searchText, String stype) {
		try {
			ArrayList<String> results = new ArrayList<String>();
			String query = "SELECT uid FROM PROFILE WHERE " + stype
					+ " LIKE '%" + searchText + "%';";
			String r = conn.query(query, 1);
			ArrayList<String> friends = MySqlConnector.tokenizeResults(r,
					MySqlConnector.COL_DELIM + MySqlConnector.ROW_DELIM);
			for (String f : friends) {
				results.add(f);
			}
			return results;
		} catch (SQLException e) {
			System.err.println("SQL error occurred while finding friend.");
			return null;
		}
	}

	public ArrayList<Recipe> getTopRecipes(int num) {
		try {
			ArrayList<Recipe> recipes = new ArrayList<Recipe>();
			String query = "SELECT * FROM RECIPES ORDER BY rating DESC;";
			String results = conn.query(query, 9);
			ArrayList<String> rows = MySqlConnector.tokenizeResults(results,
					MySqlConnector.ROW_DELIM);
			for (int i = 0; i < rows.size() && i < num; i++) {
				ArrayList<String> cols = MySqlConnector.tokenizeResults(
						rows.get(i), MySqlConnector.COL_DELIM);
				Recipe r = new Recipe(cols.get(0), cols.get(1), cols.get(2),
						cols.get(3), cols.get(4),
						Integer.parseInt(cols.get(5)), Integer.parseInt(cols
								.get(6)), cols.get(7), Integer.parseInt(cols
								.get(8)));
				recipes.add(r);
			}
			return recipes;
		} catch (SQLException e) {
			System.out.println("SQL error while getting recipes for " + uid
					+ ".");
			return null;
		}
	}

	// getRecipes
	public ArrayList<Recipe> getRecipes() {
		try {
			ArrayList<Recipe> recipes = new ArrayList<Recipe>();
			String query = "SELECT recipeName, recipeDescription, recipeIngrediants, "
					+ "recipeSteps, rating, numRatings, iid, dateMade FROM RECIPES WHERE uid = '"
					+ uid + "' ORDER BY dateMade DESC;";
			String results = conn.query(query, 8);
			ArrayList<String> rows = MySqlConnector.tokenizeResults(results,
					MySqlConnector.ROW_DELIM);
			for (int i = 0; i < rows.size(); i++) {
				ArrayList<String> cols = MySqlConnector.tokenizeResults(
						rows.get(i), MySqlConnector.COL_DELIM);
				Recipe r = new Recipe(uid, cols.get(0), cols.get(1),
						cols.get(2), cols.get(3),
						Integer.parseInt(cols.get(4)), Integer.parseInt(cols
								.get(5)), cols.get(6), Integer.parseInt(cols
								.get(7)));
				recipes.add(r);
			}
			return recipes;
		} catch (SQLException e) {
			System.out.println("SQL error while getting recipes for " + uid
					+ ".");
			return null;
		}
	}

	// addRecipe
	public boolean addRecipe(Recipe recipe) {
		try {
			String result = conn.query(
					"SELECT * FROM RECIPES WHERE recipeName = '"
							+ recipe.get_recipeName() + "';", 1);
			ArrayList<String> token = MySqlConnector.tokenizeResults(result,
					MySqlConnector.COL_DELIM + MySqlConnector.ROW_DELIM);
			if (!token.isEmpty())
				return false;

			String insert = "INSERT INTO RECIPES(uid,recipeName,recipeDescription,recipeIngrediants,recipeSteps,iid,dateMade) VALUES('"
					+ recipe.get_uid()
					+ "','"
					+ recipe.get_recipeName()
					+ "','"
					+ recipe.get_recipeDescription()
					+ "','"
					+ recipe.get_recipeIngrediants()
					+ "','"
					+ recipe.get_recipeSteps()
					+ "','"
					+ recipe.get_iid()
					+ "'," + recipe.get_dateMade() + ");";
			conn.iud(insert);
			return true;
		} catch (SQLException e) {
			System.err.println("Error occurred while adding recipe.\n"
					+ e.getMessage());
			return false;
		}
	}

	// findRecipe
	public ArrayList<Recipe> findRecipe(String searchedText, String stype) {
		try {
			ArrayList<Recipe> recipes = new ArrayList<Recipe>();
			String query = "SELECT uid, recipeName, recipeDescription, recipeIngrediants, "
					+ "recipeSteps, rating, numRatings, iid, dateMade FROM RECIPES WHERE "
					+ stype + " LIKE '%" + searchedText + "%';";
			String results = conn.query(query, 9);
			ArrayList<String> rows = MySqlConnector.tokenizeResults(results,
					MySqlConnector.ROW_DELIM);
			for (int i = 0; i < rows.size(); i++) {
				ArrayList<String> cols = MySqlConnector.tokenizeResults(
						rows.get(i), MySqlConnector.COL_DELIM);
				Recipe r = new Recipe(cols.get(0), cols.get(1), cols.get(2),
						cols.get(3), cols.get(4),
						Integer.parseInt(cols.get(5)), Integer.parseInt(cols
								.get(6)), cols.get(7), Integer.parseInt(cols
								.get(8)));
				recipes.add(r);
			}
			return recipes;
		} catch (SQLException e) {
			System.err.println("SQL error while searching recipes for " + uid
					+ ".");
			System.err.println(e.getMessage());
			return null;
		}
	}

	public ArrayList<Recipe> findRecentRecipes(String uid, int number) {
		try {
			ArrayList<Recipe> recipes = new ArrayList<Recipe>();
			String query = "SELECT r.uid, r.recipeName, r.recipeDescription, r.recipeIngrediants, "
					+ "r.recipeSteps, r.rating, r.numRatings, r.iid, r.dateMade "
					+ "FROM RECIPES r, FRIENDS f "
					+ "WHERE f.uid = '"
					+ uid
					+ "' AND r.uid = f.friend " + "ORDER BY dateMade DESC;";
			String results = conn.query(query, 9);
			ArrayList<String> rows = MySqlConnector.tokenizeResults(results,
					MySqlConnector.ROW_DELIM);
			for (int i = 0; i < rows.size() && i < number; i++) {
				ArrayList<String> cols = MySqlConnector.tokenizeResults(
						rows.get(i), MySqlConnector.COL_DELIM);
				Recipe r = new Recipe(cols.get(0), cols.get(1), cols.get(2),
						cols.get(3), cols.get(4),
						Integer.parseInt(cols.get(5)), Integer.parseInt(cols
								.get(6)), cols.get(7), Integer.parseInt(cols
								.get(8)));
				recipes.add(r);
			}
			return recipes;
		} catch (SQLException e) {
			System.err.println("SQL error while getting recent recipes for "
					+ uid + ".");
			System.err.println(e.getMessage());
			return null;
		}
	}

	public void insertImage(String iid, String imgPath) {
		conn.insertImage(uid, iid, imgPath);
	}
	
	public void insertImageForProfile(String iid, String imgPath){
		try{
			conn.insertImage(uid, iid, imgPath);
			conn.iud("UPDATE PROFILE SET iid = '" + iid + "' WHERE uid = '" + uid + "';");
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
		}
	}

	public File getImageforProfile(String uid) {
		try {
			String query = "SELECT iid FROM PROFILE WHERE uid = '" + uid + "';";
			String result = conn.query(query, 1);
			String iid = MySqlConnector.tokenizeResults(result,
					MySqlConnector.COL_DELIM + MySqlConnector.ROW_DELIM).get(0);
			if (iid.trim().equalsIgnoreCase("null"))
				return null;
			return conn.getImage(uid, iid);
		} catch (SQLException e) {
			System.err.println("Error getting image for profile " + uid);
			System.out.println(e.getMessage());
			return null;
		}
	}

	public File getImageForRecipe(String uid, String recipeName) {
		try {
			String query = "SELECT iid FROM RECIPES WHERE uid = '" + uid
					+ "' AND recipeName = '" + recipeName + "';";
			String result = conn.query(query, 1);
			String iid = MySqlConnector.tokenizeResults(result,
					MySqlConnector.COL_DELIM + MySqlConnector.ROW_DELIM).get(0);
			if (iid.trim().equalsIgnoreCase("null"))
				return null;
			return conn.getImage(uid, iid);
		} catch (SQLException e) {
			System.err.println("Error getting image for recipe " + recipeName);
			System.out.println(e.getMessage());
			return null;
		}
	}

	public void sendComment(String recipeMaker, String recipeName,
			String comment) {
		try {
			String query = "INSERT INTO COMMENTS VALUES ('" + recipeMaker
					+ "','" + recipeName + "','" + System.currentTimeMillis()
					+ "','" + uid + "','" + comment + ");";
			conn.iud(query);
		} catch (SQLException e) {
			System.err.println("Error sending comment for " + recipeName);
			System.out.println(e.getMessage());
		}
	}

	public ArrayList<Comment> getComments(String recipeMaker, String recipeName) {
		try {
			ArrayList<Comment> comments = new ArrayList<Comment>();
			String query = "SELECT * FROM COMMENTS WHERE uid = '" + recipeMaker
					+ "' AND recipeName = '" + recipeName + "';";
			String result = conn.query(query, 5);
			ArrayList<String> rows = MySqlConnector.tokenizeResults(result,
					MySqlConnector.ROW_DELIM);
			for (String s : rows) {
				ArrayList<String> cols = MySqlConnector.tokenizeResults(s,
						MySqlConnector.COL_DELIM);
				comments.add(new Comment(cols.get(0), cols.get(1), Integer
						.parseInt(cols.get(2)), cols.get(3), cols.get(4)));
			}
			return comments;
		} catch (SQLException e) {
			System.err.println("Error getting comments for " + recipeName);
			System.out.println(e.getMessage());
			return null;
		}
	}

	/**
	 * setuid
	 * 
	 * Sets the value of uid
	 * 
	 * @param uid
	 *            user id value
	 */
	public void setuid(String uid) {
		this.uid = uid;
	}

	/**
	 * setpwd
	 * 
	 * Sets the value of pwd
	 * 
	 * @param pwd
	 *            user's password
	 */
	public void setpwd(String pwd) {
		this.pwd = pwd;
	}

	/**
	 * getuid
	 * 
	 * Returns the value of uid.
	 * 
	 * @return value of uid.
	 */
	public String getuid() {
		return uid;
	}
}
