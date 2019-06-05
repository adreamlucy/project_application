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

import Instanceclass.proInfo;
import net.sf.json.JSONArray;

public class proInfoServlet extends HttpServlet implements Serializable {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
		PrintWriter out =  response.getWriter();
		Map map = new HashMap();
	    String path = request.getPathInfo();
	    if("/query.proInfo".equals(path)) {
	    	String pro_sno = request.getParameter("pro_sno");
	    	System.out.println(pro_sno+"-----");
	    	String pro_username = "";
	    	String pageNum = "";
	    	String pageSize = "";
	    	List<proInfo> list = DAO.proInfoList.query(pro_sno, pro_username, pageNum, pageSize);
	    	map.put("data", list.get(0));
	    }else if("/update.proInfo".equals(path)) {
	    	proInfo pro = new proInfo();
			pro.setPro_sno(Integer.parseInt(request.getParameter("pro_sno")));
			pro.setPro_username(request.getParameter("pro_username"));
			pro.setPro_address(request.getParameter("pro_address"));
			pro.setPro_borndata(request.getParameter("pro_borndata"));
			pro.setPro_email(request.getParameter("pro_email"));
			pro.setPro_introduce(request.getParameter("pro_introduce"));
			pro.setPro_phone(request.getParameter("pro_phone"));
			pro.setPro_pro(request.getParameter("pro_pro"));
			pro.setPro_sex(Integer.parseInt(request.getParameter("pro_sex")));
			pro.setPro_username(request.getParameter("pro_username"));
			int n = DAO.proInfoList.updateProInfo(pro);
			if(n == 0) {
				map.put("status", 0);
				map.put("description", "更新成功");
			}else {
				map.put("status", -1);
				map.put("description", "更新失败");
			}
	    }else if("/queryAll.proInfo".equals(path)) {
	    	String pro_sno = "";
	    	String pro_username = "";
	    	String pageNum = "";
	    	String pageSize = "";
	    	List<proInfo> list = DAO.proInfoList.query(pro_sno, pro_username, pageNum, pageSize);
	    	System.out.println(list.size()+"-----");
	    	map.put("data", list);
	    }
	    JSONArray array = JSONArray.fromObject(map);
	    out.println(array.toString());
	}

}
