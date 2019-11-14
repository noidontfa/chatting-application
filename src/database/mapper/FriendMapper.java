package database.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import database.model.FriendModel;

public class FriendMapper implements RowMapper<FriendModel> {

	@Override
	public FriendModel mapRow(ResultSet rs) {
		
	
		try {
			FriendModel friendModel = new FriendModel();
			friendModel.setId(rs.getLong("id"));
			friendModel.setUserId(rs.getLong("user_id"));
			friendModel.setFriendUserId(rs.getLong("friend_user_id"));
			return friendModel;
		} catch (SQLException e) {
			return null;
		}
		
		
		
	}

}
