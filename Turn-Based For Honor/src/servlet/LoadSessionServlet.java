package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.BattleLog;
import bean.Character;
import bean.Chat;
import bean.Map;
import bean.Player;
import bean.Session;
import bean.User;
import service.BattleLogService;
import service.CharacterService;
import service.ChatService;
import service.MapService;
import service.PlayerService;
import service.SessionService;
import service.UserService;

/**
 * Servlet implementation class LoadSessionServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/LoadSessionServlet" })
public class LoadSessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static HashMap<Integer,List<Session>> SESSIONS = new HashMap<Integer,List<Session>>();
	private static HashMap<Integer,List<Player>> PLAYERS = new HashMap<Integer,List<Player>>();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String type = request.getParameter("type");
		String check = request.getParameter("check");
		int id = Integer.parseInt(request.getParameter("id"));
		int pid = -1;
		boolean checkflag = false;
		if(check!=null&&!check.trim().equals("")) {
			checkflag = true;
		}
		SessionService sservice = null;
		MapService mservice = null;
		PlayerService pservice = null;
		ChatService cservice = null;
		CharacterService chservice = null;
		BattleLogService bservice = null;
		List<Player> pl = null;
		List<Player> pcl = null;
		List<Chat> cl = null;
		List<Character> chl = null;
		Session s = null;
		List<BattleLog> b = null;
		Map map = null;
		if (type == null) {
			type = "";
		}
		sservice = new SessionService();
		s = sservice.findSessionById(id);
		System.out.println(id+" "+s);
		request.setAttribute("session", s);
		User tu = null;
		switch(type) {
		case "timer":
		case "character_select":
		case "battlefield":
			tu = (bean.User)request.getSession().getAttribute("currentuser");
			request.setAttribute("thisuser", null);
			request.setAttribute("thisuserpl", null);
			request.setAttribute("thisuserpcl", null);
			request.setAttribute("thisuserpcount", 0);
			request.setAttribute("thisuserpccount", 0);
			if(tu!=null){
				User insess = s.getUser(tu.getId());
				List<bean.Player> pll = s.getUserPlayer(tu.getId());
				List<bean.Player> plcl = s.getPlayer(tu.getId());
				request.setAttribute("thisuser", insess);
				request.setAttribute("thisuserpl", pll);
				request.setAttribute("thisuserpcl", plcl);
				if(pll!=null){
					request.setAttribute("thisuserpcount", pll.size());
				}
				if(plcl!=null){
					request.setAttribute("thisuserpccount", plcl.size());
				}
			}break;
		}
		switch (type) {
		case "ban":
		case "kick":
		case "playerlist":
			pservice = new PlayerService();
			pl = pservice.findUserBySession(id);
			request.setAttribute("players", pl);
			request.getRequestDispatcher("/client/segment/session_playerlist_segment.jsp").forward(request, response);
			break;
		case "timer":
			request.getRequestDispatcher("/client/segment/session_timer_segment.jsp").forward(request, response);
			break;
		case "character_select":
			chservice = new CharacterService();
			chl = chservice.listCharacters();
			request.setAttribute("chars", chl);
			request.getRequestDispatcher("/client/segment/charactermenu_segment.jsp").forward(request, response);
			break;
		case "battlefield":
			pid = Integer.parseInt(request.getParameter("user_id"));
			mservice = new MapService();
			pservice = new PlayerService();
			bservice = new BattleLogService();
			map = mservice.findMapById(s.getMap_id());
			pcl = pservice.findPlayerBySession(id);
			b = bservice.findUserBattleLogByTurn(s.getId(),pid,s.getState());
			request.setAttribute("thisbattlelogs", b);
			request.setAttribute("map", map);
			request.setAttribute("characters", pcl);
			request.getRequestDispatcher("/client/segment/session_battlefield_segment.jsp").forward(request, response);
			break;
		default:
			mservice = new MapService();
			map = mservice.findMapById(s.getMap_id());
			request.setAttribute("map", map);
			request.getRequestDispatcher("/client/session.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		AsyncContext async = request.startAsync();
		async.start(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				String type = request.getParameter("type");
				String sid = request.getParameter("id");
				int id = -1;
				SessionService sservice = null;
				MapService mservice = null;
				PlayerService pservice = null;
				ChatService cservice = null;
				CharacterService chservice = null;
				UserService uservice = null;
				List<Player> pl = null;
				List<Player> pcl = null;
				List<Chat> cl = null;
				Player p = null;
				Character ch = null;
				User u = null;
				Session s = null;
				int user_id = -1;
				if (type == null) {
					type = "";
				}
				System.out.println("post");
				switch (type) {
				case "playerlist":
					id = Integer.parseInt(sid);
					int uid = Integer.parseInt(request.getParameter("user_id"));
					pservice = new PlayerService();
					p = new Player();
					p.setSession_id(id);
					p.setUser_id(uid);
					pservice.addPlayer(p);
					break;
				case "character_select":
					pservice = new PlayerService();
					chservice = new CharacterService();
					int pn = Integer.parseInt(request.getParameter("ccount"));
					int cid = Integer.parseInt(request.getParameter("character_id"));
					ch = chservice.findCharacterById(cid);
					for (int i = 0; i < pn; i++) {
						int pcid = Integer.parseInt(request.getParameter("player_idx" + i));
						p = pservice.findUserById(pcid);
						p.setCharacter_id(cid);
						p.setPos(-1,-1);
						p.setState(-1);
						p.setHealth_max(ch.getHealth());
						p.setHealth(ch.getHealth());
						p.setSpeed(ch.getSpeed());
						pservice.updatePlayer(p);
					}
					break;
				case "timer":
					break;
				case "ban":
						sservice = new SessionService();
						uservice = new UserService();
						pservice = new PlayerService();
						user_id = Integer.parseInt(request.getParameter("user_id"));
						id = Integer.parseInt(sid);
						s = sservice.findSessionById(id);
						u = uservice.findUserById(user_id);
						u.setState(-1);
						uservice.editUser(u);
						System.out.println("BANNED "+u.getUsername()+"!!");
						pl = pservice.findPlayerByUId(user_id);
						for(Player tplayer:pl) {
							tplayer.setState(-2);
							pservice.updatePlayer(tplayer);
						}
						if(s.getHost_id()==user_id) {
							pl = pservice.findPlayerBySession(id);
							for(Player tplayer:pl) {
								if(tplayer.getUser_id()==user_id) {
									continue;
								}
								tplayer.setState(-2);
								pservice.updatePlayer(tplayer);
							}
						}
						break;
				case "kick":
						pservice = new PlayerService();
						sservice = new SessionService();
						id = Integer.parseInt(sid);
						s = sservice.findSessionById(id);
						user_id = Integer.parseInt(request.getParameter("user_id"));
						pl = pservice.findPlayerBySUId(id,user_id);
						for(Player tplayer:pl) {
							tplayer.setState(-2);
							pservice.updatePlayer(tplayer);
							System.out.println("KICKED "+tplayer.getUsername()+"("+tplayer.getId()+")!!");
						}
						if(s.getHost_id()==user_id) {
							pl = pservice.findPlayerBySession(id);
							for(Player tplayer:pl) {
								if(tplayer.getUser_id()==user_id) {
									continue;
								}
								tplayer.setState(-2);
								pservice.updatePlayer(tplayer);
							}
						}
						break;
				}
				try {
					doGet(request, response);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				async.complete();
			}
		});
		
	}

}
