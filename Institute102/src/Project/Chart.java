package Project;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Chart extends JFrame implements ActionListener{
	
	JButton bt1 = new JButton("닫기");
	
	public Chart(){
		this.setTitle("수강현황차트");
		this.setVisible(true);
		this.setSize(300, 300);
		
		this.add(bt1);
		
		this.setLayout(null);
		
		bt1.setBounds(200, 10, 70, 30);
		bt1.addActionListener(this);
	}

	public static void main(String[] args) {
		new Chart();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String s = e.getActionCommand();
		
		if(s.equals(bt1.getText())){
			dispose();
		}
		
	}

}
