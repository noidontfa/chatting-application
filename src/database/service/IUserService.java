package database.service;

import java.util.List;

import database.model.UserModel;

public interface IUserService {
	UserModel findByUsernameAndPassword(String username,String password);
	List<UserModel> findFriendsById(Long id);
	List<UserModel> findGroupUsersById(Long id, Long userId);
}
