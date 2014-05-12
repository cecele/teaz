package hei.devweb.controllers;

import hei.devweb.metier.Manager;
import hei.devweb.model.Eleve;

import java.io.IOException;
import java.util.Date;



import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * ValiderAnnonce
 * 
 * Elle permet de valider une annonce par le responsable TEA
 * 
 * @author Projet
 *
 */
@WebServlet("/ValiderAnnonceServlet")
public class ValiderAnnonceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();    
		Integer nbOffreEnAttente = (Integer) (session.getAttribute("nbOffreEnAttente"));
		nbOffreEnAttente --;
		session.setAttribute( "nbOffreEnAttente", nbOffreEnAttente );
		Integer id  = Integer.parseInt(request.getParameter("id"));
		Date date = new Date();
		Manager.getInstance().annonce_validation(id,date);
		
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/pages/validationannonces.jsp");
		view.forward(request, response);
	}


}
