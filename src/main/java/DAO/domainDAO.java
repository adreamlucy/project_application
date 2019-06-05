package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Instanceclass.domain;
import util.dbcputil;

public class domainDAO {
	public static String query(){
		List<domain> list = new ArrayList<domain>();
		Connection conn = dbcputil.getConnection();
		String sql = "select * from domain";
		String content = "";
		try {
			Statement state = conn.createStatement();
			ResultSet res =  state.executeQuery(sql);
			while(res.next()) {
				domain dom = new domain();
				dom.setDomain_id(res.getInt("domain_id"));
			    dom.setDomain_name(res.getString("domain_name"));
			    content+=res.getInt("domain_id")+","+res.getString("domain_name")+"&";
			}
			return content;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return content;
	 }
	public static int update(int domain_id, String domain_name) {
		Connection conn= dbcputil.getConnection();
		String sql = "update domain set domain_name = '"+domain_name+"' where domain_id = "+domain_id;
		try {
			Statement state = conn.createStatement();
			int n = state.executeUpdate(sql);
			if(n > 0) {
				return 0;
			}else {
				return -1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	 }
	public static int del(int domain_id) {
		Connection conn = dbcputil.getConnection();
		String sql  = "delete from domain where domain_id = "+domain_id;
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
			throw new RuntimeException();
		}
	}
	public static int add(String domain_name, int domain_id) {
		Connection conn = dbcputil.getConnection();
		String sql = "insert into domain (domain_name, domain_id) value ('"+domain_name+"','"+domain_id+"')";
		System.out.println(sql);
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
			throw new RuntimeException();
		}
		
	 }
}
