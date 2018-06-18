package tlb.service.impl;

import java.util.List;

import tlb.dao.Productdao;
import tlb.dao.impl.ProductDaoimpl;
import tlb.domain.Pagebean;
import tlb.domain.Product;
import tlb.service.Productservice;

public class Productserviceimpl implements Productservice {

	@Override
	public List<Product> findnew() throws Exception{
		// 查找最新商品
		
		Productdao pdao=new ProductDaoimpl();
		
		return pdao.findnew();
	}

	@Override
	public List<Product> findhot() throws Exception{
		// 查找最热商品
		Productdao pdao=new ProductDaoimpl();
		return pdao.findhot();
	}

	@Override
	public Product findbyid(String pid) throws Exception{
		Productdao pdao=new ProductDaoimpl();
		return pdao.findbyid(pid);
	}

	@Override
	public Pagebean<Product> findbean(int currentpage, int pagesize, String cid) throws Exception{

		Productdao pdao=new ProductDaoimpl();
		List<Product> list=pdao.findp(currentpage,pagesize,cid);
		
		int totalcount=pdao.findtc(cid);
		
		return new Pagebean<>(list, currentpage, pagesize, totalcount);
	}

	@Override
	public List<Product> findall() throws Exception{
		// 查找所有商品
		Productdao pdao=new ProductDaoimpl();
		return pdao.findall();
	}

	@Override
	public void add(Product p) throws Exception{
		// TODO Auto-generated method stub
		Productdao pdao=new ProductDaoimpl();
		pdao.addp(p);
		
	}

	@Override
	public void deletep(String pid) throws Exception{

		Productdao pdao=new ProductDaoimpl();
		pdao.deletep(pid);
	}

	

}
