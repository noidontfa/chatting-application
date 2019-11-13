package dao;

import model.database.UserModel;

public interface IUserDAO extends GenericDAO<UserModel> {
	
	UserModel findByUsernameAndPassword(String username,String password);
}
