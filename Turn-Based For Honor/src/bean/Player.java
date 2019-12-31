package bean;

import java.sql.Timestamp;

public class Player {
	private int id;
	private int session_id;
	private int user_id;
	private String username;
	private int character_id;
	private String user_avatar;
	private String character_avatar;
	private int team;
	private int health;
	private int health_max;
	private int revenge;
	private int state;
	private int speed;
	private int posx;
	private int posy;
	private Timestamp join_time;
	private Timestamp move_time;
	private Timestamp end_time;
	
	
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUser_avatar() {
		return user_avatar;
	}
	public void setUser_avatar(String user_avatar) {
		this.user_avatar = user_avatar;
	}
	public String getCharacter_avatar() {
		return character_avatar;
	}
	public void setCharacter_avatar(String character_avatar) {
		this.character_avatar = character_avatar;
	}
	public int getHealth_max() {
		return health_max;
	}
	public void setHealth_max(int health_max) {
		this.health_max = health_max;
	}
	public int getTeam() {
		return team;
	}
	public void setTeam(int team) {
		this.team = team;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getSession_id() {
		return session_id;
	}
	public void setSession_id(int session_id) {
		this.session_id = session_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getCharacter_id() {
		return character_id;
	}
	public void setCharacter_id(int character_id) {
		this.character_id = character_id;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public int getRevenge() {
		return revenge;
	}
	public void setRevenge(int revenge) {
		this.revenge = revenge;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getPosx() {
		return posx;
	}
	public void setPosx(int posx) {
		this.posx = posx;
	}
	public int getPosy() {
		return posy;
	}
	public void setPosy(int posy) {
		this.posy = posy;
	}
	public Timestamp getJoin_time() {
		return join_time;
	}
	public void setJoin_time(Timestamp join_time) {
		this.join_time = join_time;
	}
	public Timestamp getMove_time() {
		return move_time;
	}
	public void setMove_time(Timestamp move_time) {
		this.move_time = move_time;
	}
	public Timestamp getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Timestamp end_time) {
		this.end_time = end_time;
	}
	public void setPos(int i, int j) {
		// TODO Auto-generated method stub
		posx = i;
		posy = j;
	}
	
	
}
