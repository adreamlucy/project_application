package user;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

public class updateprojectsubmitServlet extends HttpServlet implements Serializable {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
		PrintWriter out =  response.getWriter();
		String project_proid = request.getParameter("project_proid");
		Map map = new HashMap();
		int n= DAO.userprojectDAO.updateprojectsubmit(project_proid);
		if(n == 0) {
			map.put("status", 0);
			map.put("description", "success");
		}else {
			map.put("status", -1);
			map.put("description", "Ã·Ωª ß∞‹");
		}
		JSONArray array = JSONArray.fromObject(map);
		out.println(array.toString());
		out.close();
	}
	
}
