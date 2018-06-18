package tlb.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tlb.domain.Category;
import tlb.domain.Pagebean;
import tlb.domain.Product;
import tlb.service.Productservice;
import tlb.service.impl.Productserviceimpl;

public class Productservlet extends BaseServlet {
	public String findbyid(HttpServletRequest request, HttpServletResponse response) throws Exception {
//根据pid查找商品  放入域中  转发到详情页 取出展示
		String pid = request.getParameter("pid");
		Productservice ps=new Productserviceimpl();
		Product p=ps.findbyid(pid);
		request.setAttribute("product", p);
	return "/jsp/product_info.jsp";
	}

	
	
	public String findbypage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//new pagebean  根据cid  将查找到的数据封装进集合  传递currentpage pagesize  cid
		//然后将pagebean放入域中，请求转发到list页面 取出 展示
		int currentpage = Integer.parseInt(request.getParameter("currentpage"));
		String cid = request.getParameter("cid");
		Productservice ps=new Productserviceimpl();
		int pagesize=12;
		
		Pagebean<Product> bean=ps.findbean(currentpage,pagesize,cid);	
	    request.setAttribute("pbean", bean);	
		return "/jsp/product_list.jsp";
			}
	
	
	
	
}
