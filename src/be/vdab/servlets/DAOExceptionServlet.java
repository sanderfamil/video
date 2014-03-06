package be.vdab.servlets;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class DAOExceptionServlet
 */
@WebServlet("/DAOExceptionServlet")
public class DAOExceptionServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;
		private static final String VIEW= "/WEB-INF/JSP/daoexception.jsp";
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session!=null){
			@SuppressWarnings("unchecked")
		Set<Long> mandje = (Set<Long>) session.getAttribute("mandje");
			request.setAttribute("mandje",mandje);}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

}
