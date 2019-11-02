package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import Client.Client;
import Client.ClientHandler;
import component.ComponentInformation;
import component.DisplayJTable;
import component.RoundJTextField;
import interfaceGUI.IDisplayMessage;
import listener.MySaveFileActionListener;
import listener.MySendFileActionListener;
import listener.MySendMessageActionListener;
import model.Message;
import model.TooltipModel;
import model.User;

public class ChattingForm extends JFrame implements IDisplayMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static ChattingForm instance;
	private JPanel contentPane;
	private JTextField txtMessage;
	DefaultListModel<String> jListModel = new DefaultListModel<>();
	

	private ClientHandler clientHandler;
	private JButton btnSend;
	private JButton btnNewButton;
	private JPanel panelFriend;
	private JPanel panelGroup;
	private JPanel panelFriendInfo;
	private JPanel panelMe;
	private JPanel panelChatting;
	/**
	 * Launch the application.
	 */
	private User user;
	private List<Integer> toUserId = new ArrayList<>();
	private DisplayJTable table;
	private boolean meChat = false;
	private boolean uChat = false;
	private int roomSelected;
	/**
	 * Create the frame.
	 */
	public ChattingForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtMessage = new RoundJTextField();
		txtMessage.addActionListener(new MySendMessageActionListener());
		txtMessage.setBounds(269, 394, 304, 59);
		contentPane.add(txtMessage);
		txtMessage.setColumns(10);

		btnSend = new JButton("Send");
		btnSend.addActionListener(new MySendMessageActionListener());
		btnSend.setBounds(627, 394, 85, 48);
		contentPane.add(btnSend);

		btnNewButton = new JButton("...");
		btnNewButton.addActionListener(new MySendFileActionListener());
		btnNewButton.setBounds(583, 394, 45, 48);
		contentPane.add(btnNewButton);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(64, 102, 195, 351);
		contentPane.add(tabbedPane);
		tabbedPane.setBackground(Color.PINK);

		panelFriend = new JPanel();
		panelFriend.setLayout(new BoxLayout(panelFriend, BoxLayout.Y_AXIS));
		JScrollPane scrollFriend = new JScrollPane(panelFriend, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		tabbedPane.addTab("Friends", null, scrollFriend, null);

		panelGroup = new JPanel();
		panelGroup.setLayout(new BoxLayout(panelGroup, BoxLayout.Y_AXIS));
		JScrollPane scrollGroup = new JScrollPane(panelGroup, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		tabbedPane.addTab("Groups", null, scrollGroup, null);

		panelFriendInfo = new JPanel();
		panelFriendInfo.setBounds(269, 36, 443, 75);
		contentPane.add(panelFriendInfo);

		panelMe = new JPanel();
		panelMe.setBounds(64, 36, 195, 68);
		contentPane.add(panelMe);
		
		panelChatting = new JPanel(new BorderLayout());
		panelChatting.setBounds(269, 121, 443, 263);
		table = new DisplayJTable();
		table.addMouseListener(new MySaveFileActionListener());
		JScrollPane scrollChatting = new JScrollPane(table,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panelChatting.add(BorderLayout.CENTER,scrollChatting);
		contentPane.add(panelChatting);
		
	}

	public ClientHandler getClientHandler() {
		return clientHandler;
	}

	public JTextField getTxtMessage() {
		return txtMessage;
	}

	public static ChattingForm getInstance() {

		if (instance == null) {
			instance = new ChattingForm();
		}
		// instance.user = user;
		return instance;
	}

	public User getUser() {
		return user;
	}



	public DisplayJTable getTable() {
		return table;
	}

	public void setUser(User user) {
		this.user = user;
		for (int i = 0; i < user.getFriends().size(); i++) {
			User friendUser = user.getFriends().get(i);
			ComponentInformation info = new ComponentInformation(friendUser);
			panelFriend.add(info);
			panelFriend.add(Box.createVerticalStrut(1));
			// jListModel.addElement(user.getFriends().get(i).getNickName());
		}

		clientHandler = new ClientHandler(Client.getInstance().getSocket(), this, user);
		clientHandler.start();
	}

	@Override
	public void writeMessageToGUI(Message message) {
		//jTablemodel.addRow(new Object[] { message });
		// this method receice mesage form user who is chatting with u!
		if(this.roomSelected == message.getId()) {
			User currentUser = user.getFriends().stream().filter((u) -> u.getId() == message.getId()).findFirst().get();
			TooltipModel tooltipModel = new TooltipModel();	
			if(this.uChat) {
				tooltipModel.setHideImage(true);
			} else {
				tooltipModel.setHideImage(false);
			}
			if(message.getCommad().equals("msg")) {
				tooltipModel.setMsg(message.getMsg());
			} else if (message.getCommad().equals("file")) {
				tooltipModel.setMsg(message.getFileName());
				tooltipModel.setFileTransfer(true);
				tooltipModel.setFileBytes(message.getFileBytes());
			}	
			SimpleDateFormat formatter = new SimpleDateFormat("HH:mm a"); 
			tooltipModel.setTime(formatter.format(message.getTimeDate()));
			tooltipModel.setName(currentUser.getNickName());
			
			table.addRow(tooltipModel);
			
			this.meChat = false;
			this.uChat = true;
		}
		

	}

	@Override
	public void displayMessage(Message message) {
		//jTablemodel.addRow(new Object[] { message });
		
		TooltipModel tooltipModel = new TooltipModel();
		tooltipModel.setLeft(false);
		if(meChat) {
			tooltipModel.setHideImage(true);
		} else {
			tooltipModel.setHideImage(false);
		}	
		if(message.getCommad().equals("msg")) {
			tooltipModel.setMsg(message.getMsg());
		} else if (message.getCommad().equals("file")) {
			tooltipModel.setMsg(message.getFileName());
			tooltipModel.setFileTransfer(true);
			tooltipModel.setFileBytes(message.getFileBytes());
		}	
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm a"); 
		tooltipModel.setTime(formatter.format(message.getTimeDate()));
		tooltipModel.setName(user.getNickName());
		
		table.addRow(tooltipModel);
		
		this.meChat = true;
		this.uChat = false;
	}

	public List<Integer> getToUserId() {
		return toUserId;
	}

	public void setToUserId(List<Integer> toUserId) {
		this.toUserId = toUserId;
	}

	public int getRoomSelected() {
		return roomSelected;
	}

	public void setRoomSelected(int roomSelected) {
		this.roomSelected = roomSelected;
	}
}
