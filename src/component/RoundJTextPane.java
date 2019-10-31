package component;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JTextPane;

public class RoundJTextPane extends JTextPane{

	private static final long serialVersionUID = 1L;
	@Override 
	protected void paintComponent(Graphics g) {
	    if (!isOpaque() && getBorder() instanceof RoundedCornerBorder) {
	      Graphics2D g2 = (Graphics2D) g.create();
	      g2.setPaint(getBackground());
	      g2.fill(((RoundedCornerBorder) getBorder()).getBorderShape(
	          0, 0, getWidth() - 1, getHeight() - 1));
	      g2.dispose();
	    }
	    super.paintComponent(g);
	  }
	  @Override public void updateUI() {
	    super.updateUI();
	    setOpaque(false);
	    setBorder(new RoundedCornerBorder(5));
	  }
	  

}
