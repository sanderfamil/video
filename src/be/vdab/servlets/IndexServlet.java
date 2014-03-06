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

import be.vdab.dao.GenreDAO;
import be.vdab.entities.Genre;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/index.htm")
public class IndexServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
private final transient GenreDAO genreDAO = new GenreDAO();
private static final String VIEW = "/WEB-INF/JSP/index.jsp";
   
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	Iterable<Genre> genres = genreDAO.findAllGenre();
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
}
}