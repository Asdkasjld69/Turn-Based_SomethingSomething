package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.BattleLog;
import database.dao.BattleLogDAO;

public class BattleLogService {
	BattleLogDAO dao = new BattleLogDAO();
	
	public void addBattleLog(BattleLog session) {
		try {
			dao.addBattleLog(session);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public BattleLog findBattleLogById(int id) {
		BattleLog r = null;
		try {
			r = dao.findBattleLogById(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}
	
	public List<BattleLog> findBattleLogBySession(int session_id) {
		List<BattleLog> l = new ArrayList<BattleLog>();
		try {
			l = dao.findBattleLogBySession(session_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
	}
	
	public List<BattleLog> findBattleLogBySUId(int session_id,int user_id) {
		List<BattleLog> l = new ArrayList<BattleLog>();
		try {
			l = dao.findBattleLogBySUId(session_id, user_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
	}
	
	public List<BattleLog> findBattleLogBySPId(int session_id,int player_id) {
		List<BattleLog> l = new ArrayList<BattleLog>();
		try {
			l = dao.findBattleLogBySPId(session_id, player_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
	}
	
	public List<BattleLog> findBattleLogByTurn(int session_id,int turn) {
		List<BattleLog> l = new ArrayList<BattleLog>();
		try {
			l = dao.findBattleLogByTurn(session_id, turn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
	}	
	
	public List<BattleLog> findUserBattleLogByTurn(int session_id,int user_id,int turn) {
		List<BattleLog> l = new ArrayList<BattleLog>();
		try {
			l = dao.findUserBattleLogByTurn(session_id,user_id, turn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
	}	
}
