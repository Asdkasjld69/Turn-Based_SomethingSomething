package database.dao;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import bean.Character;
import database.DataSourceUtils;

public class CharacterDAO {
	
	public List<Character> listCharacters() throws SQLException {
		String sql = "select * from CHARACTERS";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<Character>(Character.class));
	}

	public void addCharacter(Character character) throws SQLException {
		String sql = "insert into CHARACTERS(name,health,speed) values(?,?,?)";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		int row = runner.update(sql, character.getName(), character.getHealth(),
				character.getSpeed());
		if (row == 0) {
			throw new RuntimeException();
		}
	}

	public Character findCharacterById(int id) throws SQLException {
		String sql = "select * from CHARACTERS where id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		Character character = runner.query(sql, new BeanHandler<Character>(Character.class), id);
		return character;
	}	
	
	public boolean updateCharacter(Character character) throws SQLException {
		String sql = "update CHARACTERS set name=?,health=?,speed=?,avatar=? where id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		int r = runner.update(sql,character.getName(),character.getHealth(),character.getSpeed(),character.getAvatar(),character.getId());
		if(r>0) {
			return true;
		}
		return false;
	}	
}
