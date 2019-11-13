package model.transfer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Group implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private List<User> ListGroup = new ArrayList<>();
	
	

	public Group(Long id, String name, List<User> listGroup) {
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
