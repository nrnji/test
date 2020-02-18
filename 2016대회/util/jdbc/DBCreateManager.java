package jdbc;

import java.sql.SQLException;

import javax.swing.JOptionPane;

public class DBCreateManager {

	public DBCreateManager() {
		DBManager dbm;
		try {
			dbm = new DBManager();
			dbm.setUpdate("drop database if exists institute");
			dbm.setUpdate("CREATE SCHEMA `institute` DEFAULT CHARACTER SET utf8 ;");
			dbm.setUpdate("use institute");
			dbm.setUpdate("CREATE TABLE `institute`.`admin` (\r\n" + 
					"  `id` VARCHAR(20) NOT NULL,\r\n" + 
					"  `passwd` VARCHAR(20) NULL,\r\n" + 
					"  `name` VARCHAR(20) NOT NULL,\r\n" + 
					"  PRIMARY KEY (`id`));");
			dbm.setUpdate("CREATE TABLE `institute`.`course` (\r\n" + 
					"  `CourseName` VARCHAR(30) NOT NULL,\r\n" + 
					"  `TeachName` VARCHAR(20) NOT NULL,\r\n" + 
					"  `Price` INT NULL,\r\n" + 
					"  PRIMARY KEY (`CourseName`));");
			dbm.setUpdate("CREATE TABLE `institute`.`coursemanage` (\r\n" + 
					"  `RegisterCode` INT NOT NULL AUTO_INCREMENT,\r\n" + 
					"  `MemberCode` INT NULL,\r\n" + 
					"  `MemberName` VARCHAR(30) NULL,\r\n" + 
					"  `CourseName` VARCHAR(30) NULL,\r\n" + 
					"  `CoursePrice` INT NULL,\r\n" + 
					"  `Period` INT NULL,\r\n" + 
					"  `RegPrice` INT NULL,\r\n" + 
					"  `crregdate` DATE NULL,\r\n" + 
					"  PRIMARY KEY (`RegisterCode`));	");
			dbm.setUpdate("CREATE TABLE `institute`.`member` (\r\n" + 
					"  `MemberCode` INT NOT NULL AUTO_INCREMENT,\r\n" + 
					"  `MemberName` VARCHAR(30) NULL,\r\n" + 
					"  `Phone` VARCHAR(20) NULL,\r\n" + 
					"  `Address` VARCHAR(30) NULL,\r\n" + 
					"  `Regdate` TIMESTAMP NULL,\r\n" + 
					"  PRIMARY KEY (`MemberCode`));");
			dbm.setUpdate("load data local infile './util/file/admin.txt' into table admin ignore 1 lines");
			dbm.setUpdate("load data local infile './util/file/course.txt' into table course ignore 1 lines");
			dbm.setUpdate("load data local infile './util/file/crhistory.txt' into table coursemanage ignore 1 lines");
			dbm.setUpdate("load data local infile './util/file/member.txt' into table `member` ignore 1 lines");
			
			System.out.println("DB세팅");
			JOptionPane.showMessageDialog(null, "DB세팅이 완료되었습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

}
