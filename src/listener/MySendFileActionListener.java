package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.swing.JFileChooser;

import gui.ChattingForm;
import model.transfer.Message;

public class MySendFileActionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {

		ChattingForm instance = ChattingForm.getInstance();
		JFileChooser chooser = new JFileChooser();
		File f = null;
		int temp = chooser.showOpenDialog(null); // mo file
		if (temp != JFileChooser.CANCEL_OPTION) {
			f = chooser.getSelectedFile();
			try {
				List<Long> ids = instance.getToUserId();
				if (!ids.isEmpty()) {
					Message message = new Message(instance.getRoomSelected(), f, ids, new Date());				
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

}
