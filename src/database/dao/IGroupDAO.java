package database.dao;

import java.util.List;

import database.model.GroupModel;

public interface IGroupDAO extends GenericDAO<GroupModel>{
	List<GroupModel> findGroupsById(Long id);
}
