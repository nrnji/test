package Project;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class AddManage extends JFrame implements ActionListener{
	
	ArrayList<Bean> list;
	Bean bean;
	DBMgr mgr = new DBMgr();
	
	JLabel lb1= new JLabel("강 좌 명 :");
	JComboBox com1= new JComboBox();
	
	JLabel lb2= new JLabel("     회 원 코 드:");
	JComboBox com2= new JComboBox();
	
	JLabel lb3= new JLabel("     회 원 명 :");
	JTextField tf1= new JTextField(10);
	
	JButton bt1= new JButton("수강신청");
	JButton bt2= new JButton("삭제");
	JButton bt3= new JButton("전체보기");
	JButton bt4= new JButton("파일로저장");
	JButton bt5= new JButton("강좌별차트");
	JButton bt6= new JButton("닫기");
	
	JLabel lb4= new JLabel("< 수 강 신 청 현 황 >");
	
	static JTable table;
	static JScrollPane jsp = new JScrollPane();
	
	static String col[] = {"registercode","membercode","membername","coursename","courseprice","period","regprice","crregdate"};

	JPanel  p1= new JPanel();
	JPanel  p2= new JPanel();
	
	public AddManage(){
		

		list = mgr.course();
		
		for(int i=0;i<list.size();i++){
			bean = list.get(i);
			com1.addItem(bean.getC_cname()+"");
			
		}
		
		list = mgr.member();
		
		for(int i=0;i<list.size();i++){
			bean = list.get(i);
			com2.addItem(bean.getM_mcode()+"");
		}
		
		
		p1.add(lb1);
		p1.add(com1);
		p1.add(lb2);
		p1.add(com2);
		p1.add(lb3);
		p1.add(tf1);
		
		p2.add(bt1);
		p2.add(bt2);
		p2.add(bt3);
		p2.add(bt4);
		p2.add(bt5);
		p2.add(bt6);
		
		this.add(p1);
		this.add(p2);
		
		this.add(lb4);
		this.add(jsp);
		
		this.setLayout(null);
		
		p1.setBounds(0, 10, 690, 50);
		p2.setBounds(0, 80, 690, 50);
		lb4.setBounds(0, 120, 690, 50);
		
		lb4.setHorizontalAlignment(JLabel.CENTER);
		jsp.setBounds(0, 200, 690, 450);
		
		list = mgr.coursemanage();
		
		String record[][] = new String[list.size()][col.length];
		
		for(int i = 0; i<list.size();i++){
			bean = list.get(i);
			
			record[i][0] = bean.getCm_rcode()+"";
			record[i][1] = bean.getCm_mcode()+"";
			record[i][2] = bean.getCm_mname();
			record[i][3]= bean.getCm_cname();
			record[i][4] = bean.getCm_cprice()+"";
			record[i][5] = bean.getCm_period()+"";
			record[i][6] = bean.getCm_regprice()+"";
			record[i][7]  = bean.getCm_crregdate();
			
		}
		
		DefaultTableModel model = new DefaultTableModel(record, col){
			public boolean isCellEditable(int row, int column){
				return false;
			}
		};
		
		table = new JTable(model);
		jsp.setViewportView(table);
		
		DefaultTableCellRenderer al = new DefaultTableCellRenderer();
		al.setHorizontalAlignment(JLabel.CENTER);
		
		table.getColumnModel().getColumn(0).setCellRenderer(al);
		table.getColumnModel().getColumn(1).setCellRenderer(al);
		table.getColumnModel().getColumn(2).setCellRenderer(al);
		table.getColumnModel().getColumn(3).setCellRenderer(al);
		table.getColumnModel().getColumn(4).setCellRenderer(al);
		table.getColumnModel().getColumn(5).setCellRenderer(al);
		table.getColumnModel().getColumn(6).setCellRenderer(al);
		table.getColumnModel().getColumn(7).setCellRenderer(al);
		
		
		
		com1.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				
				String course = com1.getSelectedItem()+"";
				
				list = mgr.courseSearch(course);
				String record[][] = new String[list.size()][col.length];
				
				for(int i = 0; i<list.size();i++){
					bean = list.get(i);
					
					record[i][0] = bean.getCm_rcode()+"";
					record[i][1] = bean.getCm_mcode()+"";
					record[i][2] = bean.getCm_mname();
					record[i][3]= bean.getCm_cname();
					record[i][4] = bean.getCm_cprice()+"";
					record[i][5] = bean.getCm_period()+"";
					record[i][6] = bean.getCm_regprice()+"";
					record[i][7]  = bean.getCm_crregdate();
					
				}
				
				DefaultTableModel model = new DefaultTableModel(record, col){
					public boolean isCellEditable(int row, int column){
						return false;
					}
				};
				
				table = new JTable(model);
				jsp.setViewportView(table);
				
				
				DefaultTableCellRenderer al = new DefaultTableCellRenderer();
				al.setHorizontalAlignment(JLabel.CENTER);
				
				table.getColumnModel().getColumn(0).setCellRenderer(al);
				table.getColumnModel().getColumn(1).setCellRenderer(al);
				table.getColumnModel().getColumn(2).setCellRenderer(al);
				table.getColumnModel().getColumn(3).setCellRenderer(al);
				table.getColumnModel().getColumn(4).setCellRenderer(al);
				table.getColumnModel().getColumn(5).setCellRenderer(al);
				table.getColumnModel().getColumn(6).setCellRenderer(al);
				table.getColumnModel().getColumn(7).setCellRenderer(al);
				
				
			}
			
		});
		
		bt1.addActionListener(this);
		bt2.addActionListener(this);
		bt3.addActionListener(this);
		bt4.addActionListener(this);
		bt5.addActionListener(this);
		bt6.addActionListener(this);
		


		this.setTitle("수강신청및관리");
		this.setVisible(true);
		this.setSize(700, 700);
		
		
		
	}
	
	public static void main(String[] args) {
		
		new AddManage();
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String s = e.getActionCommand();
		
		if(s.equals(bt1.getText())){
			
			new Apply();
			
		}else if(s.equals(bt2.getText())){
			
			if(table.getSelectedRow()==-1){
				JOptionPane.showMessageDialog(null, "삭제할 레코드를 선택해주세요.");
			}else{
				
				int yn = JOptionPane.showConfirmDialog(null, "등록코드" + table.getValueAt(table.getSelectedRow(), 0) + "를 삭제하시겠습니까?","수강정보 삭제",JOptionPane.OK_CANCEL_OPTION);
				
				if(yn==0){
				
					int code = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
					
					mgr.Del(code);
					
					list = mgr.coursemanage();
					
					String record[][] = new String[list.size()][col.length];
					
					for(int i = 0; i<list.size();i++){
						bean = list.get(i);
						
						record[i][0] = bean.getCm_rcode()+"";
						record[i][1] = bean.getCm_mcode()+"";
						record[i][2] = bean.getCm_mname();
						record[i][3]= bean.getCm_cname();
						record[i][4] = bean.getCm_cprice()+"";
						record[i][5] = bean.getCm_period()+"";
						record[i][6] = bean.getCm_regprice()+"";
						record[i][7]  = bean.getCm_crregdate();
						
					}
					
					DefaultTableModel model = new DefaultTableModel(record, col){
						public boolean isCellEditable(int row, int column){
							return false;
						}
					};
					
					table = new JTable(model);
					jsp.setViewportView(table);
					
					DefaultTableCellRenderer al = new DefaultTableCellRenderer();
					al.setHorizontalAlignment(JLabel.CENTER);
					
					table.getColumnModel().getColumn(0).setCellRenderer(al);
					table.getColumnModel().getColumn(1).setCellRenderer(al);
					table.getColumnModel().getColumn(2).setCellRenderer(al);
					table.getColumnModel().getColumn(3).setCellRenderer(al);
					table.getColumnModel().getColumn(4).setCellRenderer(al);
					table.getColumnModel().getColumn(5).setCellRenderer(al);
					table.getColumnModel().getColumn(6).setCellRenderer(al);
					table.getColumnModel().getColumn(7).setCellRenderer(al);
				}
					
			}
			
		}else if(s.equals(bt3.getText())){
			
			list = mgr.coursemanage();
			
			String record[][] = new String[list.size()][col.length];
			
			for(int i = 0; i<list.size();i++){
				bean = list.get(i);
				
				record[i][0] = bean.getCm_rcode()+"";
				record[i][1] = bean.getCm_mcode()+"";
				record[i][2] = bean.getCm_mname();
				record[i][3]= bean.getCm_cname();
				record[i][4] = bean.getCm_cprice()+"";
				record[i][5] = bean.getCm_period()+"";
				record[i][6] = bean.getCm_regprice()+"";
				record[i][7]  = bean.getCm_crregdate();
				
			}
			
			DefaultTableModel model = new DefaultTableModel(record, col){
				public boolean isCellEditable(int row, int column){
					return false;
				}
			};
			
			table = new JTable(model);
			jsp.setViewportView(table);
			
			DefaultTableCellRenderer al = new DefaultTableCellRenderer();
			al.setHorizontalAlignment(JLabel.CENTER);
			
			table.getColumnModel().getColumn(0).setCellRenderer(al);
			table.getColumnModel().getColumn(1).setCellRenderer(al);
			table.getColumnModel().getColumn(2).setCellRenderer(al);
			table.getColumnModel().getColumn(3).setCellRenderer(al);
			table.getColumnModel().getColumn(4).setCellRenderer(al);
			table.getColumnModel().getColumn(5).setCellRenderer(al);
			table.getColumnModel().getColumn(6).setCellRenderer(al);
			table.getColumnModel().getColumn(7).setCellRenderer(al);
			
			
			
			
		}else if(s.equals(bt4.getText())){
			
			try{
				
				FileDialog fd = new FileDialog(new JFrame(), "텍스트 파일로 저장하기",FileDialog.SAVE);
				fd.setDirectory(System.getProperty("user.dir"));
				
				fd.setFile(com1.getSelectedItem() + "수강현황");
				fd.setVisible(true);
				
				FileWriter fw = new FileWriter(fd.getDirectory()+fd.getFile()+".txt");
				BufferedWriter bw = new BufferedWriter(fw);
				
				SimpleDateFormat format = new SimpleDateFormat("yyyy년  M월 dd일 E요일");
				
				bw.write("출력일 : " + format.format(Calendar.getInstance().getTime()) + "\t" + "강좌명 : " + com1.getSelectedItem());
				bw.newLine();
				bw.newLine();
				
				bw.write("등록코드" + "\t" + "회원코드" + "\t" + "회원명" + "\t" + "강좌명" + "\t" + "강좌수강료" + "\t" + "수강기간" + "\t" + "수강료" + "\t" + "수강신청일");
				bw.newLine();
				
				String course = com1.getSelectedItem()+"";
				list = mgr.courseSearch(course);
				
				
				String record[][] = new String[list.size()][col.length];
				
				for(int i = 0; i<list.size();i++){
					bean = list.get(i);
					
					record[i][0] = bean.getCm_rcode()+"";
					record[i][1] = bean.getCm_mcode()+"";
					record[i][2] = bean.getCm_mname();
					record[i][3]= bean.getCm_cname();
					record[i][4] = bean.getCm_cprice()+"";
					record[i][5] = bean.getCm_period()+"";
					record[i][6] = bean.getCm_regprice()+"";
					record[i][7]  = bean.getCm_crregdate();
					
					bw.write(record[i][0] + "\t" + record[i][1] + "\t" + record[i][2] + "\t" + record[i][3] + "\t" + record[i][4] + "\t" + record[i][5] + "\t" + record[i][6] + "\t" + record[i][7]);
					bw.newLine();
					
				}
				
				bw.close();
				fw.close();
				
			}catch(Exception e1){
				
			}
			
		}else if(s.equals(bt5.getText())){
			
			new Chart();
			
		}else if(s.equals(bt6.getText())){
			
			dispose();
		}
		
	}

}
