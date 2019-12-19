package customGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import model.transfer.ChatOutputModel;
import util.RoundImage;

public class ChattingOutputGUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblImage ;
	private JLabel lblName;
	private JLabel lblDate;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_6;
	private JTextArea txtMessage;
	/**
	 * Create the panel.
	 */
	private ChatOutputModel chatOutputModel;
	
	
	public ChattingOutputGUI() {
		//setOpaque(false);
		setBackground(new Color(221,200,255,80));
		//setOpaque(false);
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		flowLayout_2.setHgap(10);
		flowLayout_2.setVgap(2);
		panel.setOpaque(false);
		panel.setBackground(new Color(248,248,248));
		panel.setPreferredSize(new Dimension(50, 50));
		add(panel, BorderLayout.WEST);
		
		lblImage = new JLabel("");
		lblImage.setPreferredSize(new Dimension(40, 40));
		lblImage.setBackground(Color.DARK_GRAY);
		panel.add(lblImage);
		
		panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(0, 0));
		panel_1.setOpaque(false);
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		panel_2 = new JPanel();
		panel_2.setOpaque(false);
		panel_2.setBackground(new Color(248,248,248));
		panel_2.setPreferredSize(new Dimension(10, 20));
		panel_1.add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_4.setOpaque(false);
		FlowLayout flowLayout_1 = (FlowLayout) panel_4.getLayout();
		flowLayout_1.setHgap(10);
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		panel_4.setPreferredSize(new Dimension(100, 10));
		panel_2.add(panel_4, BorderLayout.EAST);
		
		lblDate = new JLabel("14:54");
		lblDate.setForeground(Color.BLACK);
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_4.add(lblDate);
		
		JPanel panel_5 = new JPanel();
		panel_5.setOpaque(false);
		FlowLayout flowLayout = (FlowLayout) panel_5.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_2.add(panel_5, BorderLayout.CENTER);
		
		lblName = new JLabel("");
		lblName.setForeground(new Color(78, 25, 190));
		lblName.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_5.add(lblName);
		
		JPanel panel_3 = new JPanel();
		panel_3.setOpaque(false);
		//panel_3.setOpaque(false);
		panel_3.setBackground(new Color(248,248,248));
		panel_1.add(panel_3, BorderLayout.WEST);
		
		panel_6 = new JPanel();
		panel_6.setOpaque(false);
		panel_1.add(panel_6, BorderLayout.CENTER);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		txtMessage = new JTextArea();
		txtMessage.setWrapStyleWord(true);
		txtMessage.setOpaque(false);
		txtMessage.setLineWrap(true);
		txtMessage.setForeground(Color.BLACK);
		txtMessage.setEditable(false);
		panel_6.add(txtMessage, BorderLayout.CENTER);

	}
	
	public void setImage(String path) {
        try {
            ImageIcon icon = new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(
            		20,
            		20,
            		Image.SCALE_DEFAULT
            		));

            lblImage.setIcon(new ImageIcon(RoundImage.getRoundImage(icon.getImage(), 100)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	public void setImage(JLabel img) {
		
		lblImage.setIcon(img.getIcon());
		lblImage.setSize(new Dimension(20,20));
	}
	
	public void setImage(byte[] img) {
		try {
            ImageIcon icon = new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(
            		20,
            		20,
            		Image.SCALE_DEFAULT
            		));

            lblImage.setIcon(new ImageIcon(RoundImage.getRoundImage(icon.getImage(), 100)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    
	}
	
	public void setText(String msg,String date) {
		txtMessage.setText(msg);
		lblDate.setText(date);
		
		//this.setPreferredSize(new Dimension(this.getPreferredSize().width,this.getPreferredSize().height));
	}
	
	public void setName(String name) {
		lblName.setText(name);
	}
	
	public void setFile(String name, String date) {
		lblDate.setText(date);
		txtMessage.setFont(new Font("SansSerif", Font.BOLD, 12));
		//txtMessage.setForeground(new Color(158,158,158));
		txtMessage.setText("File: " + name);
	}
	
	
	public int getHeight() {
	
		return this.getPreferredSize().height + txtMessage.getLineCount()*10;
	}

	public ChatOutputModel getChatOutputModel() {
		return chatOutputModel;
	}

	public void setChatOutputModel(ChatOutputModel chatOutputModel) {
		this.chatOutputModel = chatOutputModel;
	}
	

}
