package tlb.dao;

import java.util.List;

import tlb.domain.Order;
import tlb.domain.Orderitem;

public interface Orderdao {

	void saveorder(Order order)throws Exception;

	void saveorderitem(Order order)throws Exception;

	List<Order> findpage(int currentpage, int pagesize, String uid)throws Exception;

	int findall(String uid)throws Exception;

	Order findbyoid(String oid)throws Exception;

	void update(Order order)throws Exception;

	List<Order> findallorder(String state)throws Exception;



	

}
