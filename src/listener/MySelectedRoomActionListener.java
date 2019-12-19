package listener;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import constant.SystemConstants;
import customGUI.InformationGUI;
import gui.ChattingForm2;
import model.transfer.Message;

public class MySelectedRoomActionListener extends MouseAdapter {

	// Color defaut blue;
	@Override
	public void mousePressed(MouseEvent e) {
		InformationGUI info = (InformationGUI) e.getSource();
		info.disableBold();
		ChattingForm2 instance = ChattingForm2.getInstance();
		if (info.getRoomId() != instance.getRoomSelected()) {
			Message msgLoadding = new Message();
			List<Integer> ids = new ArrayList<>();
			for (int i = 0; i < info.getUsers().size(); i++) {
				ids.add(info.getUsers().get(i).getId());
			}
			
			
			instance.setRoomSelected(info.getRoomId()); // friends user id
			if (info.isPrivateChat()) {
				instance.setMyRoomId(instance.getUser().getId());
				msgLoadding.setCommad(SystemConstants.MESS_PRIVATE_LOADDING);
				ids.add(instance.getUser().getId());
			} else {
				instance.setMyRoomId(info.getRoomId());
				msgLoadding.setCommad(SystemConstants.MESS_GROUP_LOADDING);
			}
			instance.setToUserId(ids);
			// load database form Server
			msgLoadding.setId(instance.getUser().getId());
			msgLoadding.setToUser(ids);

			msgLoadding.setRoomId(instance.getRoomSelected());
			msgLoadding.setToRoomId(instance.getMyRoomId());

			instance.getClientHandler().sendMessage(msgLoadding);
			instance.setMeChat(false);
			instance.setuChat(false);
			instance.getTable().removeAllRow();
			instance.getRoomInformationGUI().setName(info.getName());
			instance.getRoomInformationGUI().setImage(info.getImage());
				
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		InformationGUI info = (InformationGUI) e.getSource();
		info.setBackground(new Color(240,240,240));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		InformationGUI info = (InformationGUI) e.getSource();
		info.setBackground(new Color(248,248,248));
	}

}
