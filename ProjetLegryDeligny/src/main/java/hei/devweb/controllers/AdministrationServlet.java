package hei.devweb.controllers;

import hei.devweb.metier.Manager;
import hei.devweb.model.Eleve;
import hei.devweb.model.Offre;
import hei.devweb.model.Structure;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * <b>Page d'administration</b>
 * 
 * <p>Elle permet de lister les différents groupes par niveau d'accès au site:</p>
 * <ul>
 * <li>Les responsables de commission (profil 1). Ils peuvent poster des annonces et valider la premiere partie d'une heure de TEA</li>
 * <li>Les responsables du BDE (profil2). C'est le profil du responsable TEA. Il peut valider les annonces postées et valider définitivement une offre de TEA. Il peut aussi consulter la liste des élèves, faire une recherche et afficher les détail d'un élève</li>
 * <li>Les responsables de l'administration (profil2). Le profil permet de faire une recherche dans la BDD pour consulter les heures de tea faites pare les élèves</li>
 * <li>Le super admin (profil3). Il peut accéder à tout le site pour effectuer la maintenance nécessaire</li>
 * 
 * @author Projet
 *
 */
public class AdministrationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		List<Eleve> responsablesStructure = Manager.getInstance().getEleveResponsables(1);
		request.setAttribute("respStruct",responsablesStructure);
		
		List<Eleve> adminbde = Manager.getInstance().getEleveResponsables(2);
		request.setAttribute("adminbde",adminbde);
		
		List<Eleve> admin = Manager.getInstance().getEleveResponsables(3);
		request.setAttribute("admin",admin);
		
		List<Eleve> superadmin = Manager.getInstance().getEleveResponsables(4);
		request.setAttribute("superadmin",superadmin);
		
		List<Structure> commission = Manager.getInstance().getStructure_OrdreNom();
		request.setAttribute("commissions",commission);
		
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/pages/administration.jsp");
		view.forward(request, response);
	}

}
