package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class dbcputiltest {
 public static void main(String[] args) {
	Connection conn = dbutil.getConnection();
	String sql = "insert into user_login(user_username, user_password) value (?, ?)";
	
	try {
		PreparedStatement prestate = conn.prepareStatement(sql);
		prestate.setString(1, "aaaaa");
		prestate.setString(2, "bbb");
		int n = prestate.executeUpdate();
		System.out.println(n);
//		Statement state = conn.createStatement();
//		ResultSet res = state.executeQuery("select * from s_login");
//		ResultSetMetaData meta = res.getMetaData();
//		System.out.println(meta.getColumnName(2));
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
