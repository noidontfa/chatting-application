//package database;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import model.transfer.Group;
//import model.transfer.User;
//
//public class DatabaseUser {
//	public List<User> users = new ArrayList<>();
//	
//	public DatabaseUser() {
//		users.add(new User(0,"Thinh0","thinh0","123456"));
//		users.add(new User(1, "Thinh1", "thinh1", "123456"));
//		users.add(new User(2, "Thinh2", "thinh2", "123456"));
//		users.add(new User(3, "Thinh3", "thinh3", "123456"));
//		users.add(new User(4, "Thinh4", "thinh4", "123456"));
//		
//		for(int i = 0; i < users.size(); i++) {
//			users.get(i).setFriends(users);
//			users.get(i).getGroups().add(new Group(0, "Group Test", users));
//		}		
//		
//	}
//	
//	public User getUser(User user) {
//		
//		for(User i : users) {
//			if(user.getUsername().equals(i.getUsername()) && user.getPassword().equals(i.getPassword())) {
//				return i;
//			}
//		}
//		return user;		
//	}
//	
//public User getUser(int id) {
//		
//		return users.get(id);	
//	}
//}
