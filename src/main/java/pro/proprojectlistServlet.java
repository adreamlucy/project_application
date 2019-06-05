package pro;

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

import Instanceclass.projectInfo;
import Instanceclass.proproject;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class proprojectlistServlet extends HttpServlet implements Serializable {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
		PrintWriter out =  response.getWriter();
		String pro_id = request.getParameter("pro_id");
		String status = request.getParameter("status");
		String sort = request.getParameter("sort");
		Map map = new HashMap();
		System.out.println(pro_id);
		try {
			List<projectInfo> list= DAO.proprojectDAO.queryProject(pro_id, status, sort);
			map.put("data",list);
			map.put("status",0);
			map.put("total", list.size());
//			for(int i = 0; i<list.size();i++) {
//				System.out.println(list.get(i).project_pid);
//			}
			JSONArray array = JSONArray.fromObject(map);
			out.println(array.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	



}
