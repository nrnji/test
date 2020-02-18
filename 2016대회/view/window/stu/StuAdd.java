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
		super.setFrame(350, 250, "회원 등록");
	}

	public static void main(String[] args) {
		new StuAdd();
	}

	@Override
	public void setComp() {
		code = new JLabel("회 원 코 드 : ");
		name = new JLabel("*회 원 명 : ");
		phone = new JLabel("휴 대 폰 : ");
		add = new JLabel("주 소 : ");
		date = new JLabel("등 록 일 : ");
		Time = new JLabel(new SimpleDateFormat("yyyy년 M월 dd일").format(new Date()));
		Time.setHorizontalAlignment(JLabel.RIGHT);
		
		jtfCode1 = new JTextField(23);
		jtfCode2 = new JTextField(23);
		jtfCode3 = new JTextField(23);
		jtfCode4 = new JTextField(23);
		Time.setPreferredSize(new Dimension(234, 22));
		jtfCode1.setEditable(false);
		
		addButton = new JButton("추가");
		exitButton = new JButton("닫기");
		
		southFlow = new JPanel();
		
		gridPn = new JPanel(new GridLayout(5, 1));
		flow = new JPanel[5];
		for (int i = 0; i < flow.length ; i++) {
			flow[i] = new JPanel(new FlowLayout(2));
		}
		
		centerPn = new JPanel(new BorderLayout());
		basePanel = new BasePanel(centerPn, "회 원 등 록", Font.BOLD, 11, 5, 5, 5, 5);
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
			Message.errMessage("필수항목(*)을 모두 입력하세요", "회원등록 에러");
			return;
		}
		DBManager dbm;
		try {
			dbm = new DBManager();
			dbm.setPreUpdate("insert into `member` values(0,?,?,?,?)", jtfCode2.getText(), jtfCode3.getText(), jtfCode4.getText(), new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
			Message.infoMessage(jtfCode2.getText() +"회원이 등록되었습니다." , "메시지");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
