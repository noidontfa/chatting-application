package interfaceGUI;

import model.transfer.Message;

public interface IDisplayMessage {
	// hello
	void  writeMessageToGUI(Message message);
	void displayMessage(Message message);
	void showMessageInfo(Message message);
}
