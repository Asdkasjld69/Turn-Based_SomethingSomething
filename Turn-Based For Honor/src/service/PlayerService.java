package service;

import java.sql.SQLException;
import java.util.List;

import bean.Player;
import database.dao.PlayerDAO;

public class PlayerService {
	PlayerDAO dao = new PlayerDAO();
	
	public void addPlayer(Player session) {
		try {
			dao.addPlayer(session);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Player> findUserBySUId(int session_id,int user_id) {
		List<Player> r = null;
		try {
			r = dao.findUserBySUId(session_id,user_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}
	
	public List<Player> findPlayerBySUId(int session_id,int user_id) {
		List<Player> r = null;
		try {
			r = dao.findPlayerBySUId(session_id, user_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}
	
	public Player findUserById(int user_id) {
		Player r = null;
		try {
			r = dao.findUserById(user_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}
	
	public Player findPlayerById(int player_id) {
		Player r = null;
		try {
			r = dao.findPlayerById(player_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}
	
	public List<Player> findUserBySession(int session_id) {
		List<Player> r = null;
		try {
			r = dao.findUserBySession(session_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}
	
	public List<Player> findPlayerBySession(int session_id) {
		List<Player> r = null;
		try {
			r = dao.findPlayerBySession(session_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}
	
	public boolean updatePlayer(Player session) {
		boolean r = false;
		try {
			r = dao.updatePlayer(session);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}
}
