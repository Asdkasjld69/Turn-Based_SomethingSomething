package database.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import bean.BattleLog;
import database.DataSourceUtils;

public class BattleLogDAO {

	public void addBattleLog(BattleLog battlelog) throws SQLException {
		String sql = "insert into BATTLELOGS(player_id,turn,stateno,movex,movey,priority) values(?,?,?,?,?,?)";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		int row = runner.update(sql, battlelog.getPlayer_id(),battlelog.getTurn(),battlelog.getStateno(),battlelog.getMovex(),battlelog.getMovey(),battlelog.getPriority());
		if (row == 0) {
			throw new RuntimeException();
		}
	}
	
	public BattleLog findBattleLogById(int id) throws SQLException {
		String sql = "select CHARACTERS.name,USERS.name,BATTLELOGS.* from BATTLELOGS,CHARACTERS,USERS,PLAYERS where BATTLELOGS.player_id=PLAYERS.id and PLAYERS.user_id=USERS.id and PLAYERS.character_id = CHARACTERS.id and BATTLELOGS.id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		BattleLog battleLog = runner.query(sql, new BeanHandler<BattleLog>(BattleLog.class), id);
		return battleLog;
	}

	public List<BattleLog> findBattleLogBySession(int session_id) throws SQLException {
		String sql = "select CHARACTERS.name,USERS.username,BATTLELOGS.* from BATTLELOGS,CHARACTERS,USERS,PLAYERS where BATTLELOGS.player_id=PLAYERS.id and PLAYERS.user_id=USERS.id and PLAYERS.character_id = CHARACTERS.id and PLAYERS.session_id=? order by turn,priority,BATTLELOGS.create_time";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		List<BattleLog> BattleLog = runner.query(sql, new BeanListHandler<BattleLog>(BattleLog.class), session_id);
		return BattleLog;
	}
	
	public List<BattleLog> findBattleLogBySUId(int session_id,int user_id) throws SQLException {
		String sql = "select CHARACTERS.name,USERS.username,BATTLELOGS.* from BATTLELOGS,CHARACTERS,USERS,PLAYERS where BATTLELOGS.player_id=PLAYERS.id and PLAYERS.user_id=USERS.id and PLAYERS.character_id = CHARACTERS.id and PLAYERS.session_id=? and USERS.id=? order by turn,priority,BATTLELOGS.create_time";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		List<BattleLog> BattleLog = runner.query(sql, new BeanListHandler<BattleLog>(BattleLog.class),session_id, user_id);
		return BattleLog;
	}
	
	public List<BattleLog> findBattleLogBySPId(int session_id,int player_id) throws SQLException {
		String sql = "select CHARACTERS.name,USERS.username,BATTLELOGS.* from BATTLELOGS,CHARACTERS,USERS,PLAYERS where BATTLELOGS.player_id=PLAYERS.id and PLAYERS.user_id=USERS.id and PLAYERS.character_id = CHARACTERS.id and PLAYERS.session_id=? and BATTLELOGS.player_id=? order by turn,priority,BATTLELOGS.create_time";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		List<BattleLog> BattleLog = runner.query(sql, new BeanListHandler<BattleLog>(BattleLog.class),session_id, player_id);
		return BattleLog;
	}
	
	public List<BattleLog> findBattleLogByTurn(int session_id,int turn) throws SQLException {
		String sql = "select CHARACTERS.name,USERS.username,BATTLELOGS.* from BATTLELOGS,CHARACTERS,USERS,PLAYERS where BATTLELOGS.player_id=PLAYERS.id and PLAYERS.user_id=USERS.id and PLAYERS.character_id = CHARACTERS.id and PLAYERS.session_id=? and turn=? order by priority,BATTLELOGS.create_time";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		List<BattleLog> BattleLog = runner.query(sql, new BeanListHandler<BattleLog>(BattleLog.class),session_id,turn);
		return BattleLog;
	}
	
	public List<BattleLog> findUserBattleLogByTurn(int session_id,int user_id,int turn) throws SQLException {
		String sql = "select CHARACTERS.name,USERS.username,BATTLELOGS.* from BATTLELOGS,CHARACTERS,USERS,PLAYERS where BATTLELOGS.player_id=PLAYERS.id and PLAYERS.user_id=USERS.id and PLAYERS.character_id = CHARACTERS.id and PLAYERS.session_id=? and USERS.id=? and turn=? order by priority,BATTLELOGS.create_time";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		List<BattleLog> BattleLog = runner.query(sql, new BeanListHandler<BattleLog>(BattleLog.class),session_id,user_id,turn);
		return BattleLog;
	}
}
