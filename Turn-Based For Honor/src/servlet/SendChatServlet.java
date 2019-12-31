package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Chat;
import bean.Chat_Public;
import bean.User;
import service.ChatService;

/**
 * Servlet implementation class SendChatServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/SendChatServlet" })
public class SendChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ChatService cservice = new ChatService();
		User user = (User)request.getSession().getAttribute("currentuser");
		String ssid = request.getParameter("id");
		int sid=-1;
		if(ssid!=null) {
			sid=Integer.parseInt(ssid);
		}
		if(user==null) {
			response.sendRedirect(this.getServletContext().getContextPath()+"/");
			return;
		}
		String type = request.getParameter("type");
		String message = request.getParameter("message");
		switch(type) {
		case"public":	Chat_Public c = new Chat_Public();
						c.setContent(message);
						c.setUser_id(user.getId());
						c.setUsername(user.getUsername());
						cservice.addPublicChat(c);
						request.getRequestDispatcher("/LoadChatServlet?type=public").forward(request, response);break;
		case"session": 	Chat sc = new Chat();
						sc.setContent(message);
						sc.setPlayer_id(user.getId());
						sc.setUsername(user.getUsername());
						sc.setSession_id(sid);
						cservice.addChat(sc);
						request.getRequestDispatcher("/LoadChatServlet?type=session&id="+sid).forward(request, response);break;
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
