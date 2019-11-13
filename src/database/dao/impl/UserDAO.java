package database.dao.impl;

import java.util.List;

import database.dao.IUserDAO;
import database.mapper.UserMapper;
import database.model.UserModel;

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

	@Override
	public List<UserModel> findGroupUsersById(Long id, Long userId) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT user_id as id, nickname, username, password FROM _group_member ");
		sql.append("INNER JOIN _users ON _group_member.user_id = _users.id ");
		sql.append("WHERE group_id = ? AND user_id != ?");
		List<UserModel> users = query(sql.toString(), new UserMapper(), id, userId);
		return users;
	}

	
}
