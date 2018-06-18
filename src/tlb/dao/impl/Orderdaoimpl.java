package tlb.dao.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import tlb.dao.Orderdao;
import tlb.domain.Order;
import tlb.domain.Orderitem;
import tlb.domain.Product;
import tlb.domain.User;
import tlb.utils.DataSourceUtils;

public class Orderdaoimpl implements Orderdao {

	@Override
	public void saveorder(Order order) throws Exception {
QueryRunner qr = new QueryRunner();
		String sql="insert into orders values(?,?,?,?,?,?,?,?)";
		qr.update(DataSourceUtils.getConnection(),sql, order.getOid(),order.getOrdertime(),order.getTotal(),order.getState(),order.getAddress(),order.getName(),
				order.getTelephone(),order.getUser().getUid());
	}



	@Override
	public void saveorderitem(Order order) throws Exception {
	
		QueryRunner qr = new QueryRunner();
		String sql="insert into orderitem values(?,?,?,?,?)";
		List<Orderitem> itemlist = order.getList();
		for (Orderitem orderitem : itemlist) {
			qr.update(DataSourceUtils.getConnection(),sql, orderitem.getItemid(),orderitem.getCount(),orderitem.getSubtotal(),orderitem.getP().getPid(),
					order.getOid());
		}	
		
	}



	@Override
	public List<Order> findpage(int currentpage, int pagesize, String uid) throws Exception {
		// 查找分页数据  封装成list
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from orders where uid=? order by ordertime desc limit ?,?";
		List<Order> orders = qr.query(sql, new BeanListHandler<>(Order.class), uid,(currentpage-1)*pagesize,pagesize);
		
		sql="select * from orderitem oi,product p where oi.pid = p.pid and oi.oid=?";
		for (Order order : orders) {
			List<Map<String, Object>> maplist = qr.query(sql, new MapListHandler(), order.getOid());
			for (Map<String, Object> map : maplist) {
				Orderitem odi=new Orderitem();
				Product p=new Product();
				BeanUtils.populate(odi, map);
				BeanUtils.populate(p, map);
				odi.setP(p);
				order.getList().add(odi);
			}
		}
		return orders;
			
			/*sql="select * from orderitem where oid = ?";
			Orderitem orderitem = qr.query(sql, new BeanHandler<>(Orderitem.class), order.getOid());
			//获取pid
			sql="select pid from orderitem where oid = ? ";
			List<Object> pidlist = qr.query(sql, new ColumnListHandler(), order.getOid());
			for (Object object : pidlist) {
				sql="select * from product where pid = ?";
				Product product = qr.query(sql, new BeanHandler<>(Product.class), object.toString());
				orderitem.setP(product);
				order.getList().add(orderitem);
			}
		}
		return orders;*/
	}



	@Override
	public int findall(String uid) throws Exception {
		// 查找用户下的总订单数
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select count(*) from orders where uid = ? ";
		return ((Long)qr.query(sql, new ScalarHandler(), uid)).intValue();
	}



	@Override
	public Order findbyoid(String oid) throws Exception {
		// 查询oid  封装数据 返回
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from orders where oid = ? ";
		Order order = qr.query(sql, new BeanHandler<>(Order.class), oid);
		sql="select * from orderitem oi,product p where oi.pid=p.pid and oi.oid=?";
		List<Map<String, Object>> maplist = qr.query(sql, new MapListHandler(), oid);
		for (Map<String, Object> map : maplist) {
			Orderitem odi=new Orderitem();
			Product p=new Product();
			BeanUtils.populate(odi, map);
			BeanUtils.populate(p, map);
			odi.setP(p);
			order.getList().add(odi);
		}
		
		return order;
	}



	@Override
	public void update(Order order) throws Exception {
		// 更新订单状态
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="update orders set state=?,address=?,name=?,telephone=? where oid=?";
		qr.update(sql, order.getState(),order.getAddress(),order.getName(),order.getTelephone(),order.getOid());
	}



	@Override
	public List<Order> findallorder(String state) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from orders";
		if(state==null){
			return qr.query(sql, new BeanListHandler<>(Order.class));
		}
		sql="select * from orders where state=?";
		return qr.query(sql, new BeanListHandler<>(Order.class), state);
	}

}
