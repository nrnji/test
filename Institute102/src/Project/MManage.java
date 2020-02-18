package Project;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MManage extends JFrame implements ActionListener{

	ArrayList<Bean> list;
	Bean bean;
	DBMgr mgr = new DBMgr();
	
	JLabel lb1= new JLabel("ȸ����");
	JTextField tf1= new JTextField(10);
	JButton bt1= new JButton("��ȸ");
	JButton bt2= new JButton("���ΰ�ħ");
	JButton bt3= new JButton("�ݱ�");

	static JTable table;
	static JScrollPane jsp = new JScrollPane();
	
	static String col[] = {"membercode","membername","phone","address","regdate"};
	
	JPanel p1= new JPanel();
	
	JPopupMenu popup = new JPopupMenu();
	
	JMenuItem item1= new JMenuItem("����");
	JMenuItem item2= new JMenuItem("����");
	
	public MManage(){
		this.setTitle("ȸ�� ����");
		this.setVisible(true);
		this.setSize(750, 600);
	
		p1.add(lb1);
		p1.add(tf1);
		p1.add(bt1);
		p1.add(bt2);
		p1.add(bt3);
		
		popup.add(item1);
		popup.add(item2);
		
		this.add("North",p1);
		this.add("Center",jsp);
		
		list = mgr.member();
		
		String record[][] = new String[list.size()][col.length];
		
		for(int i=0;i<list.size();i++){
			bean = list.get(i);
			
			record[i][0] = bean.getM_mcode()+"";
			record[i][1] = bean.getM_mname();
			record[i][2] = bean.getM_phone();
			record[i][3] = bean.getM_address();
			record[i][4] = bean.getM_regdate();
			
		}
		
		DefaultTableModel model = new DefaultTableModel(record, col){
			
			public boolean isCellEditable(int row, int column){
				return false;
			}
		};
		
		table = new JTable(model);
		jsp.setViewportView(table);
				
		
		table.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(e.getButton()==3){
					
					int row = table.rowAtPoint(e.getPoint());
					table.setRowSelectionInterval(row, row);
					table.setSelectionBackground(Color.YELLOW);
					
					popup.show((Component) e.getSource(), e.getX(), e.getY());
				}
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		bt1.addActionListener(this);
		bt2.addActionListener(this);
		bt3.addActionListener(this);
		
		item1.addActionListener(this);
		item2.addActionListener(this);
		
		table.setAutoCreateRowSorter(true);
		
	}
	
	Vector a(){
		
		
		// ���� -> ����
		// -> ���� ������ ó���� ��
		
		// ����
		// -> ���� �����ͺ��̽� �� �Է� �� ��
		
		
		
		return null;
	}
	
	

	
	public static void main(String[] args) {
		
		new MManage();

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		String s = e.getActionCommand();
		
		if(s.equals(bt1.getText())){
			
			if(tf1.getText().equals("")){
				JOptionPane.showMessageDialog(null, "ȸ������ �Է����ּ���.");
			}else{
				
				String name = tf1.getText();
				
				list = mgr.search(name);
				
				String record[][] = new String[list.size()][col.length];
				
				for(int i=0;i<list.size();i++){
					bean = list.get(i);
					
					record[i][0] = bean.getM_mcode()+"";
					record[i][1] = bean.getM_mname();
					record[i][2] = bean.getM_phone();
					record[i][3] = bean.getM_address();
					record[i][4] = bean.getM_regdate();
					
				}
				
				DefaultTableModel model = new DefaultTableModel(record, col){
					
					public boolean isCellEditable(int row, int column){
						return false;
					}
				};
				
				table = new JTable(model);
				jsp.setViewportView(table);
						
				table.addMouseListener(new MouseListener(){

					@Override
					public void mouseClicked(MouseEvent e) {
						
						if(e.getButton()==3){
							
							int row = table.rowAtPoint(e.getPoint());
							table.setRowSelectionInterval(row, row);
							table.setSelectionBackground(Color.YELLOW);
							
							popup.show((Component) e.getSource(), e.getX(), e.getY());
						}
						
					}

					@Override
					public void mouseEntered(MouseEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseExited(MouseEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mousePressed(MouseEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseReleased(MouseEvent arg0) {
						// TODO Auto-generated method stub
						
					}
					
				});
				
			}
		}else if(s.equals(bt2.getText())){
			
			list = mgr.member();
			
			String record[][] = new String[list.size()][col.length];
			
			for(int i=0;i<list.size();i++){
				bean = list.get(i);
				
				record[i][0] = bean.getM_mcode()+"";
				record[i][1] = bean.getM_mname();
				record[i][2] = bean.getM_phone();
				record[i][3] = bean.getM_address();
				record[i][4] = bean.getM_regdate();
				
			}
			
			DefaultTableModel model = new DefaultTableModel(record, col){
				
				public boolean isCellEditable(int row, int column){
					return false;
				}
			};
			
			table = new JTable(model);
			jsp.setViewportView(table);
			
			tf1.setText("");
			
			table.addMouseListener(new MouseListener(){

				@Override
				public void mouseClicked(MouseEvent e) {
					
					if(e.getButton()==3){
						
						int row = table.rowAtPoint(e.getPoint());
						table.setRowSelectionInterval(row, row);
						table.setSelectionBackground(Color.YELLOW);
						
						popup.show((Component) e.getSource(), e.getX(), e.getY());
					}
					
				}

				@Override
				public void mouseEntered(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseExited(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mousePressed(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
			});
			
		}else if(s.equals(bt3.getText())){
			
			dispose();
			
		}else if(s.equals(item1.getText())){
			
			InfoUpdate.tf1.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
			InfoUpdate.tf2.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
			InfoUpdate.tf3.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
			InfoUpdate.tf4.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
			InfoUpdate.tf5.setText(table.getValueAt(table.getSelectedRow(), 4).toString());
			
			new InfoUpdate();
			
		}else if(s.equals(item2.getText())){
			
			int yn = JOptionPane.showConfirmDialog(null, table.getValueAt(table.getSelectedRow(), 1) + "���� ������ �����Ͻðڽ��ϱ�?", "ȸ�� ����" , JOptionPane.OK_CANCEL_OPTION);
			
			if(yn ==0){
				
				int code = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
				
			}
		}
		
	}

}
