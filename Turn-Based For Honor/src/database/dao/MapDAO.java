package database.dao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import bean.Map;
import database.DataSourceUtils;

public class MapDAO {

	public void addMap(Map map) throws SQLException {
		String sql = "insert into MAPS(name,width,height,obstacle,fire,trap,pit) values(?,?,?,?,?,?,?)";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		int row = runner.update(sql, map.getName(), map.getWidth(),
				map.getHeight(), map.getObstacle(), map.getFire(),
				map.getTrap(), map.getPit());
		if (row == 0) {
			throw new RuntimeException();
		}
	}

	public Map findMapById(int id) throws SQLException {
		String sql = "select * from MAPS where id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		Map map = runner.query(sql, new BeanHandler<Map>(Map.class), id);
		map.setMap(map.getWidth(), map.getHeight(), map.getObstacle(), map.getFire(), map.getTrap(), map.getPit());
		return map;
	}	
	
	public List<Map> findMapByCondition(List<Object[]> conditions) throws SQLException {
		List<Map> l = null;
		List<Object> o = new ArrayList<Object>();
		String sql = "select * from MAPS";
		for (int i = 0; i < conditions.size(); i++) {
			o.add(conditions.get(i)[1]);
			sql += " and ";
			switch ((String) conditions.get(i)[0]) {
			case "widthl":
				sql += "width>=?";
				break;
			case "widthh":
				sql += "width<=?";
				break;
			case "heightl":
				sql += "height>=?";
				break;
			case "heighth":
				sql += "height<=?";
				break;
			default:
				sql += conditions.get(i)[0] + " like ?";
			}
		}
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		if (conditions.size() <= 0) {
			l = runner.query(sql, new BeanListHandler<Map>(Map.class));
		} else {
			l = runner.query(sql, new BeanListHandler<Map>(Map.class), o);
		}
		return l;
	}	
	
	public boolean updateMap(Map map) throws SQLException {
		String sql = "update MAPS set name=?,width=?,height=?,obstacle=?,fire=?,trap=?,pit=? where id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		int r = runner.update(sql,map.getName(),map.getWidth(),map.getHeight(),map.getObstacle(),map.getFire(),map.getTrap(),map.getPit(),map.getId());
		if(r>0) {
			return true;
		}
		return false;
	}	
}
