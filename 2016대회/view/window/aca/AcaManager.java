package window.aca;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import base.comp.BaseFrame;
import base.comp.BasePanel;
import window.application.ApplicaionManager;
import window.course.CourseManager;
import window.stu.StuAdd;
import window.stu.StuManager;

public class AcaManager extends BaseFrame {

	private JButton addStuButton;
	private JButton managerStuButton;
	private JButton managerCourseButton;
	private JButton addManagerCourseButton;
	private JButton exitButton;
	private JLabel imagela;
	private JPanel flow;
	private JPanel allPn;
	private BasePanel basePanel;

	public AcaManager() {
		super.setFrame(600, 500, "학원 관리");
	}

	public static void main(String[] args) {
		new AcaManager();
	}

	@Override
	public void setComp() {
		addStuButton = new JButton("회원 등록");
		managerStuButton = new JButton("회원 관리");
		managerCourseButton = new JButton("강좌 관리");
		addManagerCourseButton = new JButton("수강 신청 및 관리");
		exitButton = new JButton("종 료");

		imagela = new JLabel();
		imagela.setIcon(new ImageIcon("./util/image/main.jpg"));

		flow = new JPanel();

		allPn = new JPanel(new BorderLayout());

		basePanel = new BasePanel(allPn, 0, 5, 5, 5);
	}

	@Override
	public void setDesign() {
		flow.add(addStuButton);
		flow.add(managerStuButton);
		flow.add(managerCourseButton);
		flow.add(addManagerCourseButton);
		flow.add(exitButton);

		allPn.add(imagela);
		allPn.add(flow, BorderLayout.NORTH);

		super.add(basePanel);
	}

	@Override
	public void setAction() {
		addStuButton.addActionListener((e) -> {
			dispose();
			new StuAdd();
		});
		managerStuButton.addActionListener((e) -> {
			dispose();
			new StuManager();
		});
		managerCourseButton.addActionListener((e) -> {
			dispose();
			new CourseManager();
		});
		addManagerCourseButton.addActionListener((e) -> {
			dispose();
			new ApplicaionManager();
		});
		exitButton.addActionListener((e) -> {
			dispose();
			System.exit(0);
		});
	}
}
