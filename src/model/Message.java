package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

public class Message  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String msg;
	private int toUser;
	private Date timeDate;
	private String fileName;
	private byte []fileBytes = new byte[3145728];
	private String commad;
	
	public Message(int id, String msg, int toUser, Date timeDate) {
		this.id = id;
		this.msg = msg;
		this.toUser = toUser;
		this.timeDate = timeDate;
		this.commad = "msg";
	}
	
	public Message(int id,File f, int toUser, Date timeDate) throws IOException {
		this.id = id;	
		this.toUser = toUser;
		this.timeDate = timeDate;
		this.commad = "file";
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
	public int getToUser() {
		return toUser;
	}
	public void setToUser(int toUser) {
		this.toUser = toUser;
	}
	public Date getTimeDate() {
		return timeDate;
	}
	public void setTimeDate(Date timeDate) {
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
	
	
	
}
