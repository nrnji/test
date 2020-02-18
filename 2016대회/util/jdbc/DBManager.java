package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager {

	private Connection con;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public DBManager() throws SQLException {
		con = DriverManager.getConnection("jdbc:mysql://localhost/?characterEncoding=UTF-8&serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false&allowLoadLocalInfile=true", "root", "1234");
		stmt = con.createStatement();
		stmt.execute("SET GLOBAL local_infile = 1");
	}
	
	public void setUpdate(String sql) throws SQLException {
		stmt.executeUpdate(sql);
	}
	
	public void setPreUpdate(String sql, String...value) throws SQLException {
		stmt.execute("use institute");
		pstmt = con.prepareStatement(sql);
		for (int i = 0; i < value.length; i++) {
			pstmt.setString(i+1, value[i]);
		}
		pstmt.executeUpdate();
	}
	
	public ResultSet setPreRs(String sql, String...value) throws SQLException {
		stmt.execute("use institute");
		pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		for (int i = 0; i < value.length; i++) {
			pstmt.setString(i+1, value[i]);
		}
		rs = pstmt.executeQuery();
		return rs;
	}
	

}
