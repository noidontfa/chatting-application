package customGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.MatteBorder;

import listener.MySendFileActionListener2;
import listener.MySendMessageActionListener2;
import listener.MySendMessageKeyUpListener;

public class ChattingInputGUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea txtMessage;
	private JButton btnSend;
	private JLabel lblImageSendFile;
	/**
	 * Create the panel.
	 */
	public ChattingInputGUI() {
		setPreferredSize(new Dimension(595, 80));
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(158, 158, 158)));
		panel.setPreferredSize(new Dimension(10, 30));
		panel.setBackground(new Color(248,248,248));
		add(panel, BorderLayout.NORTH);
		
		lblImageSendFile = new JLabel("");
		lblImageSendFile.setBorder(null);
		ImageIcon imageIcon = new ImageIcon("C:\\Users\\Thinh\\eclipse-workspace-2\\Swing\\img\\file-before.png");
		//ImageIcon imageIconHover = new ImageIcon("C:\\Users\\Thinh\\eclipse-workspace-2\\UIDesign\\img\\file_hover.png");
		Image iconImage = imageIcon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 5));
		lblImageSendFile.setIcon(new ImageIcon(iconImage));
		lblImageSendFile.addMouseListener(new MySendFileActionListener2());
		panel.add(lblImageSendFile);
		
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setPreferredSize(new Dimension(100, 10));
		panel_1.add(panel_2, BorderLayout.EAST);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		btnSend = new JButton("SEND");
		btnSend.setBorder(null);
		btnSend.setForeground(Color.BLUE);
		btnSend.setBackground(new Color(248,248,248));
		btnSend.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSend.addActionListener(new MySendMessageActionListener2());
		panel_2.add(btnSend, BorderLayout.CENTER);
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(null);
		panel_3.add(scrollPane, BorderLayout.CENTER);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(248, 248, 248));
		panel_4.setPreferredSize(new Dimension(30, 10));
		scrollPane.setRowHeaderView(panel_4);
		
		txtMessage = new JTextArea();
		txtMessage.setWrapStyleWord(true);
		txtMessage.setMargin(new Insets(12, 12, 12, 12));
		txtMessage.setLineWrap(true);
		txtMessage.setBorder(null);
		txtMessage.setBackground(new Color(248, 248, 248));
		txtMessage.setAlignmentY(5.0f);
		txtMessage.setAlignmentX(5.0f);
		txtMessage.addKeyListener(new MySendMessageKeyUpListener());
		scrollPane.setViewportView(txtMessage);

	}
	
	public String getText() {
		return txtMessage.getText();
	}
	
	public void setText(String msg) {
		txtMessage.setText(msg);
	}
	
	public void setImage(String path) {
		ImageIcon imageIcon = new ImageIcon(path);
		Image iconImage = imageIcon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		lblImageSendFile.setIcon(new ImageIcon(iconImage));
	}
}
