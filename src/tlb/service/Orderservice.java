package tlb.service;

import java.util.List;

import tlb.domain.Order;
import tlb.domain.Pagebean;

public interface Orderservice {

	void saveorder(Order order)throws Exception;

	Pagebean<Order> findorders(int currentpage, int pagesize, String uid)throws Exception;

	Order findbyid(String oid)throws Exception;

	void update(Order order)throws Exception;

	List<Order> findall(String state)throws Exception;

}
