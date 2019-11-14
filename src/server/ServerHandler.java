package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Map;

import constant.SystemConstants;
import database.converter.PrivateMessageConverter;
import database.model.PrivateMessageModel;
import database.service.IFriendService;
import database.service.IPrivateMessageService;
import database.service.impl.FriendService;
import database.service.impl.PrivateMessageService;
import model.transfer.Message;

public class ServerHandler implements Runnable{
	private Socket socket;
	private ObjectInputStream objectInputStream;
	private ObjectOutputStream objectOutputStream;
	
//	DatabaseUser databaseUser = new DatabaseUser();
	
	private IFriendService friendService = new FriendService();
	private IPrivateMessageService privateMessageService = new PrivateMessageService();
	private PrivateMessageConverter privateMessageConverter = new PrivateMessageConverter();
	
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
				Map<Integer, ServerHandler> map = Server.getInstance().getOnlineUsers();
				Message message  = (Message)objectInputStream.readObject();
				List<Integer> toUserId = message.getToUser();
				for(Integer id : toUserId) {
					if(map.containsKey(id)) {
						map.get(id).sendMessageToClient(message);
					}
					Long friendId =friendService.findByUserIdAndFriendUserId(Integer.valueOf(message.getId()).longValue(), id.longValue()).getId();
					long timeDate = message.getTimeDate().getTime();
					String msg = null;
					if(message.getCommad().equals(SystemConstants.MESS_STRING)) {
						msg = message.getMsg();		
					}
					PrivateMessageModel privateMessageModel = privateMessageConverter.toPrivateMessageModel(friendId, msg, timeDate);
					privateMessageService.save(privateMessageModel);
				}		
				
				
				
					
									
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
}
