package bean;

public class BattleLog {
	 private int id;
	 private int player_id;
	 private int user_id;
	 private int session_id;
	 private int turn;
	 private int stateno;
	 private int movex;
	 private int movey;
	 private int priority;
	 private String user_name;
	 private String character_name;
	 
	 
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getCharacter_name() {
		return character_name;
	}
	public void setCharacter_name(String character_name) {
		this.character_name = character_name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPlayer_id() {
		return player_id;
	}
	public void setPlayer_id(int player_id) {
		this.player_id = player_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getSession_id() {
		return session_id;
	}
	public void setSession_id(int session_id) {
		this.session_id = session_id;
	}
	public int getTurn() {
		return turn;
	}
	public void setTurn(int turn) {
		this.turn = turn;
	}
	public int getStateno() {
		return stateno;
	}
	public void setStateno(int stateno) {
		this.stateno = stateno;
	}
	public int getMovex() {
		return movex;
	}
	public void setMovex(int movex) {
		this.movex = movex;
	}
	public int getMovey() {
		return movey;
	}
	public void setMovey(int movey) {
		this.movey = movey;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}

	 
}
