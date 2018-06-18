package tlb.dao;

import java.util.List;

import tlb.domain.Product;

public interface Productdao {

	List<Product> findnew()throws Exception;

	List<Product> findhot()throws Exception;

	Product findbyid(String pid)throws Exception;

	List<Product> findp(int currentpage, int pagesize, String cid)throws Exception;

	int findtc(String cid)throws Exception;

	void update(String cid)throws Exception;

	List<Product> findall()throws Exception;

	void addp(Product p)throws Exception;

	void deletep(String pid)throws Exception;

	

}
