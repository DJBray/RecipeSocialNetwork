/**
 * Class: Recipe
 * @author Daniel J Bray
 * 
 * This class is used to transport and store recipe data.
 */

package jdbc_storage;

public class Recipe {

	private String uid, recipeName, recipeDescription, recipeIngrediants, recipeSteps, iid;
	private int rating, numRatings, dateMade;
	
	public Recipe(String uid, String recipeName, String recipeDescription, String recipeIngrediants, 
			String recipeSteps, int rating, int numRatings, String iid, int dateMade){
		this.uid = uid;
		this.recipeName = recipeName;
		this.recipeDescription = recipeDescription;
		this.recipeIngrediants = recipeIngrediants;
		this.recipeSteps = recipeSteps;
		this.rating = rating;
		this.numRatings = numRatings;
		this.iid = iid;
		this.dateMade = dateMade;
	}
	
	public String get_uid(){
		return uid;
	}
	
	public String get_recipeName(){
		return recipeName;
	}
	
	public String get_recipeDescription(){
		return recipeDescription;
	}
	
	public String get_recipeIngrediants(){
		return recipeIngrediants;
	}
	
	public String get_recipeSteps(){
		return recipeSteps;
	}
	
	public int get_rating(){
		return rating;
	}
	
	public int get_numRatings(){
		return numRatings;
	}
	
	public String get_iid(){
		return iid;
	}
	
	public int get_dateMade(){
		return dateMade;
	}
}
