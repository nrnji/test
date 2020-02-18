package Project;

import java.sql.*;
import java.util.ArrayList;

public class DBMgr {

	DBcon conn = new DBcon();
	
	public DBMgr(){
		
		conn = new DBcon();
	}
	
	public ArrayList<Bean> login(String id){
		
		ArrayList<Bean> list= new ArrayList<Bean>();
		Bean bean;
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String sql = "select * from admin where id='" + id + "';";
		
		try{
			con = conn.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				bean = new Bean();
				bean.setA_id(rs.getString(1));
				bean.setA_pw(rs.getString(2));
				bean.setA_name(rs.getString(3));
				list.add(bean);
			}
		}catch(Exception e){
			
		}
		
		return list;
	}
	
	public ArrayList<Bean> member(){
		
		ArrayList<Bean> list= new ArrayList<Bean>();
		Bean bean;
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String sql = "select * from member;";
		
		try{
			con = conn.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				bean = new Bean();
				bean.setM_mcode(rs.getInt(1));
				bean.setM_mname(rs.getString(2));
				bean.setM_phone(rs.getString(3));
				bean.setM_address(rs.getString(4));
				bean.setM_regdate(rs.getString(5));
				list.add(bean);
			}
		}catch(Exception e){
			
		}
		
		return list;
	}
	
	public ArrayList<Bean> memberSelect(int code){
		
		ArrayList<Bean> list= new ArrayList<Bean>();
		Bean bean;
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String sql = "select * from member where membercode = " + code + ";";
		
		try{
			con = conn.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				bean = new Bean();
				bean.setM_mcode(rs.getInt(1));
				bean.setM_mname(rs.getString(2));
				bean.setM_phone(rs.getString(3));
				bean.setM_address(rs.getString(4));
				bean.setM_regdate(rs.getString(5));
				list.add(bean);
			}
		}catch(Exception e){
			
		}
		
		return list;
	}
	
	
	public ArrayList<Bean> course(){
		
		ArrayList<Bean> list= new ArrayList<Bean>();
		Bean bean;
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String sql = "select * from course order by coursename asc;";
		
		try{
			con = conn.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				bean = new Bean();
				bean.setC_cname(rs.getString(1));
				bean.setC_tname(rs.getString(2));
				bean.setC_price(rs.getInt(3));
				list.add(bean);
			}
		}catch(Exception e){
			
		}
		
		return list;
	}
	
	public ArrayList<Bean> courseSelect(String name){
		
		ArrayList<Bean> list= new ArrayList<Bean>();
		Bean bean;
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String sql = "select * from course where coursename='" + name + "';";
		
		try{
			con = conn.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				bean = new Bean();
				bean.setC_cname(rs.getString(1));
				bean.setC_tname(rs.getString(2));
				bean.setC_price(rs.getInt(3));
				list.add(bean);
			}
		}catch(Exception e){
			
		}
		
		return list;
	}
	
	public ArrayList<Bean> coursemanage(){
		
		ArrayList<Bean> list= new ArrayList<Bean>();
		Bean bean;
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String sql = "select * from coursemanage order by registercode desc;";
		
		try{
			con = conn.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				bean = new Bean();
				bean.setCm_rcode(rs.getInt(1));
				bean.setCm_mcode(rs.getInt(2));
				bean.setCm_mname(rs.getString(3));
				bean.setCm_cname(rs.getString(4));
				bean.setCm_cprice(rs.getInt(5));
				bean.setCm_period(rs.getInt(6));
				bean.setCm_regprice(rs.getInt(7));
				bean.setCm_crregdate(rs.getString(8));
				list.add(bean);
			}
		}catch(Exception e){
			
		}
		
		return list;
	}
	
	public ArrayList<Bean> courseSearch(String course){
		
		ArrayList<Bean> list= new ArrayList<Bean>();
		Bean bean;
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String sql = "select * from coursemanage where coursename='" + course + "' order by registercode desc;";
		
		try{
			con = conn.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				bean = new Bean();
				bean.setCm_rcode(rs.getInt(1));
				bean.setCm_mcode(rs.getInt(2));
				bean.setCm_mname(rs.getString(3));
				bean.setCm_cname(rs.getString(4));
				bean.setCm_cprice(rs.getInt(5));
				bean.setCm_period(rs.getInt(6));
				bean.setCm_regprice(rs.getInt(7));
				bean.setCm_crregdate(rs.getString(8));
				list.add(bean);
			}
		}catch(Exception e){
			
		}
		
		return list;
	}
	
	
	
	public ArrayList<Bean> courseSearch(String cname, String tname, int price){
		
		ArrayList<Bean> list= new ArrayList<Bean>();
		Bean bean;
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String sql = "select * from course where coursename like '%" + cname + "%' and teachname like '%" + tname + "%' and price=" + price + ";";
		
		System.out.println(sql);
		
		try{
			con = conn.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				bean = new Bean();
				bean.setC_cname(rs.getString(1));
				bean.setC_tname(rs.getString(2));
				bean.setC_price(rs.getInt(3));
				list.add(bean);
			}
		}catch(Exception e){
			
		}
		
		return list;
	}
	
	
	public ArrayList<Bean> search(String name){
		
		ArrayList<Bean> list= new ArrayList<Bean>();
		Bean bean;
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String sql = "select * from member where membername like '%" + name + "%';";
		
		try{
			con = conn.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				bean = new Bean();
				bean.setM_mcode(rs.getInt(1));
				bean.setM_mname(rs.getString(2));
				bean.setM_phone(rs.getString(3));
				bean.setM_address(rs.getString(4));
				bean.setM_regdate(rs.getString(5));
				list.add(bean);
			}
		}catch(Exception e){
			
		}
		
		return list;
	}
	
	public ArrayList<Bean> MAdd(int code, String name, String phone, String address){
		
		ArrayList<Bean> list= new ArrayList<Bean>();
		Bean bean;
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String sql = "insert into member values(" + code + ",'" + name + "','" + phone + "','" + address + "',curtime())";
		
		System.out.println(sql);
		
		try{
			con = conn.getConnection();
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
			
		}catch(Exception e){
			
		}
		
		return list;
	}
	
	public ArrayList<Bean> Infoupdate(int code, String name, String phone, String address){
		
		ArrayList<Bean> list= new ArrayList<Bean>();
		Bean bean;
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String sql = "update member set  membername='" + name + "', phone='" +phone + "', address='" + address + "' where membercode=" + code + ";";
		
		System.out.println(sql);
		
		try{
			con = conn.getConnection();
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
			
		}catch(Exception e){
			
		}
		
		return list;
	}
	
	public ArrayList<Bean> CAdd(String cname, String tname, int price){
		
		ArrayList<Bean> list= new ArrayList<Bean>();
		Bean bean;
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String sql = "insert into course values('" + cname + "','" + tname + "'," + price + ");";
		
		System.out.println(sql);
		
		try{
			con = conn.getConnection();
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
			
		}catch(Exception e){
			
		}
		
		return list;
	}
	
	public ArrayList<Bean> Apply(int rcode, int mcode, String mname, String cname, int cprice, int period, double regprice, String date){
		
		ArrayList<Bean> list= new ArrayList<Bean>();
		Bean bean;
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String sql = "insert into coursemanage values(" + rcode + "," + mcode + ",'" + mname + "','" + cname + "'," + cprice + "," + period + "," + regprice + ",'" + date + "')";
		
		System.out.println(sql);
		
		try{
			con = conn.getConnection();
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
			
		}catch(Exception e){
			
		}
		
		return list;
	}
	
	
	public ArrayList<Bean> Del(int code){
		
		ArrayList<Bean> list= new ArrayList<Bean>();
		Bean bean;
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String sql = "delete from coursemanage where registercode=" + code + ";";
		
		System.out.println(sql);
		
		try{
			con = conn.getConnection();
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
			
		}catch(Exception e){
			
		}
		
		return list;
	}
	
	public ArrayList<Bean> CourseUpdate(String cname, String tname, int price, String pre){
		
		ArrayList<Bean> list= new ArrayList<Bean>();
		Bean bean;
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String sql = "update course set coursename='" + cname + "', teachname='" +tname + "', price=" + price + " where coursename='" + pre + "';"; 
		
		System.out.println(sql);
		
		try{
			con = conn.getConnection();
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
			
		}catch(Exception e){
			
		}
		
		return list;
	}
	
	
	public ArrayList<Bean> CDel(String name){
		
		ArrayList<Bean> list= new ArrayList<Bean>();
		Bean bean;
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String sql = "delete from course where coursename='" + name + "';";
		
		System.out.println(sql);
		
		try{
			con = conn.getConnection();
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
			
		}catch(Exception e){
			
		}
		
		return list;
	}
	
}
