package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Instanceclass.proLogin;
import util.dbcputil;

public class prorolelogin {

	public static List<proLogin> queryproLoginAccount() throws SQLException{
		Connection conn = dbcputil.getConnection();
		Statement state = conn.createStatement();
		String sql = "select * from pro_login";
		ResultSet res = state.executeQuery(sql);
		List<proLogin> list = new ArrayList<proLogin>();
		while(res.next()) {
			proLogin use = new proLogin();
			use.setPro_id(res.getInt("pro_id"));
			use.setPro_sno(res.getString("pro_sno"));
			use.setPro_password(res.getString("pro_password"));
			list.add(use);
		}
		dbcputil.close(conn);
		return list;
	}
	public static int addLoginAccount(String pro_sno, String pro_password) throws SQLException {
		Connection conn = dbcputil.getConnection();
		int n = -1;
		String sql = "insert into pro_login(pro_sno, pro_password) value ('" + pro_sno+ "' ," + " '" + pro_password + "')";
		try {
			System.out.println(sql);
			Statement state = conn.createStatement();
		    n = state.executeUpdate(sql);
			if(n > 0) {
				return 0;
			}else {
				return -1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			dbcputil.close(conn);
		}
		return -1;
	}
	public static int updateLoginAccount(int pro_id, String pro_sno, String pro_password) {
		Connection conn = dbcputil.getConnection();
		int n = -1;
		String sql = "update  pro_login set pro_sno = '" + pro_sno + "' , pro_password  =  '" + pro_password + "'  where pro_id = " + pro_id;
		try {
			Statement state = conn.createStatement();
			 n = state.executeUpdate(sql);
			if(n > 0) {
				return 0;
			}else {
				return -1;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			dbcputil.close(conn);
		}
		return -1;
	}
	public static int delLoginAccount(int pro_id) {
		Connection conn = dbcputil.getConnection();
		int n = -1;
		String sql = "delete from pro_login where pro_id =" + pro_id;
		
		try {
			Statement state = conn.createStatement();
			n = state.executeUpdate(sql);
			if(n > 0) {
				return 0;
			}else {
				return -1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			dbcputil.close(conn);
		}
		return -1;
	}
	public static int queryOne(String pro_sno) throws SQLException {
		int n = 0;
		Connection conn = dbcputil.getConnection();
		String sql = "select * from pro_login where pro_sno = '" + pro_sno + "'" ;
		Statement state = conn.createStatement();
		ResultSet res = state.executeQuery(sql);
		while(res.next()) {
			n++;
		}
		if(n >= 0) {
			n = 0;
		}else {
			n = -1;
		}
		dbcputil.close(conn);
		return n;
	}
	public static int queryOne(String pro_sno, int pro_id) throws SQLException {
		int n = -1;
		Connection conn = dbcputil.getConnection();
		String sql = "select pro_sno from pro_login where pro_id = " + pro_id ;
		Statement state = conn.createStatement();
		ResultSet res = state.executeQuery(sql);
	   
		dbcputil.close(conn);
		return n;
	}
}
