package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Character;
import database.dao.CharacterDAO;

public class CharacterService {
	CharacterDAO dao = new CharacterDAO();
	
	public void addCharacter(Character session) {
		try {
			dao.addCharacter(session);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Character findCharacterById(int id) {
		Character r = null;
		try {
			r = dao.findCharacterById(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}
	
	public List<Character> listCharacters() {
		List<Character> l = new ArrayList<Character>();
		try {
			l = dao.listCharacters();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
	}
	
	public boolean updateCharacter(Character session) {
		boolean r = false;
		try {
			r = dao.updateCharacter(session);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}
}
