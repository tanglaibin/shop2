package tlb.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tlb.domain.Order;
import tlb.service.Orderservice;
import tlb.service.impl.Orderserviceimpl;

public class AdminOrderServlet extends BaseServlet {
	public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//查询所有订单  放入域中 到页面取出展示
		String state = request.getParameter("state");
		
		Orderservice ods=new Orderserviceimpl();
		List<Order> list=null;
		try {
			list = ods.findall(state);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("olist", list);
	return "/admin/order/list.jsp";
	}

}
