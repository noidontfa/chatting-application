package database.model;

import java.util.ArrayList;
import java.util.List;

public class GroupModel {
	private Long id;
	private String groupName;
	private List<UserModel> ListUsers = new ArrayList<>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public List<UserModel> getListUsers() {
		return ListUsers;
	}
	public void setListUsers(List<UserModel> listUsers) {
		ListUsers = listUsers;
	}
	
	
}
