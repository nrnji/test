package Setting;

import java.sql.*;

public class DBin {

	static Connection con = null;
	static Statement stmt = null;
	
	static void init()throws Exception{
		try{
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306","root","1234");
			stmt = con.createStatement();
			stmt.executeQuery("use Institute");
		}catch(Exception e){
			
		}
	}
	
	static boolean isDB(String DB) throws Exception{
		ResultSet rs = stmt.executeQuery("show databases like '%" + DB + "%';");
		return rs.next();
	}
	
	static void executeQuery(String sql) throws Exception{
		stmt.executeUpdate(sql);
	}
	
}
