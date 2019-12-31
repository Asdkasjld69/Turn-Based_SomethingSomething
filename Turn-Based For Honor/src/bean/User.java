package bean;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String username; 
	private String password; 
	private String gender; 
	private String email; 
	private String activeCode; 
	private String role; 
	private int state;
	private Date registTime;
	private String avatar;
	private int gamesfinished;
	private int turnsmoved;
	private int gamesabandoned;
	private int damagedealt;
	private int damagereceived;
	private int kills;
	private int deaths;
	private int assists;
	private int win;
	private int lose;
	private int draw;
	private int likes;
	private int dislikes;

	public Date getRegistTime() {
		return registTime;
	}

	public void setRegistTime(Date registTime) {
		this.registTime = registTime;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getActiveCode() {
		return activeCode;
	}

	public void setActiveCode(String activeCode) {
		this.activeCode = activeCode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public int getGamesfinished() {
		return gamesfinished;
	}

	public void setGamesfinished(int gamesfinished) {
		this.gamesfinished = gamesfinished;
	}

	public int getTurnsmoved() {
		return turnsmoved;
	}

	public void setTurnsmoved(int turnsmoved) {
		this.turnsmoved = turnsmoved;
	}

	public int getGamesabandoned() {
		return gamesabandoned;
	}

	public void setGamesabandoned(int gamesabandoned) {
		this.gamesabandoned = gamesabandoned;
	}

	public int getDamagedealt() {
		return damagedealt;
	}

	public void setDamagedealt(int damagedealt) {
		this.damagedealt = damagedealt;
	}

	public int getDamagereceived() {
		return damagereceived;
	}

	public void setDamagereceived(int damagereceived) {
		this.damagereceived = damagereceived;
	}

	public int getKills() {
		return kills;
	}

	public void setKills(int kills) {
		this.kills = kills;
	}

	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}

	public int getDeaths() {
		return deaths;
	}

	public int getAssists() {
		return assists;
	}

	public void setAssists(int assists) {
		this.assists = assists;
	}

	public int getWin() {
		return win;
	}

	public void setWin(int win) {
		this.win = win;
	}

	public int getLose() {
		return lose;
	}

	public void setLose(int lose) {
		this.lose = lose;
	}

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getDislikes() {
		return dislikes;
	}

	public void setDislikes(int dislikes) {
		this.dislikes = dislikes;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
