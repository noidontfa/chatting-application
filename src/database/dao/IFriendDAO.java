package database.dao;

import database.model.FriendModel;

public interface IFriendDAO extends GenericDAO<FriendModel>{
	FriendModel findByUserIdAndFriendUserId(Long userId, Long FriendUserId);
}
