package servlet;

import java.io.IOException;
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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		AsyncContext async = request.startAsync();
		async.start(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				SessionService sservice = new SessionService();
				String type = request.getParameter("type");
				String search_id = null;
				String search_map_id = null;
				String search_name = null;
				String search_map_name = null;
				String search_cap = null;
				String search_state = null;
				String search_password = null;
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
						search_map_name, search_cap, search_state, search_password);
				request.setAttribute("sessions", l);
				request.setAttribute("loaded", 1);
				try {
					request.getRequestDispatcher("/client/segment/sessionlist_segment.jsp").forward(request, response);
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
