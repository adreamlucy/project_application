package DAO;

import java.io.Console;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Instanceclass.projectInfo;
import Instanceclass.userproject;
import util.dbcputil;

public class userprojectDAO {

 public static List<userproject> query(String user_sno, String status){
	 List<userproject>list = new ArrayList<userproject>();
	 Connection conn= dbcputil.getConnection();
	 int score[] = new int[8];
	 int account = 0;
	 String sql = "";
	 if(status!=""&&status!=null) {
		 sql = "select * from user_info , project_info , project_distribute "
			 		+ "where user_info.user_sno = project_info.project_authorid and  "
			 		+ "project_info.project_pid = project_distribute.project_proid and "
			 		+ "user_info.user_sno ='"+user_sno+ "' and project_info.project_status = '"+status+"'";
	 }else {
		 sql = "select * from user_info , project_info , project_distribute "
			 		+ "where user_info.user_sno = project_info.project_authorid and  "
			 		+ "project_info.project_pid = project_distribute.project_proid and "
			 		+ "user_info.user_sno ='"+user_sno+ "'";
	 }
	 try {
		 System.out.println(sql);
		Statement state = conn.createStatement();
		ResultSet res = state.executeQuery(sql);
		while(res.next()) {
			userproject use = new userproject();
			use.setProject_proid(res.getString("project_proid"));
			use.setUser_sno(res.getString("user_sno"));
			use.setUser_username(res.getString("user_username"));
			use.setProject_sort(res.getString("project_sort"));
			use.setHelp_teacher(res.getString("project_helpteacher"));
			use.setProject_submit(res.getString("project_submit"));
			use.setProject_status(res.getString("project_status"));
			list.add(use);	
		}
		return list;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();

	}
	 return list;
	
 }
 public static int updateprojectsubmit(String project_pid) {
	 Connection conn= dbcputil.getConnection();
	 String sql = "update project_info set project_submit = 1 where project_pid = '" +project_pid+"'";
	 try {
//		 System.out.println(sql);
		 Statement state = conn.createStatement();
		int n = state.executeUpdate(sql);
		 System.out.println(n);
		if(n > 0) {
			return 0;
		}else {
			return -1;
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		dbcputil.close(conn);
	}
	 return -1;
 }
 public static int delprojectsubmit(String project_pid) {
	 Connection conn= dbcputil.getConnection();
	 String sql = "delete from project_info where project_pid  ='" +project_pid+"'";
	 try {
		 System.out.println(sql);
		 Statement state = conn.createStatement();
		int n = state.executeUpdate(sql);
		 System.out.println(n);
		if(n > 0) {
			return 0;
		}else {
			return -1;
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		dbcputil.close(conn);
	}
	 return -1;
 }
 public static int addprojectsubmit(projectInfo use) {
	 Connection conn= dbcputil.getConnection();
	String  sql = "insert into project_info ( project_name , project_helpteacher , project_sort ,"
	 		+ "project_startime , project_endtime , project_authorid , project_authorname ,"
	 		+ "project_creatime , project_puport , project_refence , project_submit , project_status , project_pid,pro_id,file,shenhe) value('" +
	 		use.project_name+"','"+use.project_helpteacher+"','"+use.project_sort+"','"+
	 		use.project_startime+"','"+use.project_endtime+"','"+use.project_authorid+"','"+
	 		use.project_authorname+"','"+use.project_creatime+"','"+use.project_puport+"','"+
	 		use.project_refence+"',"+ 0 +",'"+use.project_status+"', '" +use.project_pid+"','"+use.pro_id+"','"+use.file+"','"+use.shenhe+"')";
	 try {
		 Statement state = conn.createStatement();
		int n = state.executeUpdate(sql);
		if(n > 0) {
			sql = "insert into project_distribute ( project_proid , project_name , pro_autherid ,"
			 		+ "pro_name , score,expert_mind) value('" +
			 		use.project_pid+"','"+use.project_name+"','"+use.project_authorid+"','"+
			 		use.project_authorname+"',"+0+",'"+"')";
			int m = state.executeUpdate(sql);
			if(m > 0) {
				return 0;
			}else {
				return -2;
			}
		
		}else {
			return -1;
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 return -1;
 }
 
 
}
