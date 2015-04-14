package socialnetworkapp;

import java.util.Date;

public class Message implements Comparable<Message>{
	private String content;
	private Date timestamp;

	Message(String content){
		this.content=content;
		this.timestamp= new Date();

	}


	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}


	@Override
	public int compareTo(Message otherMsg) {
		return this.getTimestamp().compareTo(otherMsg.getTimestamp());
	}

}
