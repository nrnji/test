package Project;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Apply extends JFrame implements ActionListener{
	
	ArrayList<Bean> list;
	Bean bean;
	DBMgr mgr= new DBMgr();
	
	JLabel lb1= new JLabel("수 강 신 청");
	JLabel lb2= new JLabel("회 원 코 드 : ");
	JLabel lb3= new JLabel("회 원 명 : ");
	JLabel lb4= new JLabel("강 좌 명 : ");
	JLabel lb5= new JLabel("과 목 수 강 료 : ");
	JLabel lb6= new JLabel("수 강 기 간 : ");
	JLabel lb7= new JLabel("신 청 일 : ");
	JLabel lb8= new JLabel("");
	
	JComboBox com1= new JComboBox();
	JComboBox com2= new JComboBox();
	
	JTextField tf1= new JTextField(10);
	JTextField tf2= new JTextField(10);
	JTextField tf3= new JTextField(10);
	
	JButton bt1= new JButton("추가");
	JButton bt2= new JButton("닫기");
	
	JPanel p1= new JPanel(new GridLayout(6,2));
	JPanel p2= new JPanel();
	
	public Apply(){
		
		list = mgr.member();
		
		for(int i=0;i<list.size();i++){
			bean = list.get(i);
			com1.addItem(bean.getM_mcode()+"");
		}
		
		bean = list.get(0);
		tf1.setText(bean.getM_mname());
		
		list = mgr.course();
		
		for(int i=0;i<list.size();i++){
			bean = list.get(i);
			com2.addItem(bean.getC_cname());
		}
		
		bean = list.get(0);
		tf2.setText(bean.getC_price()+"");
		
		tf1.setEditable(false);
		tf2.setEditable(false);
		
		p1.add(lb2);
		p1.add(com1);
		p1.add(lb3);
		p1.add(tf1);
		p1.add(lb4);
		p1.add(com2);
		p1.add(lb5);
		p1.add(tf2);
		p1.add(lb6);
		p1.add(tf3);
		p1.add(lb7);
		p1.add(lb8);
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy년 M월 dd일");
		lb8.setText(format.format(Calendar.getInstance().getTime()));
		
		p2.add(bt1);
		p2.add(bt2);
		
		this.add("North",lb1);
		this.add("Center",p1);
		this.add("South",p2);
		
		
		lb1.setHorizontalAlignment(JLabel.CENTER);
		lb8.setHorizontalAlignment(JLabel.RIGHT);
	
		bt1.addActionListener(this);
		bt2.addActionListener(this);
		
		com1.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent arg0) {
		
				int code = Integer.parseInt(com1.getSelectedItem()+"");
				list = mgr.memberSelect(code);
				
				bean = list.get(0);
				tf1.setText(bean.getM_mname());
				
				
			}
			
		});
		
		
		com2.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent arg0) {
		
				String name = com2.getSelectedItem()+"";
				
				list = mgr.courseSelect(name);
				
				bean = list.get(0);
				tf2.setText(bean.getC_price()+"");
				
			}
			
		});
		
		
		this.setTitle("수강신청");
		this.setVisible(true);
		this.setSize(300, 300);
	}
	

	public static void main(String[] args) {
		new Apply();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String s = e.getActionCommand();
		
		if(s.equals(bt1.getText())){
			
			if(tf3.getText().equals("")){
				JOptionPane.showMessageDialog(null, "모든 정보를 입력해주세요.");
			}else{
				
				int mcode = Integer.parseInt(com1.getSelectedItem()+"");
				String mname = tf1.getText();
				String cname = com2.getSelectedItem()+"";
				int cprice = Integer.parseInt(tf2.getText());
				int period = Integer.parseInt(tf3.getText());
				
				list = mgr.coursemanage();
				int rcode = list.size()+1;
				
				double sale=0;
				
				if(period>=6){
					sale = 0.2;
				}else if(period>=3){
					sale = 0.3;
				}else{
					sale = 0;
				}
				
				double regprice = (1-sale) * cprice * period;
				
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				String date = format.format(Calendar.getInstance().getTime());
				
				mgr.Apply(rcode, mcode, mname, cname, cprice, period, regprice, date);
				
				list = mgr.coursemanage();
				
				String record[][] = new String[list.size()][AddManage.col.length];
				
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
				
				DefaultTableModel model = new DefaultTableModel(record, AddManage.col){
					public boolean isCellEditable(int row, int column){
						return false;
					}
				};
				
				AddManage.table = new JTable(model);
				AddManage.jsp.setViewportView(AddManage.table);
				
				DefaultTableCellRenderer al = new DefaultTableCellRenderer();
				al.setHorizontalAlignment(JLabel.CENTER);
				
				AddManage.table.getColumnModel().getColumn(0).setCellRenderer(al);
				AddManage.table.getColumnModel().getColumn(1).setCellRenderer(al);
				AddManage.table.getColumnModel().getColumn(2).setCellRenderer(al);
				AddManage.table.getColumnModel().getColumn(3).setCellRenderer(al);
				AddManage.table.getColumnModel().getColumn(4).setCellRenderer(al);
				AddManage.table.getColumnModel().getColumn(5).setCellRenderer(al);
				AddManage.table.getColumnModel().getColumn(6).setCellRenderer(al);
				AddManage.table.getColumnModel().getColumn(7).setCellRenderer(al);
				
				
			}
		}else if(s.equals(bt2.getText())){
			
			dispose();
			
		}
		
	}

}
