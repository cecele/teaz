package hei.devweb.controllers;

import hei.devweb.metier.Manager;
import hei.devweb.model.Eleve;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AjouterDroit
 * 
 * La servlet AjouterDroit permet de gérer les droits des utilisateurs sur le panneau d'administration.
 *
 * Elle récupère en paramètre le matricule, le nom et le prénom rentrés dans un formulaire, ainsi que le statut à donner à l'élève concerné via une variable secrète "act"<br/> 
 * Elle compare ensuite le nom et le prénom rentrés avec ceux renvoyés par la base de donnée pour le matricule envoyé.<br/>
 * Si les prénoms et noms sont correct, elle appelle la méthode ajouterDroits qui prend le matricule de l'élève et son futur statut comme paramètre pour lui donner les droits<br/>
 *
 * 
 * @author Projet
 */
@WebServlet("/AjouterDroit")
public class AjouterDroitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    /**
     * 
     */
    public AjouterDroitServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
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
