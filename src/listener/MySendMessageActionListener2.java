package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import gui.ChattingForm2;
import model.transfer.Message;

public class MySendMessageActionListener2 implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// event sent message
		ChattingForm2 instance = ChattingForm2.getInstance();			
		String msg = instance.getMessage();
		if(!msg.trim().equals("")) {
			List<Integer> ids = instance.getToUserId();
			if (!ids.isEmpty()) {
				Message messageModel = new Message(instance.getUser().getId(),msg, ids, new Date());		
				messageModel.setToRoomId(instance.getRoomSelected());
				messageModel.setRoomId(instance.getMyRoomId());
				instance.displayMessage(messageModel);
				instance.getClientHandler().sendMessage(messageModel);
				instance.setMessage("");	
			} else {
				System.out.println("Please select a friends u want to message!");
			}
		}
		
		
		
		
	}

}
