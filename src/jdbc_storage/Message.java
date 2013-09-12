/**
 * Class: Message
 * @author Daniel J. Bray
 * 
 * This class is used to handle the data stored in messages and transfer it to the
 * application's GUI. Each message is composed of the specified attributes of the 
 * constructor and has getter/setter methods that match accordingly.
 */
package jdbc_storage;

public class Message {
	private String uid, fromUser, subject, message;
	private int messageId, dateSent;
	
	public Message(String uid, int messageId, String fromUser, 
			String subject, int dateSent, String message){
		this.uid = uid;
		this.messageId = messageId;
		this.fromUser = fromUser;
		this.subject = subject;
		this.dateSent = dateSent;
		this.message = message;
	}
	
	public String get_uid(){
		return uid;
	}
	
	public String get_fromUser(){
		return fromUser;
	}
	
	public String get_subject(){
		return subject;
	}
	
	public String get_message(){
		return message;
	}
	
	public int get_messageId(){
		return messageId;
	}
	
	public int get_dateSent(){
		return dateSent;
	}
}
