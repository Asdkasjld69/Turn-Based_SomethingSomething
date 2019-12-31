package servlet;

import java.io.IOException;
import java.util.List;

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
@WebServlet(asyncSupported = true, urlPatterns = { "/ListMapServlet" })
public class ListMapServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		MapService service = new MapService();
		String id = request.getParameter("search_id");
		String name = request.getParameter("search_name");
		String widthl = request.getParameter("search_widthl");
		String widthh = request.getParameter("search_widthh");
		String heightl = request.getParameter("search_heightl");
		String heighth = request.getParameter("search_heighth");
		List<Map> l = service.findMapByCondition(id, name, widthl, widthh, heightl, heighth);
		request.setAttribute("maps", l);
		request.setAttribute("loaded", 1);
		request.getRequestDispatcher("/client/subwindow/createmenu.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
