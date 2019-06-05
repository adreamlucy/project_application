package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Instanceclass.scoreInfo;
import util.dbcputil;

public class scoreDAO {

public static String[] query(String project_id) {
	
	Connection conn = dbcputil.getConnection();
	String content = "";
	String sql = "select * from project_distribute where project_proid = '"+project_id+"'";
	try {
		Statement state = conn.createStatement();
		ResultSet res = state.executeQuery(sql);
		scoreInfo sco = new scoreInfo();
		while(res.next()) {
		content = res.getString("project_proid") + "," + res.getString("project_name")+","+
				  res.getString("pro_autherid") + "," + res.getString("pro_name")+","+
				  res.getString("expert_mind");
		for(int i=0;i<9;i++) {
			content+=","+res.getInt("score"+i);
		}
		}
		String []list = {content};
		return list;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		throw new RuntimeException(e);
	}
 }
	public static int update(String project_proid, int score, String user_sno) {
		Connection conn =  dbcputil.getConnection();
//		String sql = "update project_distribute set score = "+score;
		String upSql ="";
		try {
			Statement state = conn.createStatement();
//			int n= state.executeUpdate(sql);
			System.out.println(user_sno);
			int len = user_sno.split("&").length;
			if(len == 3&&score>1) {
				upSql = "update project_info set project_status =3 , score = "+score+", shenhe = '"+user_sno+"' where project_pid='"+project_proid+"'";
			}
			if(len == 3 && score<=1) {
				upSql = "update project_info set project_status =4 , score = "+score+", shenhe = '"+user_sno+"' where project_pid='"+project_proid+"'";
			}
			if(len<3) {
				upSql = "update project_info set project_status =2 , score = "+score+", shenhe = '"+user_sno+"' where project_pid='"+project_proid+"'";
			}
			System.out.println(upSql);
			int n2 = state.executeUpdate(upSql);
			return 0;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	 }
}
