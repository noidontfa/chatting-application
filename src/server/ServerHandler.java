package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import constant.SystemConstants;
import database.converter.GroupMessageConverter;
import database.converter.PrivateMessageConverter;
import database.model.GroupMessageModel;
import database.model.PrivateMessageModel;
import database.service.IFriendService;
import database.service.IGroupMessageService;
import database.service.IPrivateMessageService;
import database.service.impl.FriendService;
import database.service.impl.GroupMessageService;
import database.service.impl.PrivateMessageService;
import model.transfer.Message;

public class ServerHandler implements Runnable {
	private Socket socket;
	private ObjectInputStream objectInputStream;
	private ObjectOutputStream objectOutputStream;

//	DatabaseUser databaseUser = new DatabaseUser();

	private IFriendService friendService = new FriendService();
	private IGroupMessageService groupMessageService = new GroupMessageService();
	private IPrivateMessageService privateMessageService = new PrivateMessageService();
	private PrivateMessageConverter privateMessageConverter = new PrivateMessageConverter();
	private GroupMessageConverter groupMessageConverter = new GroupMessageConverter();

	public ServerHandler(Socket socket) {
		this.socket = socket;

		try {

			objectOutputStream = new ObjectOutputStream(this.socket.getOutputStream());
			objectInputStream = new ObjectInputStream(this.socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendMessageToClient(List<Message> messages) {
		try {
			this.objectOutputStream.writeObject(messages);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				Map<Integer, ServerHandler> map = Server.getInstance().getOnlineUsers();
				Message message = (Message) objectInputStream.readObject();
				String commad = message.getCommad();
				if (commad.equals(SystemConstants.MESS_PRIVATE_LOADDING)) {
					List<Message> lists = getPrivateChatFromDatabase(message);
					map.get(message.getId()).sendMessageToClient(lists);

				} else if (commad.equals(SystemConstants.MESS_GROUP_LOADDING)) {
					List<Message> lists = getGroupChatFromDatabase(message);
					map.get(message.getId()).sendMessageToClient(lists);
				} else {
					List<Integer> toUserId = message.getToUser();
					for (Integer id : toUserId) {
						if (map.containsKey(id)) {
							List<Message> messages = new ArrayList<Message>();
							messages.add(message);
							map.get(id).sendMessageToClient(messages);
						}
					}
					int size = toUserId.size();
					if (size == 1) {
						savePrivateMessage(message);
					} else {
						saveGroupMessage(message);
					}

				}

			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		}

	}

	public List<Message> getPrivateChatFromDatabase(Message message) {
		Long userId = Integer.valueOf(message.getId()).longValue();
		Long friendUserId = message.getToUser().get(0).longValue();
		Long id = friendService.findByUserIdAndFriendUserId(userId, friendUserId).getId();// user-id
		Long id2 = friendService.findByUserIdAndFriendUserId(friendUserId, userId).getId();// friend-user-id
		List<PrivateMessageModel> listModels = privateMessageService.findByFriendId(id, id2);
		List<Message> listMessages = new ArrayList<>();
		for (PrivateMessageModel model : listModels) {
			Message msg = privateMessageConverter.toPrivateMessageTransfer(id, userId, friendUserId,
					message.getRoomId(), message.getToRoomId(), model);
			listMessages.add(msg);
		}
		return listMessages;
	}

	public List<Message> getGroupChatFromDatabase(Message message) {
		Long groupId = Integer.valueOf(message.getRoomId()).longValue();
		List<GroupMessageModel> listModels = groupMessageService.findByGroupId(groupId);
		List<Message> listMessages = new ArrayList<>();
		for (GroupMessageModel model : listModels) {
			Message msg = groupMessageConverter.toMessageTransfer(message.getId(), model);
			listMessages.add(msg);
		}
		return listMessages;

	}
	
	public void savePrivateMessage(Message message) {
		Long friendId = friendService.findByUserIdAndFriendUserId(
				Integer.valueOf(message.getId()).longValue(), message.getToUser().get(0).longValue()).getId();
		long timeDate = message.getTimeDate().getTime();
		String msg = null;
		byte[] fileBytes = null;
		if (message.getCommad().equals(SystemConstants.MESS_STRING)) {
			msg = message.getMsg();
		} else if (message.getCommad().equals(SystemConstants.MESS_FILE)) {
			msg = message.getFileName();
			fileBytes = message.getFileBytes();
		}
		PrivateMessageModel privateMessageModel = privateMessageConverter
				.toPrivateMessageModel(friendId, msg, timeDate, fileBytes);
		privateMessageService.save(privateMessageModel);
		
	}
	
	public void saveGroupMessage(Message message) {
		GroupMessageModel groupMessageModel = groupMessageConverter.toGroupMessageModel(message);
		groupMessageService.save(groupMessageModel);
	}

}
