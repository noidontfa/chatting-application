package database.converter;

import java.sql.Timestamp;

import database.model.PrivateMessageModel;

public class PrivateMessageConverter {
	public PrivateMessageModel toPrivateMessageModel(Long friendId, String msg, long date) {
		
		PrivateMessageModel privateMessageModel = new PrivateMessageModel();
		privateMessageModel.setFriendId(friendId);
		privateMessageModel.setMsg(msg);
		privateMessageModel.setCreatedDate(new Timestamp(date));
		
		return privateMessageModel;
	}
}
