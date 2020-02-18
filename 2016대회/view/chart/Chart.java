package chart;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import base.comp.BaseFrame;
import jdbc.DBManager;
import window.application.ApplicaionManager;

public class Chart extends BaseFrame {

	private JPanel buttonbor;
	private JButton exitButton;
	private ResultSet rs;

	public Chart() {
		setFrame(500, 500, "수강현황차트");
	}

	public static void main(String[] args) {
		new Chart();
	}

	@Override
	public void setComp() {
		// TODO Auto-generated method stub
		exitButton = new JButton("닫기");

		buttonbor = new JPanel(new BorderLayout());
		buttonbor.setBorder(new EmptyBorder(5, 0, 0, 5));

	}

	@Override
	public void setDesign() {
		buttonbor.add(exitButton, BorderLayout.NORTH);
		buttonbor.add(new JLabel(" "));
		buttonbor.add(new JLabel(" "), BorderLayout.SOUTH);

		super.add(buttonbor, BorderLayout.EAST);
	}

	@Override
	public void setAction() {
		exitButton.addActionListener(e -> {
			dispose();
			new ApplicaionManager();
		});
	}

	@Override
	public void paint(Graphics g) {
		ArrayList<Integer> array = new ArrayList<Integer>();
		try {
			DBManager dbm = new DBManager();
			rs = dbm.setPreRs("select count(*) from courseManage");
			rs.next();
			double a = rs.getInt(1);
			rs = dbm.setPreRs("select count(*) from courseManage group by Coursename");
			while (rs.next()) {
				double test2 = Math.floor(rs.getInt(1) * 100 / a * 3.6);
				array.add((int) test2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		int wid = 300;
		int he = 300;

		int test = 90;

		Color colors[] = { Color.red, Color.orange, Color.yellow, Color.green, Color.blue, Color.MAGENTA, Color.pink };

		System.out.println(array.size());
		
		
		
		for (int j = 0; j < array.size(); j++) {
			g.setColor(colors[j]);
			System.out.println(test + " " + (test - array.get(j)) + colors[j].toString());
			g.fillArc(50, 100, wid, he, test, -array.get(j));
			test = test - array.get(j);
		}

//		test += array.get(i);
//		
//		g.setColor(Color.red);
//		g.fillArc(50, 100, wid, he, 90, -array.get(0));
//		g.setColor(Color.orange);
//		g.fillArc(50, 100, wid, he, 90-array.get(0),-+array.get(1));
//		g.setColor(Color.yellow);
//		g.setColor(Color.green);
//		g.setColor(Color.blue);
//		g.setColor(Color.MAGENTA);
//		g.setColor(Color.pink);

	}

}
