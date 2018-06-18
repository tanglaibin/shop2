package tlb.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tlb.domain.Product;
import tlb.service.Productservice;
import tlb.service.impl.Productserviceimpl;

public class IndexServlet extends BaseServlet {
	public String index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     //查询出最新商品 热门商品后 放入域中  到首页提取出来数据
		Productservice ps=new Productserviceimpl();
		
		List<Product> nlist=null;
		List<Product> hlist=null;
		try {
			nlist = ps.findnew();
			hlist=ps.findhot();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		request.setAttribute("nlist", nlist);
		request.setAttribute("hlist", hlist);
		
		
	 return "/jsp/index.jsp";
	 
	 }

}
