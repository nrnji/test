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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class CManage extends JFrame implements ActionListener{

	ArrayList<Bean> list;
	Bean bean;
	DBMgr mgr = new DBMgr();
	
	JLabel lb1= new JLabel("*과 정 명");
	JLabel lb2= new JLabel("*강 사 명");
	JLabel lb3= new JLabel("수 강 료");
	JLabel lb4= new JLabel("<강 좌 현 황>");
	
	JTextField tf1= new JTextField(10);
	JTextField tf2= new JTextField(10);
	
	JComboBox com1 = new JComboBox();
	
	JButton bt1= new JButton("조회");
	JButton bt2= new JButton("등록");
	JButton bt3= new JButton("수정");
	JButton bt4= new JButton("삭제");
	JButton bt5= new JButton("전체보기");
	JButton bt6= new JButton("닫기");
	
	static JTable table;
	static JScrollPane jsp = new JScrollPane();
	
	static String col[] = {"선택All", "coursename", "teachname","price"};

	JPanel p1=new JPanel(new GridLayout(2,4));
	JPanel p2= new JPanel();
	
	JCheckBox ck = new JCheckBox("선택All");
	
	public CManage(){
		this.setTitle("강좌 관리");
		this.setVisible(true);
		this.setSize(500, 500);
		
		p1.add(lb1);
		p1.add(lb2);
		p1.add(lb3);
		p1.add(new JLabel(""));
		
		p1.add(tf1);
		p1.add(tf2);
		p1.add(com1);
		p1.add(bt1);
		
		p2.add(bt2);
		p2.add(bt3);
		p2.add(bt4);
		p2.add(bt5);
		p2.add(bt6);
		
		this.add(p1);
		this.add(p2);
		this.add(lb4);
		this.add(ck);
		this.add(jsp);
		
		this.setLayout(null);
		
		p1.setBounds(10, 10, 470, 50);
		p2.setBounds(10, 80, 470, 50);
		
		jsp.setBounds(0, 170, 490, 300);
		lb4.setBounds(0, 130, 470, 50);
		ck.setBounds(5, 145, 80, 20);
		
		list = mgr.course();
		
		String record[][] = new String[list.size()][col.length];
		
		for(int i=0;i<list.size();i++){
			bean = list.get(i);
			
			record[i][1]= bean.getC_cname();
			record[i][2] = bean.getC_tname();
			record[i][3] = bean.getC_price()+"";
			
		}
		
		DefaultTableModel model = new DefaultTableModel(record,col){
			public boolean isCellEditable(int row, int column){
				return false;
			}
		};
		
		table = new JTable(model);
		jsp.setViewportView(table);
		
		lb1.setHorizontalAlignment(JLabel.CENTER);
		lb2.setHorizontalAlignment(JLabel.CENTER);
		lb3.setHorizontalAlignment(JLabel.CENTER);
		lb4.setHorizontalAlignment(JLabel.CENTER);

		
		DefaultTableCellRenderer ck = new DefaultTableCellRenderer(){
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
			
				JCheckBox box = new JCheckBox();
				box.setHorizontalAlignment(JLabel.CENTER);
				return box;
				
			}
		};
		
		table.getColumn("선택All").setCellRenderer(ck);
		table.getColumn("선택All").setMaxWidth(50);
		
		DefaultTableCellRenderer al = new DefaultTableCellRenderer();
		al.setHorizontalAlignment(JLabel.CENTER);
		table.getColumn("coursename").setCellRenderer(al);
		table.getColumn("teachname").setCellRenderer(al);
		table.getColumn("price").setCellRenderer(al);
		
		bt1.addActionListener(this);
		bt2.addActionListener(this);
		bt3.addActionListener(this);
		bt4.addActionListener(this);
		bt5.addActionListener(this);
		bt6.addActionListener(this);
		
		for(int i=0;i<=7;i++){
			com1.addItem(60000 + (i*10000));
		}
		
	}
	public static void main(String[] args) {
		new CManage();

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String s = e.getActionCommand();
		
		if(s.equals(bt1.getText())){
			
			String cname = tf1.getText();
			String tname = tf2.getText();
			int price = Integer.parseInt(com1.getSelectedItem().toString());
			
			list = mgr.courseSearch(cname, tname, price);
			
			String record[][] = new String[list.size()][col.length];
			
			for(int i=0;i<list.size();i++){
				bean = list.get(i);
				
				record[i][1]= bean.getC_cname();
				record[i][2] = bean.getC_tname();
				record[i][3] = bean.getC_price()+"";
				
			}
			
			DefaultTableModel model = new DefaultTableModel(record,col){
				public boolean isCellEditable(int row, int column){
					return false;
				}
			};
			
			table = new JTable(model);
			jsp.setViewportView(table);
			
			lb1.setHorizontalAlignment(JLabel.CENTER);
			lb2.setHorizontalAlignment(JLabel.CENTER);
			lb3.setHorizontalAlignment(JLabel.CENTER);
			lb4.setHorizontalAlignment(JLabel.CENTER);

			
			DefaultTableCellRenderer ck = new DefaultTableCellRenderer(){
				public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
				
					JCheckBox box = new JCheckBox();
					box.setHorizontalAlignment(JLabel.CENTER);
					return box;
					
				}
			};
			
			table.getColumn("선택All").setCellRenderer(ck);
			
			table.getColumn("선택All").setMaxWidth(50);
			
			DefaultTableCellRenderer al = new DefaultTableCellRenderer();
			al.setHorizontalAlignment(JLabel.CENTER);
			table.getColumn("coursename").setCellRenderer(al);
			table.getColumn("teachname").setCellRenderer(al);
			table.getColumn("price").setCellRenderer(al);
			
		}else if(s.equals(bt2.getText())){
			
			if(tf1.getText().equals("")){
				
				JOptionPane.showMessageDialog(null, "필수항목(*)을 모두 입력하세요","메시지",0);
				tf1.requestFocus();
				
			}else{
				
				if(tf2.getText().equals("")){
					
					JOptionPane.showMessageDialog(null, "필수항목(*)을 모두 입력하세요","메시지",0);
					tf1.requestFocus();
					
				}else{
					
					String cname = tf1.getText();
					String tname = tf2.getText();
					int price = Integer.parseInt(com1.getSelectedItem().toString());
					
					mgr.CAdd(cname, tname, price);
					
				}

				
			}
		}else if(s.equals(bt3.getText())){
			
			if(table.getSelectedRow()==-1){
				JOptionPane.showMessageDialog(null, "수정할 강좌를 선택하세요","메시지",0);
			}else{
				
				Update.tf1.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
				Update.tf2.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
				Update.tf3.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
				
				new Update();
				
			}
		}else if(s.equals(bt4.getText())){
			
			
			if(table.getSelectedRow()==-1){
				JOptionPane.showMessageDialog(null, "삭제할 강좌를 선택하세요","메시지",0);
			}else{

				int yn = JOptionPane.showConfirmDialog(null, table.getValueAt(table.getSelectedRow(), 1) + " 강좌를 삭제하시겠습니까?","강좌 삭제",JOptionPane.OK_CANCEL_OPTION);
				
				if(yn==0){
					
					String name = table.getValueAt(table.getSelectedRow(), 1).toString();
					mgr.CDel(name);
					
					list = mgr.course();
					
					String record[][] = new String[list.size()][col.length];
					
					for(int i=0;i<list.size();i++){
						bean = list.get(i);
						
						record[i][1]= bean.getC_cname();
						record[i][2] = bean.getC_tname();
						record[i][3] = bean.getC_price()+"";
						
					}
					
					DefaultTableModel model = new DefaultTableModel(record,col){
						public boolean isCellEditable(int row, int column){
							return false;
						}
					};
					
					table = new JTable(model);
					jsp.setViewportView(table);
					
					DefaultTableCellRenderer ck = new DefaultTableCellRenderer(){
						public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
						
							JCheckBox box = new JCheckBox();
							box.setHorizontalAlignment(JLabel.CENTER);
							return box;
							
						}
					};
					
					table.getColumn("선택All").setCellRenderer(ck);
					table.getColumn("선택All").setMaxWidth(50);
					
					DefaultTableCellRenderer al = new DefaultTableCellRenderer();
					al.setHorizontalAlignment(JLabel.CENTER);
					table.getColumn("coursename").setCellRenderer(al);
					table.getColumn("teachname").setCellRenderer(al);
					table.getColumn("price").setCellRenderer(al);
					
				}
			}
			
		}else if(s.equals(bt5.getText())){
			
			list = mgr.course();
			
			String record[][] = new String[list.size()][col.length];
			
			for(int i=0;i<list.size();i++){
				bean = list.get(i);
				
				record[i][1]= bean.getC_cname();
				record[i][2] = bean.getC_tname();
				record[i][3] = bean.getC_price()+"";
				
			}
			
			DefaultTableModel model = new DefaultTableModel(record,col){
				public boolean isCellEditable(int row, int column){
					return false;
				}
			};
			
			table = new JTable(model);
			jsp.setViewportView(table);
			
			DefaultTableCellRenderer ck = new DefaultTableCellRenderer(){
				public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
				
					JCheckBox box = new JCheckBox();
					box.setHorizontalAlignment(JLabel.CENTER);
					return box;
					
				}
			};
			
			table.getColumn("선택All").setCellRenderer(ck);
			table.getColumn("선택All").setMaxWidth(50);
			
			DefaultTableCellRenderer al = new DefaultTableCellRenderer();
			al.setHorizontalAlignment(JLabel.CENTER);
			table.getColumn("coursename").setCellRenderer(al);
			table.getColumn("teachname").setCellRenderer(al);
			table.getColumn("price").setCellRenderer(al);
			
		}else if(s.equals(bt6.getText())){
			dispose();
			
		}
		
	}

}
