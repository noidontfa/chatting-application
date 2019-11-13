package database.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import database.model.GroupModel;

public class GroupMapper implements RowMapper<GroupModel>{

	@Override
	public GroupModel mapRow(ResultSet rs) {
		
		try {
			GroupModel groupModel = new GroupModel();	
			groupModel.setGroupName(rs.getString("groupname"));
			groupModel.setId(rs.getLong("id"));
			return groupModel;
		} catch (SQLException e) {
			return null;
		}
	
	}

}
