package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.dbcputil;
import Instanceclass.projectInfo;
public class projectList {

public static List<projectInfo> query(String sno){
	Connection conn = dbcputil.getConnection();
	String sql = "select * from project_info where pro_id =" +sno;
	List<projectInfo> list = new ArrayList<projectInfo>();
	try {
		Statement state = conn.createStatement();
		ResultSet res = state.executeQuery(sql);
		while(res.next()) {
			projectInfo project = new projectInfo();
			 project.setProject_pid(res.getString("project_pid"));
			 project.setProject_name(res.getString("project_name"));
			 project.setProject_sort(res.getString("project_sort"));
			 project.setProject_creatime(res.getString("project_creatime"));
			 project.setProject_endtime(res.getString("project_endtime"));
			 project.setProject_startime(res.getString("project_startime"));
			 project.setProject_authorid(res.getString("project_authorid"));
			 project.setProject_authorname(res.getString("project_authorname"));
			 project.setProject_puport(res.getString("project_puport"));
			 project.setProject_status(res.getString("project_status"));
			 project.setProject_helpteacher(res.getString("project_helpteacher"));
			 project.setProject_refence(res.getString("project_refence"));
			list.add(project);
		}
		return list;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		throw new RuntimeException();
	}
	
}
}
