package user;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class downloadMuBanServlet extends HttpServlet implements Serializable {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String basePath = request.getSession().getServletContext().getRealPath("d:");  
        //System.out.println(basePath);  
        String filedisplay = "文件名.doc";  
        String filedownload = "d:" + File.separator + "file.doc";  
        System.out.println("----------------------"+filedownload);  
        response.setContentType("applicaiton/x-download");  
        response.addHeader("Content-Disposition", "attachment;filename="+filedisplay);  

        InputStream is = null;  
        OutputStream os = null;  
        BufferedInputStream bis = null;  
        BufferedOutputStream bos = null;  

        is = new FileInputStream(new File(filedownload));  
        bis = new BufferedInputStream(is);  
        os = response.getOutputStream();  
        bos = new BufferedOutputStream(os);  

        byte[] b = new byte[1024];  
        int len = 0;  
        while((len = bis.read(b)) != -1){  
            bos.write(b,0,len);  
        }  

        bis.close();  
        is.close();  
        bos.close();  
        os.close();   
		
		
		
		
	}

}
