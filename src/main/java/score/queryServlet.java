package score;

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
import Instanceclass.scoreInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class queryServlet extends HttpServlet implements Serializable {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
		PrintWriter out =  response.getWriter();
		Map map = new HashMap();
		String project_id= request.getParameter("project_proid");
		
		String []list = DAO.scoreDAO.query(project_id);
		map.put("data", list);
		map.put("status", 0);
		JSONArray array = JSONArray.fromObject(map);
		out.println(array.toString());
		out.close();
	}

}
