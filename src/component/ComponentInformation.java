package component;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;

import listener.MySelectedFriendActionListener;
import model.transfer.User;

public class ComponentInformation extends JPanel {

	/**
	 * 
	 */
	private int roomId;
	private static final long serialVersionUID = 1L;
	private List<User> users = new ArrayList<>();
	private boolean privateChat = true;
	private JLabel lbNameRoom;
	JLabel lbImage;
	JLabel lbMsg;

	/**
	 * Create the panel.
	 */

	public ComponentInformation() {

		setBackground(Color.white);

		lbImage = new JLabel();
		lbMsg = new JLabel("This is message...");
		Font f = lbMsg.getFont();
		lbMsg.setFont(f.deriveFont(f.getSize() | Font.ITALIC));
		lbNameRoom = new JLabel("");
		try {
			drawImg();
		} catch (IOException e) {
			e.printStackTrace();
		}

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(lbImage)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lbNameRoom, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
								.addComponent(lbMsg, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(36, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lbNameRoom, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(lbMsg))
						.addComponent(lbImage))
				.addContainerGap()));
		setLayout(groupLayout);

		this.addMouseListener(new MySelectedFriendActionListener());
	}

	public JLabel getLbNameRoom() {
		return lbNameRoom;
	}

	public void setLbNameRoom(JLabel lbNameRoom) {
		this.lbNameRoom = lbNameRoom;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	void drawImg() throws IOException {

//		BufferedImage image = ImageIO.read(new File("C:\\Users\\Thinh\\eclipse-workspace-2\\JTabbedPane\\img\\44948.png"));
//
//		
//		Area clip = new Area( new Rectangle(0, 0, image.getWidth(), image.getHeight()) );
//		Area oval = new Area( new Ellipse2D.Double(0, 0, image.getWidth() - 1, image.getHeight() - 1) );
//		clip.subtract( oval );
//		Graphics g2d = image.createGraphics();
//		g2d.setClip( clip );
//		g2d.fillOval(0, 0, image.getWidth(), image.getHeight());
//		lbImage = new JLabel(new ImageIcon(image));
		ImageIcon icon = new ImageIcon("C:\\Users\\Thinh\\eclipse-workspace-2\\JTabbedPane\\img\\44948.png");
		Image scaleImage = icon.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
		lbImage = new JLabel(new ImageIcon(scaleImage));
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public boolean isPrivateChat() {
		return privateChat;
	}

	public void setPrivateChat(boolean privateChat) {
		this.privateChat = privateChat;
	}
}
