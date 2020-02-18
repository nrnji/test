package base.comp;

import javax.swing.JFrame;

import base.IDesign;

public abstract class BaseFrame extends JFrame implements IDesign{
	public void setFrame(int w, int h, String title) {
		super.setTitle(title);
		super.setSize(w, h);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setLocationRelativeTo(null);
		
		setComp();
		setDesign();
		setAction();
		
		setVisible(true);
	}
}
