package util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;

public class dbcputil {
	static String driver = "";
	static String url = "";
	static String username = "";
	static String password = "";
	static Connection conn = null;
	static int initsize;
	static int maxActive;
	static BasicDataSource bs = null;
	static{
		Properties cf =new Properties();
		InputStream in = dbcp.class.getClassLoader().getResourceAsStream("db.properties");
		try {
			cf.load(in);
			driver = cf.getProperty("jdbc.driver");
			url = cf.getProperty("jdbc.url");
			username = cf.getProperty("jdbc.username");
			password = cf.getProperty("jdbc.password");
			initsize = Integer.parseInt(cf.getProperty("initSize"));
			maxActive = Integer.parseInt(cf.getProperty("maxActive"));
			bs = new BasicDataSource();
			bs.setDriverClassName(driver);
			bs.setUrl(url);
			bs.setUsername(username);
			bs.setPassword(password);
			bs.setInitialSize(initsize);
			bs.setMaxActive(maxActive);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	public static Connection getConnection(){
		Connection conn = null;
		try {
			conn = bs.getConnection();
			return conn;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}	
	}
	public static void close(Connection conn) {
		try {
			if(conn!= null) {
				conn.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
//	public static void main(String[] args) {
//		Connection conn = getConnection();
//		System.out.print(conn);
//	}
}
