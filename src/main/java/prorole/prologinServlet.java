package prorole;

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

import DAO.prorolelogin;
import Instanceclass.proLogin;
import net.sf.json.JSONArray;

public class prologinServlet extends HttpServlet implements Serializable {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
		PrintWriter out = response.getWriter(); 
		Map map = new HashMap();
	   String path = request.getPathInfo();
	   if("/query.prologin".equals(path)) {
		   List<proLogin> list;
			try {
				list = DAO.prorolelogin.queryproLoginAccount();
				map.put("data", list);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   }else if("/add.prologin".equals(path)) {
		   int account = -1;
			int n = -1;
			  
			  String pro_sno = request.getParameter("pro_sno");
			  String pro_password = request.getParameter("pro_password");
			try {
				System.out.println(pro_sno);
				account = prorolelogin.queryOne(pro_sno);
				if(account == 0) {
					n = prorolelogin.addLoginAccount(pro_sno, pro_password);
					if(n == 0) {
						map.put("status", 0);
						map.put("description", "success");
					}else {
						map.put("status", -2);
						map.put("description", "Ìí¼ÓÊ§°Ü");
					}
				}else {
					map.put("status", -1);
					map.put("description", "ÖØ¸´");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
	   }else if("/update.prologin".equals(path)) {
		   String pro_sno = request.getParameter("pro_sno");
			  String pro_password =  request.getParameter("pro_password");
			  int pro_id = Integer.parseInt(request.getParameter("pro_id"));
			  int n = prorolelogin.updateLoginAccount(pro_id, pro_sno, pro_password);
			  if( n == 0) {
				  map.put("status" , 0);
				  map.put("decription", "success");
			  }else {
				  map.put("status" , -1);
				  map.put("decription", "¸üÐÂÊ§°Ü");
			  }
	   }else if("/delete.prologin".equals(path)) {
		   int pro_id = Integer.parseInt(request.getParameter("pro_id"));
			int n = prorolelogin.delLoginAccount(pro_id);
			if(n == 0) {
				map.put("status", 0);
				map.put("description", "success");
			}else {
				map.put("status", -1);
				map.put("description", "É¾³ýÊ§°Ü");
			}
	   }
	   JSONArray array = JSONArray.fromObject(map);
	   out.println(array.toString());
	}
}
