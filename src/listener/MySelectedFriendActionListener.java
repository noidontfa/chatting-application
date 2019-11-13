package listener;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import component.ComponentInformation;
import gui.ChattingForm;

public class MySelectedFriendActionListener extends MouseAdapter{

	
	// Color defaut blue;
	@Override
	public void mousePressed(MouseEvent e) {
		ComponentInformation info = (ComponentInformation)e.getSource();
		ChattingForm instance = ChattingForm.getInstance();
		List<Long> ids = new ArrayList<>();
		for(int i = 0; i < info.getUsers().size(); i++) {
			ids.add(info.getUsers().get(i).getId());
		}
		instance.setToUserId(ids);
		instance.setRoomSelected(info.getRoomId());
		if(info.isPrivateChat()) {
			instance.setMyRoomId(instance.getUser().getId());
		} else {
			instance.setMyRoomId(info.getRoomId());
		}
		// load database form Server
		instance.getTable().removeAllRow();
		
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		ComponentInformation info = (ComponentInformation)e.getSource();
		info.setBackground(Color.PINK);
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		ComponentInformation info = (ComponentInformation)e.getSource();
		info.setBackground(Color.WHITE);
	}
	

}
