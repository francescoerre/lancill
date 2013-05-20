package lancill.objectfields.pojo;

import java.util.List;

public class User extends Person{
	
	private String mail;
	private String userid;
	private List<String> contacts;
	private List<User> friends;
	
	public User(){}
	
	public User(String name){
		super(name);
	}
	
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public List<String> getContacts() {
		return contacts;
	}
	public void setContacts(List<String> contacts) {
		this.contacts = contacts;
	}
	public List<User> getFriends() {
		return friends;
	}
	public void setFriends(List<User> friends) {
		this.friends = friends;
	}
	
}
