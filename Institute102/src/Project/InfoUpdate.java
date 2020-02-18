package Project;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class InfoUpdate extends JFrame implements ActionListener{

	ArrayList<Bean> list;
	Bean bean;
	DBMgr mgr = new DBMgr();
	
	JLabel lb1= new JLabel("회 원 코 드 : ");
	JLabel lb2= new JLabel("회 원 명 : ");
	JLabel lb3= new JLabel("휴 대 폰 : ");
	JLabel lb4= new JLabel("주 소 : ");
	JLabel lb5= new JLabel("등 록 일 : ");
	
	static JTextField tf1= new JTextField(10);
	static JTextField tf2= new JTextField(10);
	static JTextField tf3= new JTextField(10);
	static JTextField tf4= new JTextField(10);
	static JTextField tf5= new JTextField(10);
	
	JButton bt1= new JButton("수정");
	JButton bt2= new JButton("취소");
	
	JPanel p1= new JPanel(new GridLayout(6,2));
	JPanel p2 = new JPanel();
	
	public InfoUpdate(){
		this.setTitle("회원 정보 수정");
		this.setVisible(true);
		this.setSize(300, 300);
		
		p1.add(lb1);
		p1.add(tf1);
		p1.add(lb2);
		p1.add(tf2);
		p1.add(lb3);
		p1.add(tf3);
		p1.add(lb4);
		p1.add(tf4);
		p1.add(lb5);
		p1.add(tf5);
		
		p2.add(bt1);
		p2.add(bt2);
		
		this.add("Center",p1);
		this.add("South",p2);
		
		bt1.addActionListener(this);
		bt2.addActionListener(this);
		
		tf1.setEditable(false);
		tf5.setEditable(false);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	
		String s = e.getActionCommand();
		
		if(s.equals(bt1.getText())){
			
			String name = tf2.getText();
			String phone = tf3.getText();
			String address = tf4.getText();
			
			int code = Integer.parseInt(tf1.getText());
			
			mgr.Infoupdate(code, name, phone, address);
			JOptionPane.showMessageDialog(null, tf2.getText() + " 님의 회원정보수정이 완료되었습니다.");
			
			list = mgr.member();
			
			String record[][] = new String[list.size()][MManage.col.length];
			
			for(int i=0;i<list.size();i++){
				bean = list.get(i);
				
				record[i][0] = bean.getM_mcode()+"";
				record[i][1] = bean.getM_mname();
				record[i][2] = bean.getM_phone();
				record[i][3] = bean.getM_address();
				record[i][4] = bean.getM_regdate();
				
			}
			
			DefaultTableModel model = new DefaultTableModel(record, MManage.col){
				
				public boolean isCellEditable(int row, int column){
					return false;
				}
			};
			
			MManage.table = new JTable(model);
			MManage.jsp.setViewportView(MManage.table);
			dispose();
			
		}else if(s.equals(bt2.getText())){
			
			dispose();
		}
		
	}

}
