package database.service.impl;

import java.util.List;

import database.dao.IGroupDAO;
import database.dao.impl.GroupDAO;
import database.model.GroupModel;
import database.service.IGroupService;

public class GroupService implements IGroupService {

	IGroupDAO groupDAO;
	
	public GroupService() {
		groupDAO = new GroupDAO();
	}
	
	@Override
	public List<GroupModel> findGroupsById(Long id) {		
		return groupDAO.findGroupsById(id);
	}

}
