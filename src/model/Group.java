package model;

import java.util.ArrayList;
import java.util.List;

public class Group {
	private int id;
	private String name;
	private List<User> ListGroup = new ArrayList<>();
	
	

	public Group(int id, String name, List<User> listGroup) {
		this.id = id;
		this.name = name;
		this.ListGroup = listGroup;
	}
	public List<User> getListGroup() {
		return ListGroup;
	}
	public void setListGroup(List<User> listGroup) {
		ListGroup = listGroup;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
