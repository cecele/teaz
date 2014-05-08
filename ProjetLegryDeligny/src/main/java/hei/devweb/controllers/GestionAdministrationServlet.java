package hei.devweb.controllers;

import hei.devweb.metier.Manager;
import hei.devweb.model.Eleve;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GestionAdministrationServlet
 */
@WebServlet("/GestionAdministrationServlet")
public class GestionAdministrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionAdministrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
			String id  = request.getParameter("id");
			Manager.getInstance().retirerDroits(id);
			
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/pages/administration.jsp");
			view.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("matricule");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		int droit = Integer.parseInt(request.getParameter("act"));
		
		Eleve test = Manager.getInstance().getEleveById(id);
		if(nom.equalsIgnoreCase(test.getEleve_nom()) && prenom.equalsIgnoreCase(test.getEleve_prenom())){
			Manager.getInstance().ajouterDroits(id,droit);
			
		}
		response.sendRedirect("administration");

	}

}
