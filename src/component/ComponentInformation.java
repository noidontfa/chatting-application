package component;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;

import listener.MySelectedFriendActionListener;
import model.User;



public class ComponentInformation extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	JLabel lbUserName;
	JLabel lbImage;
	JLabel lbMsg;
	/**
	 * Create the panel.
	 */
	
	
	public ComponentInformation(User user) {
		this.user = user;
		setBackground(Color.white);
		
		lbImage = new JLabel();
		lbMsg = new JLabel("This is message...");
		Font f = lbMsg.getFont();
		lbMsg.setFont(f.deriveFont(f.getSize() | Font.ITALIC));
		lbUserName = new JLabel(user.getNickName());
		try {
			drawImg();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lbImage)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lbUserName, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbMsg, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(36, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lbUserName, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lbMsg))
						.addComponent(lbImage))
					.addContainerGap())
		);
		setLayout(groupLayout);
		
		this.addMouseListener(new MySelectedFriendActionListener());
	}



	
	public User getUser() {
		return user;
	}




	public void setUser(User user) {
		this.user = user;
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
		Image scaleImage = icon.getImage().getScaledInstance(50, 50,Image.SCALE_DEFAULT);
		lbImage = new JLabel(new ImageIcon(scaleImage));
	}
}
