package database.dao;

import java.util.List;

import database.model.PrivateMessageModel;

public interface IPrivateMessageDAO extends GenericDAO<PrivateMessageModel> {
	Long save(PrivateMessageModel privateMessageModel);
	List<PrivateMessageModel> findByFriendId(Long id, Long id2);
}
