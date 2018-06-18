package tlb.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import tlb.dao.UserDao;
import tlb.domain.User;
import tlb.utils.DataSourceUtils;

public class UserDaoImpl implements UserDao {

	@Override
	public void saveuser(User u) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="insert into user values(?,?,?,?,?,?,?,?,?,?)";
		qr.update(sql, u.getUid(),u.getUsername(),u.getPassword(),u.getName(),u.getEmail(),u.getTelephone(),u.getBirthday(),
				u.getSex(),u.getState(),u.getCode());
		
	}

	@Override
	public User findcode(String code) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from user where code=?";
		
		return qr.query(sql, new BeanHandler<>(User.class), code);
	}

	@Override
	public void updateu(User user) throws Exception {

		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="UPDATE user SET state = ?, code = ? WHERE uid = ?";
		qr.update(sql, user.getState(),user.getCode(),user.getUid());
	}

	@Override
	public User findbyname(String username,String password) throws Exception{
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from user where username=? and password=? limit 1";
		return qr.query(sql, new BeanHandler<>(User.class), username,password);
	}

}
