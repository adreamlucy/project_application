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
public class proInfoList {

	public static List<proInfo> query(String pro_sno, String  pro_username, String pageNum, String pageSize){
		Connection conn = dbcputil.getConnection();
		String sql = "";
		sql = "select * from pro_info";
		if(pro_sno !=null && pro_sno !="") {
			sql = "select * from pro_info where pro_sno = " + pro_sno;
		}
		if(pro_username!=null && pro_username!= "") {
			sql = "select * from pro_info where pro_username = " + pro_username;
		}
		if((pro_sno !=null && pro_sno !="")&&(pro_username!=null && pro_username!= "")) {
			sql = "select * from pro_info where pro_username = " + pro_username + "and pro_sno = " + pro_sno;
		}
		if((pageNum !=null && pageNum !="")&&(pageSize!=null && pageSize!= "")) {
			sql += " limit " + (Integer.parseInt(pageNum)-1)*Integer.parseInt(pageSize) + "," + pageSize;
		}
		
		try {
			Statement state = conn.createStatement();
			System.out.println(sql);
			ResultSet res = state.executeQuery(sql);
			List<proInfo> list = new ArrayList<proInfo>();
			while(res.next()) {
				proInfo pro = new proInfo();
				pro.setPro_id(res.getInt("pro_id"));
				pro.setPro_sno(res.getInt("pro_sno"));
				pro.setPro_username(res.getString("pro_username"));
				pro.setPro_sex(Integer.parseInt(res.getString("pro_sex")));
				pro.setPro_borndata(res.getString("pro_borndata"));
				pro.setPro_university(res.getString("pro_university"));
				pro.setPro_college(res.getString("pro_college"));
				pro.setPro_pro(res.getString("pro_pro"));
				pro.setPro_sort(res.getString("pro_sort"));
				pro.setPro_phone(res.getString("pro_phone"));
				pro.setPro_email(res.getString("pro_email"));
				pro.setPro_address(res.getString("pro_address"));
				pro.setPro_introduce(res.getString("pro_introduce"));
				list.add(pro);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	public static int updateProInfo(proInfo pro) {
		Connection conn = dbcputil.getConnection();

		String sql = "update pro_info set pro_username = '" +pro.getPro_username()+"', "
				+ "pro_address = '"+pro.getPro_address()+"', pro_borndata ='"  +pro.getPro_borndata()+"', pro_email ='"+pro.getPro_email()+"', "
						+ "pro_introduce ='"  +pro.getPro_introduce()+"', pro_pro ='" +pro.getPro_pro()+"', pro_sex ="  +pro.getPro_sex()+" where pro_sno = "  +pro.getPro_sno();
//		System.out.println(sql);
		try {
			Statement state = conn.createStatement();
			int n = state.executeUpdate(sql);
			if(n > 0) {
				return 0;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
}
