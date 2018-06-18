package tlb.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import tlb.dao.categorydao;
import tlb.domain.Category;
import tlb.utils.DataSourceUtils;

public class categorydaoimpl implements categorydao {

	@Override
	public List<Category> findlanmu() throws Exception {
QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
String sql="select * from category";
		
		return qr.query(sql, new BeanListHandler<>(Category.class));
	}

	@Override
	public void updatec(Category cat) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="insert into category values(?,?)";
		qr.update(sql, cat.getCid(),cat.getCname());
	}

	@Override
	public Category findbycid(String cid) throws Exception {
		// TODO Auto-generated method stub
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from category where cid = ?";
		return qr.query(sql, new BeanHandler<>(Category.class), cid);
	}

	@Override
	public void update2(Category cat2) throws Exception {
		// TODO Auto-generated method stub
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="update category set cname=? where cid=?";
		qr.update(sql, cat2.getCname(),cat2.getCid());
	}

	@Override
	public void deletec(String cid) throws Exception {
		// 删除cid分类
		QueryRunner qr = new QueryRunner();
		String sql="DELETE FROM category WHERE cid = ? ";
		qr.update(DataSourceUtils.getConnection(),sql, cid);
	}

}
