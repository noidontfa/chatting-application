package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.database.UserModel;

public class UserMapper implements RowMapper<UserModel>{

	@Override
	public UserModel mapRow(ResultSet rs) {
		try {
			UserModel user = new UserModel();
			user.setId(rs.getLong("id"));
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			user.setNickname(rs.getString("nickname"));
			return user;
		} catch (SQLException e) {
			
			return null;
		}
	}

}
