package service;

import model.database.UserModel;

public interface IUserService {
	UserModel findByUsernameAndPassword(String username,String password);
}
