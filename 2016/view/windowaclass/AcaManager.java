package windowaclass;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import base.comp.BaseFrame;
import base.comp.BasePanel;

public class AcaManager extends BaseFrame{

	private JButton exitButton;
	private JButton movieManagerButton;
	private JButton stuManagerButton;
	private JButton stuAddButton;
	private JButton movieAddManagerButton;
	private JPanel northPn;
	private JLabel imageLabel;
	private JPanel allPn;
	private BasePanel basePanel;

	@Override
	public void setComp() {
		stuAddButton = new JButton("회원 등록");
		stuManagerButton = new JButton("회원 관리");
		movieManagerButton = new JButton("강좌 관리");
		movieAddManagerButton = new JButton("수강 신청 및 관리");
		exitButton = new JButton("종료");
		
		northPn = new JPanel();
		
		imageLabel = new JLabel(new ImageIcon("./util/image/main.jpg"));
		
		allPn = new JPanel(new BorderLayout());
		
		basePanel = new BasePanel(allPn, 0, 5, 5, 5);
	}

	@Override
	public void setDesign() {
		northPn.add(stuAddButton);
		northPn.add(stuManagerButton);
		northPn.add(movieManagerButton);
		northPn.add(movieAddManagerButton);
		northPn.add(exitButton);
		
		allPn.add(northPn, BorderLayout.NORTH);
		allPn.add(imageLabel);
		
		super.add(basePanel);
	}

	@Override
	public void setAction() {
		exitButton.addActionListener((e) -> {
			dispose();
		});
	}
	
	public AcaManager() {
		super.setFrame(550, 480, "학원 관리");
	}
	
	public static void main(String[] args) {
		new AcaManager();
	}
}
