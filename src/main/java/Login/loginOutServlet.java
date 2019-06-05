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

public class loginOutServlet extends HttpServlet implements Serializable {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String token = request.getParameter("token");
		Map map = new HashMap();
		if(token != null) {
			map.put("status" , 0);
			map.put("descripion", "success");
		}else {
			map.put("status" , -1);
			map.put("descripion", "“Ï≥£¥ÌŒÛ");
		}
		JSONArray array = JSONArray.fromObject(map);
		out.println(array.toString());
	}

}
