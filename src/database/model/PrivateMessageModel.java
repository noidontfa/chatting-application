package database.model;

import java.sql.Date;
import java.sql.Timestamp;

public class PrivateMessageModel {
	
	private String msg;
	private Timestamp createdDate;
	private Long friendId;
	
	
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	public Long getFriendId() {
		return friendId;
	}
	public void setFriendId(Long friendId) {
		this.friendId = friendId;
	}
	
	
	
}
