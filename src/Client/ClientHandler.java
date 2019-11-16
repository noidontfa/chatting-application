package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import interfaceGUI.IDisplayMessage;
import model.transfer.Message;
import model.transfer.User;

public class ClientHandler extends Thread{
	private Socket socket = null;
	private ObjectOutputStream objectOutputStream = null;
	private ObjectInputStream objectInputStream = null;
	private IDisplayMessage gui;
	
	public ClientHandler() {
		
	};
	
	public ClientHandler(Socket socket,IDisplayMessage gui,User user) {
		this.socket = socket;
		this.gui = gui;
		try {
			objectOutputStream = new ObjectOutputStream(this.socket.getOutputStream());
			objectInputStream = new ObjectInputStream(this.socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendMessage(Message message) {
		try {
			objectOutputStream.writeObject(message);								
		}
		 catch (IOException e) {
			e.printStackTrace();
		} 
	}
	@Override
	public void run() {
		while(true) {
			try {
				
				@SuppressWarnings("unchecked")
				List<Message> msgObject = (ArrayList<Message>)objectInputStream.readObject();	
				
				if(msgObject.size() == 1) {
					gui.writeMessageToGUI(msgObject.get(0));
				} else {
					for(Message message : msgObject) {
						if(message.getId() == message.getToUser().get(0)) {
							gui.displayMessage(message);
						} else {
							gui.writeMessageToGUI(message);
						}
					}
				}
				
				
			
				
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		}
	}


}
