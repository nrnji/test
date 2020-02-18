package Project;

import java.sql.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Main extends JFrame implements ActionListener{
	
	JButton bt1= new JButton("회원 등록");
	JButton bt2= new JButton("회원 관리");
	JButton bt3= new JButton("강좌 관리");
	JButton bt4= new JButton("수강 신청 및 관리");
	JButton bt5= new JButton("종 료");
	
	JPanel p1= new JPanel();
	JPanel p2 = new JPanel();
	
	ImageIcon img = new ImageIcon("DataFiles/main.jpg");
	JLabel imglb = new JLabel(img);
	
	public Main(){
		this.setTitle("학원 관리");
		this.setVisible(true);
		this.setSize(630, 500);
		
		p1.add(bt1);
		p1.add(bt2);
		p1.add(bt3);
		p1.add(bt4);
		p1.add(bt5);
		
		p1.add(imglb);
		
		this.add("North",p1);
		this.add("Center",p2);
		
		bt1.addActionListener(this);
		bt2.addActionListener(this);
		bt3.addActionListener(this);
		bt4.addActionListener(this);
		bt5.addActionListener(this);
		
		
		
	}
	


	@Override
	public void actionPerformed(ActionEvent e) {
	
		String s = e.getActionCommand();
		
		if(s.equals(bt1.getText())){
			new MAdd();
		}else if(s.equals(bt2.getText())){
			new MManage();
		}else if(s.equals(bt3.getText())){
			new CManage();
		}else if(s.equals(bt4.getText())){
			new AddManage();
		}else if(s.equals(bt5.getText())){
			dispose();
			new Login();
			
		}
		
	}

}
