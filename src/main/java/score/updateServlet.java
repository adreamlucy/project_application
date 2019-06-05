package score;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Instanceclass.scoreInfo;
import net.sf.json.JSONArray;

public class updateServlet extends HttpServlet implements Serializable {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
		PrintWriter out =  response.getWriter();
		Map map = new HashMap();
		String project_proid = request.getParameter("project_proid");
		System.out.println(request.getParameter("score"));
		int score = Integer.parseInt(request.getParameter("score"));
		String user_sno = request.getParameter("user_sno");
		int n = DAO.scoreDAO.update(project_proid, score, user_sno);
		if(n == 0) {
			map.put("status", 0);
			map.put("description", "success");
		}else {
			map.put("status", -1);
			map.put("description", "“Ï≥£¥ÌŒÛ");
		}
		JSONArray array = JSONArray.fromObject(map);
		out.println(array.toString());
		out.close();
		
	}
}
