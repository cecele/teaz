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
import javax.servlet.http.HttpSession;


/**
 * Postuler Servlet
 * 
 * Elle permet de faire postuler un élève à une offre.
 * Si le nombre de places disponibles est supérieur à 1, on ajoute la TEA au profil de l'élève afin qu'il ne puisse plus postuler,
 * puis on retire une place disponible à l'offre.
 * Sinon, si le nombre de places disponibles est égal à 1, en met l'annonce hors ligne en plus de la rajouter à l'élève, sans oublier de retirer une place disponible à l'offre
 * 
 * @author Projet
 *
 */
@WebServlet("/PostulerServlet")
public class PostulerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
	}
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		HttpSession session = request.getSession();    
		Eleve eleve = (Eleve) (session.getAttribute("sessionEleve"));
		String matricule = eleve.getId_eleve();
		
		Integer id = Integer.parseInt(request.getParameter("id"));
		System.out.println("id");
		int nbPlaces = Manager.getInstance().getNbPlaces(id);
		
		if(nbPlaces > 1)
		{
			Manager.getInstance().ajouterTea(id,matricule);
			Manager.getInstance().offre_placemoins(id);
		}
		else if(nbPlaces == 1)
		{
			Manager.getInstance().ajouterTea(id,matricule);
			Manager.getInstance().annonce_miseHorsLigne(id);
			Manager.getInstance().offre_placemoins(id);
		}
		
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/pages/tea.jsp");
		view.forward(request, response);
	}

}
