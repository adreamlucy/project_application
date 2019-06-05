package adminManage;

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

public class updatepasswordServlet extends HttpServlet implements Serializable {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
		PrintWriter out = response.getWriter(); 
		Map map = new HashMap();
		String role_type = request.getParameter("role_type");
		String user_sno = request.getParameter("user_sno");
		String newpassword = request.getParameter("newpassword");
		int n = DAO.updatepassword.update(role_type, user_sno, newpassword);
		if(n == 0) {
			map.put("status", 0);
			map.put("description", "success");
		}else {
			map.put("status", n);
			map.put("description", "“Ï≥£¥ÌŒÛ");
		}
		JSONArray array = JSONArray.fromObject(map);
		out.println(array.toString());
	}

}
