package user;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Instanceclass.userInfo;
import net.sf.json.JSONArray;

public class userInfoServlet extends HttpServlet implements Serializable {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
		PrintWriter out =  response.getWriter();
		Map map = new HashMap();
	    String path = request.getPathInfo();
	    if("/query.userInfo".equals(path)) {
	    	String user_sno = "";
			List <userInfo> list;
			String type = request.getParameter("role_type");
			if(type != null && type != "") {
				 if(Integer.parseInt(type) == 0) {
					 user_sno = request.getParameter("user_sno");
				 }else if(Integer.parseInt(type) == 1) {
					 user_sno = request.getParameter("pro_sno");
				 }
				 try {
					list = DAO.userInfoList.queryUser(Integer.parseInt(type), user_sno);
					map.put("status", 0);
					map.put("data", list);
					map.put("description", "success");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				map.put("status", -1);
				map.put("description", "查询成功");
			}
	    }else if("/update.userInfo".equals(path)) {
	    	userInfo use = new userInfo();
			use.user_address = request.getParameter("user_address");
			use.user_borndata = request.getParameter("user_borndata");
			use.user_college = request.getParameter("user_college");
			use.user_email = request.getParameter("user_email");
			use.user_phone = request.getParameter("user_phone");
			use.user_pro = request.getParameter("user_pro");
			use.user_sex = Integer.parseInt(request.getParameter("user_sex"));
			use.user_sno = request.getParameter("user_sno");
			use.user_sort = request.getParameter("user_sort");
			use.user_university = request.getParameter("user_university");
			use.user_username = request.getParameter("user_username");
			int user_id = Integer.parseInt(request.getParameter("user_id"));
			
			int n = DAO.userInfoList.updateUserInfo(use, user_id);
			if(n == 0) {
				map.put("status", 0);
				map.put("description", "success");
				
			}else {
				map.put("status", -1);
				map.put("description", "更新错误");
			}
	    }
		JSONArray array = JSONArray.fromObject(map);
		out.println(array.toString());
	}
}
