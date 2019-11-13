package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Client.Client;
import gui.ChattingForm;
import gui.LoginForm;
import model.transfer.User;

public class MyLoginActionListener  implements ActionListener{

	
	@Override
	public void actionPerformed(ActionEvent e) {
		String username = LoginForm.getInstance().getTxtUserName().getText();
		String password = LoginForm.getInstance().getTxtPassword().getText();
		User user = new User();
		user.setId((long) -1);
		user.setPassword(password);
		user.setUsername(username);
		Client client = Client.getInstance();
		client.setUser(user);
		client.execute();
		if(client.getUser() != null) {
				ChattingForm.getInstance().setUser(client.getUser());
				ChattingForm.getInstance().setVisible(true);
				LoginForm.getInstance().setVisible(false);
		} else {
			JOptionPane.showMessageDialog(null, "Failed!");
		}
		
	}

}
