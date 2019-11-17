package database.service.impl;

import java.util.List;

import database.dao.IGroupMessageDAO;
import database.dao.impl.GroupMessageDAO;
import database.model.GroupMessageModel;
import database.service.IGroupMessageService;

public class GroupMessageService implements IGroupMessageService{

	private IGroupMessageDAO groupMessageDAO;
	public GroupMessageService() {
		groupMessageDAO = new GroupMessageDAO();
	}
	
	@Override
	public Long save(GroupMessageModel groupMessageModel) {
		return groupMessageDAO.save(groupMessageModel);
	}

	@Override
	public List<GroupMessageModel> findByGroupId(Long groupId) {
		return groupMessageDAO.findByGroupId(groupId);
	}

}
