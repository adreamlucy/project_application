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

import DAO.prorolelogin;
import Instanceclass.projectInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class useProjectListServelt extends HttpServlet implements Serializable {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
		PrintWriter out = response.getWriter(); 
		Map map = new HashMap();
		 String project_authorid =  request.getParameter("project_authorid");
		 String pro_id =  request.getParameter("pro_id");
		 String pageNum = request.getParameter("pageNum");
		 String pageSize =  request.getParameter("pageSize");
		 String status = request.getParameter("status");
		 String keyword = request.getParameter("keyword");
//		 System.out.println(project_authorid);
		 List<projectInfo> list = DAO.userInfoList.queryProjectInfo(status,keyword, pageNum, pageSize);
		 map.put("data", list);
		 map.put("total", list.size());
		 JSONArray array = JSONArray.fromObject(map);
		 out.println(array.toString());
		
	}
}
