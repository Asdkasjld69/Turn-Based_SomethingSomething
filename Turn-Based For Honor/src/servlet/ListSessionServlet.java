package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Session;
import service.SessionService;

/**
 * Servlet implementation class ListSessionServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/ListSessionServlet" })
public class ListSessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static List<Session> last_sessions = new ArrayList<Session>();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		SessionService sservice = new SessionService();
		String type = request.getParameter("type");
		String check = request.getParameter("check");
		boolean checkflag = false;
		if(check!=null&&!check.trim().equals("")) {
			checkflag = true;
		}
		String search_id = null;
		String search_map_id = null;
		String search_name = null;
		String search_map_name = null;
		String search_username = null;
		String search_cap = null;
		String search_state = null;
		String search_password = null;
		String currentusername = request.getParameter("currentusername");
		search_username = request.getParameter("search_username");
		if (type != null) {
			switch (type) {
			case "SUPER":
				search_id = request.getParameter("search_id");
				search_map_id = request.getParameter("search_map_id");
			case "NORMAL":
				search_name = request.getParameter("search_name");
				search_map_name = request.getParameter("search_map_name");
				search_cap = request.getParameter("search_cap");
				search_state = request.getParameter("search_state");
				search_password = request.getParameter("search_password");
				break;
			}
		}
		List<Session> l = sservice.findSessionByConditions(search_id, search_map_id, search_name,
				search_map_name, search_cap, search_state, search_password, search_username, currentusername, type);
		if(checkflag) {
			if(l.size()!=last_sessions.size()) {
				System.out.println("NEW SESSION!!!");
				last_sessions = l;
			}
			request.setAttribute("sessnum", l.size());
			request.getRequestDispatcher("/client/flag/checksessionupdate.jsp").forward(request, response);
		}
		else {
			request.setAttribute("sessions", l);
			request.getRequestDispatcher("/client/segment/sessionlist_segment.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String type = request.getParameter("type");
		SessionService ss = new SessionService();
		Session s = null;
		switch(type) {
		case"remove":
				int id = Integer.parseInt(request.getParameter("id"));
				s = ss.findSessionById(id);
				s.setState(-2);
				ss.updateSession(s);break;
		}
		doGet(request, response);
	}

}
