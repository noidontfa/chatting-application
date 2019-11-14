package database.service.impl;

import database.dao.IPrivateMessageDAO;
import database.dao.impl.PrivateMessageDAO;
import database.model.PrivateMessageModel;
import database.service.IPrivateMessageService;

public class PrivateMessageService implements IPrivateMessageService{

	IPrivateMessageDAO privateMessageDAO;
	
	public PrivateMessageService() {
		privateMessageDAO = new PrivateMessageDAO();
	}
	
	@Override
	public Long save(PrivateMessageModel privateMessageModel) {
	
		return privateMessageDAO.save(privateMessageModel);
	}
	
}
