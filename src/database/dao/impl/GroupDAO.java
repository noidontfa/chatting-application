package database.dao.impl;

import java.util.List;

import database.dao.IGroupDAO;
import database.mapper.GroupMapper;
import database.model.GroupModel;

public class GroupDAO extends AbstractDAO<GroupModel> implements IGroupDAO{

	@Override
	public List<GroupModel> findGroupsById(Long id) {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT _groups.id,_groups.groupname FROM _groups ");
			sql.append("INNER JOIN _group_member ON _groups.id = _group_member.group_id ");
			sql.append("WHERE user_id = ? ");
			List<GroupModel> groups = query(sql.toString(), new GroupMapper(), id);
			return groups;
		
	}
	
}
