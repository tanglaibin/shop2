package tlb.web.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BaseServlet
 */
public class BaseServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			Class clazz=this.getClass();
			String m=request.getParameter("method");
			if(m==null){
				m="index";
			}
			
			Method method=clazz.getMethod(m, HttpServletRequest.class,HttpServletResponse.class);
			String s=(String) method.invoke(this,request,response);
			if(s!=null){
				request.getRequestDispatcher(s).forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	public String index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return null;
	}

}
