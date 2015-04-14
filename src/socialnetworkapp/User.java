package socialnetworkapp;

import java.util.ArrayList;

public class User {
	private String username;
	private ArrayList<Message> messages;
	private ArrayList<User> followingUsers;

	User(String username){
		this.username=username;
		this.messages= new ArrayList<Message>();
		this.followingUsers= new ArrayList<User>();
	}



	public ArrayList<User> getFollowingUsers() {
		return followingUsers;
	}



	public void setFollowingUsers(ArrayList<User> followingUsers) {
		this.followingUsers = followingUsers;
	}



	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public ArrayList<Message> getMessages() {
		return messages;
	}
	public void setMessages(ArrayList<Message> messages) {
		this.messages = messages;
	}

	@Override
	public boolean equals(Object obj) {
		if(this==obj)
			return true;
		if(!(obj instanceof User))
			return false;
		User user = (User) obj;

		return user.getUsername().equals(this.getUsername());

	}

	@Override
	public int hashCode(){
		return username.hashCode();
	}




}
