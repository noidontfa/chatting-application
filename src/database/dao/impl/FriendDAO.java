package database.dao.impl;

import java.util.List;

import database.dao.IFriendDAO;
import database.mapper.FriendMapper;
import database.model.FriendModel;

public class FriendDAO extends AbstractDAO<FriendModel> implements IFriendDAO {

	@Override
	public FriendModel findByUserIdAndFriendUserId(Long userId, Long friendUserId) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM _friends ");
		sql.append("WHERE user_id = ? AND friend_user_id = ? ");
		
		List<FriendModel> friendModel = query(sql.toString(), new FriendMapper(), userId, friendUserId);
		
		return friendModel.isEmpty() ? null : friendModel.get(0);
	}

}
