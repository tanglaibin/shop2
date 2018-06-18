package tlb.service;

import java.util.List;

import tlb.domain.Pagebean;
import tlb.domain.Product;

public interface Productservice {

	List<Product> findnew()throws Exception;

	List<Product> findhot()throws Exception;

	Product findbyid(String pid)throws Exception;

	Pagebean<Product> findbean(int currentpage, int pagesize, String cid)throws Exception;

	List<Product> findall()throws Exception;

	void add(Product p)throws Exception;

	void deletep(String pid)throws Exception;

	

}
