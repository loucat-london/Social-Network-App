package socialnetworkapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Command {

	private String commandline;
	private Set<User> users;

	Command(String commandline){
		this.commandline=commandline;
		this.users = new HashSet<User>();

	}

	public String getCommandline() {
		return commandline;
	}

	public void setCommandline(String commandline) {
		this.commandline = commandline;
	}


	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	private User getUserByName(String username){
		Iterator<User> iter=users.iterator();

		while(iter.hasNext()){
			User tmp = iter.next();

			if(tmp.getUsername().equals(username)){
				return tmp;
			}
		}
		return null;
	}

	private String getTimeAgo(Message msg){
		Date now = new Date();

		long msec= now.getTime()- msg.getTimestamp().getTime();

		long minutes= msec/ (1000*60);

		if(minutes!=0){
			if(minutes==1)
				return " (" + minutes + " minute ago)";
			else return " (" + minutes + " minutes ago)";
		}
		else {
			if(msec/1000==1)
				return " (" + (msec/1000) + " second ago)";
			else return " (" + (msec/1000) + " seconds ago)";

		}

	}


	public void interpret(){

		if(commandline.contains(" -> ")){
			posting();
		}

		if(!commandline.contains(" ")){
			reading();
		}

		if(commandline.contains("follows")){
			following();
		}

		if(commandline.contains("wall")){
			getWall();
		}

	}

	private void posting(){
		String username;
		String message;

		username = commandline.substring(0, commandline.indexOf(" -> "));
		User newUser = new User(username);
		message =  commandline.substring(commandline.indexOf(" -> ")+4);
		Message msg= new Message(message);


		if(users.contains(newUser)){

			if(getUserByName(newUser.getUsername())!=null){
				newUser=getUserByName(newUser.getUsername());
			}

		}
		ArrayList<Message> msgs=newUser.getMessages();
		msgs.add(msg); 
		users.add(newUser);


	}

	private void getWall() {
		String username;
		username = commandline.substring(0, commandline.indexOf(" wall"));

		User user =getUserByName(username);

		ArrayList<Message> wallMsgs=new ArrayList<Message>();

		for(Message msg:user.getMessages()){
			msg.setContent(user.getUsername() + " - " + msg.getContent());
			wallMsgs.add(msg);
		}

		for(User following:user.getFollowingUsers()){
			for(Message msg:following.getMessages()){
				msg.setContent(following.getUsername() + " - " + msg.getContent());
				wallMsgs.add(msg);
			}	
		}

		Collections.sort(wallMsgs);
		Collections.reverse(wallMsgs);

		for(Message msg: wallMsgs){
			System.out.println(msg.getContent() + getTimeAgo(msg));
		}
	}

	private void following() {
		String username;
		username = commandline.substring(0, commandline.indexOf(" follows"));
		String following = commandline.substring(commandline.indexOf("follows ")+8);

		User followerUser =getUserByName(username);
		if(followerUser==null){
			followerUser = new User(username);
			users.add(followerUser);
		}
		User followingUser=getUserByName(following);

		(followerUser.getFollowingUsers()).add(followingUser);
	}

	private void reading() {
		String username;

		username = commandline;
		User newUser = new User(username);

		if(users.contains(newUser)){
			if(getUserByName(newUser.getUsername())!=null){
				newUser=getUserByName(newUser.getUsername());
			}
		}

		ArrayList<Message> msgs=newUser.getMessages();

		for(Message msg: msgs){
			System.out.println(msg.getContent() + getTimeAgo(msg));
		}
	}

}
