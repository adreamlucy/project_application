package user;

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
import Instanceclass.userInfo;
import Instanceclass.userproject;
import net.sf.json.JSONArray;

public class userprojectopreationServlet extends HttpServlet implements Serializable {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
		PrintWriter out =  response.getWriter();
		Map map = new HashMap();
	    String path = request.getPathInfo();
	    if("/add.userprojectopreation".equals(path)) {
	    	projectInfo use = new projectInfo();
			use.setProject_authorid(request.getParameter("project_authorid"));
			use.setProject_creatime(request.getParameter("project_creatime"));
			use.setProject_endtime(request.getParameter("project_endtime"));
			use.setProject_startime(request.getParameter("project_startime"));
			use.setProject_sort(request.getParameter("project_sort"));
			use.setProject_helpteacher(request.getParameter("project_helpteacher"));
			use.setProject_puport(request.getParameter("project_puport"));
			use.setProject_refence(request.getParameter("project_refence"));
			use.setProject_status(request.getParameter("project_status"));
			use.setProject_authorname(request.getParameter("project_authorname"));
			use.setProject_name(request.getParameter("project_name"));
			use.setPro_id(request.getParameter("pro_id"));
			use.setFile(request.getParameter("file"));
			use.setShenhe(request.getParameter("shenhe"));
			use.setProject_pid(use.getProject_authorid() + ((int)(Math.random()*900)+100));
			int n= DAO.userprojectDAO.addprojectsubmit(use);
			if(n == 0) {
				map.put("status", 0);
				map.put("description", "项目申报称");
			}else {
				map.put("status", -1);
				map.put("description", "项目申报失败");
			}
	    }else if("/delete.userprojectopreation".equals(path)) {
	    	String project_proid = request.getParameter("project_proid");
			int n= DAO.userprojectDAO.delprojectsubmit(project_proid);
			if(n == 0) {
				map.put("status", 0);
				map.put("description", "success");
			}else {
				map.put("status", -1);
				map.put("description", "删除成功");
			}
	    }else if("/query.userprojectopreation".equals(path)) {
	    	String user_sno = request.getParameter("user_sno");
			String status = request.getParameter("status");
			List <userproject>list = DAO.userprojectDAO.query(user_sno, status);
			map.put("status", 0);
			map.put("data", list);
			map.put("description", "success");
	    }
		JSONArray array = JSONArray.fromObject(map);
		out.println(array.toString());
	}
}
