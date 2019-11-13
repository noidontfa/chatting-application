package database.service.impl;

import java.util.List;

import database.dao.IUserDAO;
import database.dao.impl.UserDAO;
import database.model.UserModel;
import database.service.IUserService;

public class UserService implements IUserService{

	IUserDAO userDAO;
	
	public UserService() {
		userDAO = new UserDAO();
	}
	
	@Override
	public UserModel findByUsernameAndPassword(String username, String password) {
		return userDAO.findByUsernameAndPassword(username, password);
	}

	@Override
	public List<UserModel> findFriendsById(Long id) {
		return userDAO.findFriendsById(id);
	}

	@Override
	public List<UserModel> findGroupUsersById(Long id, Long userId) {
		return userDAO.findGroupUsersById(id, userId);
	}

}
