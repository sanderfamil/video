package be.vdab.servlets;

import java.io.IOException;
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
import be.vdab.dao.GenreDAO;
import be.vdab.entities.Film;
import be.vdab.entities.Genre;

/**
 * Servlet implementation class GenreServlet
 */
@WebServlet("/genre.htm")
public class GenreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final transient FilmDAO filmDAO = new FilmDAO();
	private final transient GenreDAO genreDAO = new GenreDAO();
	private static final String VIEW = "/WEB-INF/JSP/genre.jsp";
	
      
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Iterable<Genre> genres = genreDAO.findAllGenre();
		if(request.getParameter("nummer")!=null){
		Genre genre = genreDAO.readGenre(Long.parseLong(request.getParameter("nummer")));
		if(genre!=null){
		Iterable<Film> films = filmDAO.findByGenre(Long.parseLong(request.getParameter("nummer")));
		request.setAttribute("genre", genre);
		request.setAttribute("films", films);}}
		request.setAttribute("genres",genres);

		HttpSession session = request.getSession(false);
		if(session!=null){
			@SuppressWarnings("unchecked")
		Set<Long> mandje = (Set<Long>) session.getAttribute("mandje");
			request.setAttribute("mandje",mandje);}
		request.getRequestDispatcher(VIEW).forward(request, response);
		

	}
	@Resource(name= "jdbc/video")
	public void setDataSource(DataSource dataSource){
		genreDAO.setDataSource(dataSource);
		filmDAO.setDataSource(dataSource);
	}
	



}
