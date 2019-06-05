package Login;

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

public class loginServlet extends HttpServlet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
		PrintWriter out = response.getWriter();
		int role_type = Integer.parseInt(request.getParameter("role_type"));
		String user_sno = request.getParameter("user_sno");
		String user_password = request.getParameter("user_password");
		System.out.println(user_sno+"---"+user_password+"-----");
		int n = DAO.login.login(role_type, user_sno, user_password);
		Map map =  new HashMap();
		if(n == 0) {
			map.put("status", 0);
			map.put("description", "µ«¬º≥…π¶");
			map.put("token", util.radomtoken.getRandom());
		}else {
			map.put("status", -1);
			map.put("description", "’À∫≈ªÚ√‹¬Î¥ÌŒÛ");
		}
		JSONArray array = JSONArray.fromObject(map);
		out.println(array.toString());
		
		
	}
	

}
