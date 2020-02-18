package windowaclass;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import base.comp.BaseFrame;
import base.comp.BasePanel;
import jdbc.DBManager;
import message.MessageManager;

public class Login extends BaseFrame {

	private JLabel title;
	private JLabel id;
	private JLabel pw;
	private JTextField jtfId;
	private JButton exitButton;
	private JButton loginButton;
	private JPasswordField jtfPw;
	private JPanel centerPn;
	private BasePanel basePanel;
	private JPanel backgroundPn;
	private ImageIcon icon;
//	private JLabel s;
	private ResultSet rs;

	@Override
	public void setComp() {
		icon = new ImageIcon("./util/image/login.jpg");
		backgroundPn = new JPanel(new BorderLayout()) {
			@Override
			protected void paintComponent(Graphics g) {
				g.drawImage(icon.getImage(),0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		
	
		
		title = new JLabel("관리자 로그인");
		id = new JLabel("ID : ");
		pw = new JLabel("PASSWORD : ");
		id.setHorizontalAlignment(JLabel.RIGHT);
		pw.setHorizontalAlignment(JLabel.RIGHT);
		
		jtfId = new JTextField(20);
		jtfPw = new JPasswordField(20);
		
		loginButton = new JButton("로그인");
		exitButton = new JButton("종료");
		
		centerPn = new JPanel(new GridLayout(3, 2, 3, 3));
		centerPn.setOpaque(false);
		basePanel = new BasePanel(centerPn, "관리자 로그인", "굴림", Font.BOLD, 15, 10, 30, 30, 30);
		
	}

	@Override
	public void setDesign() {
		
		centerPn.add(id);
		centerPn.add(jtfId);
		centerPn.add(pw);
		centerPn.add(jtfPw);
		centerPn.add(loginButton);
		centerPn.add(exitButton);
		
		backgroundPn.add(basePanel);
		basePanel.setOpaque(false);
		
		super.add(backgroundPn);
	}

	@Override
	public void setAction() {
		exitButton.addActionListener((e) -> {
			dispose();
		});
		loginButton.addActionListener(this::getCheckLogin);
	}
	
	public void getCheckLogin(ActionEvent e) {
		try {
			if (jtfId.getText().trim().equals("") || jtfPw.getText().trim().equals("")) {
				MessageManager.errMessage("아이디와 비밀번호가 공백입니다.");
				return;
			}
			DBManager dbmg = new DBManager();
			rs = dbmg.getPreRs("select * from admin where id = ? and passwd = ?", jtfId.getText().trim(), jtfPw.getText().trim());
			if (!rs.next()) {
				MessageManager.errMessage("아이디와 비밀번호를 확인해주세요.");
				return;
			}
			MessageManager.infoMessage("로그인이 완료되었습니다.");
			dispose();
			new AcaManager();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	
	public Login() {
		setFrame(350, 250, "로그인");
	}
	
	public static void main(String[] args) {
		new Login();
	}

}
