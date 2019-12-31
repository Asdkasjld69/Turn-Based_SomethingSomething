package servlet;

import java.io.IOException;
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

/**
 * Servlet implementation class LoadSessionServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/LoadSessionServlet" })
public class LoadSessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String type = request.getParameter("type");
		String sid = request.getParameter("id");
		if(sid==null||sid=="") {
			System.out.println("!"+type+" "+sid);
		}
		int id = Integer.parseInt(sid);
		int pid = -1;
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
		switch(type) {
		case "timer":
		case "character_select":
		case "battlefield":
			Session cs = (bean.Session)request.getAttribute("session");
			User tu = (bean.User)request.getSession().getAttribute("currentuser");
			request.setAttribute("thisuser", null);
			request.setAttribute("thisuserpl", null);
			request.setAttribute("thisuserpcl", null);
			request.setAttribute("thisuserpcount", 0);
			request.setAttribute("thisuserpccount", 0);
			if(tu!=null){
				User insess = cs.getUser(tu.getId());
				List<bean.Player> pll = cs.getUserPlayer(tu.getId());
				List<bean.Player> plcl = cs.getPlayer(tu.getId());
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
			request.setAttribute("battlelogs", b);
			request.setAttribute("map", map);
			request.setAttribute("characters", pcl);
			request.getRequestDispatcher("/client/segment/session_battlefield_segment.jsp").forward(request, response);
			break;
		default:
			mservice = new MapService();
			pservice = new PlayerService();
			cservice = new ChatService();
			bservice = new BattleLogService();
			pid = Integer.parseInt(request.getParameter("user_id"));
			map = mservice.findMapById(s.getMap_id());
			pl = pservice.findUserBySession(id);
			pcl = pservice.findPlayerBySession(id);
			cl = cservice.findChatBySessionId(id);
			b = bservice.findUserBattleLogByTurn(s.getId(),pid,s.getState());
			request.setAttribute("map", map);
			request.setAttribute("players", pl);
			request.setAttribute("characters", pcl);
			request.setAttribute("chats", cl);
			request.setAttribute("battlelogs", b);
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
				int id = Integer.parseInt(sid);
				SessionService sservice = null;
				MapService mservice = null;
				PlayerService pservice = null;
				ChatService cservice = null;
				CharacterService chservice = null;
				List<Player> pl = null;
				List<Player> pcl = null;
				List<Chat> cl = null;
				Player p = null;
				Character ch = null;
				if (type == null) {
					type = "";
				}
				System.out.println("post");
				System.out.println(type);
				switch (type) {
				case "playerlist":
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
