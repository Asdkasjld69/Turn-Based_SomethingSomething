package database.dao;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import bean.Chat;
import bean.Chat_Public;
import database.DataSourceUtils;

public class ChatDAO {
	public void addChat(Chat chat) throws SQLException {
		String sql = "insert into CHATS(session_id,player_id,content) values(?,?,?)";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		int row = runner.update(sql, chat.getSession_id(), chat.getPlayer_id(),
				chat.getContent());
		if (row == 0) {
			throw new RuntimeException();
		}
	}
	
	public void deleteChat(int id) throws SQLException {
		String sql = "delete from CHATS where id=? ";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		int row = runner.update(sql, id);
		if (row == 0) {
			throw new RuntimeException();
		}
	}

	public List<Chat> findChatBySessionId(int id){
		String sql = "select username,CHATS.* from CHATS,USERS where USERS.id=player_id and session_id=? order by create_time";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		List<Chat> l = null;
		try {
			l = runner.query(sql, new BeanListHandler<Chat>(Chat.class),id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
	}
	
	public void addPublicChat(Chat_Public chat) throws SQLException {
		String sql = "insert into CHATS_PUBLIC(user_id,content) values(?,?)";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		int row = runner.update(sql, chat.getUser_id(),chat.getContent());
		if (row == 0) {
			throw new RuntimeException();
		}
	}
	
	public List<Chat_Public> listPublicChat(){
		String sql = "select username,CHATS_PUBLIC.* from CHATS_PUBLIC,USERS where USERS.id=user_id order by create_time";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		List<Chat_Public> l = null;
		try {
			l = runner.query(sql, new BeanListHandler<Chat_Public>(Chat_Public.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
	}
	
	public void deletePublicChat(int id) throws SQLException {
		String sql = "delete from CHATS_PUBLIC where id=? ";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		int row = runner.update(sql, id);
		if (row == 0) {
			throw new RuntimeException();
		}
	}
}
