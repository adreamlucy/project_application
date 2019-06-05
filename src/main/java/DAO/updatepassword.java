package DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import util.dbcputil;

public class updatepassword {

 public static int  update(String role_type, String user_sno, String newpassword) {
	 Connection  conn = dbcputil.getConnection();
	 String sql = "";
	 if(Integer.parseInt(role_type) == 0) {
		 sql = "update user_login set user_password = '"+newpassword+"' where user_sno = '"+user_sno+"'";
	 }else  if(Integer.parseInt(role_type) == 1){
		 sql = "update pro_login set pro_password = '"+newpassword+"' where pro_sno = '"+user_sno+"'";
	 }else if(Integer.parseInt(role_type) == 2) {
		 sql = "update admin_login set admin_password = '"+newpassword+"' where admin_sno = '"+user_sno+"'";
	 }
	 try {
//		 System.out.println(sql);
		Statement state = conn.createStatement();
		int n = state.executeUpdate(sql);
//		System.out.println(n);
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
