package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Map;
import database.dao.MapDAO;

public class MapService {
	private MapDAO dao = new MapDAO();
	
	public void addMap(Map map) {
		try {
			dao.addMap(map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Map findMapById(int id) {
		Map map = null;
		try {
			map = dao.findMapById(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
	public List<Map> findMapByCondition(String id,String name,String widthl,String widthh,String heightl,String heighth) {
		List<Map> map = null;
		ArrayList<Object[]> conditions = new ArrayList<Object[]>();
		if(id!=null&&!id.trim().equals("")) {
			conditions.add(new Object[] {"id","%"+id+"%"});
		}
		if(name!=null&&!name.trim().equals("")) {
			conditions.add(new Object[] {"name","%"+name+"%"});
		}
		if(widthl!=null&&!widthl.trim().equals("")) {
			conditions.add(new Object[] {"widthl",Integer.parseInt(widthl)});
		}
		if(widthh!=null&&!widthh.trim().equals("")) {
			conditions.add(new Object[] {"widthh",Integer.parseInt(widthh)});
		}
		if(heightl!=null&&!heightl.trim().equals("")) {
			conditions.add(new Object[] {"heightl",Integer.parseInt(heightl)});
		}
		if(heighth!=null&&!heighth.trim().equals("")) {
			conditions.add(new Object[] {"heighth",Integer.parseInt(heighth)});
		}
		try {
			map = dao.findMapByCondition(conditions);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
	public boolean updateMap(Map map) {
		boolean r = false;
		try {
			r = dao.updateMap(map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}
}
