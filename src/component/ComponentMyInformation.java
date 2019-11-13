package component;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ComponentMyInformation extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lbUsername;
	private JTextField txtSearch;
	private JLabel lbIFriendIcon;
	private JLabel lblGroupIcon;
	/**
	 * Create the panel.
	 */
	public ComponentMyInformation() {
		
		lbUsername = new JLabel("New label");
		lbUsername.setBounds(10, 10, 72, 19);
		lbUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lbUsername.setFont(new Font("Arial", Font.BOLD, 16));
		
		txtSearch = new JTextField();
		txtSearch.setBounds(10, 35, 82, 19);
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("C:\\Users\\Thinh\\eclipse-workspace-2\\JTabbedPane\\img\\icon-add-user.png"));
			Image dImage = img.getScaledInstance(16, 16, Image.SCALE_SMOOTH);
			lbIFriendIcon = new JLabel(new ImageIcon(dImage));
			lbIFriendIcon.setBounds(115, 35, 35, 19);
			lbIFriendIcon.setPreferredSize(lbIFriendIcon.getPreferredSize());
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
		
		try {
			img = ImageIO.read(new File("C:\\Users\\Thinh\\Desktop\\group512.png"));
			Image dImage = img.getScaledInstance(16, 16, Image.SCALE_SMOOTH);
			lblGroupIcon = new JLabel(new ImageIcon(dImage));
			lblGroupIcon.setBounds(102, 38, 16, 16);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setLayout(null);
		add(txtSearch);
		add(lbIFriendIcon);
		add(lblGroupIcon);
		add(lbUsername);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
	public JLabel getLbUsername() {
		return lbUsername;
	}
	public void setLbUsername(JLabel lbUsername) {
		this.lbUsername = lbUsername;
	}
	
	
}
