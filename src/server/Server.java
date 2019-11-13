package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import converter.UserConverter;
import model.database.UserModel;
import model.transfer.User;
import service.IUserService;
import service.impl.UserService;

public class Server extends Thread{
	private static Server instance;
	private static final int PORT = 9090;
	private ServerSocket server;
	
	private boolean doExecute = false;
	private static Map<Long, ServerHandler> onlineUsers = new HashMap<>();


	private static ExecutorService pool = Executors.newFixedThreadPool(500);
 	
	//database tam
//	DatabaseUser databaseUser = new DatabaseUser();
	private IUserService userService = new UserService();
	private UserConverter userConverter = new UserConverter();
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
			//user = databaseUser.getUser(user);
			UserModel userModel = userService.findByUsernameAndPassword(user.getUsername(),user.getPassword());
			user = userConverter.toUserTransfer(userModel);
			
			if (user.getId() != -1) {
				// we have user
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
	
	public Map<Long, ServerHandler> getOnlineUsers() {
		return onlineUsers;
	}

}
