package bean;

import java.sql.Timestamp;
import java.util.List;

import service.PlayerService;
import service.UserService;

public class Session {
	private int id;
	private int map_id;
	private int host_id;
	private int ucount;
	private int pcount;
	private int cap;
	private int state;
	private String name;
	private String map_name;
	private String password;
	private Timestamp create_time;
	private Timestamp start_time;
	private Timestamp end_time;
	
	
	
	public int getHost_id() {
		return host_id;
	}
	public void setHost_id(int host_id) {
		this.host_id = host_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMap_name() {
		return map_name;
	}
	public void setMap_name(String map_name) {
		this.map_name = map_name;
	}
	
	public int getUcount() {
		return ucount;
	}
	public void setUcount(int ucount) {
		this.ucount = ucount;
	}
	public int getPcount() {
		return pcount;
	}
	public void setPcount(int pcount) {
		this.pcount = pcount;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMap_id() {
		return map_id;
	}
	public void setMap_id(int map_id) {
		this.map_id = map_id;
	}
	public int getCap() {
		return cap;
	}
	public void setCap(int cap) {
		this.cap = cap;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}
	public Timestamp getStart_time() {
		return start_time;
	}
	public void setStart_time(Timestamp start_time) {
		this.start_time = start_time;
	}
	public Timestamp getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Timestamp end_time) {
		this.end_time = end_time;
	}
	
	public User getUser(int pid) {
		PlayerService ps = new PlayerService();
		UserService us = new UserService();
		List<Player> p = null;
		User u = null;
		p = ps.findUserBySUId(id, pid);
		if(p!=null&&p.size()>0) {
			u = us.findUserById(p.get(0).getUser_id());
		}
		System.out.println("U "+u);
		return u;
	}
	
	public List<Player> getUserPlayer(int pid) {
		PlayerService ps = new PlayerService();
		List<Player> p = null;
		p = ps.findUserBySUId(id, pid);
		System.out.println("UP "+p);
		return p;
	}
	
	public List<Player> getPlayer(int pid) {
		PlayerService ps = new PlayerService();
		List<Player> p = null;
		p = ps.findPlayerBySUId(id, pid);
		System.out.println("P "+p);
		return p;
	}
	
}
