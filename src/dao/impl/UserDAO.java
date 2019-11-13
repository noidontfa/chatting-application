package dao.impl;

import java.util.List;

import dao.IUserDAO;
import mapper.UserMapper;
import model.database.UserModel;

public class UserDAO extends AbstractDAO<UserModel> implements IUserDAO{

	@Override
	public UserModel findByUsernameAndPassword(String username, String password) {
		String sql = "SELECT * FROM _users WHERE username = ? AND password = ?";
		
		List<UserModel> user = query(sql, new UserMapper(), username,password);
		return user.isEmpty() ? null : user.get(0);
	}
}
