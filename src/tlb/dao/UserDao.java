package tlb.dao;

import tlb.domain.User;

public interface UserDao {

	void saveuser(User u)throws Exception;

	User findcode(String code)throws Exception;

	void updateu(User user)throws Exception;

	User findbyname(String username,String password)throws Exception;

}
