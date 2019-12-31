package database.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import bean.Session;
import database.DataSourceUtils;

public class SessionDAO {

	public void addSession(Session session) throws SQLException {
		String sql = "insert into SESSIONS(name,cap,map_id,password) values(?,?,?,?)";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		int row = runner.update(sql, session.getName(), session.getCap(), session.getMap_id(), session.getPassword());
		if (row == 0) {
			throw new RuntimeException();
		}
	}

	public Session addAndReturnSession(Session session) throws SQLException {
		String sql = "insert into SESSIONS(name,cap,map_id,password,state) values(?,?,?,?,?)";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		int si = -1000 - findSessionByConditions(new ArrayList<String[]>()).size();
		int row = runner.update(sql, session.getName(), session.getCap(), session.getMap_id(), session.getPassword(),
				si);
		if (row == 0) {
			throw new RuntimeException();
		}
		sql = "select MAPS.name map_name,SESSIONS.* from SESSIONS,MAPS where map_id=MAPS.id and SESSIONS.state=?";
		Session sess = runner.query(sql, new BeanHandler<Session>(Session.class), si);
		sess.setState(0);
		updateSession(sess);
		return sess;
	}

	public Session findSessionById(int id) throws SQLException {
		String sql = "select ucount,pcount,MAPS.name map_name,SESSIONS.* from SESSIONS,MAPS,(select distinct session_id,count(user_id) ucount from PLAYERS WHERE state>-2 group by session_id) suser,(select session_id,count(*) pcount from PLAYERS where state>-2 group by session_id) splayer where splayer.session_id=SESSIONS.id and suser.session_id=SESSIONS.id and map_id=MAPS.id and SESSIONS.id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		Session session = runner.query(sql, new BeanHandler<Session>(Session.class), id);
		return session;
	}

	public List<Session> findSessionByConditions(List<String[]> conditions) throws SQLException {
		List<Session> l = null;
		List<String> o = new ArrayList<String>();
		String sql = "select pcount,ucount,MAPS.name map_name,SESSIONS.* from SESSIONS,MAPS,(select distinct session_id,count(user_id) ucount from PLAYERS where state>-2 group by session_id) suser,(select session_id,count(*) pcount from PLAYERS where state > -2 group by session_id) splayer where splayer.session_id=SESSIONS.id and suser.session_id=SESSIONS.id and map_id=MAPS.id and SESSIONS.state>-1";
		for (int i = 0; i < conditions.size(); i++) {
			sql += " and ";
			if (conditions.get(i)[0].equals("password")) {
				switch (conditions.get(i)[1]) {
				case "Y":
					sql += conditions.get(i)[0] + " is NOT NULL";
					break;
				case "N":
					sql += conditions.get(i)[0] + " is NULL";
				}
				continue;
			}
			if (conditions.get(i)[0].equals("state")) {
				switch (conditions.get(i)[1]) {
				case "0":
					sql += conditions.get(i)[0] + " =0";
					break;
				case "1":
					sql += conditions.get(i)[0] + " >0";
				}
				continue;
			}
			o.add(conditions.get(i)[1]);
			switch (conditions.get(i)[0]) {
			case "cap":
				sql += conditions.get(i)[0] +"=?";
				break;
			default:
				sql += conditions.get(i)[0] + " like ?";
			}
		}
		sql += " order by create_time desc";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		if (o.size() <= 0) {
			l = runner.query(sql, new BeanListHandler<Session>(Session.class));
		} else {
			l = runner.query(sql, new BeanListHandler<Session>(Session.class), o.toArray());
		}
		return l;
	}

	public boolean updateSession(Session session) throws SQLException {
		String sql = "update SESSIONS set name=?,cap=?,map_id=?,state=?,password=? where id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		int r = runner.update(sql, session.getName(), session.getCap(), session.getMap_id(), session.getState(),
				session.getPassword(), session.getId());
		if (r > 0) {
			return true;
		}
		return false;
	}
}
