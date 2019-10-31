package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import gui.ChattingForm;
import model.Message;
import model.User;

public class MySendMessageActionListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// event sent message
		ChattingForm instance = ChattingForm.getInstance();			
		String msg = instance.getTxtMessage().getText();
		User user = instance.getUser();
		Message messageModel = null;
		List<Integer> ids = instance.getToUserId();
		if (!ids.isEmpty()) {
			for(int i = 0; i < ids.size(); i++) {
				messageModel = new Message(user.getId(),msg, ids.get(i), new Date());
				instance.getClientHandler().sendMessage(messageModel);
			}
			instance.displayMessage(messageModel);
			instance.getTxtMessage().setText("");	
		} else {
			System.out.println("Please select a friends u want to message!");
		}
		
		
		
	}

}
