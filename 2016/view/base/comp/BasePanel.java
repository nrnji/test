package base.comp;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class BasePanel extends JPanel{

	public BasePanel() {
		
	}

	public BasePanel(JPanel centerPn) {
		super(new BorderLayout());
		super.add(centerPn, BorderLayout.CENTER);				
	}

	
	public BasePanel(JPanel centerPn, int top, int left, int bottom, int right) {
		this(centerPn);
		super.setBorder(new EmptyBorder(top, left, bottom, right));
	}

	
	public BasePanel(JPanel centerPn, String title, String fontName, int style, int size, int top, int left, int bottom, int right) {
		this(centerPn, top, left, bottom, right);
		JLabel titleJl = new JLabel(title);
		titleJl.setHorizontalAlignment(JLabel.CENTER);
		titleJl.setFont(new Font("±¼¸²", style, 15));
		titleJl.setBorder(new EmptyBorder(0, 0, 10, 0));
		super.add(titleJl, BorderLayout.NORTH);
	}
}