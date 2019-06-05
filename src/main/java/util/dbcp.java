package util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;

public class dbcp {
	static String driver = "";
	static String url = "";
	static String username = "";
	static String password = "";
	static Connection conn = null;
	static Statement state = null;
	static ResultSet res = null;
	public static void main(String[] args) {
		Properties cf =new Properties();
		InputStream in = dbcp.class.getClassLoader().getResourceAsStream("db.properties");
		try {
			cf.load(in);
			driver = cf.getProperty("jdbc.driver");
			url = cf.getProperty("jdbc.url");
			username = cf.getProperty("jdbc.username");
			password = cf.getProperty("jdbc.password");
			BasicDataSource bs = new BasicDataSource();
			bs.setDriverClassName(driver);
			bs.setUrl(url);
			bs.setUsername(username);
			bs.setPassword(password);
			bs.setInitialSize(5);
			bs.setMaxActive(20);
			bs.setMinIdle(5);
			conn = bs.getConnection();
			state = conn.createStatement();
			String sql =" select * from s_login";
			res = state.executeQuery(sql);
			while(res.next()) {
				String username = res.getString("s_unit");
				String password = res.getString("s_password");
				System.out.println(username  + "---" + password);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		
		
		
		
		
		
	}
}
