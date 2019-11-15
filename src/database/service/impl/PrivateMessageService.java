package database.service.impl;

import java.util.List;

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

	@Override
	public List<PrivateMessageModel> findByFriendId(Long id, Long id2) {
		
		return privateMessageDAO.findByFriendId(id, id2);
	}
	
}
