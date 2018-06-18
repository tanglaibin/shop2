package tlb.web.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import tlb.domain.User;
import tlb.service.UserService;
import tlb.service.impl.UserServiceImpl;
import tlb.utils.UUIDUtils;

public class UserServlet extends BaseServlet {
	public String registui(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	return "/jsp/register.jsp";
	}
	
	public String regist(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获取表单数据  封装user  调用service存储数据库
		User u=new User();
		Map<String, String[]> map = request.getParameterMap();
		BeanUtils.populate(u, map);
		u.setUid(UUIDUtils.getId());
		u.setCode(UUIDUtils.getCode());
		//System.out.println(u.getCode());
		UserService us=new UserServiceImpl();
		us.saveu(u);
		
		
		request.setAttribute("msg", "恭喜，请前往邮箱激活！");
		
		return "/jsp/msg.jsp";
		
		}
	
	
	
public String active(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//获取code  更新user state为1  清除code
	
	String code = request.getParameter("code");
	
	UserService us=new UserServiceImpl();
	
	User user=us.findbycode(code);
	
	user.setState(1);
	
	user.setCode(null);
	
	us.updateuser(user);
	
	request.setAttribute("msg", "恭喜，注册成功");
		
	return "/jsp/msg.jsp";
		
		}
	
public String loginui(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	return "/jsp/login.jsp";
	
	}
	

public String login(HttpServletRequest request, HttpServletResponse response) throws Exception {
	//接收name  密码   根据name查询数据库  得到user   看user是否为空  为空提示未注册  不为空   先查询state 与密码  分别提示 未激活  密码错误等 
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	UserService us=new UserServiceImpl();
	User user=us.findbyname(username,password);
	
	//String password2=user.getPassword();
	
	
	if(user==null){
		request.setAttribute("msg", "用户不存在或密码错误");
		return "/jsp/login.jsp";
	}else if(user.getState()==0){
		request.setAttribute("msg", "用户未激活");
		return "/jsp/login.jsp";
	}
	
	
	request.getSession().setAttribute("user", user);
	return "/jsp/index.jsp";
	}

public String loginout(HttpServletRequest request, HttpServletResponse response) throws Exception {
	//从session中取出user  删除  重定向至首页 
	request.getSession().removeAttribute("user");
	return "/jsp/index.jsp";
	}


}
