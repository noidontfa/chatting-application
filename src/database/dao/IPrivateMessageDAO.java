package database.dao;

import database.model.PrivateMessageModel;

public interface IPrivateMessageDAO extends GenericDAO<PrivateMessageModel> {
	Long save(PrivateMessageModel privateMessageModel);
}
