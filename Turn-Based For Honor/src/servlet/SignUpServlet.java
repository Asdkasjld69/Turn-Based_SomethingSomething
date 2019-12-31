package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.User;
import security.ActiveCodeUtils;
import security.exception.RegisterException;
import service.UserService;

/**
 * Servlet implementation class SignUpServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/SignUpServlet" })
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserService uservice = new UserService();
		String username = request.getParameter("username");
		String gender = request.getParameter("gender");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		String email = request.getParameter("email");
		boolean flag = false;
		if(uservice.findUserByUsername(username)!=null) {
			request.setAttribute("usernameMessage", "<span style='color:#FF3333;'>用户名已存在</span>");
			flag = true;
		}
		if(uservice.findUserByEmail(email)!=null) {
			request.setAttribute("emailMessage", "<span style='color:#FF3333;'>邮箱已被使用</span>");
			flag = true;
		}
		if(!email.matches("^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$")) {
			flag = true;
		}
		if(!password.equals(password2)) {
			flag = true;
		}
		if(flag) {
			request.getRequestDispatcher("/client/security/signup.jsp").forward(request, response);
		}
		User nu = new User();
		nu.setUsername(username);
		nu.setGender(gender);
		nu.setPassword(password);
		nu.setEmail(email);
		nu.setActiveCode(ActiveCodeUtils.createActiveCode());
		try {
			uservice.register(nu);
			request.setAttribute("email", email);
			request.getRequestDispatcher("/client/security/signupsuccess.jsp").forward(request, response);
		} catch (RegisterException e) {
			// TODO Auto-generated catch block
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/client/security/signup.jsp").forward(request, response);
		}
		
	}

}
