package servlet;

import java.io.IOException;

import javax.security.auth.login.LoginException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.User;
import service.UserService;

/**
 * Servlet implementation class SignInServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/SignInServlet" })
public class SignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserService uservice = new UserService();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = null;
		try {
			user = uservice.login(username, password);
		} catch (LoginException e) {
			// TODO Auto-generated catch block
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/client/security/signin.jsp").forward(request, response);
			return;
		}
		request.getSession().setAttribute("currentuser", user);
		response.sendRedirect(this.getServletContext().getContextPath()+"/client/main.jsp");
	}
}
