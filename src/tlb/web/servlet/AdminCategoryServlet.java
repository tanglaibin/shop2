package tlb.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tlb.domain.Category;
import tlb.service.Productservice;
import tlb.service.categoryservice;
import tlb.service.impl.Productserviceimpl;
import tlb.service.impl.categoryserviceimpl;
import tlb.utils.UUIDUtils;

public class AdminCategoryServlet extends BaseServlet {
	public String findAll(HttpServletRequest request, HttpServletResponse response) throws Exception {

		categoryservice cs=new categoryserviceimpl();
		List<Category> clist=cs.findlanmu();
		
		request.setAttribute("clist", clist);
	
	return "/admin/category/list.jsp";
	}

	
	public String addcui(HttpServletRequest request, HttpServletResponse response) throws Exception {
	return "/admin/category/add.jsp";
	}
	
	public String addc(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//添加新分类名称 更新进去数据库  重定向至查找所有方法
		String cname = request.getParameter("cname");
		categoryservice cs=new categoryserviceimpl();
		Category cat=new Category();
		cat.setCname(cname);
		cat.setCid(UUIDUtils.getId());
		cs.update(cat);
		
		response.sendRedirect(request.getContextPath()+"/admincategory?method=findAll");
		
		return null;
		
		}
	
	public String editui(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String cid = request.getParameter("cid");
		categoryservice cs=new categoryserviceimpl();
		Category cat=cs.findbycid(cid);
		request.setAttribute("cat", cat);
		
		return "/admin/category/edit.jsp";
		}
	
	
	public String edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String cid = request.getParameter("cid");
		String cname = request.getParameter("cname");
		categoryservice cs=new categoryserviceimpl();
		Category cat2=cs.findbycid(cid);
		cat2.setCname(cname);
		
		cs.update2(cat2);
		response.sendRedirect(request.getContextPath()+"/admincategory?method=findAll");
		return null;
		}
	
	public String deletecat(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//删除分类  要先把该分类下的商品cid设置为null  再删除分类
		//先根据cid  找到商品 设置商品cid为null  再删除cid的分类
		String cid = request.getParameter("cid");
		categoryservice cs=new categoryserviceimpl();
		cs.deletec(cid);
		response.sendRedirect(request.getContextPath()+"/admincategory?method=findAll");
		
		return null;
		}
	
	
	
	
	
}
