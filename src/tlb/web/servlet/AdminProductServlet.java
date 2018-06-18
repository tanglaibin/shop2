package tlb.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tlb.domain.Category;
import tlb.domain.Product;
import tlb.service.Productservice;
import tlb.service.categoryservice;
import tlb.service.impl.Productserviceimpl;
import tlb.service.impl.categoryserviceimpl;

public class AdminProductServlet extends BaseServlet {
	public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//查找所有商品   封装list集合  返回页面  取出展示
		Productservice ps=new Productserviceimpl();
		List<Product> list=null;
		try {
			list = ps.findall();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("list", list);
	    return "/admin/product/list.jsp";
	}
	
	
	
	public String addpui(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 先取得  category  放入域中  查找所有商品   封装list集合  返回页面  取出展示
		categoryservice cs=new categoryserviceimpl();
		List<Category> clist=cs.findlanmu();
		
		request.setAttribute("clist", clist);

		return "/admin/product/add.jsp";
		}
	
	public String deletep(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 删除商品
		String pid = request.getParameter("pid");
		Productservice ps=new Productserviceimpl();
		ps.deletep(pid);
		response.sendRedirect(request.getContextPath()+"/adminProduct?method=findAll");
		return null;
		}
	
}
