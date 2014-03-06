package be.vdab.servlets;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import be.vdab.dao.FilmDAO;
import be.vdab.dao.ReservatieDAO;
import be.vdab.entities.Film;

/**
 * Servlet implementation class RapportServlet
 */
@WebServlet("/rapport.htm")
public class RapportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "WEB-INF/JSP/rapport.jsp";
	private static final String REDIRECT_URL="%s/index.htm";
	private transient final ReservatieDAO reservatieDAO = new ReservatieDAO();
	private transient final FilmDAO filmDAO = new FilmDAO();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session!=null){
			@SuppressWarnings("unchecked")
		List<Film> nietGereserveerd = (List<Film>)session.getAttribute("nietGereserveerd");
		request.setAttribute("nietGereserveerd", nietGereserveerd);
		}

		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		session.invalidate();
		response.sendRedirect(response.encodeRedirectURL(String.format(
				REDIRECT_URL, request.getContextPath())));
	}
	@Resource(name = "jdbc/video")
	public void setDataSource(DataSource dataSource) {
		reservatieDAO.setDataSource(dataSource);
		filmDAO.setDataSource(dataSource);
	}

}
