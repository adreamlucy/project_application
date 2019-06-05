package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Instanceclass.userLogin;
import net.sf.json.JSONObject;
import util.dbcputil;

public class uselogin {

	public static List<userLogin> queryUserLoginAccount() throws SQLException{
		Connection conn = dbcputil.getConnection();
		Statement state = conn.createStatement();
		String sql = "select * from user_login";
		ResultSet res = state.executeQuery(sql);
		List<userLogin> list = new ArrayList<userLogin>();
		while(res.next()) {
			userLogin use = new userLogin();
			use.setUser_id(res.getInt("user_id"));
			use.setUser_sno(res.getString("user_sno"));
			use.setUser_password(res.getString("user_password"));
			list.add(use);
		}
		dbcputil.close(conn);
		return list;
	}
	public static int addLoginAccount(String user_sno, String user_password) throws SQLException {
		Connection conn = dbcputil.getConnection();
		String sql = "insert into user_login(user_sno, user_password) value ( '" + user_sno +"' , '" + user_password  +"' )";
		Statement state = conn.createStatement();
		int n = state.executeUpdate(sql);
		dbcputil.close(conn);
		if(n > 0) {
			return 0;
		}else {
			return -2;
		}
		
	}
	public static int updateLoginAccount(int user_id, String user_sno, String user_password) {
		Connection conn = dbcputil.getConnection();
		String sql = "update  user_login set user_sno = '" + user_sno +"' , user_password = '" + user_password +  "'  where user_id = " +user_id;
		try {
			Statement state = conn.createStatement();
			int n = state.executeUpdate(sql);
			dbcputil.close(conn);
			if(n > 0) {
				return 0;
			}else {
				return -1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	public static int delLoginAccount(int user_id) {
		Connection conn = dbcputil.getConnection();
		String sql = "delete from user_login where user_id = " + user_id;
		try {
			Statement state = conn.createStatement();
			int n = state.executeUpdate(sql);
			if(n > 0) {
				return 0;
			}else {
				return -1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}finally {
			dbcputil.close(conn);
		}
		
	}
	public static int queryOne(String user_sno) throws SQLException {
		int n = 0;
		Connection conn = dbcputil.getConnection();
		String sql = "select * from user_login where user_sno = '" +   user_sno + "'";
		Statement state = conn.createStatement();
		ResultSet res = state.executeQuery(sql);
		while(res.next()) {
			n++;
		}
		dbcputil.close(conn);
		if (n > 0){
			return 1;
		}else {
			return -1;
		}
	}
}
