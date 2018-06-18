package tlb.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tlb.domain.Category;
import tlb.service.categoryservice;
import tlb.service.impl.categoryserviceimpl;
import tlb.utils.JsonUtil;

public class categoryservlet extends BaseServlet {
	public void findlanmu(HttpServletRequest request, HttpServletResponse response) throws Exception {
//取得栏目数据  放入域中 页面遍历得到  其他页面动态包含
	categoryservice cs=new categoryserviceimpl();
	List<Category> clist=cs.findlanmu();
	//System.out.println(clist);	
	response.setContentType("text/html;charset=utf-8");
	String list = JsonUtil.list2json(clist);
	//System.out.println(list);
	response.getWriter().println(list);
	
	}

}
