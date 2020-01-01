package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Chat;
import bean.Chat_Public;
import bean.Session;
import bean.User;
import service.ChatService;
import service.SessionService;

/**
 * Servlet implementation class ChatServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/ChatServlet" })
public class ChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		ChatService cservice = new ChatService();
		SessionService ss = new SessionService();
		String type = request.getParameter("type");
		String ssid = request.getParameter("id");
		String init = request.getParameter("init");
		Session s = null;
		int sid = -1;
		switch (type) {
		case "public":
			List<Chat_Public> l = cservice.listPublicChat();
			request.setAttribute("chats", l);
			request.getRequestDispatcher("/client/segment/chat_public_segment.jsp").forward(request, response);
			break;
		case "session":
			sid = Integer.parseInt(ssid);
			List<Chat> sl = cservice.findChatBySessionId(sid);
			request.setAttribute("chats", sl);
			if(init!=null&&!init.trim().equals("")) {
				s = ss.findSessionById(sid);
				System.out.println(s);
				User tu = (User)request.getSession().getAttribute("currentuser");
				if(tu!=null){
					request.setAttribute("thisuser", s.getUser(tu.getId()));
				}
				else{
					response.sendRedirect(this.getServletContext().getContextPath()+"/");
				}
				request.setAttribute("session", s);
				request.getRequestDispatcher("/client/segment/chatframe_session_segment.jsp").forward(request, response);
			}
			else {
				request.getRequestDispatcher("/client/segment/chat_session_segment.jsp").forward(request, response);
			}
			
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
				User user = (User) request.getSession().getAttribute("currentuser");
				if (user == null) {
					try {
						response.sendRedirect(getServletContext().getContextPath() + "/");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return;
				}
				ChatService cservice = new ChatService();
				String ssid = request.getParameter("id");
				int sid = -1;
				String type = request.getParameter("type");
				String message = request.getParameter("message");
				switch (type) {
				case "public":
					Chat_Public c = new Chat_Public();
					c.setContent(message);
					c.setUser_id(user.getId());
					c.setUsername(user.getUsername());
					cservice.addPublicChat(c);
					break;
				case "session":
					sid = Integer.parseInt(ssid);
					Chat sc = new Chat();
					sc.setContent(message);
					sc.setPlayer_id(user.getId());
					sc.setUsername(user.getUsername());
					sc.setSession_id(sid);
					cservice.addChat(sc);
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
