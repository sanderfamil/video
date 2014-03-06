package be.vdab.servlets;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import be.vdab.dao.FilmDAO;
import be.vdab.entities.Film;

/**
 * Servlet implementation class FilmServlet
 */
@WebServlet("/film.htm")
public class FilmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/film.jsp";
	private static final String REDIRECT_URL = "%s/mandje.htm";
	private final transient FilmDAO filmDAO = new FilmDAO();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(request.getParameter("nummer")!=null){
		boolean inmandje = false;
		if(session!=null){
			@SuppressWarnings("unchecked")
			Set<Long> mandje=(Set<Long>)session.getAttribute("mandje");
			if(mandje.contains(Long.parseLong(request.getParameter("nummer")))){
				inmandje = true;
			}
			request.setAttribute("mandje", mandje);
			
		}
		Film film = filmDAO.readFilm(Long.parseLong(request
				.getParameter("nummer")));
		if(film!=null)
		{		request.setAttribute("film", film);
		request.setAttribute("inmandje",inmandje);}}
		request.getRequestDispatcher(VIEW).forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		Set<Long> filmNrsInMandje = (Set<Long>) session.getAttribute("mandje");
		if (filmNrsInMandje == null) {
			filmNrsInMandje = new LinkedHashSet<>();
		}
		long nummer = Long.parseLong(request.getParameter("filmnummer"));

		filmNrsInMandje.add(nummer);

		session.setAttribute("mandje", filmNrsInMandje);

		response.sendRedirect(response.encodeRedirectURL(String.format(
				REDIRECT_URL, request.getContextPath())));

	}

	@Resource(name = "jdbc/video")
	public void setDataSource(DataSource dataSource) {

		filmDAO.setDataSource(dataSource);
	}

}
