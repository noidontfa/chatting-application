package service;

import java.util.List;

import model.database.UserModel;

public interface IUserService {
	UserModel findByUsernameAndPassword(String username,String password);
	List<UserModel> findFriendsById(Long id);
}
