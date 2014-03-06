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

import be.vdab.dao.KlantDAO;
import be.vdab.entities.Klant;

/**
 * Servlet implementation class KlantServlet
 */
@WebServlet("/klant.htm")
public class KlantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/klant.jsp";

	private static transient KlantDAO klantDAO = new KlantDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String input = request.getParameter("achternaam");
		if(input != null && !input.equals("")){
			Iterable<Klant> klanten = klantDAO.searchKlant(input);
			request.setAttribute("klanten", klanten);
		}
		else{
			if(request.getParameter("zoeken")!= null){
				request.setAttribute("fouten", "Geef minstens 1 teken in");
			}
		}
		HttpSession session = request.getSession(false);
		if(session!=null){
			@SuppressWarnings("unchecked")
		Set<Long> mandje = (Set<Long>) session.getAttribute("mandje");
			request.setAttribute("mandje",mandje);}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}



	@Resource(name = "jdbc/video")
	public void setDataSource(DataSource dataSource) {

		klantDAO.setDataSource(dataSource);
	}


}
