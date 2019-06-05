package userole;

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

import DAO.uselogin;
import Instanceclass.userLogin;
import net.sf.json.JSONArray;

public class userloginServlet extends HttpServlet implements Serializable {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  response.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
		  PrintWriter out = response.getWriter();
		  Map map = new HashMap();
		  String path = request.getPathInfo();
		  if("/query.userlogin".equals(path)) {
			  List<userLogin> list;
			try {
				list = DAO.uselogin.queryUserLoginAccount();
				map.put("data", list); 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  }else if("/add.userlogin".equals(path)){
			  int account = 0;
				int n = 0;
				  String user_sno = request.getParameter("user_sno");
				  String user_password = request.getParameter("user_password");
				try {
					account = uselogin.queryOne(user_sno);
					if(account <= 0) {
						n = uselogin.addLoginAccount(user_sno, user_password);
						if(n == 0) {
							map.put("status", 0);
							map.put("description", "success");
						}else {
							map.put("status", -2);
							map.put("description", "�쳣����");
						}
					}else {
						map.put("status", -1);
						map.put("description", "�����ظ�");
					}
					JSONArray array = JSONArray.fromObject(map);
					out.println(array.toString());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		  }else if("/update.userlogin".equals(path)) {
			  String user_sno = request.getParameter("user_sno");
			  String user_password = request.getParameter("user_password");
			  int user_id = Integer.parseInt(request.getParameter("user_id"));
			  int n = uselogin.updateLoginAccount(user_id, user_sno, user_password);
			  if(n == 0) {
					map.put("status", n);
					map.put("description", "success");
				}else {
					map.put("status", -1);
					map.put("description", "ɾ��ʧ�ܣ��쳣����");
				}
		  }else if("/delete.userlogin".equals(path)) {
			  int user_id = Integer.parseInt(request.getParameter("user_id"));
				int n = uselogin.delLoginAccount(user_id);
				if(n == 0) {
					map.put("status", n);
					map.put("description", "success");
				}else {
					map.put("status", -1);
					map.put("description", "ɾ��ʧ�ܣ��쳣����");
				}
		  }
			JSONArray array = JSONArray.fromObject(map);
			out.println(array.toString());
	}

}
