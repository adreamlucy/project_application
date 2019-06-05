package pro;

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

import Instanceclass.projectInfo;
import net.sf.json.JSONArray;

public class queryprojectdetailsServlet extends HttpServlet implements Serializable {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
		PrintWriter out =  response.getWriter();
		Map map = new HashMap();
		String project_proid = request.getParameter("project_proid");
		List<projectInfo> list = DAO.idqueryprojectinfo.query(project_proid);
		map.put("status", 0);
		map.put("data", list);
		JSONArray array = JSONArray.fromObject(map);
		out.println(array.toString());
	}

}
