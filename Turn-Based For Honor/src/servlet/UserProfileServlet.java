package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.User;
import service.UserService;

/**
 * Servlet implementation class UserProfileServlet
 */
@WebServlet("/UserProfileServlet")
public class UserProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserService uservice = new UserService();
		String sid = request.getParameter("id");
		int id = Integer.parseInt(sid);
		User user = uservice.findUserById(id);
		if(user==null) {
			response.sendRedirect(this.getServletContext().getContextPath()+"/user/UNF.html");
			return;
		}
		request.setAttribute("user", user);
		User cuser = (User)request.getSession().getAttribute("currentuser");
		if(user.getId()==cuser.getId()) {
			request.setAttribute("isself", 1);
		}
		else {
			request.setAttribute("isself", 0);
		}
		request.getRequestDispatcher("/user/profile.jsp").forward(request, response);
	}

}
