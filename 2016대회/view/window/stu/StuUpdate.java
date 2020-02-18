package window.stu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.sql.ResultSet;
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

public class StuUpdate extends BaseFrame{

	private JLabel code;
	private JLabel name;
	private JLabel phone;
	private JLabel add;
	private JLabel date;
	private JLabel Time;
	private JTextField jtfCode2;
	private JTextField jtfCode1;
	private JTextField jtfCode3;
	private JTextField jtfCode4;
	private JTextField jtfCode5;
	private JButton addButton;
	private JButton exitButton;
	private JPanel southFlow;
	private JPanel gridPn;
	private JPanel centerPn;
	private BasePanel basePanel;
	private ResultSet rs;
	String code2, date2;
	public StuUpdate(String code, String date) {
		this.code2 = code;
		this.date2 = date;
		super.setFrame(400, 300, "회원 정보 수정");
		jtfCode1.setText(code);
		jtfCode5.setText(date);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new StuUpdate("1","1234-11-12 11:11:11");
	}

	@Override
	public void setComp() {
		// TODO Auto-generated method stub
		code = new JLabel("회 원 코 드 : ");
		name = new JLabel("회 원 명 : ");
		phone = new JLabel("휴 대 폰 : ");
		add = new JLabel("주 소 : ");
		date = new JLabel("등 록 일 : ");
		
		jtfCode1 = new JTextField(23);
		jtfCode2 = new JTextField(23);
		jtfCode3 = new JTextField(23);
		jtfCode4 = new JTextField(23);
		jtfCode5 = new JTextField(23);
		
		jtfCode1.setEditable(false);
		jtfCode5.setEditable(false);
		
		try {
			DBManager dbm = new DBManager();
			rs = dbm.setPreRs("select * from `member` where memberCode = ?", code2);
			if (rs.next()) {
				jtfCode2.setText(rs.getString(2));
				jtfCode3.setText(rs.getString(3));
				jtfCode4.setText(rs.getString(4) + ".0");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		addButton = new JButton("수정");
		exitButton = new JButton("닫기");
		
		southFlow = new JPanel();
		
		gridPn = new JPanel(new GridLayout(6, 1));
		
		centerPn = new JPanel(new BorderLayout());
		
		basePanel = new BasePanel(centerPn, 0, 5, 0, 0);
	}

	@Override
	public void setDesign() {
		gridPn.add(code);
		gridPn.add(jtfCode1);
		gridPn.add(name);
		gridPn.add(jtfCode2);
		gridPn.add(phone);
		gridPn.add(jtfCode3);
		
		gridPn.add(add);
		gridPn.add(jtfCode4);
		gridPn.add(date);
		gridPn.add(jtfCode5);
		
		southFlow.add(addButton);
		southFlow.add(exitButton);
		
		centerPn.add(gridPn);
		centerPn.add(southFlow, BorderLayout.SOUTH);
		
		super.add(basePanel);
	}

	@Override
	public void setAction() {
		// TODO Auto-generated method stub
		addButton.addActionListener(e -> {
			try {
				DBManager dbm = new DBManager();
				dbm.setPreUpdate("update `member` set memberName = ?, phone = ?, address = ? where memberCode = ?", jtfCode2.getText(), jtfCode3.getText(), jtfCode4.getText(), jtfCode1.getText());
				Message.infoMessage(jtfCode2.getText()+"님의 회원정보수정이 완료되엇습니다.", "메시지");
				dispose();
				new StuManager();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});
		exitButton.addActionListener(e -> {
			dispose();
			new StuManager();
		});
	}

}
