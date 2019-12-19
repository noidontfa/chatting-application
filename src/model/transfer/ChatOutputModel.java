package model.transfer;

import javax.swing.JLabel;

public class ChatOutputModel {
	private JLabel Image;
	private String name;
	private String msg;
	private String time;
	private boolean fileTransfer = false;
	private byte []fileBytes;
	private boolean hideImage = true;
	private int color = 1;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public boolean isHideImage() {
		return hideImage;
	}
	public void setHideImage(boolean hideImage) {
		this.hideImage = hideImage;
	}
	public byte [] getFileBytes() {
		return fileBytes;
	}
	public void setFileBytes(byte [] fileBytes) {
		
		this.fileBytes = fileBytes;
	}
	public boolean isFileTransfer() {
		return fileTransfer;
	}
	public void setFileTransfer(boolean fileTransfer) {
		this.fileTransfer = fileTransfer;
	}
	public JLabel getImage() {
		return Image;
	}
	public void setImage(JLabel image) {
		Image = image;
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}

	
	
	
	
}
