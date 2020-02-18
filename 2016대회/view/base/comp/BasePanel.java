package base.comp;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

public class BasePanel extends JPanel{

	private JScrollPane jsp;

	public BasePanel() {
		super(new BorderLayout());
	}
	
	public BasePanel(JPanel centerPn) {
		this();
		super.add(centerPn, BorderLayout.CENTER);
	}
	
	public BasePanel(JPanel centerPn, int top, int left, int bottom, int right) {
		this(centerPn);
		super.setBorder(new EmptyBorder(top, left, bottom, right));
	}
	
	public BasePanel(JPanel centerPn, String title,int top, int left, int bottom, int right) {
		this(centerPn, top, left, bottom, right);
		JLabel lbl = new JLabel(title);
		lbl.setHorizontalAlignment(JLabel.CENTER);
		lbl.setFont(new Font("±¼¸²", Font.BOLD, 15));
		lbl.setBorder(new EmptyBorder(0, 0, 10, 0));
		super.add(lbl, BorderLayout.NORTH);
	}
	
	public BasePanel(JPanel centerPn, String title, int fontStlye, int size ,int top, int left, int bottom, int right) {
		this(centerPn, top, left, bottom, right);
		JLabel lbl = new JLabel(title);
		lbl.setHorizontalAlignment(JLabel.CENTER);
		lbl.setFont(new Font("±¼¸²", fontStlye, size));
		super.add(lbl, BorderLayout.NORTH);
	}
	
	public BasePanel(JPanel northPn, JTable table, int...colSize) {
		this();
		for (int i = 0; i < colSize.length; i++) {
			table.getColumnModel().getColumn(i).setPreferredWidth(colSize[i]);
		}
		jsp = new JScrollPane(table);
		super.add(northPn, BorderLayout.NORTH);
		super.add(jsp);
	}
	
	public BasePanel(int align, JPanel northPn, JTable table, int...colSize) {
		this(northPn, table, colSize);
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(align);
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(dtcr);
		}
	}
}
