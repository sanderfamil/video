package be.vdab.servlets;


import java.io.IOException;
import java.math.BigDecimal;
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
import be.vdab.entities.Film;

/**
 * Servlet implementation class MandjeServlet
 */
@WebServlet("/mandje.htm")
public class MandjeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW="/WEB-INF/JSP/mandje.jsp";
	private transient final FilmDAO filmDAO= new FilmDAO();
	private static final String REDIRECT_URL = "%s/mandje.htm";
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session!=null){
			@SuppressWarnings("unchecked")
			Set<Long> mandje = (Set<Long>) session.getAttribute("mandje");
			List<Film> filmLijst = new ArrayList<>();
			BigDecimal totaal = BigDecimal.ZERO;
			for(long filmNr:mandje){
				filmLijst.add(filmDAO.readFilm(filmNr));
				totaal = totaal.add(filmDAO.readFilm(filmNr).getPrijs());
				
			}
			request.setAttribute("mandje", mandje);
			request.setAttribute("totaal", totaal);
			request.setAttribute("filmLijst", filmLijst);
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		@SuppressWarnings("unchecked")
		Set<Long> filmNrsInMandje = (Set<Long>) session.getAttribute("mandje");
		
		if(request.getParameter("nummer")!=null){
			try{
				for (String nummerAlsString:request.getParameterValues("nummer")){
					filmNrsInMandje.remove(Long.parseLong(nummerAlsString));
				}
			}catch (Exception ex){
				
			}
			session.setAttribute("mandje", filmNrsInMandje);
		}
		response.sendRedirect(response.encodeRedirectURL(String.format(
				REDIRECT_URL, request.getContextPath())));

	}

	
	@Resource(name = "jdbc/video")
	public void setDataSource(DataSource dataSource) {

		filmDAO.setDataSource(dataSource);
	}


}
