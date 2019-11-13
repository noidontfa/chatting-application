package service.impl;

import java.util.List;

import dao.IUserDAO;
import dao.impl.UserDAO;
import model.database.UserModel;
import service.IUserService;

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

}
