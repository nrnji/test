package window.stu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import base.comp.BaseFrame;
import base.comp.BasePanel;
import jdbc.DBManager;
import message.Message;
import window.aca.AcaManager;

public class StuAdd extends BaseFrame{

	private JLabel code;
	private JLabel phone;
	private JLabel add;
	private JLabel date;
	private JLabel name;
	private JLabel Time;
	private JButton exitButton;
	private JButton addButton;
	private JPanel centerPn;
	private JPanel gridPn;
	private JPanel flow[];
	private JTextField jtfCode1;
	private JTextField jtfCode2;
	private JTextField jtfCode3;
	private JTextField jtfCode4;
	private BasePanel basePanel;
	private JPanel southFlow; 
	public StuAdd() {
		super.setFrame(350, 250, "ȸ�� ���");
	}

	public static void main(String[] args) {
		new StuAdd();
	}

	@Override
	public void setComp() {
		code = new JLabel("ȸ �� �� �� : ");
		name = new JLabel("*ȸ �� �� : ");
		phone = new JLabel("�� �� �� : ");
		add = new JLabel("�� �� : ");
		date = new JLabel("�� �� �� : ");
		Time = new JLabel(new SimpleDateFormat("yyyy�� M�� dd��").format(new Date()));
		Time.setHorizontalAlignment(JLabel.RIGHT);
		
		jtfCode1 = new JTextField(23);
		jtfCode2 = new JTextField(23);
		jtfCode3 = new JTextField(23);
		jtfCode4 = new JTextField(23);
		Time.setPreferredSize(new Dimension(234, 22));
		jtfCode1.setEditable(false);
		
		addButton = new JButton("�߰�");
		exitButton = new JButton("�ݱ�");
		
		southFlow = new JPanel();
		
		gridPn = new JPanel(new GridLayout(5, 1));
		flow = new JPanel[5];
		for (int i = 0; i < flow.length ; i++) {
			flow[i] = new JPanel(new FlowLayout(2));
		}
		
		centerPn = new JPanel(new BorderLayout());
		basePanel = new BasePanel(centerPn, "ȸ �� �� ��", Font.BOLD, 11, 5, 5, 5, 5);
	}

	@Override
	public void setDesign() {
		flow[0].add(code);
		flow[0].add(jtfCode1);
		flow[1].add(name);
		flow[1].add(jtfCode2);
		flow[2].add(phone);
		flow[2].add(jtfCode3);
		flow[3].add(add);
		flow[3].add(jtfCode4);
		flow[4].add(date);
		flow[4].add(Time);
		
		for (int i = 0; i < flow.length; i++) {
			gridPn.add(flow[i]);
		}
		
		southFlow.add(addButton);
		southFlow.add(exitButton);
		
		centerPn.add(gridPn);
		centerPn.add(southFlow, BorderLayout.SOUTH);
		
		super.add(basePanel);
		
	}

	@Override
	public void setAction() {
		addButton.addActionListener((e) -> {
			check();
		});
		exitButton.addActionListener((e) -> {
			dispose();
			new AcaManager();
		});
	}
	
	private void check() {
		if (jtfCode2.getText().isBlank()) {
			Message.errMessage("�ʼ��׸�(*)�� ��� �Է��ϼ���", "ȸ����� ����");
			return;
		}
		DBManager dbm;
		try {
			dbm = new DBManager();
			dbm.setPreUpdate("insert into `member` values(0,?,?,?,?)", jtfCode2.getText(), jtfCode3.getText(), jtfCode4.getText(), new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
			Message.infoMessage(jtfCode2.getText() +"ȸ���� ��ϵǾ����ϴ�." , "�޽���");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
