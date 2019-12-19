package customGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;

import util.RoundImage;

public class MyInformationGUI extends JPanel {

	/**
	 * Create the panel.
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblImage;
	private JLabel lblUsername;
	
	public MyInformationGUI() {
		setBorder(null);
		setSize(new Dimension(320, 80));
		setBackground(new Color(248,248,248));
		setPreferredSize(new Dimension(320, 80));
		
		lblImage = new JLabel("");
		lblImage.setSize(new Dimension(60, 60));
		lblImage.setPreferredSize(new Dimension(60, 60));
		
		lblUsername = new JLabel("nameUser");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 14));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(lblImage, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
					.addGap(244))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblUsername)
						.addComponent(lblImage, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap())
		);
		setLayout(groupLayout);

	}
	
	public void setImage(String path) {
        try {
            ImageIcon icon = new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(
            		50,
            		50,
            		Image.SCALE_SMOOTH
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
	

	

}
