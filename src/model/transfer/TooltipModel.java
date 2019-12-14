package model.transfer;

public class TooltipModel {
	// private byte[] Image;
	private String name;
	private String msg;
	private String time;
	private boolean fileTransfer = false;
	private byte []fileBytes;
	private boolean left = true;
	private boolean hideImage = true;
	
	
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
	public boolean isLeft() {
		return left;
	}
	public void setLeft(boolean left) {
		this.left = left;
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

	
	
	
	
}
