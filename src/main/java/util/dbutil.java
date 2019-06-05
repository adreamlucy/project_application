package util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class dbutil {
 static String driver= "";
 static String url="";
 static String username="";
 static String password="";
 
 static {
	 Properties cf = new Properties();
	 InputStream in = dbutil.class.getClassLoader().getResourceAsStream("db.properties");
	 try {
		cf.load(in);
		driver = cf.getProperty("jdbc.driver");
		url = cf.getProperty("jdbc.url");
		username = cf.getProperty("jdbc.username");
		password = cf.getProperty("jdbc.password");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
//	    throw new RuntimeException(e);
	}
 }
 
 public static Connection getConnection (){
   Connection conn = null;
   try {
	Class.forName(driver);
	conn = DriverManager.getConnection(url, username, password);
	} catch (Exception e) {
		e.printStackTrace();
		throw new RuntimeException(e);
	}
	 return conn; 
  }
public static void close(Connection conn) {
	try {
		if(conn!=null) {
			conn.close();
		}
	} catch (SQLException e) {
		e.printStackTrace();
		System.out.println(e);
	}
 }
//public static void main(String[] args) {
//	Connection conn = getConnection();
//	System.out.println(conn);
//	
//}
}

