package database.service;

import java.util.List;

import database.model.GroupMessageModel;

public interface IGroupMessageService {
	Long save(GroupMessageModel groupMessageModel);
	List<GroupMessageModel> findByGroupId(Long groupId);
}
