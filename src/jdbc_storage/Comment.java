package jdbc_storage;

public class Comment {
	private String uid, recipeName, poster, comment;
	private int postDate;
	
	public Comment(String uid, String recipeName, int postDate, String poster, String comment){
		this.uid = uid;
		this.recipeName = recipeName;
		this.postDate = postDate;
		this.poster = poster;
		this.comment = comment;
	}
	
	public String get_uid(){
		return uid;
	}
	
	public String get_recipeName(){
		return recipeName;
	}
	
	public int get_postDate(){
		return postDate;
	}
	
	public String get_poster(){
		return poster;
	}
	
	public String get_comment(){
		return comment;
	}
}
