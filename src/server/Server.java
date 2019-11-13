package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import database.converter.GroupConverter;
import database.converter.UserConverter;
import database.model.GroupModel;
import database.model.UserModel;
import database.service.IGroupService;
import database.service.IUserService;
import database.service.impl.GroupService;
import database.service.impl.UserService;
import model.transfer.User;

public class Server extends Thread{
	private static Server instance;
	private static final int PORT = 9090;
	private ServerSocket server;
	
	private boolean doExecute = false;
	private static Map<Integer, ServerHandler> onlineUsers = new HashMap<>();


	private static ExecutorService pool = Executors.newFixedThreadPool(500);
 	
	//database tam
//	DatabaseUser databaseUser = new DatabaseUser();
	private IUserService userService = new UserService();
	private IGroupService groupService = new GroupService();
	private UserConverter userConverter = new UserConverter();
	private GroupConverter groupConverter = new GroupConverter();
	
	//

	@Override
	public void run() {
		while (doExecute) {
			System.out.println("Waiting Client to contect");
			Socket socket = null;
			ObjectOutputStream  objectOutputStream = null;
			ObjectInputStream   objectInputStream = null;
			try {
				socket = server.accept();
				objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
				objectInputStream = new ObjectInputStream(socket.getInputStream());
				
				
				System.out.println("New client connected: " + socket);
				doAuthentication(socket,objectOutputStream,objectInputStream);
			} catch (IOException e) {

				e.printStackTrace();
			} 
	}
}
	
	public Server() {
		try {
			server = new ServerSocket(PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.start();
	}

	public void execute() {	
		doExecute = true;
	}

	public void stopServer() {
		doExecute = false;
	}

	private void doAuthentication(Socket socket ,ObjectOutputStream objectOutputStream,ObjectInputStream objectInputStream) {
		
		try {
			User user = (User) objectInputStream.readObject();
			UserModel userModel = userService.findByUsernameAndPassword(user.getUsername(),user.getPassword());			
			if (userModel != null) {
				// we have user
				Long userId = userModel.getId();
				user = userConverter.toUserTransfer(userModel);
				List<UserModel> listFriends = userService.findFriendsById(userId);
				for(UserModel friend : listFriends) {
					user.getFriends().add(userConverter.toUserTransfer(friend));
				}
				
				List<GroupModel> listGroups = groupService.findGroupsById(userId);
				for(GroupModel group : listGroups) {
					List<UserModel> users = userService.findGroupUsersById(group.getId(), userId);
					group.setListUsers(users);
					
					user.getGroups().add(groupConverter.toGroupTransfer(group));
				}
				
				objectOutputStream.writeObject(user);

				ServerHandler clientHandler = new ServerHandler(socket);
				onlineUsers.put(user.getId(), clientHandler);
				pool.execute(clientHandler);	
			} else {
				objectOutputStream.writeObject(null);				
				
				objectInputStream.close();
				objectOutputStream.close();
				socket.close();
			}
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		} 

	}

	public static Server getInstance() {
		if (instance == null) {
			instance = new Server();
		}
		return instance;
	}
	
	public Map<Integer, ServerHandler> getOnlineUsers() {
		return onlineUsers;
	}

}
