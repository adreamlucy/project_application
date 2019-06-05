package adminManage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Instanceclass.userInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class adminUseRoleListServlet extends HttpServlet implements Serializable  {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  response.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
		  PrintWriter out = response.getWriter();
		  List<userInfo> list;
		  Map map = new HashMap();
		  String user_username = request.getParameter("user_username");
		  String user_sno = request.getParameter("user_sno");
		  String pageNum =  request.getParameter("pageNum");
		  String pageSize = request.getParameter("pageSize");
		try {
			list = DAO.userInfoList.queryUserLoginAccount(user_username, user_sno, pageNum, pageSize);
			map.put("data", list);
			map.put("total", list.size());
			System.out.println(list.size());
			JSONArray array = JSONArray.fromObject(map);
			out.println(array.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
