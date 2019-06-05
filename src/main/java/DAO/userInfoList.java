package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Instanceclass.*;
import util.dbcputil;

public class userInfoList {
	
	public static List<userInfo> queryUserLoginAccount(String user_username, String user_sno, String pageNum, String pageSize) throws SQLException{
		Connection conn = dbcputil.getConnection();
		String sql = "";
		sql = "select * from user_info";
		Statement state = conn.createStatement();
		if(user_username!= null && user_username!= "") {
			sql = "select * from user_info  where user_username = " + user_username;
		}
		if(user_sno!= null && user_sno!= "") {
			sql = "select * from user_info  where user_sno = " + user_sno;
		}
		if((user_sno!= null && user_sno!= "")&&(user_username!= null && user_username!= "")) {
			sql = "select * from user_info  where user_username = " + user_username + " and user_sno =" + user_sno;
		}
//		sql += " limit " + (Integer.parseInt(pageNum)-1)*Integer.parseInt(pageSize) + "," + pageSize;
		System.out.println(sql);
		ResultSet res = state.executeQuery(sql);
		List<userInfo> list = new ArrayList<userInfo>();
		while(res.next()) {
			userInfo use = new userInfo();
			use.setUser_id(res.getInt("user_id"));
			use.setUser_username(res.getString("user_username"));
			use.setUser_sno(res.getString("user_sno"));
			use.setUser_university(res.getString("user_university"));
			use.setUser_college(res.getString("user_college"));
			use.setUser_pro(res.getString("user_pro"));
			use.setUser_address(res.getString("user_address"));
			use.setUser_borndata(res.getString("user_borndata"));;
			use.setUser_phone(res.getString("user_phone"));
			use.setUser_email(res.getString("user_email"));
			use.setUser_sort(res.getString("user_sort"));
			list.add(use);
		}
		dbcputil.close(conn);
		return list;
	}
	public static List<userInfo> queryUser( int type, String user_sno) throws SQLException{
		Connection conn = dbcputil.getConnection();
		String sql = "";
		if(type == 0) {
			sql = "select * from user_info where user_sno = '" + user_sno + "'";
		}else if(type == 1) {
			sql = "select * from pro_info where pro_sno = '" + user_sno + "'";
		}
		Statement state = conn.createStatement();
		ResultSet res = state.executeQuery(sql);
		List<userInfo> list = new ArrayList<userInfo>();
		while(res.next()) {
			userInfo use = new userInfo();
			use.setUser_id(res.getInt("user_id"));
			use.setUser_username(res.getString("user_username"));
			use.setUser_sno(res.getString("user_sno"));
			use.setUser_university(res.getString("user_university"));
			use.setUser_college(res.getString("user_college"));
			use.setUser_pro(res.getString("user_pro"));
			use.setUser_address(res.getString("user_address"));
			use.setUser_borndata(res.getString("user_borndata"));;
			use.setUser_phone(res.getString("user_phone"));
			use.setUser_email(res.getString("user_email"));
			use.setUser_sort(res.getString("user_sort"));
			use.setUser_sex(res.getInt("user_sex"));
			list.add(use);
		}
		dbcputil.close(conn);
		return list;
	}
	public static int updateUserInfo(userInfo use, int user_id) {
		Connection conn = dbcputil.getConnection();
		String sql = "update user_info set user_address = '" + use.user_address
				                     + "' , user_borndata = '" + use.user_borndata
				                     + "' , user_college = '" + use.user_college
				                     + "' , user_email = '" + use.user_email
				                     + "' , user_phone = '" + use.user_phone
				                     + "' , user_pro = '" + use.user_pro
				                     + "' , user_university = '" + use.user_university
				                     + "' , user_username = '" + use.user_username
				                     + "' , user_sort = '" + use.user_sort
				                     + "' , user_sex =  " + use.user_sex +" where user_id = " + user_id;
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
		}
		return -1;
	}
	public static List<projectInfo> queryProjectInfo(String status,String keyword, String pageNum, String pageSize){
		Connection conn = dbcputil.getConnection();
		try {
			Statement state = conn.createStatement();
			String sql = "";
			sql = "select * from project_info";
			if(status != null && status != "") {
				sql = "select * from project_info where  project_status ='"+status+"'";
			}
			if(keyword != null && keyword != "") {
				sql = "select * from project_info where   project_name like '%"+keyword+"%'";
			}
			if((keyword != null && keyword != "")&&(status != null && status != "")) {
				sql = "select * from project_info where project_status ='"+status+"' and  project_name like '%"+keyword+"%'";
			}
			System.out.println(sql);
//			sql += " limit "+ (Integer.parseInt(pageNum)-1)*Integer.parseInt(pageSize) + "," + pageSize;
			ResultSet res = state.executeQuery(sql);
			List<projectInfo> list = new ArrayList<projectInfo>();
			while(res.next()) {
				projectInfo project = new projectInfo();
				project.setProject_id(res.getInt("project_id"));
				project.setProject_pid(res.getString("project_pid"));
				project.setProject_name(res.getString("project_name"));
				project.setProject_sort(res.getString("project_sort"));
				project.setProject_startime(res.getString("project_startime"));
				project.setProject_endtime(res.getString("project_endtime"));
				project.setProject_authorid(res.getString("project_authorid"));
		        project.setProject_authorname(res.getString("project_authorname"));
		        project.setProject_creatime(res.getString("project_creatime"));
		        project.setProject_puport(res.getString("project_puport"));
		        project.setProject_status(res.getString("project_status"));
		        project.setProject_helpteacher(res.getString("project_helpteacher"));
		        project.setProject_refence(res.getString("project_refence"));
		        project.setShenhe(res.getString("shenhe"));
		        project.setFile(res.getString("file"));
		        list.add(project);
			}
		
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("Òì³£´íÎó");
		}
	}
	public static int updataProjectInfo(projectInfo project){
		Connection conn = dbcputil.getConnection();
	    String sql = "update project_info set project_name = " + "'" + project.project_name  + "'"
	    		+  " ,  project_helpteacher = "+ "'" +  project.project_helpteacher  + "'"
	    		+" , project_sort = "+ "'" + project.project_sort + "'"
	    		+" , project_startime = "+ "'" + project.project_startime + "'"
	    		+" , project_endtime = "+ "'" + project.project_endtime + "'"
	    		+" , project_authorname = "+ "'" + project.project_authorname + "'"
	    		+" , project_creatime = "+ "'" + project.project_creatime + "'"
	    		+" , project_puport = "+ "'" + project.project_puport + "'"
	    		+" , project_refence = "+ "'" + project.project_refence + "'"
	    		+"  where project_id = "+project.project_id;
	   try {
		Statement state = 	conn.createStatement();
		int n = state.executeUpdate(sql);
		if(n > 0) {
			return 0;
		}else {
			return -1;
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		throw new RuntimeException("ï¿½ï¿½ï¿½ï¿½Ê§ï¿½ï¿½");
	 }
	}
	
}
