package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import Client.Client;
import Client.ClientHandler;
import constant.SystemConstants;
import customGUI.ChattingInputGUI;
import customGUI.ChattingOutputGUI;
import customGUI.DisplayJTable;
import customGUI.InformationGUI;
import customGUI.MyInformationGUI;
import customGUI.RoomInformationGUI;
import interfaceGUI.IDisplayMessage;
import keeptoo.KGradientPanel;
import listener.MySaveFileActionListener2;
import model.transfer.ChatOutputModel;
import model.transfer.Group;
import model.transfer.Message;
import model.transfer.User;
import util.RoundImage;

public class ChattingForm2 extends JFrame implements IDisplayMessage {

	/**
	 * 
	 */
	private static ChattingForm2 instance;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelMyInfor;
	private JPanel panelInfor;
	private JPanel panelRoom;
	private JPanel panelChatInput;
	private MyInformationGUI myInformationGUI;
	private RoomInformationGUI roomInformationGUI;
	private ChattingInputGUI chattingInputGUI;
	private Map<Integer, InformationGUI> inforMap = new HashMap<>();;
	/**
	 * Launch the application.
	 */
	private User user;
	private ClientHandler clientHandler;
	private Map<Integer, User> respondent = new HashMap<>();
	private List<Integer> toUserId = new ArrayList<>();
	private DisplayJTable table;
	private boolean meChat = false;
	private boolean uChat = false;
	private int roomSelected; // this variable is handled by MySelectedFriendsListener. friend or room id
	private int myRoomId; // same user or room id

	/**
	 * Create the frame.
	 */
	public ChattingForm2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		panel_1.setPreferredSize(new Dimension(10, 80));
		panel_1.setBackground(new Color(248, 248, 248));
		panel.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BorderLayout(0, 0));

		panelMyInfor = new JPanel();
		panelMyInfor.setBorder(null);
		FlowLayout flowLayout = (FlowLayout) panelMyInfor.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		panelMyInfor.setPreferredSize(new Dimension(320, 10));
		panel_1.add(panelMyInfor, BorderLayout.WEST);

		panelRoom = new JPanel();
		panelRoom.setBackground(Color.WHITE);
		panelRoom.setBorder(new MatteBorder(0, 1, 0, 0, (Color) new Color(158, 158, 158)));
		panelRoom.setBackground(Color.WHITE);
		panel_1.add(panelRoom, BorderLayout.CENTER);

		JPanel panel_2 = new JPanel();
		panel_2.setMinimumSize(new Dimension(320, 80));
		panel_2.setMaximumSize(new Dimension(320, 80));
		panel_2.setOpaque(false);
		panel_2.setBorder(new MatteBorder(1, 0, 0, 1, new Color(158, 158, 158)));
		panel_2.setSize(new Dimension(320, 80));
		panel_2.setPreferredSize(new Dimension(320, 10));
		panel_2.setBackground(Color.WHITE);
		panel.add(panel_2, BorderLayout.WEST);
		panel_2.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setSize(new Dimension(320, 80));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(320, 80));
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setAlignmentY(0.0f);
		scrollPane.setAlignmentX(0.0f);
		scrollPane.setBorder(null);
		panelInfor = new JPanel();
		panelInfor.setOpaque(false);
		panelInfor.setPreferredSize(new Dimension(320, 80));
		scrollPane.setViewportView(panelInfor);
		FlowLayout fl_panelInfor = new FlowLayout(FlowLayout.LEFT, 0, 0);
		panelInfor.setLayout(fl_panelInfor);
		panel_2.add(scrollPane, BorderLayout.CENTER);

		JPanel panel_4 = new JPanel();
		panel_4.setOpaque(false);
		panel_4.setBackground(new Color(248, 248, 248));

		panel_4.setBorder(new MatteBorder(0, 0, 1, 0, new Color(158, 158, 158)));
		scrollPane.setColumnHeaderView(panel_4);
		panel_4.setPreferredSize(new Dimension(320, 30));
		panel_4.setFont(new Font("Tahoma", Font.PLAIN, 13));

		JLabel lblNewLabel = new JLabel("Messages");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));

		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup().addContainerGap()
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 221, Short.MAX_VALUE)
						.addComponent(lblDate, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		gl_panel_4
				.setVerticalGroup(
						gl_panel_4.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING,
										gl_panel_4.createSequentialGroup()
												.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
														.addComponent(lblNewLabel).addComponent(lblDate))
												.addContainerGap()));
		panel_4.setLayout(gl_panel_4);

		JPanel panel_3 = new JPanel();
		panel.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BorderLayout(0, 0));

		panelChatInput = new JPanel();
		panelChatInput.setPreferredSize(new Dimension(10, 80));
		panelChatInput.setBackground(Color.DARK_GRAY);
		panel_3.add(panelChatInput, BorderLayout.SOUTH);
		panelChatInput.setLayout(new BorderLayout(0, 0));

		KGradientPanel panel_6 = new KGradientPanel();
		panel_6.kGradientFocus = 100;
		panel_6.kStartColor = new Color(248, 248, 248);

		panel_6.kEndColor = new Color(57, 80, 234);
		panel_6.setBorder(new MatteBorder(1, 0, 1, 0, (Color) new Color(158, 158, 158)));
		panel_3.add(panel_6, BorderLayout.CENTER);
		panel_6.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setOpaque(false);
		scrollPane_1.getViewport().setOpaque(false);
		scrollPane_1.setBorder(null);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel_6.add(scrollPane_1, BorderLayout.CENTER);

		table = new DisplayJTable();
		table.addMouseListener(new MySaveFileActionListener2());
		table.setOpaque(false);
		scrollPane_1.setViewportView(table);

		setGUI();
	}

	public void setGUI() {

		myInformationGUI = new MyInformationGUI();
		panelMyInfor.add(myInformationGUI);

		panelRoom.setLayout(new BorderLayout(0, 0));
		roomInformationGUI = new RoomInformationGUI();
		panelRoom.add(roomInformationGUI, BorderLayout.WEST);
		JPanel panel = new JPanel();
		panel.setBackground(new Color(248, 248, 248));
		panelRoom.add(panel, BorderLayout.CENTER);

		chattingInputGUI = new ChattingInputGUI();
		panelChatInput.add(chattingInputGUI);

	}

	public static ChattingForm2 getInstance() {
		if (instance == null) {
			instance = new ChattingForm2();
		}
		return instance;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;

		renderFriends();
		renderGroups();
		// respondent.put(user.getId(), user);
		myInformationGUI.setName(user.getNickName());
		myInformationGUI.setImage("C:\\Users\\Thinh\\eclipse-workspace-2\\Swing\\img\\myimage.png");

		JLabel lblImage = new JLabel();
		try {
			ImageIcon icon = new ImageIcon(
					new ImageIcon("C:\\Users\\Thinh\\eclipse-workspace-2\\Swing\\img\\myimage.png").getImage()
							.getScaledInstance(27, 27, Image.SCALE_DEFAULT));

			lblImage.setIcon(new ImageIcon(RoundImage.getRoundImage(icon.getImage(), 100)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		user.setImageChatting(lblImage);

		user.setImage(myInformationGUI.getImage());
		respondent.put(user.getId(), user);
		clientHandler = new ClientHandler(Client.getInstance().getSocket(), this, user);
		clientHandler.start();
	}

	public void renderFriends() {
		for (int i = 0; i < user.getFriends().size(); i++) {
			User userFriend = user.getFriends().get(i);

			InformationGUI info = new InformationGUI();
			info.getUsers().add(userFriend);
			info.setRoomId(userFriend.getId());
			info.setName(userFriend.getNickName());
			info.setPrivateChat(true);
			info.setImage("C:\\Users\\Thinh\\eclipse-workspace-2\\Swing\\img\\myimage.png");
			
			inforMap.put(userFriend.getId(), info);
			
			JLabel lblImage = new JLabel();
			try {
				ImageIcon icon = new ImageIcon(
						new ImageIcon("C:\\Users\\Thinh\\eclipse-workspace-2\\Swing\\img\\myimage.png").getImage()
								.getScaledInstance(27, 27, Image.SCALE_DEFAULT));

				lblImage.setIcon(new ImageIcon(RoundImage.getRoundImage(icon.getImage(), 100)));
			} catch (Exception e) {
				e.printStackTrace();
			}
			userFriend.setImageChatting(lblImage);

			userFriend.setImage(info.getImage());

			respondent.put(userFriend.getId(), userFriend);
			panelInfor.add(info);

		}
	}

	public void renderGroups() {
		for (int i = 0; i < user.getGroups().size(); i++) {
			Group group = user.getGroups().get(i);
			InformationGUI info = new InformationGUI();
			info.setRoomId(group.getId());
			info.setName(group.getName());
			info.setUsers(group.getListGroup());
			info.setPrivateChat(false);
			info.setImage("C:\\Users\\Thinh\\eclipse-workspace-2\\Swing\\img\\myimage.png");
			panelInfor.add(info);
		}
	}

	public String getMessage() {
		return chattingInputGUI.getText();
	}

	public void setMessage(String msg) {
		chattingInputGUI.setText(msg);
	}

	@Override
	public void writeMessageToGUI(Message message) {
		
		
		
		if (this.roomSelected == message.getRoomId() && this.myRoomId == message.getToRoomId()) {
			if (message.getToUser().get(0) == message.getId()) {
				displayMessage(message);
			} else {
				this.addRow(respondent.get(message.getId()), message, uChat);
			}

			this.meChat = false;
			this.uChat = true;

		}

	}

	@Override
	public void displayMessage(Message message) {
		// this.addRow(this.user, message, meChat, false);
		this.addRow(this.user, message, meChat);
		this.meChat = true;
		this.uChat = false;

	}
	
	@Override
	public void showMessageInfo(Message message) {
	
		if(inforMap.containsKey(message.getRoomId())) {
			String pattern = "MM/dd/yyyy";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			String date = simpleDateFormat.format(message.getTimeDate());
			
			
			
			inforMap.get(message.getRoomId()).setMessage(respondent.get(message.getId()).getNickName(),
					message.getMsg() == null ? message.getFileName() : message.getMsg(),date);

		}
	}

	public void addRow(User user, Message message, boolean whoChating) {
		ChatOutputModel chatOutputModel = new ChatOutputModel();
		if (whoChating) {
			chatOutputModel.setHideImage(true);
		} else {
			chatOutputModel.setHideImage(false);
		}

		chatOutputModel.setImage(user.getImageChatting());

		if (message.getCommad().equals(SystemConstants.MESS_STRING)) {
			chatOutputModel.setMsg(message.getMsg());
		} else if (message.getCommad().equals(SystemConstants.MESS_FILE)) {
			chatOutputModel.setMsg(message.getFileName());
			chatOutputModel.setFileTransfer(true);
			chatOutputModel.setFileBytes(message.getFileBytes());
		}
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm a");
		chatOutputModel.setTime(formatter.format(message.getTimeDate()));
		chatOutputModel.setName(user.getNickName());

		ChattingOutputGUI chattingOutputGUI = new ChattingOutputGUI();
		chattingOutputGUI.setChatOutputModel(chatOutputModel);
		if (chatOutputModel.isFileTransfer()) {
			chattingOutputGUI.setFile(chatOutputModel.getMsg(), chatOutputModel.getTime());
		} else
			chattingOutputGUI.setText(chatOutputModel.getMsg(), chatOutputModel.getTime());
		chattingOutputGUI.setImage(chatOutputModel.getImage());
		chattingOutputGUI.setName(chatOutputModel.getName());

		table.addRow(chattingOutputGUI);
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

	public int getMyRoomId() {
		return myRoomId;
	}

	public void setMyRoomId(int myRoomId) {
		this.myRoomId = myRoomId;
	}

	public boolean isMeChat() {
		return meChat;
	}

	public void setMeChat(boolean meChat) {
		this.meChat = meChat;
	}

	public boolean isuChat() {
		return uChat;
	}

	public void setuChat(boolean uChat) {
		this.uChat = uChat;
	}

	public ClientHandler getClientHandler() {
		return clientHandler;
	}

	public RoomInformationGUI getRoomInformationGUI() {
		return roomInformationGUI;
	}

	public DisplayJTable getTable() {
		return table;
	}

	public ChattingInputGUI getChattingInputGUI() {
		return chattingInputGUI;
	}

	

}
