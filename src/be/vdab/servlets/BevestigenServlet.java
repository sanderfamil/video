package be.vdab.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
import be.vdab.dao.KlantDAO;
import be.vdab.dao.ReservatieDAO;
import be.vdab.entities.Film;
import be.vdab.entities.Klant;
import be.vdab.entities.Reservatie;

/**
 * Servlet implementation class BevestigenServlet
 */
@WebServlet("/bevestigen.htm")
public class BevestigenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/bevestigen.jsp";
	private static final String REDIRECT_URL="%s/rapport.htm";
	private static transient FilmDAO filmDAO = new FilmDAO();
	private static transient KlantDAO klantDAO = new KlantDAO();
	private static transient ReservatieDAO reservatieDAO = new ReservatieDAO();
			
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session!=null){
		@SuppressWarnings("unchecked")
		Set<Long> mandje = (Set<Long>) session.getAttribute("mandje");
		if(mandje!=null){
			request.setAttribute("aantalfilms", mandje.size());
			request.setAttribute("mandje",mandje);}
					
		}
		else{
		request.setAttribute("aantalfilms", 0);}
		if(request.getParameter("nummer")!=null){
		Klant klant = klantDAO.readKlant(Long.parseLong(request.getParameter("nummer")));
		request.setAttribute("klant", klant);}
		request.getRequestDispatcher(VIEW).forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		long klantnummer = Long.parseLong(request.getParameter("klantnummer"));
		if(session!=null){
			@SuppressWarnings("unchecked")
			Set<Long> mandje=(Set<Long>)session.getAttribute("mandje");
			List<Film> films = new ArrayList<>();
			List<Film> nietGereserveerd = new ArrayList<>();
			for(long filmnummer : mandje){
				films.add(filmDAO.readFilm(filmnummer));
			}
			for(Film film:films){
				if(film.getVoorraad()>film.getGereserveerd()){
					Reservatie reservatie = new Reservatie(klantnummer,film.getFilmNr());
					reservatieDAO.newReservatie(reservatie);
					
				}
				else{nietGereserveerd.add(film);}

			}
			if(nietGereserveerd.isEmpty()){
				session.invalidate();
			}
			else{session.setAttribute("nietGereserveerd", nietGereserveerd);}
		
		}
		response.sendRedirect(response.encodeRedirectURL(String.format(
				REDIRECT_URL, request.getContextPath())));
	}
	
	
	@Resource(name= "jdbc/video")
	public void setDataSource(DataSource dataSource){
		klantDAO.setDataSource(dataSource);
		filmDAO.setDataSource(dataSource);
		reservatieDAO.setDataSource(dataSource);
	}
	

}
