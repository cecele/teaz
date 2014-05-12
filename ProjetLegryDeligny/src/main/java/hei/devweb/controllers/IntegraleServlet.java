package hei.devweb.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Projet
 *
 */
@WebServlet("/IntegraleServlet")
public class IntegraleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id  = Integer.parseInt(request.getParameter("id"));
		
		if(id==1)
		{
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/pages/integrale.jsp");
			view.forward(request, response);
		}
		else if(id==2)
		{
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/pages/comite.jsp");
			view.forward(request, response);
		}
		else if(id==3)
		{
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/pages/pes.jsp");
			view.forward(request, response);
		}
		else if(id==4)
		{
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/pages/bda.jsp");
			view.forward(request, response);
		}
		else if(id==5)
		{
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/pages/bcs.jsp");
			view.forward(request, response);
		}
		else if(id==6)
		{
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/pages/pet.jsp");
			view.forward(request, response);
		}

	}	

}
