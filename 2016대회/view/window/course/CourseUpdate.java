package window.course;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import base.comp.BaseFrame;
import base.comp.BasePanel;
import jdbc.DBManager;
import message.Message;

public class CourseUpdate extends BaseFrame {

	private JLabel code;
	private JLabel name;
	private JLabel phone;
	private JLabel add;
	private JLabel date;
	private JTextField jtfCode1;
	private JTextField jtfCode2;
	private JTextField jtfCode3;
	private JTextField jtfCode4;
	private ResultSet rs;
	private JButton addButton;
	private JButton exitButton;
	private JPanel southFlow;
	private JPanel gridPn;
	private JPanel centerPn;
	private BasePanel basePanel;
	String courseName;
	private Object memory;

	public CourseUpdate(String courseName) {
		this.courseName = courseName;
		super.setFrame(250, 250, "강좌 정보 수정");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new CourseUpdate("빈야사");
	}

	@Override
	public void setComp() {
		// TODO Auto-generated method stub
		code = new JLabel("강 좌 명 : ");
		name = new JLabel("강 사 명 : ");
		phone = new JLabel("수 강 료 : ");
		
		memory = null;
		
		
		jtfCode1 = new JTextField(23);
		jtfCode2 = new JTextField(23);
		jtfCode3 = new JTextField(23);

		try {
			DBManager dbm = new DBManager();
			rs = dbm.setPreRs("select * from course where CourseName = ?", courseName);
			if (rs.next()) {
				jtfCode1.setText(rs.getString(1));
				jtfCode2.setText(rs.getString(2));
				jtfCode3.setText(rs.getString(3));
				memory = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		addButton = new JButton("수정");
		exitButton = new JButton("닫기");

		southFlow = new JPanel();

		gridPn = new JPanel(new GridLayout(3, 1));

		centerPn = new JPanel(new BorderLayout());

		basePanel = new BasePanel(centerPn, 0, 5, 0, 0);
	}

	@Override
	public void setDesign() {
		// TODO Auto-generated method stub
		gridPn.add(code);
		gridPn.add(jtfCode1);
		gridPn.add(name);
		gridPn.add(jtfCode2);
		gridPn.add(phone);
		gridPn.add(jtfCode3);

		southFlow.add(addButton);
		southFlow.add(exitButton);

		centerPn.add(gridPn, BorderLayout.CENTER);
		centerPn.add(southFlow, BorderLayout.SOUTH);

		super.add(basePanel);
	}

	@Override
	public void setAction() {
		// TODO Auto-generated method stub
		addButton.addActionListener(e -> {
			try {
				DBManager dbm = new DBManager();
				dbm.setPreUpdate("update course set CourseName = ?, TeachName = ?, price = ? where CourseName = ?",
						jtfCode1.getText(), jtfCode2.getText(), jtfCode3.getText(), memory.toString());
				Message.infoMessage(memory.toString()  +"강좌정보수정이 완료되었습니다.", "메시지");
				dispose();
				new CourseManager();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});
		exitButton.addActionListener(e -> {
			dispose();
			new CourseManager();
		});
	}

}
