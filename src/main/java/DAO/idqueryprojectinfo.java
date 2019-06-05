package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Instanceclass.projectInfo;
import util.dbcputil;
public class idqueryprojectinfo {
	
public static List<projectInfo> query(String project_proid){
	 Connection conn = dbcputil.getConnection();
	 List<projectInfo>list = new ArrayList<projectInfo>();
	 String sql = "select * from project_info where project_pid = '"+project_proid+"'";
	try {
		System.out.println(sql);
		Statement state = conn.createStatement();
		ResultSet res = state.executeQuery(sql);
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
	        project.setFile(res.getString("file"));
	        list.add(project);
		}
		return list;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		throw new RuntimeException(e);
	}	
  }
}
