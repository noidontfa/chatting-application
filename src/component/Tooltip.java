package component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;

import model.transfer.TooltipModel;

public class Tooltip extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextPane txtMsg;
	private FlowLayout flowLayout = (FlowLayout) getLayout();
	/**
	 * Create the panel.
	 */
	
	public Tooltip() {		
		flowLayout.setAlignment(FlowLayout.LEFT);
		txtMsg = new RoundJTextPane();	
		textField = new RoundJTextField();	
		setBackground(Color.WHITE);
	}
	
	public void setLayout() {
		flowLayout.setVgap(6);
		flowLayout.setAlignment(FlowLayout.RIGHT);
	}
	
	public void initMsg(TooltipModel tooltipModel) {
		setMessage(tooltipModel);
		//System.out.println(msg.length());
		txtMsg.setSize(213, 42); // 40 private message ; 60 group chat
		if (tooltipModel.getMsg().length() > 27) {
			txtMsg.setPreferredSize(new Dimension(213, txtMsg.getPreferredSize().height));
		} else {
			txtMsg.setPreferredSize(new Dimension(txtMsg.getPreferredSize().width, 42));
		}
		txtMsg.setEditable(false);
		
	}
	
	public void initImage(boolean isHideImage) {
		textField.setPreferredSize(new Dimension(40,40));
		JPanel panel = new JPanel(new BorderLayout());		
		panel.setBackground(Color.WHITE);
		panel.setPreferredSize(new Dimension(42,(int)txtMsg.getPreferredSize().getHeight() ));
		if(!isHideImage) {
			panel.add(textField,BorderLayout.NORTH);
		}
		this.add(panel);
	}
	
	
	public void setMessage(TooltipModel tooltipModel) {
		txtMsg.setText(tooltipModel.getMsg());
		try {
			txtMsg.getDocument().insertString(txtMsg.getDocument().getLength(), "\n" + tooltipModel.getTime() , txtMsg.getStyle(""));
		} catch (BadLocationException e1) {

			e1.printStackTrace();
		}
		
	}
	
	public void displayLeft(TooltipModel tooltipModel) {
		initMsg(tooltipModel);	
		initImage(tooltipModel.isHideImage());
		this.add(txtMsg);
	}
	public void displayRight(TooltipModel tooltipModel) {
		setLayout();
		initMsg(tooltipModel);	
		this.add(txtMsg);
		initImage(tooltipModel.isHideImage());
		
	}
	@Override
	public int getHeight() {
		
		return Math.max(40, (int)txtMsg.getPreferredSize().getHeight()) + 1 ;
	}
	
	@Override
	public int getWidth() {
		return txtMsg.getWidth() + textField.getWidth() + 20;
	}
	
}


