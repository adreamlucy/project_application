package common;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Instanceclass.projectsort;
import net.sf.json.JSONArray;

public class sortServlet extends HttpServlet implements Serializable {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String path = request.getPathInfo();
		Map map = new HashMap();
		if("/query.sort".equals(path)) {
			List<projectsort> list = DAO.sortDAO.query();
			map.put("data", list);
		}else if("/update.sort".equals(path)) {
			int sort_id = Integer.parseInt(request.getParameter("sort_id"));
			String sort_name = request.getParameter("sort_name");
			int n = DAO.sortDAO.update(sort_id, sort_name);
			if(n == 0) {
				map.put("status", n);
				map.put("description", "success");
			}else {
				map.put("status", -1);
				map.put("description", "更新失败");
			}
					
		}else if("/delete.sort".equals(path)) {
			int sort_id = Integer.parseInt(request.getParameter("sort_id"));
			int n = DAO.sortDAO.del(sort_id);
			if(n == 0) {
				map.put("status", n);
				map.put("description", "success");
			}else {
				map.put("status", -1);
				map.put("description", "更新失败");
		}
		}else if("/add.sort".equals(path)) {
			String sort_name = request.getParameter("sort_name");
			int sort_id = Integer.parseInt(request.getParameter("sort_id"));
			int n = DAO.sortDAO.add(sort_name, sort_id);
			if(n == 0) {
				map.put("status", n);
				map.put("description", "success");
			}else {
				map.put("status", -1);
				map.put("description", "更新失败");
		}
		}

		JSONArray array = JSONArray.fromObject(map);
		out.println(array.toString());
		
	}
	
	
}
