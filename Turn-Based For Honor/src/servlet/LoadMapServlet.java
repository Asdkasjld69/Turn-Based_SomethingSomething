package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Map;
import service.MapService;

/**
 * Servlet implementation class LoadMapServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/LoadMapServlet" })
public class LoadMapServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		MapService service = new MapService();
		String smid = request.getParameter("map_id");
		int mid = Integer.parseInt(smid);
		Map map = service.findMapById(mid);
		request.setAttribute("map", map);
		request.getRequestDispatcher("/client/map.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
