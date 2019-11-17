package database.dao.impl;

import java.util.List;

import database.dao.IGroupMessageDAO;
import database.mapper.GroupMessageMapper;
import database.model.GroupMessageModel;

public class GroupMessageDAO extends AbstractDAO<GroupMessageModel> implements IGroupMessageDAO {

	@Override
	public Long save(GroupMessageModel groupMessageModel) {
		StringBuilder sql = new StringBuilder();
		if (groupMessageModel.getFileBytes() == null) {
			sql.append("INSERT INTO _group_message (msg, createddate, user_id, group_id) ");
			sql.append("VALUES (?,?,?,?)");
			return insert(sql.toString(), groupMessageModel.getMsg(), groupMessageModel.getCreatedDate(),
					groupMessageModel.getUserId(), groupMessageModel.getGroupId());
		} else {
			sql.append("INSERT INTO _group_message (msg, createddate, filebytes, user_id, group_id) ");
			sql.append("VALUES (?,?,?,?,?)");
			return insert(sql.toString(), groupMessageModel.getMsg(), groupMessageModel.getCreatedDate(),
					groupMessageModel.getFileBytes(), groupMessageModel.getUserId(), groupMessageModel.getGroupId());
		}

	}

	@Override
	public List<GroupMessageModel> findByGroupId(Long groupId) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM _group_message ");
		sql.append("WHERE group_id = ? ");
		sql.append("ORDER BY createddate ASC ");
		List<GroupMessageModel> lists = query(sql.toString(),new GroupMessageMapper() , groupId);
		return lists;
	}

}
