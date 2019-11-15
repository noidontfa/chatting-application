package database.service;

import java.util.List;

import database.model.PrivateMessageModel;

public interface IPrivateMessageService {
	Long save(PrivateMessageModel privateMessageModel);
	List<PrivateMessageModel> findByFriendId(Long id, Long id2);
}
