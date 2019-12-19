package listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.swing.JFileChooser;

import customGUI.ChattingInputGUI;
import gui.ChattingForm2;
import model.transfer.Message;

public class MySendFileActionListener2 extends MouseAdapter {

	
	@Override
	public void mousePressed(MouseEvent arg0)  {
		
		ChattingForm2 instance = ChattingForm2.getInstance();
		JFileChooser chooser = new JFileChooser();
		File f = null;
		int temp = chooser.showOpenDialog(null); // mo file
		if (temp != JFileChooser.CANCEL_OPTION) {
			f = chooser.getSelectedFile();
			try {
				List<Integer> ids = instance.getToUserId();
				if (!ids.isEmpty()) {
					Message message = new Message(instance.getUser().getId(), f, ids, new Date());		
					message.setToRoomId(instance.getRoomSelected());
					message.setRoomId(instance.getMyRoomId());
					instance.displayMessage(message);
					instance.getClientHandler().sendMessage(message);
				} else {
					System.out.println("Please select friends u want to message!");
				}

			} catch (IOException err) {
				err.printStackTrace();
			}
		}

	}
	
	@Override
	public void mouseEntered(java.awt.event.MouseEvent e) {
		ChattingInputGUI chattingInputGUI = ChattingForm2.getInstance().getChattingInputGUI();
		chattingInputGUI.setImage("C:\\Users\\Thinh\\eclipse-workspace-2\\Swing\\img\\file-hover.png");
	}
	
	@Override
	public void mouseExited(java.awt.event.MouseEvent e) {
		ChattingInputGUI chattingInputGUI = ChattingForm2.getInstance().getChattingInputGUI();
		chattingInputGUI.setImage("C:\\Users\\Thinh\\eclipse-workspace-2\\Swing\\img\\file-before.png");
	}

	
	

}
