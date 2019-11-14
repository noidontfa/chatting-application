package model.transfer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import constant.SystemConstants;

public class Message  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id; // id user
	private String msg;
	private List<Integer> toUser = new ArrayList<>();
	private Date  timeDate;
	private String fileName;
	private byte []fileBytes = new byte[3145728];
	private String commad;
	private int toRoomId;
	private int roomId; // id user or room id
	
	public Message() {
		
	}
	
	public Message(int id, String msg, List<Integer> toUser, Date  timeDate) {
		this.id = id;
		this.msg = msg;
		this.setToUser(toUser);
		this.timeDate = timeDate;
		this.commad = SystemConstants.MESS_STRING;
	
	}
	
	public Message(int id,File f, List<Integer> toUser, Date  timeDate) throws IOException {
		this.id = id;	
		this.setToUser(toUser);
		this.timeDate = timeDate;
		this.commad = SystemConstants.MESS_FILE;
		this.fileName = f.getName();
		FileInputStream fileInputStream = new FileInputStream(f);
		this.fileBytes = fileInputStream.readAllBytes();
		fileInputStream.close();
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Date  getTimeDate() {
		return timeDate;
	}
	public void setTimeDate(Date  timeDate) {
		this.timeDate = timeDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public byte[] getFileBytes() {
		return fileBytes;
	}
	public void setFileBytes(byte[] fileBytes) {
		this.fileBytes = fileBytes;
	}
	public String getCommad() {
		return commad;
	}
	public void setCommad(String commad) {
		this.commad = commad;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public List<Integer> getToUser() {
		return toUser;
	}

	public void setToUser(List<Integer> toUser) {
		this.toUser = toUser;
	}


	public int getToRoomId() {
		return toRoomId;
	}

	public void setToRoomId(int toRoomId) {
		this.toRoomId = toRoomId;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}


	
	
}
