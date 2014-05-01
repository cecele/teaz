package hei.devweb.controllers;

import hei.devweb.metier.Manager;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PostulerServlet
 */
@WebServlet("/PostulerServlet")
public class PostulerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostulerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id  = Integer.parseInt(request.getParameter("id"));
		
		
		int nbPlaces = Manager.getInstance().getNbPlaces(id);
		System.out.println(nbPlaces);
		if(nbPlaces > 1)
			Manager.getInstance().offre_placemoins(id);
		else if(nbPlaces == 1)
			Manager.getInstance().annonce_miseHorsLigne(id);
		
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/pages/tea.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
