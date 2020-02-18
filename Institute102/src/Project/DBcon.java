package Project;

import java.sql.DriverManager;
import java.sql.*;

public class DBcon {

	Connection con = null;
	
	public DBcon(){
		
		try{
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/institute","root","1234");
			
		}catch(Exception e){
			
		}
	}
	
	public Connection getConnection(){
		return con;
	}
}
