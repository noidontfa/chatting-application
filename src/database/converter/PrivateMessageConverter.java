package database.converter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import constant.SystemConstants;
import database.model.PrivateMessageModel;
import model.transfer.Message;

public class PrivateMessageConverter {
	public PrivateMessageModel toPrivateMessageModel(Long friendId, String msg, long date, byte[] fileBytes) {

		PrivateMessageModel privateMessageModel = new PrivateMessageModel();
		privateMessageModel.setFriendId(friendId);
		privateMessageModel.setMsg(msg);
		privateMessageModel.setCreatedDate(new Timestamp(date));
		privateMessageModel.setFileBytes(fileBytes);

		return privateMessageModel;
	}

	public Message toPrivateMessageTransfer(Long friendId, Long userId, Long friendUserId, int roomId, int toRoomId,
			PrivateMessageModel model) {
		
		Message msg = new Message();
		msg.setId(userId.intValue());
		msg.setRoomId(roomId);
		msg.setToRoomId(toRoomId);
		List<Integer> toUserId = new ArrayList<>();
		if (model.getFriendId() == friendId) {
			toUserId.add(userId.intValue());
		} else {
			toUserId.add(friendUserId.intValue());
		}
		msg.setToUser(toUserId);
		if (model.getFileBytes() == null) {
			msg.setCommad(SystemConstants.MESS_STRING);
			msg.setMsg(model.getMsg());
		} else {
			msg.setCommad(SystemConstants.MESS_FILE);
			msg.setFileName(model.getMsg());
			msg.setFileBytes(model.getFileBytes());
		}
		msg.setTimeDate(model.getCreatedDate());

		return msg;

	}
}
