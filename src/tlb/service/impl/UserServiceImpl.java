package tlb.service.impl;



import tlb.dao.UserDao;
import tlb.dao.impl.UserDaoImpl;
import tlb.domain.User;
import tlb.service.UserService;
import tlb.utils.MailUtils;

public class UserServiceImpl implements UserService {

	@Override
	public void saveu(User u) throws Exception  {
		UserDao dao=new UserDaoImpl();
		dao.saveuser(u);
		//邮箱激活  发送激活邮件  将激活地址发送到msg  用户点击 后修改state为1
		String emailMsg="这是一封激活邮件，请<a href='http://localhost:8080/demo/user?method=active&code="+u.getCode()+"'>点击</a>激活。";		
		MailUtils.sendMail(u.getEmail(), emailMsg);
	}

	@Override
	public User findbycode(String code) throws Exception{
		UserDao dao=new UserDaoImpl();
		return dao.findcode(code);
	}

	@Override
	public void updateuser(User user) throws Exception{
		UserDao dao=new UserDaoImpl();
		dao.updateu(user);
	}

	@Override
	public User findbyname(String username,String password)throws Exception {
		UserDao dao=new UserDaoImpl();
		return dao.findbyname(username,password);
		
	}


}
