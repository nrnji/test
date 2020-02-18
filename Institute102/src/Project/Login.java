package Project;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class Login extends JFrame implements ActionListener{
	
	ArrayList<Bean> list;
	Bean bean;
	DBMgr mgr = new DBMgr();
	
	JLabel lb1= new JLabel("������ �α���");
	JLabel lb2= new JLabel("ID : ");
	JLabel lb3= new JLabel("PW : ");
	
	JTextField tf1= new JTextField(10);
	JPasswordField tf2= new JPasswordField(10);
	
	JButton bt1= new JButton("�α���");
	JButton bt2= new JButton("����");
	
	JPanel p1= new JPanel(new GridLayout(3,2,10,10));
	JPanel p2= new JPanel();
	
	ImageIcon img = new ImageIcon("DataFiles/login.jpg");
	JLabel imgLb = new JLabel(img);
	
	public Login(){
		
		this.setTitle("�α���");
		this.setVisible(true);
		this.setSize(360, 270);
		
		p1.add(lb2);
		p1.add(tf1);
		p1.add(lb3);
		p1.add(tf2);
		p1.add(bt1);
		p1.add(bt2);
		
		this.add(lb1);
			
		p2.add(imgLb);
		
	
		this.add(p1);
		this.add(p2);
		
		this.setLayout(null);
		
		p2.setBounds(0, -10, 340, 250);
		p1.setBounds(30, 50, 270, 150);
		
		bt1.addActionListener(this);
		bt2.addActionListener(this);
		
		p1.setOpaque(false);
		
		lb1.setFont(new Font("����ü",Font.BOLD,15));
		
		lb1.setBounds(120, 10, 200, 20);
	}

	public static void main(String[] args) {

		new Login();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String s = e.getActionCommand();
		
		if(s.equals(bt1.getText())){
			
			if(tf1.getText().equals("")||tf2.getText().equals("")){
				JOptionPane.showMessageDialog(null, "���̵�� ��й�ȣ�� �����Դϴ�.","Message",0);
			}else{
				
				String id = tf1.getText();
				
				list = mgr.login(id);
				
				try{
					bean = list.get(0);
					
					if(tf2.getText().equals(bean.getA_pw())){
						
						dispose();
						JOptionPane.showMessageDialog(null, "�α����� �Ϸ�Ǿ����ϴ�.","Message",1);
						new Main();
						
					}else{
						
						JOptionPane.showMessageDialog(null, "���̵�� ��й�ȣ�� Ȯ�����ּ���.","Message",0);
					}
					
				}catch(Exception e1){
					
					JOptionPane.showMessageDialog(null, "���̵�� ��й�ȣ�� Ȯ�����ּ���.","Message",0);
				}
			}
		}else if(s.equals(bt2.getText())){
			
			System.exit(0);
		}
		
	}

}
