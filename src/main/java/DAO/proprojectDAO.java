package DAO;

import java.io.Console;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Instanceclass.projectInfo;
import Instanceclass.proproject;
import net.sf.json.JSONArray;
import util.dbcputil;

public class proprojectDAO {
	
	 public static List<projectInfo> queryProject(String pro_sno, String status,String sort) throws SQLException{
		List<projectInfo>list = new ArrayList<projectInfo>(); 
		Connection conn = dbcputil.getConnection();
		String sql = "";
		sql = "select * from project_info"
		 		+ " where "
		 		+ "project_info.pro_id like '%"+pro_sno+ "%'";
	    if(status!=null&&status!="") {
	    	sql = "select * from project_info"
			 		+ " where   "
			 		+ "project_info.project_status ='"
			 		+ status+"' and pro_id like '%"+pro_sno+"%'";
	    }
	    if(sort!=null&&sort!="") {
	    	sql = "select * from  project_info "
			 		+ " where "
			 		+ " project_info.project_sort = '"+sort+"'  and pro_id like '%"+pro_sno+"%'";
	    }
	    if((sort!=null&&sort!="")&&(status!=null&&status!="")) {
	    	sql = "select * from  project_info "
			 		+ " where "
			 		+ "project_info.project_sort ='"+sort+ "' and project_info.project_status = '"+status+"'"
			 				+ "and pro_id like '%"+pro_sno+"%'";
	    }
		
	
		Statement state = conn.createStatement();
		System.out.println(sql);
		ResultSet res= state.executeQuery(sql);
		while(res.next()) {
			int account = 0;
			projectInfo project = new projectInfo();
		project.setProject_pid(res.getString("project_pid"));
			project.setProject_name(res.getString("project_name"));
			project.setProject_sort(res.getString("project_sort"));
			project.setProject_status(res.getString("project_status"));
			project.setShenhe(res.getString("shenhe"));
			project.setScore(res.getInt("score"));
			project.setPro_id(res.getString("pro_id"));
//			String content = "";
//			content = res.getString("project_pid") + "," + res.getString("project_name") + "," + res.getString("project_status")+","+res.getString("project_sort")+","+res.getString("shenhe");
//			content+=","+res.getInt("score");	
			list.add(project);
		}
		
		return list;
	 }
}
