package database.service.impl;

import database.dao.IFriendDAO;
import database.dao.impl.FriendDAO;
import database.model.FriendModel;
import database.service.IFriendService;

public class FriendService implements IFriendService {

	
	private IFriendDAO friendDAO;
	
	public FriendService() {
		friendDAO = new FriendDAO();
	}
	
	@Override
	public FriendModel findByUserIdAndFriendUserId(Long userId, Long friendUserId) {	
		return friendDAO.findByUserIdAndFriendUserId(userId, friendUserId);
	}

}
