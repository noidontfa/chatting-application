package database.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import database.model.PrivateMessageModel;

public class PrivateMessageMapper implements RowMapper<PrivateMessageModel> {

	@Override
	public PrivateMessageModel mapRow(ResultSet rs) {
		
		try {
			PrivateMessageModel privateMessageModel = new PrivateMessageModel();
			privateMessageModel.setCreatedDate(rs.getTimestamp("createddate"));
			privateMessageModel.setMsg(rs.getString("msg"));
			privateMessageModel.setFileBytes(rs.getBytes("filebytes"));
			privateMessageModel.setFriendId(rs.getLong("friend_id"));		
			return privateMessageModel;
		} catch (SQLException e) {
			
			return null;
		}
	
	}

}
