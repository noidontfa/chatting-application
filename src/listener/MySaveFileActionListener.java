package listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JTable;

import gui.ChattingForm;
import model.transfer.TooltipModel;


public class MySaveFileActionListener extends MouseAdapter {

	
	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("pressed");
		JTable table =  ChattingForm.getInstance().getTable();
		int row = table.convertRowIndexToModel(table.rowAtPoint(e.getPoint()));
        int col = table.convertRowIndexToModel(table.columnAtPoint(e.getPoint()));

     //   System.out.println(row + "\t" + col);
		if(row >= 0 && col >= 0) {
			TooltipModel cellRenderer = (TooltipModel)table.getModel().getValueAt(row, col);	
			if(cellRenderer.isFileTransfer()) {
				// save file;
				JFileChooser fileChooser = new JFileChooser();						
				int temp = fileChooser.showSaveDialog(null);
				if(temp != JFileChooser.CANCEL_OPTION) {
					String filePath = fileChooser.getSelectedFile().toString();
					System.out.println(filePath);
					try {
					 FileOutputStream fileOutputStream = new FileOutputStream(new File(filePath));
					 fileOutputStream.write(cellRenderer.getFileBytes());
					 fileOutputStream.close();
					} catch (IOException err) {
						err.printStackTrace();
					}
			}				
			}
	
		
		}
		
	}



	

}
