package database.service;

import java.util.List;

import database.model.GroupModel;

public interface IGroupService {
	List<GroupModel> findGroupsById(Long id);
}
