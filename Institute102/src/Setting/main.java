package Setting;

public class main {

	public static void main(String[] args) throws Exception{
		
		DBin.init();
		
		if(DBin.isDB("Institute")){
			DBin.executeQuery("drop database institute");
		}
		
		DBin.executeQuery("create database institute");
		
		DBin.init();
		
		DBin.executeQuery("create table admin(Id varchar(20), Passwd varchar(20), Name varchar(20))");
		String dp = System.getProperty("user.dir") + "\\DataFiles\\admin.txt";
		dp = dp.replace("\\", "/");
		DBin.executeQuery("load data local infile '" + dp + "' into table admin columns terminated by '\t' lines terminated by '\r\n' (id,passwd,name)");
		
		DBin.executeQuery("create table course(CourseName varchar(30), TeachName varchar(20), Price int)");
		String dp1 = System.getProperty("user.dir") + "\\DataFiles\\course.txt";
		dp1 = dp1.replace("\\", "/");
		DBin.executeQuery("load data local infile '" + dp1 + "' into table course columns terminated by '\t' lines terminated by '\r\n' (coursename, teachname, price)");
		
		DBin.executeQuery("create table coursemanage(RegisterCode int, Membercode int, MemberName varchar(30), CourseName varchar(30), CoursePrice int, Period int, RegPrice int, crregdate date)");
		String dp2 = System.getProperty("user.dir") + "\\DataFiles\\crhistory.txt";
		dp2 = dp2.replace("\\", "/");
		DBin.executeQuery("load data local infile '" + dp2 + "' into table coursemanage columns terminated by '\t' lines terminated by '\r\n' (registercode, membercode, membername, coursename, courseprice, period, regprice, crregdate)");

		DBin.executeQuery("create table member(Membercode int, MemberName varchar(30), Phone varchar(20), Address varchar(30), RegDate timestamp)");
		String dp3 = System.getProperty("user.dir") + "\\DataFiles\\member.txt";
		dp3 = dp3.replace("\\", "/");
		DBin.executeQuery("load data local infile '" + dp3 + "' into table member columns terminated by '\t' lines terminated by '\r\n' (Membercode,MemberName,phone,address,regdate)");
		
	
		DBin.executeQuery("delete from admin where passwd='비밀번호';");
		DBin.executeQuery("delete from course where teachname='강사명';");
		DBin.executeQuery("delete from coursemanage where membername='회원명';");
		DBin.executeQuery("delete from member where membername='회원명';");
		
		
	}

}
