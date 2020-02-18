package window.login;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import base.comp.BaseFrame;
import base.comp.BasePanel;
import jdbc.DBManager;
import message.Message;

public class Login extends BaseFrame{

	private JLabel id;
	private JLabel Pass;
	private JTextField jtfId;
	private JTextField jtfPw;
	private JButton loginButton;
	private JButton eixtButton;
	private JPanel centerPn;
	private BasePanel basePanel;
	private ResultSet rs;
	private JPanel backgroundPn;

	public Login() {
		super.setFrame(350, 250, "�α���");
	}

	public static void main(String[] args) {
		new Login();
	}

	@Override
	public void setComp() {
		
		backgroundPn = new JPanel(new BorderLayout()) {
			private ImageIcon icon;

			@Override
			protected void paintComponent(Graphics g) {
				icon = new ImageIcon("./util/image/login.jpg");
				g.drawImage(icon.getImage(), 0, 0, null);
				backgroundPn.setOpaque(false);
				super.paintComponent(g);
			}
		};
		
		id = new JLabel("ID : ");
		Pass = new JLabel("PASSWORD : ");
		id.setHorizontalAlignment(JLabel.RIGHT);
		Pass.setHorizontalAlignment(JLabel.RIGHT);
		
		jtfId = new JTextField();
		jtfPw = new JTextField();
		
		loginButton = new JButton("�α���");
		eixtButton = new JButton("����");
		
		centerPn = new JPanel(new GridLayout(3, 2, 5, 5));
		
		basePanel = new BasePanel(centerPn, "������ �α���", 15, 50, 15, 50);
		
		centerPn.setOpaque(false);
		basePanel.setOpaque(false);
	}

	@Override
	public void setDesign() {
		centerPn.add(id);
		centerPn.add(jtfId);
		centerPn.add(Pass);
		centerPn.add(jtfPw);
		centerPn.add(loginButton);
		centerPn.add(eixtButton);
		
		backgroundPn.add(basePanel);
		
		super.add(backgroundPn);
	}

	@Override
	public void setAction() {
		loginButton.addActionListener((e) -> {
			loginCheck();
		});
		eixtButton.addActionListener((e) -> {
			dispose();
			System.exit(0);
		});
	}
	
	private void loginCheck() {
		// TODO Auto-generated method stub
		if (jtfId.getText().isBlank() || jtfPw.getText().isBlank()) {
			Message.errMessage("���̵� ��й�ȣ�� �����Դϴ�.", "Message");
			return;
		}
		try {
			DBManager dbm = new DBManager();
			rs = dbm.setPreRs("select * from admin where id = ? and passwd = ?", jtfId.getText(), jtfPw.getText());
			if (!(rs.next())) {
				Message.errMessage("���̵�� ��й�ȣ�� Ȯ�����ּ���.", "Message");
				return;
			}
			Message.infoMessage("�α����� �Ϸ�Ǿ����ϴ�.", "Message");
			dispose();
//			new AcaManager();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

}
