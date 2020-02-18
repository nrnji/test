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
	
	JLabel lb1= new JLabel("회 원 등 록");
	JLabel lb2= new JLabel("회 원 코 드 :");
	JLabel lb3= new JLabel("*회 원 명 :");
	JLabel lb4= new JLabel("휴 대 폰 :");
	JLabel lb5= new JLabel("주  소 :");
	JLabel lb6= new JLabel("등 록 일 :");
	JLabel lb7= new JLabel("");
	
	JTextField tf1= new JTextField(10);
	JTextField tf2= new JTextField(10);
	JTextField tf3= new JTextField(10);
	JTextField tf4= new JTextField(10);
	
	SimpleDateFormat format = new SimpleDateFormat("yyyy년 M월 dd일");
	
	JButton bt1= new JButton("추가");
	JButton bt2= new JButton("닫기");
	
	JPanel p1= new JPanel(new GridLayout(5,2,-100,10));
	JPanel p2= new JPanel();
	
	public MAdd(){
		this.setTitle("회원 등록");
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
				JOptionPane.showMessageDialog(null, "필수항목(*)을 모두 입력하세요","회원등록 에러",0);
			}else{
				
				String name = tf2.getText();
				String phone = tf3.getText();
				String address = tf4.getText();
		
				
				list = mgr.member();
				
				int code = list.size()+1;
				
				mgr.MAdd(code, name, phone, address);
				
				JOptionPane.showMessageDialog(null, tf2.getText() + "회원이 등록되었습니다");
				dispose();
			}
			
		}else if(s.equals(bt2.getText())){
			dispose();
		}
		
	}

}
