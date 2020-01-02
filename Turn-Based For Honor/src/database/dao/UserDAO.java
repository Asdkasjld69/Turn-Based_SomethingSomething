package database.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import bean.User;
import database.DataSourceUtils;

public class UserDAO {

	public void addUser(User user) throws SQLException {
		String sql = "insert into USERS(username,password,gender,email,activecode) values(?,?,?,?,?)";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		int row = runner.update(sql, user.getUsername(), user.getPassword(), user.getGender(), user.getEmail(),
				user.getActiveCode());
		if (row == 0) {
			throw new RuntimeException();
		}
	}

	public User findUserByActiveCode(String activeCode) throws SQLException {
		String sql = "select * from USERS where activecode=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanHandler<User>(User.class), activeCode);

	}

	public void activeUser(String activeCode) throws SQLException {
		String sql = "update USERS set state=? where activecode=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, 1, activeCode);
	}

	public void editUser(User user) throws SQLException {
		ArrayList<Object> o = new ArrayList<Object>();
		o.add(user.getGender());
		o.add(user.getKills());
		o.add(user.getDeaths());
		o.add(user.getAssists());
		o.add(user.getLikes());
		o.add(user.getDislikes());
		o.add(user.getWin());
		o.add(user.getLose());
		o.add(user.getDraw());
		o.add(user.getGamesfinished());
		o.add(user.getTurnsmoved());
		o.add(user.getGamesabandoned());
		o.add(user.getDamagedealt());
		o.add(user.getDamagereceived());
		o.add(user.getState());
		String sql = "update USERS set gender=?,kills=?,deaths=?,assists=?,likes=?,dislikes=?,win=?,lose=?,draw=?,gamesfinished=?,turnsmoved=?,gamesabandoned=?,damagedealt=?,damagereceived=?,state=?";
		if(user.getAvatar()!=null) {
			sql += ",avatar=?";
			o.add(user.getAvatar());
		}
		sql += " where id=?";
		o.add(user.getId());
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, o.toArray());
	}

	public User findUserByUsernameAndPassword(String username, String password) throws SQLException {
		String sql = "select * from USERS where username=? and password=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanHandler<User>(User.class), username, password);
	}

	public User findUserByUsername(String username) throws SQLException {
		String sql = "select * from USERS where username=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanHandler<User>(User.class), username);
	}

	public User findUserByEmail(String email) throws SQLException {
		String sql = "select * from USERS where email=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanHandler<User>(User.class), email);
	}

	public User findUserById(int id) throws SQLException {
		String sql = "select * from USERS where id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanHandler<User>(User.class), id);
	}

}
