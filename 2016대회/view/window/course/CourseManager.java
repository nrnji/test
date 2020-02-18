package window.course;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import base.comp.BaseFrame;
import base.comp.BasePanel;
import jdbc.DBManager;
import message.Message;
import window.aca.AcaManager;

public class CourseManager extends BaseFrame{

	private JLabel lbl4;
	private JLabel lbl3;
	private JLabel lbl2;
	private JLabel lbl1;
	private JTextField jtf1;
	private JTextField jtf2;
	private JComboBox<Integer> combo1;
	private JButton addB;
	private JButton searchB;
	private JButton updateB;
	private JButton deleteB;
	private JButton allPreviewB;
	private JButton exitB;
	private JPanel gridPn;
	private JPanel flow;
	private JPanel northPn;
	private JPanel northgf;
	private BasePanel basePanel;
	private DefaultTableModel dtm;
	private JTable table;
	private ResultSet rs;
	private JPanel northBor;
	private JLabel now;
	private JCheckBox ck;

	public CourseManager() {
		super.setFrame(450, 450, "강좌 관리");
	}

	public static void main(String[] args) {
		new CourseManager();
	}

	@Override
	public void setComp() {
		// TODO Auto-generated method stub
		lbl1 = new JLabel("*과 정 명");
		lbl2 = new JLabel("*강 사 명");
		lbl3 = new JLabel("수 강 료");
		lbl4 = new JLabel(" ");
		lbl1.setHorizontalAlignment(0);
		lbl2.setHorizontalAlignment(0);
		lbl3.setHorizontalAlignment(0);
		lbl4.setHorizontalAlignment(0);
		
		jtf1 = new JTextField();
		jtf2 = new JTextField();
		
		combo1 = new JComboBox<Integer>();
		
		for (int i = 60000; i <= 130000; i+= 10000) {
			combo1.addItem(i);
		}
		
		searchB = new JButton("조회");
		addB = new JButton("등록");
		updateB = new JButton("수정");
		deleteB = new JButton("삭제");
		allPreviewB = new JButton("전체보기");
		exitB = new JButton("닫기");
		
		flow = new JPanel();
		gridPn = new JPanel(new GridLayout(2, 4));
		gridPn.setBorder(new EmptyBorder(10, 20, 0, 20));
		
		northgf = new JPanel(new GridLayout(2, 1, 0 , 10));
		
		ck = new JCheckBox("선택All");
		now = new JLabel("<강 좌 현 황>");
		now.setHorizontalAlignment(0);
		northBor = new JPanel(new BorderLayout());
		
		northPn = new JPanel(new BorderLayout());
		
		dtm("select * from course" , 1);
	}

	@Override
	public void setDesign() {
		flow.add(addB);
		flow.add(updateB);
		flow.add(deleteB);
		flow.add(allPreviewB);
		flow.add(exitB);
		
		gridPn.add(lbl1);
		gridPn.add(lbl2);
		gridPn.add(lbl3);
		gridPn.add(lbl4);
		gridPn.add(jtf1);
		gridPn.add(jtf2);
		gridPn.add(combo1);
		gridPn.add(searchB);
		
		northgf.add(gridPn);
		northgf.add(flow);
		
		northBor.add(ck, BorderLayout.WEST);
		northBor.add(now);
		northBor.add(new JLabel("                         "), BorderLayout.EAST);
		
		northPn.add(northgf, BorderLayout.CENTER);
		northPn.add(northBor, BorderLayout.SOUTH);
		
		super.add(basePanel);
	}

	@Override
	public void setAction() {
		// TODO Auto-generated method stub
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int row = table.getSelectedRow();
				int col = table.columnAtPoint(e.getPoint());
				if (e.getButton() == 1 && col == 0) {
					table.setValueAt(!(boolean) table.getValueAt(row, 0), row, 0);
				}
			}
		});
		ck.addActionListener(e -> {
			for (int i = 0; i < table.getRowCount(); i++) {
				table.setValueAt(ck.isSelected(), i, 0);
			}
		});
		searchB.addActionListener(e -> {
			super.remove(basePanel);
			dtm("select * from course where CourseName like '%" + jtf1.getText() + "%' and TeachName like '%" + jtf2.getText() + "%' and Price like '%"+ combo1.getSelectedItem().toString() +"%'", 2);
			super.add(basePanel);
			repaint();
			revalidate();
		});
		updateB.addActionListener(e -> {
			updatedelete(1);
		});
		deleteB.addActionListener(e -> {
			updatedelete(2);
		});
		allPreviewB.addActionListener(e -> {
			super.remove(basePanel);
			dtm("select * from course", 1);
			super.add(basePanel);
			repaint();
			revalidate();
		});
		exitB.addActionListener(e -> {
			dispose();
			new AcaManager();
		});
	}
	
	private void updatedelete(int ch) {
		// TODO Auto-generated method stub
		ArrayList<Integer> array = new ArrayList<Integer>();
		if (ch == 1) {
			for (int i = 0; i < table.getRowCount(); i++) {
				if ((boolean) table.getValueAt(i, 0) == true) {
					array.add(i);
				}
			}
			if (array.size() == 0) {
				Message.errMessage("수정할 강좌를 선택하세요", "메시지");
				return;
			} else if (array.size() > 1){
				Message.errMessage("수정할 강좌를 한 개 만 선택하세요", "메시지");
				return;
			}
			dispose();
//			new 
		} else {
			for (int i = 0; i < table.getRowCount(); i++) {
				if ((boolean) table.getValueAt(i, 0) == true) {
					array.add(i);
				}
			}
			if (array.size() < 1) {
				Message.errMessage("삭제할 강좌를 선택하세요.", "메시지");
				return;
			}
			if (JOptionPane.showConfirmDialog(null, table.getValueAt(array.get(0), 1).toString() + "강좌를 삭제하시겠스니까?", "강좌 삭제", JOptionPane.YES_NO_OPTION) == 0) {
				try {
					DBManager dbm = new DBManager();
					dbm.setPreUpdate("delete from course where couseName = ?", table.getValueAt(array.get(0), 1).toString());
					dtm.removeRow(array.get(0));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		repaint();
		revalidate();
	}
	
	private void dtm(String sql , int k) {
		// TODO Auto-generated method stub
		
		if ((jtf1.getText().isBlank() || jtf2.getText().isBlank()) && k == 2) {
			Message.errMessage("필수항목(*)을 모두 입력하세요", "메시지");
			return;
		}
		
		dtm = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		dtm.setColumnIdentifiers(new String[] {"선택All", "coursename", "teachername", "price"});
		try {
			DBManager dbm = new DBManager();
			rs = dbm.setPreRs(sql);
			if (rs.next()) {
				rs.beforeFirst();
			} else {
				rs = dbm.setPreRs("select * from course");
			}
			while (rs.next()) {
				dtm.addRow(new Object[] {false, rs.getString(1),rs.getString(2),rs.getString(3)});
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		table = new JTable(dtm);
		basePanel = new BasePanel(0,northPn, table, 0);
		table.getColumnModel().getColumn(0).setCellRenderer(new TableCell());
	}
	
	class TableCell extends DefaultTableCellRenderer {
		
		TableCell() {
			super.setHorizontalAlignment(0);
		}
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			
			
			if (column == 0) {
				JCheckBox ck = new JCheckBox();
				ck.setSelected((boolean) table.getValueAt(row, 0));
				ck.setHorizontalAlignment(JCheckBox.CENTER);
				
				return ck;
			}
			
			return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		}
		
	}
}
