package customGUI;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;

import util.RoundImage;

import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;

import listener.MySelectedRoomActionListener;
import model.transfer.User;

public class InformationGUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblImage;
	private JLabel lblUsername;
	private JLabel lblMessage;
	private JLabel lblDate;
	
	/**
	 * Create the panel.
	 */
	private int roomId;
	private List<User> users = new ArrayList<>();
	private boolean privateChat = true;
	
	
	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public boolean isPrivateChat() {
		return privateChat;
	}

	public void setPrivateChat(boolean privateChat) {
		this.privateChat = privateChat;
	}

	public InformationGUI() {
		setSize(new Dimension(300, 80));
		setBackground(new Color(248,248,248));
		setPreferredSize(new Dimension(320, 80));
		
		lblImage = new JLabel("");
		lblImage.setSize(new Dimension(60, 60));
		lblImage.setPreferredSize(new Dimension(60, 60));
		
		lblUsername = new JLabel("nameUser");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		lblDate = new JLabel();
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDate.setForeground(new Color(158, 158, 158));
		lblMessage = new JLabel();
		lblMessage.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMessage.setForeground(new Color(158, 158, 158));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(lblImage, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblDate, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblMessage, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE))
					.addGap(156))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblUsername)
								.addComponent(lblDate))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblMessage))
						.addComponent(lblImage, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap())
		);
		setLayout(groupLayout);
		
		this.addMouseListener(new MySelectedRoomActionListener());

	}
	
	public void setImage(String path) {
        try {
            ImageIcon icon = new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(
            		50,
            		50,
            		Image.SCALE_DEFAULT
            		));
           
            lblImage.setIcon(new ImageIcon(RoundImage.getRoundImage(icon.getImage(), 100)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	public JLabel getImage() {
		return lblImage;
	}
	
	public void setName(String name) {
		lblUsername.setText(name);
	}
	
	public void setMessage(String name,String msg, String date) {
		lblMessage.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMessage.setText(name+": "+msg);
		lblDate.setText(date);
	}
	
	public void disableBold() {
		lblMessage.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 12));
	}
	
	public String getName() {
		return lblUsername.getText();
	}
	
}
