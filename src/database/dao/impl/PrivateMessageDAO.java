package database.dao.impl;

import java.util.List;

import database.dao.IPrivateMessageDAO;
import database.mapper.PrivateMessageMapper;
import database.model.PrivateMessageModel;

public class PrivateMessageDAO extends AbstractDAO<PrivateMessageModel> implements IPrivateMessageDAO{

	@Override
	public Long save(PrivateMessageModel privateMessageModel) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO _private_message (msg, createddate, filebytes, friend_id) ");
		sql.append("VALUES (?,?,?,?) ");
		return insert(sql.toString(), privateMessageModel.getMsg(), privateMessageModel.getCreatedDate(), 
				privateMessageModel.getFileBytes(), privateMessageModel.getFriendId());
	}

	@Override
	public List<PrivateMessageModel> findByFriendId(Long id, Long id2) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM _private_message ");
		sql.append("WHERE friend_id = ? OR friend_id = ? ");
		sql.append("ORDER BY createddate ASC");
		List<PrivateMessageModel> lists = query(sql.toString(), new PrivateMessageMapper(), id,id2); 
		
		return lists;
	}

}
