package servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.BattleLog;
import bean.Map;
import bean.Player;
import bean.Session;
import bean.User;
import service.BattleLogService;
import service.MapService;
import service.PlayerService;
import service.SessionService;
import service.UserService;

/**
 * Servlet implementation class BattleLogServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/BattleLogServlet" })
public class BattleLogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int sid = Integer.parseInt(request.getParameter("session_id"));
		int pid = Integer.parseInt(request.getParameter("user_id"));
		BattleLogService bs = new BattleLogService();
		SessionService ss = new SessionService();
		MapService ms = new MapService();
		PlayerService ps = new PlayerService();
		Session s = ss.findSessionById(sid);
		Map m = ms.findMapById(s.getMap_id());
		System.out.println("W:"+m.getWidth()+" "+"H:"+m.getHeight());
		int[][] maaap = m.getMap();
		for(int[] maap:maaap) {
			for(int mp:maap) {
				System.out.print(mp);
			}
			System.out.println();
		}
		List<BattleLog> bl = bs.findBattleLogByTurn(sid, s.getState());
		List<Integer> teams = new ArrayList<Integer>();
		List<Player> pl = ps.findPlayerBySession(sid);
		boolean shouldend = false;
		if(s.getState()>0) {
			shouldend = true;
		}
		for(Player p:pl) {
			int pteam = p.getTeam();
			if(!teams.contains(pteam)&&p.getHealth()>0) {
				teams.add(pteam);
			}
		}
		if(teams.size()>1||teams.size()>0&&teams.get(0)==0) {
			shouldend = false;
		}
		if(bl.size()>=s.getPcount()||shouldend) {
			if(s.getUcount()>=s.getCap()&&s.getPcount()>1||s.getState()>0||s.getUcount()<=0||shouldend) {
				for(BattleLog b:bl) {
					Player p = ps.findPlayerById(b.getPlayer_id());
					if(p.getHealth()<=0) {
						BattleLog ded = new BattleLog();
						ded.setPlayer_id(p.getId());
						ded.setMovex(0);
						ded.setMovey(0);
						ded.setStateno(5200);
						ded.setPriority(0);
						ded.setTurn(s.getState()+1);
						bs.addBattleLog(ded);
						continue;
					}
					int stateno = b.getStateno();
					if(b.getPriority()>5) {
						switch(stateno) {
						
						
						}
					}
					p.setPos(b.getMovex(),b.getMovey());
					if(b.getPriority()<=5) {
						switch(stateno) {
						
						
						}
					}
					
					switch(stateno) {
					
					}
					ps.updatePlayer(p);
				}
				
				List<Player> npl = ps.findPlayerBySession(sid);
				for(Player p:npl) {
					System.out.println("checking collisions for "+p.getUsername()+"("+p.getCharacter_id()+")");
					checkCollision(p,npl,m);
				}
				
				shouldend = true;
				int winningteam = -1;
				teams = new ArrayList<Integer>();
				List<Player> lpl = ps.findPlayerBySession(sid);
				for(Player p:lpl) {
					int pteam = p.getTeam();
					if(!teams.contains(pteam)&&p.getHealth()>0) {
						teams.add(pteam);
					}
				}
				if(teams.size()>1||teams.size()>0&&teams.get(0)==0) {
					shouldend = false;
				}
				else {
					winningteam = teams.get(0);
				}
				s.setState(s.getState()+1);
				
				List<Player> lul = ps.findUserBySession(sid);
				if(shouldend) {
					UserService us = new UserService();
					for(Player p:lul) {
						if(p.getTeam()==winningteam) {
							User winner = us.findUserById(p.getUser_id());
							winner.setWin(winner.getWin()+1);
							winner.setState(-2);
							us.editUser(winner);
						}
						else {
							User loser = us.findUserById(p.getUser_id());
							loser.setLose(loser.getLose()+1);
							loser.setState(-2);
							us.editUser(loser);
						}
					}
					s.setState(-2);
				}
				ss.updateSession(s);
			}
		}
			
		List<BattleLog> bll = bs.findBattleLogBySession(sid);
		request.setAttribute("battlelogs", bll);
		request.getRequestDispatcher("/client/segment/session_battlelog_segment.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int sid = Integer.parseInt(request.getParameter("session_id"));
		int pid = Integer.parseInt(request.getParameter("player_id"));
		int stateno = Integer.parseInt(request.getParameter("stateno"));
		int movex = Integer.parseInt(request.getParameter("movex"));
		int movey = Integer.parseInt(request.getParameter("movey"));
		int priority = Integer.parseInt(request.getParameter("priority"));
		BattleLogService bs = new BattleLogService();
		SessionService ss = new SessionService();
		PlayerService ps = new PlayerService();
		BattleLog b = new BattleLog();
		Session s = ss.findSessionById(sid);
		Player p = ps.findPlayerById(pid);
		b.setSession_id(sid);
		b.setTurn(s.getState());
		b.setPlayer_id(pid);
		b.setStateno(stateno);
		b.setMovex(movex);
		b.setMovey(movey);
		b.setPriority(priority);
		bs.addBattleLog(b);
		p.setMove_time(new Timestamp(System.currentTimeMillis()));
		p.setState(s.getState());
		ps.updatePlayer(p);
		doGet(request, response);
	}

	private void hitDef(Player p,List<Player> pl,int dmg,String pat,Map m) {
		PlayerService ps = new PlayerService();
		String[] pt = pat.split(",");
		for(String pattern:pt) {
			if(pattern==null||pattern.trim().equals("")) {
				continue;
			}
			String[] p_coord = pattern.split("x");
			String[] p_posx = p_coord[0].split("-");
			String[] p_posy = p_coord[1].split("-");
			int p_posx1 = Integer.parseInt(p_posx[0], 16);
			int p_posy1 = Integer.parseInt(p_posy[0], 16);
			int p_posx2 = Integer.parseInt(p_posx[0], 16);
			int p_posy2 = Integer.parseInt(p_posy[0], 16);
			if(p_posx.length>1) {
				p_posx2 = Integer.parseInt(p_posx[1], 16);
			}
			if(p_posy.length>1) {
				p_posy2 = Integer.parseInt(p_posy[1], 16);
			}
			for(int i=p_posy1;i<=p_posy2;i++) {
				for(int ii=p_posx1;ii<=p_posx2;ii++) {
					for(Player vic:pl) {
						int[] pos = vic.getPos();
						if(pos[0]==ii&&pos[1]==i) {
							vic.setHealth(vic.getHealth()-dmg);
							ps.updatePlayer(vic);
						}
						pl.remove(vic);
					}
				}
			}
		}
	}
	
	private void moveTo(Player p,int posx,int posy,Map m,boolean forced) {
		PlayerService ps = new PlayerService();
		int[][] params = {{-1,-1},{-1,0},{0,-1}};
		int[][] map = m.getMap();
		int[] dir = {0,0};
		int pposx = p.getPosx();
		int pposy = p.getPosy();
		if(posx>pposx) {
			dir[0] = 1;
			if(posy>pposy) {
				dir[1]=1;
			}
			else if(posy<pposy) {
				dir[1]=-1;
			}
		}
		else if(posx<pposx) {
			dir[0] = -1;
			if(posy>pposy) {
				dir[1]=1;
			}
			else if(posy<pposy) {
				dir[1]=-1;
			}
		}
		if(!forced) {
			while(map[posy][posx]!=0&&posx>0&&posx+1<m.getWidth()&&posy>0&&posy+1<m.getHeight()) {
				posx -= dir[0];
				posy -= dir[1];
			}
		}
		else {
			while(map[posy][posx]==1&&posx>0&&posx+1<m.getWidth()&&posy>0&&posy+1<m.getHeight()) {
				posx -= dir[0];
				posy -= dir[1];
			}
			if(map[posy][posx]!=0) {
				int vich = p.getHealth();
				int attempt = 0;
				int best = -1;
				if(map[posy][posx]!=4) {
					int[][] ms = {{posx,posy},{posx,posy},{posx,posy}};
					for(int[] method:ms) {
						while(map[method[1]][method[0]]!=0&&method[0]>0&&method[0]+1<m.getWidth()&&method[1]>0&&method[1]+1<m.getHeight()) {
							method[0] += dir[0]*params[attempt][0];
							method[1] += dir[1]*params[attempt][1];
						}
						attempt++;
					}
					for(int i=0;i<attempt;i++) {
						if(best==-1 || (pposx-ms[i][0])+(pposy-ms[i][1])<(pposx-ms[best][0])+(pposy-ms[best][1])) {
							best = i;
						}
					}
					posx = ms[best][0];
					posy = ms[best][1];
				}
				switch(map[posx][posy]) {
				case 1: vich -= 25;break;
				case 2:	vich -= 50;break;
				case 3:	vich -= vich;break;
				case 4:	vich -= vich;break;
				}
			}
		}
		p.setPos(posx, posy);
		ps.updatePlayer(p);
	}
	
	private void checkCollision(Player p,List<Player> other,Map m) {
		int[][] params = {{-1,0},{-1,-1},{0,-1},{1,-1},{1,0},{1,1},{0,1},{-1,1}};
		int[][] map = m.getMap();
		PlayerService ps = new PlayerService();
		for(Player op:other) {
			System.out.println(op.getUsername());
			if(p.getId()==op.getId()) {
				System.out.println(op.getUsername()+" IS SELF");
				continue;
			}
			int[] pp = p.getPos();
			int[] opp = op.getPos();
			if(pp[0]==opp[0]&&pp[1]==opp[1]) {
				System.out.println("COLLISION DETECTED");
				for(int[] param:params) {
					System.out.println("trying"+" "+param[0]+","+param[1]);
					if(pp[0]+param[0]>=0&&pp[0]+param[0]<m.getWidth()&&pp[1]+param[1]>=0&&pp[1]+param[1]<m.getHeight()) {
						if(map[pp[1]+param[1]][pp[0]+param[0]]==0) {
							if(p.getMove_time().after(op.getMove_time())) {
								p.setPos(pp[0]+param[0], pp[1]+param[1]);
								ps.updatePlayer(p);
							}
							else {
								op.setPos(opp[0]+param[0], opp[1]+param[1]);
								ps.updatePlayer(op);
							}
							System.out.println("COLLISION SOLVED");
							break;
						}
						else {
							System.out.println("NO GOOD");
						}
					}
					else {
						System.out.println("METHOD "+param[0]+","+param[1]+" OUT OF BOUNDES("+(pp[0]+param[0])+","+(pp[1]+param[1])+")");
					}
				}
			}
		}
	}
}
