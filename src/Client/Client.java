package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import model.User;

public class Client {
	private static Client instance;
	private static final String SERVER_IP = "localhost";
	private static final int SERVER_PORT = 9090;
	private Socket socket = null;
	private User user;
	
	private ObjectOutputStream objectOutputStream = null;
	private ObjectInputStream objectInputStream = null;

	public Socket getSocket() {
		return socket;
	}

	public Client() {
		if (socket == null) {
			try {
				socket = new Socket(SERVER_IP, SERVER_PORT);
				objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
				objectInputStream = new ObjectInputStream(socket.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void execute() {
		System.out.print("Connected");
		initSocket();
		if(doAuthentication() == false) {		
			try {
				this.objectOutputStream.close();
				this.objectInputStream.close();
				this.socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
		}

	}

	private boolean doAuthentication() {

		try {
//			socket = new Socket(SERVER_IP, SERVER_PORT);
//			objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
//			objectInputStream = new ObjectInputStream(socket.getInputStream());

			objectOutputStream.writeObject(user);
			this.user = (User) objectInputStream.readObject();
			if(user != null) return true;
			else {
				return false;
			}
			 
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		} finally {
//			try {
//				objectOutputStream.close();
//				objectInputStream.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}

		}
		return false;
	}

	public static Client getInstance() {
		if (instance == null) {
			instance = new Client();
		}
		return instance;
	}

	private  void initSocket() {
		if (socket.isClosed()) {
			try {
				socket = new Socket(SERVER_IP, SERVER_PORT);
				objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
				objectInputStream = new ObjectInputStream(socket.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

	
}
