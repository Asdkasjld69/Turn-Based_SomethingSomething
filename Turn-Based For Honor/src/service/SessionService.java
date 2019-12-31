package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Session;
import database.dao.SessionDAO;

public class SessionService {
	SessionDAO dao = new SessionDAO();
	
	public void addSession(Session session) {
		try {
			dao.addSession(session);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Session addAndReturnSession(Session session) {
		Session sess = null;
		try {
			sess = dao.addAndReturnSession(session);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sess;
	}
	
	public Session findSessionById(int id) {
		Session r = null;
		try {
			r = dao.findSessionById(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}
	
	public List<Session> findSessionByConditions(String id,String map_id,String name,String map_name,String cap,String state,String password){
		List<String[]> conditions = new ArrayList<String[]>();
		List<Session> l = null;
		if(id!=null&&!id.trim().equals("")) {
			conditions.add(new String[] {"SESSIONS.id","%"+Integer.parseInt(id)+"%"});
		}
		if(map_id!=null&&!map_id.trim().equals("")) {
			conditions.add(new String[] {"MAPS.id","%"+Integer.parseInt(map_id)+"%"});
		}
		if(name!=null&&!name.trim().equals("")) {
			conditions.add(new String[] {"SESSIONS.name","%"+name+"%"});
		}
		if(map_name!=null&&!map_name.trim().equals("")) {
			conditions.add(new String[] {"MAPS.name","%"+map_name+"%"});
		}
		if(cap!=null&&!cap.trim().equals("")&&Integer.parseInt(cap)>1) {
			conditions.add(new String[] {"cap",cap});
		}
		if(state!=null&&!state.trim().equals("")) {
			conditions.add(new String[] {"state",state});
		}
		if(password!=null&&!password.trim().equals("")) {
			conditions.add(new String[] {"password",password});
		}
		try {
			l = dao.findSessionByConditions(conditions);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
	}
	
	public boolean updateSession(Session session) {
		boolean r = false;
		try {
			r = dao.updateSession(session);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}
}
