package tlb.web.servlet;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tlb.domain.Cart;
import tlb.domain.Cartitem;
import tlb.domain.Product;
import tlb.service.Productservice;
import tlb.service.impl.Productserviceimpl;

public class CartServlet extends BaseServlet {
	
	public Cart getcart(HttpServletRequest request){
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if(cart==null){
			cart=new Cart();
			request.getSession().setAttribute("cart", cart);
		}
		return cart;
	}
	
	
	public String add2cart(HttpServletRequest request, HttpServletResponse response) throws Exception {
//添加到购物车  获取pid  count  封装cartitems  装进cart  放入session  转发到cart.jsp  取出 展示
		String pid = request.getParameter("pid");
		int count = Integer.parseInt(request.getParameter("count"));
		
		Productservice ps=new Productserviceimpl();
		Product p=ps.findbyid(pid);
		
		Cartitem cartitem=new Cartitem();
		
		cartitem.setP(p);
		cartitem.setCount(count);
		
		Cart cart=getcart(request);
		cart.add2cart(cartitem);
		
		response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");
	
	    return null;
	}

	
	public String deletec(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//添加到购物车  获取pid  cart调用自己的delete方法  删除  转发到cart页面
				String pid = request.getParameter("pid");
				
				
				Cart cart=getcart(request);
				cart.delete(pid);
				
				response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");
			
			    return null;
			}
	
	
	public String clearc(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Cart cart=getcart(request);
		
			
		
		
			cart.clearcart();	
			
			request.getSession().removeAttribute("cart");
		
			response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");	
		
		
	    return null;
			}
	
	
}
