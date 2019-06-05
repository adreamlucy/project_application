package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class jdbc {
  Connection conn = null;
  static Statement state = null;
  static ResultSet res = null;
  public static void main(String[] args) throws ClassNotFoundException, SQLException {
    Connection conn = util.dbutil.getConnection();
    state = conn.createStatement();
    String sql = "select * from s_login";
    res = state.executeQuery(sql); 
    while(res.next()){
    	String username = res.getString("s_unit");
    	String password = res.getString("s_password");
    	System.out.println(username + "--" + password);
    }
    
    
  }
}
