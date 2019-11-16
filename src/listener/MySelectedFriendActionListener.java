package listener;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import component.ComponentInformation;
import constant.SystemConstants;
import gui.ChattingForm;
import model.transfer.Message;

public class MySelectedFriendActionListener extends MouseAdapter {

	// Color defaut blue;
	@Override
	public void mousePressed(MouseEvent e) {
		ComponentInformation info = (ComponentInformation) e.getSource();
		ChattingForm instance = ChattingForm.getInstance();
		if (info.getRoomId() != instance.getRoomSelected()) {
			Message msgLoadding = new Message();
			List<Integer> ids = new ArrayList<>();
			for (int i = 0; i < info.getUsers().size(); i++) {
				ids.add(info.getUsers().get(i).getId());
			}
			instance.setToUserId(ids);
			instance.setRoomSelected(info.getRoomId()); // friends user id
			if (info.isPrivateChat()) {
				instance.setMyRoomId(instance.getUser().getId());
				msgLoadding.setCommad(SystemConstants.MESS_PRIVATE_LOADDING);
			} else {
				instance.setMyRoomId(info.getRoomId());
				msgLoadding.setCommad(SystemConstants.MESS_GROUP_LOADDING);
			}
			// load database form Server
			if (msgLoadding.getCommad().equals(SystemConstants.MESS_PRIVATE_LOADDING)) {
				msgLoadding.setId(instance.getUser().getId());
				msgLoadding.setToUser(ids);

			}

			msgLoadding.setRoomId(instance.getRoomSelected());
			msgLoadding.setToRoomId(instance.getMyRoomId());

			instance.getClientHandler().sendMessage(msgLoadding);
			instance.getTable().removeAllRow();
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		ComponentInformation info = (ComponentInformation) e.getSource();
		info.setBackground(Color.PINK);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		ComponentInformation info = (ComponentInformation) e.getSource();
		info.setBackground(Color.WHITE);
	}

}
