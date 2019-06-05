package adminManage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Instanceclass.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class projectUpdateServlet extends HttpServlet implements Serializable {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
		PrintWriter out =  response.getWriter();
		Map map = new HashMap();
		 projectInfo project = new projectInfo();
		 project.project_name = request.getParameter("project_name");
		 project.project_helpteacher = request.getParameter("project_helpteacher");
		 project.project_sort = request.getParameter("project_sort");
		 project.project_startime = request.getParameter("project_startime");
		 project.project_endtime = request.getParameter("project_endtime");
		 project.project_authorname  = request.getParameter("project_authorname");
		 project.project_creatime = request.getParameter("project_creatime");
		 project.project_puport  = request.getParameter("project_puport");
		 project.project_refence  = request.getParameter("project_refence");
		 project.project_id = Integer.parseInt(request.getParameter("project_id"));
		 int n = DAO.userInfoList.updataProjectInfo(project);
		 if(n == 0) {
			 map.put("status", 0);
			 map.put("description", "success");
		 }else {
			 map.put("status", -1);
			 map.put("description", "¸üÐÂÊ§°Ü");
		 }
		 JSONArray array = JSONArray.fromObject(map);
		 out.println(array.toString());
	}
	
}
