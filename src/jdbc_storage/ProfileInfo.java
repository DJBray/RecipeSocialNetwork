package jdbc_storage;

public class ProfileInfo {
	private String uid, firstName, lastName, info, iid;
	private int birthday;
	
	public ProfileInfo(String uid){
		this.uid = uid;
		this.firstName = "My First Name";
		this.lastName = "My Last Name";
		this.info = "My personal info!";
		this.birthday = 11111111;
		this.iid = "NULL";
	}
	
	public ProfileInfo(String uid, String firstName, String lastName, String info,
			int birthday, String iid){
		this.uid = uid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.info = info;
		this.birthday = birthday;
		this.iid = iid;
	}
	
	/**
	 * parseBirthday
	 * 
	 * Used to take the pure int birthday and turn it into a normal
	 * birthday.
	 * 
	 * For example, the birthday August 29, 2010 in raw form would be
	 * 20100829
	 * After being parsed it would look like
	 * 08/29/2010
	 * 
	 * @param birthday
	 * @return
	 */
	public static String parseBirthday(int birthday){
		String bd = birthday + "";
		
		String year = bd.substring(0, 4);
		String month = bd.substring(4, 6);
		String day = bd.substring(6, 8);
		
		return month + "/" + day + "/" + year;
	}
	
	public String get_uid(){
		return uid;
	}
	
	public String get_firstName(){
		return firstName;
	}
	
	public String get_lastName(){
		return lastName;
	}
	
	public String get_info(){
		return info;
	}
	
	public int get_birthday(){
		return birthday;
	}
	
	public String get_iid(){
		return iid;
	}
}
