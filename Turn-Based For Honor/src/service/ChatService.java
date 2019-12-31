package service; 

import java.sql.SQLException;
import java.util.List;

import bean.Chat;
import bean.Chat_Public;
import database.dao.ChatDAO;

public class ChatService {
	private ChatDAO dao = new ChatDAO();
	
	public void addChat(Chat chat) {
		try {
			dao.addChat(chat);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteChat(int id) {
		try {
			dao.deleteChat(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Chat> findChatBySessionId(int id){
		return dao.findChatBySessionId(id);
	}
	
	public void addPublicChat(Chat_Public chat) {
		try {
			dao.addPublicChat(chat);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Chat_Public> listPublicChat(){
		return dao.listPublicChat();
	}
}
