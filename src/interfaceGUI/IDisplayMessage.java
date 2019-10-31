package interfaceGUI;

import model.Message;

public interface IDisplayMessage {
	
	void writeMessageToGUI(Message message);
	void displayMessage(Message message);
}
