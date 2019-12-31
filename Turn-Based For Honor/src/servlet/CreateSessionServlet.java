package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Player;
import bean.Session;
import bean.User;
import service.PlayerService;
import service.SessionService;

/**
 * Servlet implementation class CreateSessionServlet
 */
@WebServlet("/CreateSessionServlet")
public class CreateSessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user = (User) request.getSession().getAttribute("currentuser");
		String name = request.getParameter("session_name");
		String password = request.getParameter("session_password");
		if(password!=null&&password.trim().equals("")) {
			password=null;
		}
		else {
			password=password.trim();
		}
		int mid = Integer.parseInt(request.getParameter("map_id"));
		int cap = Integer.parseInt(request.getParameter("cap"));
		SessionService sservice = new SessionService();
		Session s = new Session();
		s.setName(name);
		s.setMap_id(mid);
		s.setCap(cap);
		s.setPassword(password);
		Session sess = sservice.addAndReturnSession(s);
		PlayerService pservice = new PlayerService();
		Player p = new Player();
		int sid = sess.getId();
		p.setSession_id(sid);
		p.setUser_id(user.getId());
		pservice.addPlayer(p);
		request.setAttribute("target", this.getServletContext().getContextPath()+"/LoadSessionServlet?id="+sid+"&user_id="+user.getId());
		request.setAttribute("self", this.getServletContext().getContextPath()+"/client/subwindow/createmenu.jsp");
		request.getRequestDispatcher("/misc/relay.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
