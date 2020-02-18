package window.application;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import base.comp.BaseFrame;
import base.comp.BasePanel;
import jdbc.DBManager;
import message.Message;
import window.aca.AcaManager;

public class ApplicaionManager extends BaseFrame {

	private JLabel lbl1;
	private JLabel lbl2;
	private JLabel lbl3;
	private JTextField jtf1;
	private JComboBox<Integer> combo2;
	private JComboBox<String> combo1;
	private JButton searchB;
	private JButton addB;
	private JButton saveB;
	private JButton deleteB;
	private JButton allPreviewB;
	private JButton exitB;
	private JPanel flow;
	private JPanel flow2;
	private JPanel northgf;
	private JLabel now;
	private JPanel northBor;
	private JPanel northPn;
	private DefaultTableModel dtm;
	private ResultSet rs;
	private JTable table;
	private BasePanel basePanel;

	public ApplicaionManager() {
		super.setFrame(700, 500, "������û�װ���");
	}

	public static void main(String[] args) {
		new ApplicaionManager();
	}

	@Override
	public void setComp() {
		// TODO Auto-generated method stub
		lbl1 = new JLabel("�� �� �� : ");
		lbl2 = new JLabel("ȸ �� �� �� : ");
		lbl3 = new JLabel("ȸ �� �� : ");
		lbl1.setHorizontalAlignment(4);
		lbl2.setHorizontalAlignment(4);
		lbl3.setHorizontalAlignment(4);

		jtf1 = new JTextField();

		combo1 = new JComboBox<String>();
		combo2 = new JComboBox<Integer>();

		try {
			DBManager db = new DBManager();
			rs = db.setPreRs("select * from course");
			while (rs.next()) {
				combo1.addItem(rs.getString(1));
			}
			rs = db.setPreRs("select * from courseManage");
			while (rs.next()) {
				combo2.addItem(rs.getInt(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		searchB = new JButton("���º���Ʈ");
		addB = new JButton("������û");
		saveB = new JButton("���Ϸ�����");
		deleteB = new JButton("����");
		allPreviewB = new JButton("��ü����");
		exitB = new JButton("�ݱ�");

		flow = new JPanel();
		flow2 = new JPanel(new GridLayout(1, 8));
		flow2.setBorder(new EmptyBorder(10, 20, 0, 20));

		northgf = new JPanel(new GridLayout(2, 1, 0, 20));
		northgf.setBorder(new EmptyBorder(5, 0, 20, 0));
		now = new JLabel("<�� �� �� û �� Ȳ>");
		now.setHorizontalAlignment(0);
		northBor = new JPanel(new BorderLayout());
		northBor.setBorder(new EmptyBorder(0, 0, 20, 0));
		northPn = new JPanel(new BorderLayout());

		dtm("select * from courseManage order by registercode desc");
	}

	@Override
	public void setDesign() {

		flow2.add(new JLabel(" "));
		flow2.add(lbl1);
		flow2.add(combo1);
		flow2.add(lbl2);
		flow2.add(combo2);
		flow2.add(lbl3);
		flow2.add(jtf1);
		flow2.add(new JLabel(" "));

		flow.add(addB);
		flow.add(deleteB);
		flow.add(allPreviewB);
		flow.add(saveB);
		flow.add(searchB);
		flow.add(exitB);

		northgf.add(flow2);
		northgf.add(flow);

		northBor.add(now);

		northPn.add(northgf);
		northPn.add(northBor, BorderLayout.SOUTH);

		super.add(basePanel);
	}

	@Override
	public void setAction() {
		// TODO Auto-generated method stub
		combo1.addActionListener(e -> {
			super.remove(basePanel);
			dtm("select * from coursemanage where coursename = '" + combo1.getSelectedItem().toString() + "'");
			super.add(basePanel);
			repaint();
			revalidate();
		});
		allPreviewB.addActionListener(e -> {
			super.remove(basePanel);
			dtm("select * from coursemanage order by registercode desc");
			super.add(basePanel);
			repaint();
			revalidate();
		});
		deleteB.addActionListener(e -> {
			try {
				if (JOptionPane.showConfirmDialog(null, "����ڵ带 " + table.getValueAt(table.getSelectedRow(), 0).toString() + " �����Ͻðٽ��ϱ�?", "�������� ����", JOptionPane.YES_NO_OPTION) == 0) {
					DBManager dbm = new DBManager();
					dbm.setPreUpdate("delete from coursemanage where registercode = ?",
							table.getValueAt(table.getSelectedRow(), 0).toString());
					dtm.removeRow(table.getSelectedRow());
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			repaint();
			revalidate();
		});
		saveB.addActionListener(e -> {
			String contents = "����� : "+ new SimpleDateFormat("yyyy�� M�� dd�� �Ͽ���").format(new Date()) + "		���¸� : " + combo1.getSelectedItem().toString() + "\n";
			contents += "����ڵ�	ȸ���ڵ�	ȸ����	���¸�	���¼�����	�����Ⱓ	������	������û��\n";
			for (int i = 0; i < table.getRowCount(); i++) {
				contents += table.getValueAt(i, 0) + "\t";
				contents += table.getValueAt(i, 1) + "\t";
				contents += table.getValueAt(i, 2) + "\t";
				contents += table.getValueAt(i, 3) + "\t";
				contents += table.getValueAt(i, 4) + "\t";
				contents += table.getValueAt(i, 5) + "\t";
				contents += table.getValueAt(i, 6) + "\t";
				contents += table.getValueAt(i, 7);
				contents += "\n";
			}
			new FileWi(combo1.getSelectedItem().toString(), contents);
		});
		exitB.addActionListener(e -> {
			dispose();
			new AcaManager();
		});
	}

	private void dtm(String sql) {
		// TODO Auto-generated method stub

		dtm = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		dtm.setColumnIdentifiers(new String[] { "registercode", "membercode", "membername", "coursename", "courseprice",
				"period", "regprice", "crregdate" });
		try {
			DBManager dbm = new DBManager();
			rs = dbm.setPreRs(sql);
			while (rs.next()) {
				dtm.addRow(new Object[] { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8) });
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		table = new JTable(dtm);
		basePanel = new BasePanel(0, northPn, table);
	}

	private void comboaction() {

	}

}
