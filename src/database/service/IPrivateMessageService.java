package database.service;

import database.model.PrivateMessageModel;

public interface IPrivateMessageService {
	Long save(PrivateMessageModel privateMessageModel);
}
