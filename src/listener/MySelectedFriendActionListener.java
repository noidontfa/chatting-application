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
		System.out.println("Selected: " +info.getUser().getNickName());
		ChattingForm instance = ChattingForm.getInstance();
		List<Integer> id = new ArrayList<>();
		id.add(info.getUser().getId());
		instance.setToUserId(id);
		instance.setRoomSelected(info.getUser().getId());
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
