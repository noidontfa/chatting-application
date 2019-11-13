package dao;

import java.util.List;

import model.database.UserModel;

public interface IUserDAO extends GenericDAO<UserModel> {
	
	UserModel findByUsernameAndPassword(String username,String password);
	List<UserModel> findFriendsById(Long id);
}
