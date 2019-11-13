package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Map;

import model.transfer.Message;

public class ServerHandler implements Runnable{
	private Socket socket;
	private ObjectInputStream objectInputStream;
	private ObjectOutputStream objectOutputStream;
	
//	DatabaseUser databaseUser = new DatabaseUser();
	
	public ServerHandler(Socket socket) {
		this.socket = socket;
		
		try {
			
			objectOutputStream = new ObjectOutputStream(this.socket.getOutputStream());
			objectInputStream = new ObjectInputStream(this.socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendMessageToClient(Message message) {
		try {
			System.out.println("Server Sent: " + message.getMsg());
			this.objectOutputStream.writeObject(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		while(true) {
			try { 
				Map<Long, ServerHandler> map = Server.getInstance().getOnlineUsers();
				Message message  = (Message)objectInputStream.readObject();
					System.out.println("Server Recieved: " + message.getMsg());		
					List<Long> toUserId = message.getToUser();
					for(Long id : toUserId) {
						if(map.containsKey(id)) {
							map.get(id).sendMessageToClient(message);
						}
					}					
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
}
