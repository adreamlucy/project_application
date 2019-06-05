package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import util.dbcputil;

public class login {
	public static int login(int role_type, String user_sno, String user_password) {
		int n = 0;
		String sql = "";
		if(role_type == 0) {
			 sql = "select count(*) as count from user_login where user_sno = " 
		          + "'" + user_sno + "' and " +" user_password= " + "'" +user_password + "'";
		}else if(role_type == 1) {
			sql = "select count(*)  as count from pro_login where pro_sno = " 
			          + "'" + user_sno + "' and " +" pro_password= " + "'" +user_password + "'";
		}else if(role_type == 2) {
			sql = "select count(*)  as count from admin_login where admin_sno = " 
			          + "'" + user_sno + "' and " +" admin_password= " + "'" +user_password + "'";
		}
		try {
			System.out.println(sql);
			Connection conn= dbcputil.getConnection();
			Statement state = conn.createStatement();
			ResultSet res = state.executeQuery(sql);
			while(res.next()){
				n = res.getInt("count");
			}
			if(n > 0) {
				return 0;
			}else {
				return -1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}		
	}
}
