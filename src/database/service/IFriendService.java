package database.service;

import database.model.FriendModel;

public interface IFriendService {
	FriendModel findByUserIdAndFriendUserId(Long userId, Long FriendUserId);
}
