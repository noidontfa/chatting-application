package database.dao.impl;

import database.dao.IPrivateMessageDAO;
import database.model.PrivateMessageModel;

public class PrivateMessageDAO extends AbstractDAO<PrivateMessageModel> implements IPrivateMessageDAO{

	@Override
	public Long save(PrivateMessageModel privateMessageModel) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO _private_message (msg, createddate, friend_id) ");
		sql.append("VALUES (?,?,?) ");
		return insert(sql.toString(), privateMessageModel.getMsg(), privateMessageModel.getCreatedDate(), privateMessageModel.getFriendId());
	}

}
