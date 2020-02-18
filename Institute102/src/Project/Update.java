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

public class Update extends JFrame implements ActionListener {
	
	ArrayList<Bean> list;
	Bean bean;
	DBMgr mgr = new DBMgr();
	
	JLabel lb1=new JLabel("강 좌 명 : ");
	JLabel lb2=new JLabel("강 사 명 : ");
	JLabel lb3=new JLabel("수 강 료: ");
	
	static JTextField tf1=new JTextField(10);
	static JTextField tf2=new JTextField(10);
	static JTextField tf3=new JTextField(10);
	
	JButton bt1= new JButton("수정");
	JButton bt2= new JButton("취소");
	
	JPanel p1=new JPanel(new GridLayout(3,2));
	JPanel p2=new JPanel();
	
	public Update(){
		this.setTitle("강좌 정보 수정");
		this.setVisible(true);
		this.setSize(350, 250);
		
		p1.add(lb1);
		p1.add(tf1);
		p1.add(lb2);
		p1.add(tf2);
		p1.add(lb3);
		p1.add(tf3);
		
		p2.add(bt1);
		p2.add(bt2);
		
		this.add("Center",p1);
		this.add("South",p2);
		
		bt1.addActionListener(this);
		bt2.addActionListener(this);
		
		
	}
	public static void main(String[] args) {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String s = e.getActionCommand();
		
		if(s.equals(bt1.getText())){
			
			String cname = tf1.getText();
			String tname = tf2.getText();
			int price = Integer.parseInt(tf3.getText());
			
			String pre = CManage.table.getValueAt(CManage.table.getSelectedRow(), 1).toString();
			
			mgr.CourseUpdate(cname, tname, price, pre);
			
			JOptionPane.showMessageDialog(null, pre + "강좌정보수정이 완료되었습니다.");
			dispose();
			
			list = mgr.course();
			
			String record[][] = new String[list.size()][CManage.col.length];
			
			for(int i=0;i<list.size();i++){
				bean = list.get(i);
				
				record[i][1]= bean.getC_cname();
				record[i][2] = bean.getC_tname();
				record[i][3] = bean.getC_price()+"";
				
			}
			
			DefaultTableModel model = new DefaultTableModel(record,CManage.col){
				public boolean isCellEditable(int row, int column){
					return false;
				}
			};
			
			CManage.table = new JTable(model);
			CManage.jsp.setViewportView(CManage.table);

			
			DefaultTableCellRenderer ck = new DefaultTableCellRenderer(){
				public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
				
					JCheckBox box = new JCheckBox();
					box.setHorizontalAlignment(JLabel.CENTER);
					return box;
					
				}
			};
			
			CManage.table.getColumn("선택All").setCellRenderer(ck);
			CManage.table.getColumn("선택All").setMaxWidth(50);
			
			DefaultTableCellRenderer al = new DefaultTableCellRenderer();
			al.setHorizontalAlignment(JLabel.CENTER);
			CManage.table.getColumn("coursename").setCellRenderer(al);
			CManage.table.getColumn("teachname").setCellRenderer(al);
			CManage.table.getColumn("price").setCellRenderer(al);
			
		}else if(s.equals(bt2.getText())){
			dispose();
		}
		
	}

}
