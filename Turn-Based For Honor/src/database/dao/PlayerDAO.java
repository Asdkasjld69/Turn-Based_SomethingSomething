package database.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import bean.Player;
import database.DataSourceUtils;

public class PlayerDAO {

	public void addPlayer(Player player) throws SQLException {
		String sql = "insert into PLAYERS(session_id,user_id,team) values(?,?,?)";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		int row = runner.update(sql, player.getSession_id(), player.getUser_id(), player.getTeam());
		if (row == 0) {
			throw new RuntimeException();
		}
	}

	public List<Player> findUserBySUId(int session_id, int user_id) throws SQLException {
		String sql = "select username,USERS.avatar user_avatar,PLAYERS.* from PLAYERS,USERS where user_id=USERS.id and PLAYERS.state>-2 and session_id=? and user_id=? group by USERS.id";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		List<Player> Player = runner.query(sql, new BeanListHandler<Player>(Player.class), session_id, user_id);
		return Player;
	}

	public List<Player> findPlayerBySUId(int session_id, int user_id) throws SQLException {
		String sql = "select CHARACTERS.avatar character_avatar,CHARACTERS.speed speed,username,USERS.avatar user_avatar,PLAYERS.* from PLAYERS,USERS,CHARACTERS where character_id=CHARACTERS.id and user_id=USERS.id and PLAYERS.state>-2 and session_id=? and user_id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		List<Player> Players = runner.query(sql, new BeanListHandler<Player>(Player.class), session_id, user_id);
		return Players;
	}

	public Player findUserById(int user_id) throws SQLException {
		String sql = "select username,USERS.avatar user_avatar,PLAYERS.* from PLAYERS,USERS where user_id=USERS.id and PLAYERS.state>-2 and PLAYERS.id=? ";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		Player Player = runner.query(sql, new BeanHandler<Player>(Player.class), user_id);
		return Player;
	}
	
	public Player findPlayerById(int user_id) throws SQLException {
		String sql = "select CHARACTERS.avatar character_avatar,CHARACTERS.speed speed,username,USERS.avatar user_avatar,PLAYERS.* from PLAYERS,USERS,CHARACTERS where character_id=CHARACTERS.id and user_id=USERS.id and PLAYERS.state>-2 and PLAYERS.id=? ";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		Player Player = runner.query(sql, new BeanHandler<Player>(Player.class), user_id);
		return Player;
	}

	public List<Player> findUserBySession(int session_id) throws SQLException {
		String sql = "select username,USERS.avatar user_avatar,PLAYERS.* from PLAYERS,USERS where user_id=USERS.id and PLAYERS.state>-2 and session_id=? group by USERS.id";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		List<Player> Player = runner.query(sql, new BeanListHandler<Player>(Player.class), session_id);
		return Player;
	}

	public List<Player> findPlayerBySession(int session_id) throws SQLException {
		String sql = "select CHARACTERS.avatar character_avatar,CHARACTERS.speed speed,username,USERS.avatar user_avatar,PLAYERS.* from PLAYERS,CHARACTERS,USERS where user_id=USERS.id and character_id=CHARACTERS.id and PLAYERS.state>-2 and session_id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		List<Player> Player = runner.query(sql, new BeanListHandler<Player>(Player.class), session_id);
		return Player;
	}
	
	public List<Player> findPlayerByUId(int user_id) throws SQLException {
		String sql = "select * from PLAYERS where state>-2 and user_id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		List<Player> Player = runner.query(sql, new BeanListHandler<Player>(Player.class), user_id);
		return Player;
	}

	public boolean updatePlayer(Player player) throws SQLException {
		String sql = "update PLAYERS set character_id=?,team=?,health=?,health_max=?,revenge=?,state=?,posx=?,posy=?,move_time=?,end_time=? where id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		int r = runner.update(sql, player.getCharacter_id(), player.getTeam(), player.getHealth(),player.getHealth_max(), player.getRevenge(),
				player.getState(), player.getPosx(), player.getPosy(), player.getMove_time(), player.getEnd_time(), player.getId());
		if (r > 0) {
			return true;
		}
		return false;
	}
}
