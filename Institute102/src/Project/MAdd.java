package Project;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.*;

public class MAdd extends JFrame implements ActionListener{
	
	ArrayList<Bean> list;
	Bean bean;
	DBMgr mgr = new DBMgr();
	
	JLabel lb1= new JLabel("ȸ �� �� ��");
	JLabel lb2= new JLabel("ȸ �� �� �� :");
	JLabel lb3= new JLabel("*ȸ �� �� :");
	JLabel lb4= new JLabel("�� �� �� :");
	JLabel lb5= new JLabel("��  �� :");
	JLabel lb6= new JLabel("�� �� �� :");
	JLabel lb7= new JLabel("");
	
	JTextField tf1= new JTextField(10);
	JTextField tf2= new JTextField(10);
	JTextField tf3= new JTextField(10);
	JTextField tf4= new JTextField(10);
	
	SimpleDateFormat format = new SimpleDateFormat("yyyy�� M�� dd��");
	
	JButton bt1= new JButton("�߰�");
	JButton bt2= new JButton("�ݱ�");
	
	JPanel p1= new JPanel(new GridLayout(5,2,-100,10));
	JPanel p2= new JPanel();
	
	public MAdd(){
		this.setTitle("ȸ�� ���");
		this.setVisible(true);
		this.setSize(400, 300);
		
		
		p1.add(lb2);
		p1.add(tf1);
		p1.add(lb3);
		p1.add(tf2);
		p1.add(lb4);
		p1.add(tf3);
		p1.add(lb5);
		p1.add(tf4);
		p1.add(lb6);
		p1.add(new JLabel(format.format(Calendar.getInstance().getTime())));
		
		
		p2.add(bt1);
		p2.add(bt2);
		
		this.add("North",lb1);
		this.add("Center",p1);
		this.add("South",p2);
		
		lb1.setHorizontalAlignment(JLabel.CENTER);
		lb7.setHorizontalAlignment(JLabel.RIGHT);
		
		tf1.setEditable(false);
		
		bt1.addActionListener(this);
		bt2.addActionListener(this);
		
	}
	public static void main(String[] args) {
		new MAdd();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String s = e.getActionCommand();
		
		if(s.equals(bt1.getText())){
			
			if(tf2.getText().equals("")){
				JOptionPane.showMessageDialog(null, "�ʼ��׸�(*)�� ��� �Է��ϼ���","ȸ����� ����",0);
			}else{
				
				String name = tf2.getText();
				String phone = tf3.getText();
				String address = tf4.getText();
		
				
				list = mgr.member();
				
				int code = list.size()+1;
				
				mgr.MAdd(code, name, phone, address);
				
				JOptionPane.showMessageDialog(null, tf2.getText() + "ȸ���� ��ϵǾ����ϴ�");
				dispose();
			}
			
		}else if(s.equals(bt2.getText())){
			dispose();
		}
		
	}

}
