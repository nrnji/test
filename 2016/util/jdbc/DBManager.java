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
		con = DriverManager.getConnection("jdbc:mysql://localhost/?characterEncoding=utf-8&serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false&allowLoadLocalInfile=true", "root", "1234");
		stmt = con.createStatement();
		stmt.execute("SET GLOBAL local_infile = 1");
	}
	
	public void setUpdate(String sql) throws SQLException {
		stmt.execute("use institute");
		stmt.executeUpdate(sql);
	}
	
	public ResultSet getRs(String sql) throws SQLException {
		stmt.execute("use institute");
		rs = stmt.executeQuery(sql);
		return rs;
	}
	
	public void getPreUpdate(String sql, String...values) throws SQLException {
		stmt.execute("use institute");
		pstmt = con.prepareStatement(sql);
		for (int i = 0; i < values.length; i++) {
			pstmt.setString(i+1, values[i]);
		}
		pstmt.executeUpdate();
	}
	
	public ResultSet getPreRs(String sql, String...values) throws SQLException {
		stmt.execute("use institute");
		pstmt = con.prepareStatement(sql);
		for (int i = 0; i < values.length; i++) {
			pstmt.setString(i+1, values[i]);
		}
		rs = pstmt.executeQuery();
		return rs;
	}
	
}
