package database.dao;

import java.util.List;

import database.model.UserModel;

public interface IUserDAO extends GenericDAO<UserModel> {
	
	UserModel findByUsernameAndPassword(String username,String password);
	List<UserModel> findFriendsById(Long id);
	List<UserModel> findGroupUsersById(Long id, Long userId);
	
}
