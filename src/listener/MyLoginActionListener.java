package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Client.Client;
import gui.ChattingForm2;
import gui.LoginForm;
import model.transfer.User;

public class MyLoginActionListener  implements ActionListener{

	
	@Override
	public void actionPerformed(ActionEvent e) {
		String username = LoginForm.getInstance().getTxtUserName().getText();
		String password = LoginForm.getInstance().getTxtPassword().getText();
		if(!username.trim().equals("") && !password.trim().equals("")) {
			User user = new User();
			user.setId(-1);
			user.setPassword(password);
			user.setUsername(username);
			Client client = Client.getInstance();
			client.setUser(user);
			client.execute();
			if(client.getUser() != null) {
					ChattingForm2.getInstance().setUser(client.getUser());
					ChattingForm2.getInstance().setVisible(true);
					LoginForm.getInstance().setVisible(false);
			} else {
				JOptionPane.showMessageDialog(null, "Failed!");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Password and Username are required");
		}
		
		
	}

}
