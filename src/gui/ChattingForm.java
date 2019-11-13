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
import component.ComponentMyInformation;
import component.DisplayJTable;
import component.RoundJTextField;
import constant.SystemConstants;
import interfaceGUI.IDisplayMessage;
import listener.MySaveFileActionListener;
import listener.MySendFileActionListener;
import listener.MySendMessageActionListener;
import model.transfer.Group;
import model.transfer.Message;
import model.transfer.TooltipModel;
import model.transfer.User;

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
	private ComponentMyInformation myInformation;
	/**
	 * Launch the application.
	 */
	private User user;
	private List<Long> toUserId = new ArrayList<>();
	private DisplayJTable table;
	private boolean meChat = false;
	private boolean uChat = false;
	private Long roomSelected; // this variable is handled by MySelectedFriendsListener.
	private Long myRoomId; // same

	/**
	 * Create the frame.
	 */
	public ChattingForm() {
		initComponent();

	}

	public void initComponent() {
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

		panelMe = new JPanel(new BorderLayout());
		panelMe.setBounds(64, 36, 195, 68);
		myInformation = new ComponentMyInformation();
		panelMe.add(myInformation,BorderLayout.CENTER);
		contentPane.add(panelMe);

		panelChatting = new JPanel(new BorderLayout());
		panelChatting.setBounds(269, 121, 443, 263);
		table = new DisplayJTable();
		table.addMouseListener(new MySaveFileActionListener());
		JScrollPane scrollChatting = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panelChatting.add(BorderLayout.CENTER, scrollChatting);
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

	public Long getMyRoomId() {
		return myRoomId;
	}

	public void setMyRoomId(Long myRoomId) {
		this.myRoomId = myRoomId;
	}

	public DisplayJTable getTable() {
		return table;
	}

	public void setUser(User user) {
		this.user = user;
		// render friends
		renderFriends();
		// render groups
		renderGroups();

		myInformation.getLbUsername().setText(user.getNickName());
		clientHandler = new ClientHandler(Client.getInstance().getSocket(), this, user);
		clientHandler.start();
	}

	public void renderFriends() {
		for (int i = 0; i < user.getFriends().size(); i++) {
			User friendUser = user.getFriends().get(i);
			ComponentInformation info = new ComponentInformation();
			info.getUsers().add(friendUser);
			info.setRoomId(friendUser.getId());
			info.getLbNameRoom().setText(friendUser.getNickName());
			info.setPrivateChat(true);
			panelFriend.add(info);
			panelFriend.add(Box.createVerticalStrut(1));
		}
	}

	public void renderGroups() {
		for (int i = 0; i < user.getGroups().size(); i++) {
			Group group = user.getGroups().get(i);
			ComponentInformation info = new ComponentInformation();
			info.setRoomId(group.getId());
			info.getLbNameRoom().setText(group.getName());
			info.setUsers(group.getListGroup());
			info.setPrivateChat(false);
			panelFriend.add(info);
			panelFriend.add(Box.createVerticalStrut(1));
		}
	}

	@Override
	public void writeMessageToGUI(Message message) {
		// this method receice mesage form user who is chatting with u!
		if (this.myRoomId == message.getId()) {
			User currentUser = user.getFriends().stream().filter((u) -> u.getId() == message.getId()).findFirst().get();
			this.addRow(currentUser, message, uChat, true);
			this.meChat = false;
			this.uChat = true;
		}

	}

	@Override
	public void displayMessage(Message message) {
		// jTablemodel.addRow(new Object[] { message });
		this.addRow(this.user, message, meChat, false);
		this.meChat = true;
		this.uChat = false;
	}

	public void addRow(User user, Message message, boolean whoChating, boolean isLeft) {
		TooltipModel tooltipModel = new TooltipModel();
		tooltipModel.setLeft(isLeft);
		if (whoChating) {
			tooltipModel.setHideImage(true);
		} else {
			tooltipModel.setHideImage(false);
		}
		if (message.getCommad().equals(SystemConstants.MESS_STRING)) {
			tooltipModel.setMsg(message.getMsg());
		} else if (message.getCommad().equals(SystemConstants.MESS_FILE)) {
			tooltipModel.setMsg(message.getFileName());
			tooltipModel.setFileTransfer(true);
			tooltipModel.setFileBytes(message.getFileBytes());
		}
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm a");
		tooltipModel.setTime(formatter.format(message.getTimeDate()));
		tooltipModel.setName(user.getNickName());

		table.addRow(tooltipModel);
	}

	public List<Long> getToUserId() {
		return toUserId;
	}

	public void setToUserId(List<Long> toUserId) {
		this.toUserId = toUserId;
	}

	public Long getRoomSelected() {
		return roomSelected;
	}

	public void setRoomSelected(Long roomSelected) {
		this.roomSelected = roomSelected;
	}
}
