package servlet;

import java.io.IOException;
import java.sql.Timestamp;
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
import service.BattleLogService;
import service.MapService;
import service.PlayerService;
import service.SessionService;

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
		BattleLogService bs = new BattleLogService();
		SessionService ss = new SessionService();
		MapService ms = new MapService();
		PlayerService ps = new PlayerService();
		Session s = ss.findSessionById(sid);
		Map m = ms.findMapById(s.getMap_id());
		List<BattleLog> bl = bs.findBattleLogByTurn(sid, s.getState());
		List<Player> pl = ps.findPlayerBySession(sid);
		if(bl.size()>=s.getPcount()&&s.getUcount()>1&&s.getPcount()>1) {
			for(BattleLog b:bl) {
				Player p = ps.findPlayerById(b.getPlayer_id());
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
			s.setState(s.getState()+1);
			ss.updateSession(s);
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

	private void hitDef(Player psrc,int dmg,int[][] pattern,int[][] map) {
		
	}
	
	private void moveTo(Player psrc,int posx,int posy,int[][] map) {
		
	}
	
	private void squeeze(Player psrc,Player pdes) {
		
	}
}
