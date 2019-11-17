package database.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import database.model.GroupMessageModel;

public class GroupMessageMapper implements RowMapper<GroupMessageModel> {

	@Override
	public GroupMessageModel mapRow(ResultSet rs) {

		try {
			GroupMessageModel groupMessageModel = new GroupMessageModel();
			groupMessageModel.setUserId(rs.getLong("user_id"));
			groupMessageModel.setGroupId(rs.getLong("group_id"));
			groupMessageModel.setMsg(rs.getString("msg"));
			groupMessageModel.setFileBytes(rs.getBytes("filebytes"));
			groupMessageModel.setCreatedDate(rs.getTimestamp("createddate"));
			return groupMessageModel;

		} catch (SQLException e) {
			return null;
		}
	}

}
