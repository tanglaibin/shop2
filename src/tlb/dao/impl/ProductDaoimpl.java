package tlb.dao.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import tlb.dao.Productdao;
import tlb.domain.Category;
import tlb.domain.Product;
import tlb.utils.DataSourceUtils;

public class ProductDaoimpl implements Productdao{

	@Override
	public List<Product> findnew() throws Exception{
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from product order by pdate desc limit 9";
		return qr.query(sql, new BeanListHandler<>(Product.class));
	}

	@Override
	public List<Product> findhot() throws Exception {
		
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="SELECT * FROM product WHERE is_hot=1 ORDER BY pdate DESC LIMIT 9 ";
		return qr.query(sql, new BeanListHandler<>(Product.class));
	}

	@Override
	public Product findbyid(String pid) throws Exception {

		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="SELECT * FROM product WHERE pid = ? limit 1";
		return qr.query(sql, new BeanHandler<>(Product.class), pid);
	}

	@Override
	public List<Product> findp(int currentpage, int pagesize, String cid) throws Exception {
		// 查找页内容 
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="SELECT * FROM product WHERE cid = ? limit ? , ?";
		return qr.query(sql, new BeanListHandler<>(Product.class), cid,(currentpage-1)*pagesize,pagesize);
	}

	@Override
	public int findtc(String cid) throws Exception {
		// 查找总商品数量
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="SELECT count(*) FROM product where cid=?";
		return  ((Long)qr.query(sql, new ScalarHandler(), cid)).intValue();
	}

	@Override
	public void update(String cid) throws Exception {
		// 设置该cid下的商品 cid为null
		QueryRunner qr = new QueryRunner();
		String sql="update product set cid=null where cid=?";
		qr.update(DataSourceUtils.getConnection(), sql, cid);
	}

	@Override
	public List<Product> findall() throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from product";
		return qr.query(sql, new BeanListHandler<>(Product.class));
	}

	@Override
	public void addp(Product p) throws Exception {
		
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="insert into product values(?,?,?,?,?,?,?,?,?,?)";
		qr.update(sql, p.getPid(),p.getPname(),p.getMarket_price(),p.getShop_price(),p.getPimage(),p.getPdate(),p.getIs_hot(),p.getPdesc(),
				p.getPflag(),p.getCategory().getCid());
	}

	@Override
	public void deletep(String pid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="delete from product where pid=?";
		qr.update(sql, pid);

		
	}




	
}
