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

import Instanceclass.domain;
import net.sf.json.JSONArray;

public class domainServlet extends HttpServlet implements Serializable {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String path = request.getPathInfo();
		Map map = new HashMap();
		if("/query.domain".equals(path)) {
			 String list= DAO.domainDAO.query();
			map.put("data", list);
		}else if("/update.domain".equals(path)) {
			int domain_id = Integer.parseInt(request.getParameter("domain_id"));
			String domain_name = request.getParameter("domain_name");
			int n = DAO.domainDAO.update(domain_id, domain_name);
			if(n == 0) {
				map.put("status", n);
				map.put("description", "success");
			}else {
				map.put("status", -1);
				map.put("description", "¸üÐÂÊ§°Ü");
			}
					
		}else if("/delete.domain".equals(path)) {
			int domain_id = Integer.parseInt(request.getParameter("domain_id"));
			int n = DAO.domainDAO.del(domain_id);
			if(n == 0) {
				map.put("status", n);
				map.put("description", "success");
			}else {
				map.put("status", -1);
				map.put("description", "æ›´æ–°å¤±è´¥");
		}
		}else if("/add.domain".equals(path)) {
			String domain_name = request.getParameter("domain_name");
			int domain_id = Integer.parseInt(request.getParameter("domain_id"));
			int n = DAO.domainDAO.add(domain_name, domain_id);
			if(n == 0) {
				map.put("status", n);
				map.put("description", "success");
			}else {
				map.put("status", -1);
				map.put("description", "æ›´æ–°å¤±è´¥");
		}
		}

		JSONArray array = JSONArray.fromObject(map);
		out.println(array.toString());
	}

}
