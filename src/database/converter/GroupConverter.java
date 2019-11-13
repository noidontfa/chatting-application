package database.converter;

import database.model.GroupModel;
import database.model.UserModel;
import model.transfer.Group;

public class GroupConverter {
	
	private UserConverter userConverter;
	public GroupConverter() {
		userConverter = new UserConverter();
	}
	
	
	
	public Group toGroupTransfer(GroupModel groupModel) {
		
		Group group = new Group();
		group.setId(groupModel.getId().intValue());
		group.setName(groupModel.getGroupName());
		
		for(UserModel user : groupModel.getListUsers()) {
			group.getListGroup().add(userConverter.toUserTransfer(user));
		}
		return group;
	}
}
