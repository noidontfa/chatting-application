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

	@Override
	public List<UserModel> findFriendsById(Long id) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT _users.id, _users.nickname, _users.username, _users.password ");
		sql.append("FROM _users INNER JOIN _friends	ON _friends.friend_user_id = _users.id ");
		sql.append("WHERE user_id = ?");
		
		List<UserModel> users = query(sql.toString(), new UserMapper(), id);
		return users;
	}
}
