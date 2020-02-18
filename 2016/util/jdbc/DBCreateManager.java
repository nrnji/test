package jdbc;

import java.sql.SQLException;

public class DBCreateManager {
	public DBCreateManager() {
		
		try {
			DBManager dbmg = new DBManager();
			dbmg.setUpdate("drop database if exists institute");
			dbmg.setUpdate("CREATE SCHEMA `Institute` DEFAULT CHARACTER SET utf8 ;");
			
			dbmg.setUpdate("use institute");
			
			dbmg.setUpdate("CREATE TABLE `institute`.`admin` (\r\n" + 
					"  `id` VARCHAR(20) NOT NULL,\r\n" + 
					"  `Passwd` VARCHAR(20) NULL,\r\n" + 
					"  `Name` VARCHAR(20) NOT NULL,\r\n" + 
					"  PRIMARY KEY (`id`));");
			dbmg.setUpdate("CREATE TABLE `institute`.`course` (\r\n" + 
					"  `CourseName` VARCHAR(30) NOT NULL,\r\n" + 
					"  `TeachName` VARCHAR(20) NULL,\r\n" + 
					"  `Price` INT NULL,\r\n" + 
					"  PRIMARY KEY (`CourseName`));	");
			dbmg.setUpdate("CREATE TABLE `institute`.`coursemanager` (\r\n" + 
					"  `RegisterCode` INT NOT NULL AUTO_INCREMENT,\r\n" + 
					"  `MemberCode` INT NULL,\r\n" + 
					"  `MemeberName` VARCHAR(30) NULL,\r\n" + 
					"  `CourseName` VARCHAR(30) NULL,\r\n" + 
					"  `CoursePrice` INT NULL,\r\n" + 
					"  `Period` INT NULL,\r\n" + 
					"  `RegPrice` INT NULL,\r\n" + 
					"  `orregdate` DATE NULL,\r\n" + 
					"  PRIMARY KEY (`RegisterCode`));");
			dbmg.setUpdate("CREATE TABLE `institute`.`member` (\r\n" + 
					"  `membercode` INT NOT NULL AUTO_INCREMENT,\r\n" + 
					"  `memberName` VARCHAR(30) NULL,\r\n" + 
					"  `Phone` VARCHAR(20) NULL,\r\n" + 
					"  `address` VARCHAR(30) NULL,\r\n" + 
					"  `regdate` TIMESTAMP NULL,\r\n" + 
					"  PRIMARY KEY (`membercode`));");
			
			dbmg.setUpdate("load data local infile './util/file/admin.txt' into table admin ignore 1 lines");
			dbmg.setUpdate("load data local infile './util/file/course.txt' into table course ignore 1 lines");
			dbmg.setUpdate("load data local infile './util/file/crhistory.txt' into table coursemanager ignore 1 lines");
			dbmg.setUpdate("load data local infile './util/file/member.txt' into table `institute`.`member` ignore 1 lines");
			
			System.out.println("DB¼º°ø");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
}
