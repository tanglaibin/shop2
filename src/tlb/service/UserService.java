package tlb.service;

import tlb.domain.User;

public interface UserService {

	void saveu(User u)throws Exception;

	User findbycode(String code)throws Exception;

	void updateuser(User user)throws Exception;

	User findbyname(String username,String password)throws Exception;


	

}


