package tlb.service.impl;

import java.sql.SQLException;
import java.util.List;

import tlb.dao.Orderdao;
import tlb.dao.impl.Orderdaoimpl;
import tlb.domain.Order;
import tlb.domain.Pagebean;
import tlb.service.Orderservice;
import tlb.utils.DataSourceUtils;

public class Orderserviceimpl implements Orderservice{

	@Override
	public void saveorder(Order order) throws Exception {

		
		try {
			DataSourceUtils.startTransaction();
			Orderdao odao=new Orderdaoimpl();
			//往order中存数据
			odao.saveorder(order);
			//往orderitem中存数据
			odao.saveorderitem(order);
			DataSourceUtils.commitAndClose();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			DataSourceUtils.rollbackAndClose();
			throw e;
		}
			
	}

	@Override
	public Pagebean<Order> findorders(int currentpage, int pagesize, String uid) throws Exception{
		// 查找数据 封装pgbean  返回
		Orderdao odao=new Orderdaoimpl();
		List<Order> list=odao.findpage(currentpage,pagesize,uid);
		int totalcount=odao.findall(uid);
		return new Pagebean<>(list, currentpage, pagesize, totalcount);
	}

	@Override
	public Order findbyid(String oid) throws Exception{

		Orderdao odao=new Orderdaoimpl();
		return odao.findbyoid(oid);
	}
	

	@Override
	public void update(Order order)throws Exception {
		// 更新订单状态信息
		Orderdao odao=new Orderdaoimpl();
		odao.update(order);
		
	}

	@Override
	public List<Order> findall(String state) throws Exception{
		// 查找所有orders
		Orderdao odao=new Orderdaoimpl();
		return odao.findallorder(state);
	}

}
