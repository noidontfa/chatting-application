package database.dao;

import java.util.List;

import database.model.GroupMessageModel;


public interface IGroupMessageDAO extends GenericDAO<GroupMessageModel>{

	Long save(GroupMessageModel groupMessageModel);
	List<GroupMessageModel> findByGroupId(Long groupId);
}
