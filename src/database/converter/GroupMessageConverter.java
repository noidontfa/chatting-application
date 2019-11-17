package database.converter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import constant.SystemConstants;
import database.model.GroupMessageModel;
import model.transfer.Message;

public class GroupMessageConverter {

	public GroupMessageModel toGroupMessageModel(Message message) {
		GroupMessageModel groupMessageModel = new GroupMessageModel();
		groupMessageModel.setUserId(Integer.valueOf(message.getId()).longValue());
		groupMessageModel.setGroupId(Integer.valueOf(message.getRoomId()).longValue());
		groupMessageModel.setCreatedDate(new Timestamp(message.getTimeDate().getTime()));
		if (message.getCommad().equals(SystemConstants.MESS_STRING)) {
			groupMessageModel.setMsg(message.getMsg());
		} else if (message.getCommad().equals(SystemConstants.MESS_FILE)) {
			groupMessageModel.setMsg(message.getFileName());
			groupMessageModel.setFileBytes(message.getFileBytes());
		}

		return groupMessageModel;
	}
	
	public Message toMessageTransfer(int userId, GroupMessageModel groupMessageModel) {
		Message message = new Message();
		message.setId(userId);
		message.setRoomId(groupMessageModel.getGroupId().intValue());
		message.setToRoomId(groupMessageModel.getGroupId().intValue());
		List<Integer> toUser = new ArrayList<>();
		toUser.add(groupMessageModel.getUserId().intValue());
		message.setToUser(toUser);
		if(groupMessageModel.getFileBytes() == null) {
			message.setCommad(SystemConstants.MESS_STRING);
			message.setMsg(groupMessageModel.getMsg());
		} else {
			message.setCommad(SystemConstants.MESS_FILE);
			message.setFileName(groupMessageModel.getMsg());
			message.setFileBytes(groupMessageModel.getFileBytes());
		}
		message.setTimeDate(groupMessageModel.getCreatedDate());
		
		return message;
	}
}
