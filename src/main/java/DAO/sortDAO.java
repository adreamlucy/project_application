package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Instanceclass.projectsort;
import util.dbcputil;

public class sortDAO {

public static List<projectsort> query(){
	List<projectsort> list = new ArrayList<projectsort>();
	Connection conn = dbcputil.getConnection();
	String sql = "select * from projectsort";
	try {
		Statement state = conn.createStatement();
		ResultSet res =  state.executeQuery(sql);
		while(res.next()) {
			projectsort sort = new projectsort();
			sort.setSort_id(res.getInt("sort_id"));
			sort.setSort_name(res.getString("sort_name"));
			list.add(sort);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return list;
 }
public static int update(int sort_id, String sort_name) {
	Connection conn= dbcputil.getConnection();
	String sql = "update projectsort set sort_name = '"+sort_name+"' where sort_id = "+sort_id;
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
public static int del(int sort_id) {
	Connection conn = dbcputil.getConnection();
	String sql  = "delete from projectsort where sort_id = "+sort_id;
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
public static int add(String sort_name, int sort_id) {
	Connection conn = dbcputil.getConnection();
	String sql = "insert into projectsort (sort_name, sort_id) value ('"+sort_name+"','"+sort_id+"')";
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
