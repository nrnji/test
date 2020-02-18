package base.comp;

import javax.swing.JFrame;

import base.IDisgn;

public abstract class BaseFrame extends JFrame implements IDisgn{
	
	public void setFrame(int w, int h, String title) {
		setSize(w, h);
		setTitle(title);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setComp();
		setDesign();
		setAction();
		
		setVisible(true);
	}
	
}
