package converter;

import model.database.UserModel;
import model.transfer.User;

public class UserConverter {
	
	public User toUserTransfer(UserModel userModel) {
		
		User userTransfer = new User();
		userTransfer.setId(userModel.getId());
		userTransfer.setNickName(userModel.getNickname());
		userTransfer.setUsername(userModel.getUsername());
		userTransfer.setPassword(userModel.getPassword());
		
		return userTransfer;
	}
}
