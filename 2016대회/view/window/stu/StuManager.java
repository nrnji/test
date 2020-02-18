package window.stu;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import base.comp.BaseFrame;
import base.comp.BasePanel;
import jdbc.DBManager;
import window.aca.AcaManager;

public class StuManager extends BaseFrame{

	private JLabel lbl1;
	private JTextField jtf1;
	private JButton refreshB;
	private JButton searchB;
	private JButton exitB;
	private ResultSet rs;
	private DefaultTableModel dtm;
	private JTable table;
	private JPanel northPn;
	private BasePanel basePanel;
	private String Sql;
	public StuManager() {
		super.setFrame(600, 800, "ȸ��������");
	}
	public static void main(String[] args) {
		new StuManager();
	}
	@Override
	public void setComp() {
		lbl1 = new JLabel("ȸ����");
		jtf1 = new JTextField(14);
		refreshB = new JButton("���ΰ�ħ");
		searchB = new JButton("��ȸ");
		exitB = new JButton("�ݱ�");
		
		northPn = new JPanel();
		
		setDtm("select * from `member`");
		
	}
	@Override
	public void setDesign() {
		northPn.add(lbl1);
		northPn.add(jtf1);
		northPn.add(searchB);
		northPn.add(refreshB);
		northPn.add(exitB);
		
		super.add(basePanel);
		
	}
	@Override
	public void setAction() {
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				table.setRowSelectionInterval(table.rowAtPoint(e.getPoint()),table.rowAtPoint(e.getPoint()));
				if (SwingUtilities.isRightMouseButton(e)) { //��Ŭ������ Ȯ���Ѵ�.
					table.setSelectionBackground(Color.yellow); // ��Ŭ���̸� ���õ� Row�� ������ ��������� �ٲ۴�.
				} else {
					table.setSelectionBackground(UIManager.getColor("Jtable.selectionBackground"));
				}
				super.mousePressed(e);
			}
		});
		searchB.addActionListener(e -> {
			super.remove(basePanel);
//			select * from `member` where memberName like '%��%';
			Sql = "select * from `member` where memberName like '%" + jtf1.getText().trim() + "%'";
			setDtm(Sql);
			super.add(basePanel);
			repaint();
			revalidate();
		});
		refreshB.addActionListener(e -> {
			super.remove(basePanel);
			setDtm("select * from `member`");
			super.add(basePanel);
			repaint();
			revalidate();
		});
		exitB.addActionListener(e -> {
			dispose();
			new AcaManager();
		});
	}
	
	private void setDtm(String sql) {
		try {
			DBManager dbm = new DBManager();
			rs = dbm.setPreRs(sql);
			dtm = new DefaultTableModel() {
				@Override
				public Class<?> getColumnClass(int columnIndex) {
					// TODO Auto-generated method stub
					return Integer.class;
				}
			};
			dtm.setColumnIdentifiers(new String[] {"membercode","membername","phone","address","regdate"});
			while (rs.next()) {
				dtm.addRow(new Object[] {rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)});
			}
			table = new JTable(dtm);
			table.setAutoCreateRowSorter(true);
			
			JPopupMenu pop = new JPopupMenu();

			JMenuItem update = new JMenuItem("����");
			JMenuItem delete = new JMenuItem("����");
			
			update.addActionListener(e -> {
				dispose();
				new StuUpdate(table.getValueAt(table.getSelectedRow(), 0).toString(), table.getValueAt(table.getSelectedRow(), 4).toString());
			});
			delete.addActionListener(e -> {
				int ch = JOptionPane.showConfirmDialog(null, table.getValueAt(table.getSelectedRow(), 1) + "ȸ���� ������ �����Ͻðٽ��ϱ�?", "ȸ�� ����", JOptionPane.YES_NO_OPTION);
				if (ch == 0) {
					try {
						dbm.setPreUpdate("delete from `member` where memberName = ? and memberCode = ?", table.getValueAt(table.getSelectedRow(), 1).toString(), table.getValueAt(table.getSelectedRow(), 0).toString());
						dtm.removeRow(table.getSelectedRow());
						repaint();
						revalidate();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			});
			pop.add(update);
			pop.add(delete);
			
			table.setComponentPopupMenu(pop);
			basePanel = new BasePanel(2, northPn, table, 0,0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
