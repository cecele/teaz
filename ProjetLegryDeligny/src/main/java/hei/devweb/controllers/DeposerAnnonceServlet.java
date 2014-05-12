package hei.devweb.controllers;

import hei.devweb.metier.Manager;
import hei.devweb.model.Eleve;
import hei.devweb.model.Offre;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/** Servlet DéposerAnnonce
 * 
 * Elle permet d'ajouter ou de modifier une annonce.
 * Une annonce est composée de plusieurs informations rentrées à partir d'un formulaire:
 * <ul>
 * <li>Une date de TEA, qui est la date à laquelle se passera l'évènement</li>
 * <li>Un titre</li>
 * <li>Une heure de début et de fin</li>
 * <li>Une courte description</li>
 * <li>Un mail de contact</li>
 * <li>Le nombre de places disponibles</li>
 * </ul>
 * 
 * Elle est ensuite complétée par la clé de la structure à laquelle appartient l'utilisateur, récupéée dans sa session.
 * Si la date de l'offre est plus vieille que la date du jour, elle rentre dans le test de clé de l'offre:<br/>
 * Si la clé de l'offre est à 0, c'est une création donc elle appelle une méthode dédiée.
 * Sinon, elle appelle la méthode de modification et renvoie les données de cette offre dans le formulaire, pour ne pas avoir à tout retaper.
 * 
 * 
 * @author Projet
 *
 */
public class DeposerAnnonceServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		HttpSession session = request.getSession();    
		Eleve eleve = (Eleve) (session.getAttribute("sessionEleve"));
		Integer cle_structure = eleve.getCle_structure();
		String nom_pres = eleve.getEleve_nom();
		String prenom_pres = eleve.getEleve_prenom();
		Integer cle_offre=Integer.parseInt(request.getParameter("id"));
		String description = request.getParameter("description");
		String StringDateTea = request.getParameter("date");
		String heureDebut = request.getParameter("hdebut");
		String heureFin = request.getParameter("hfin");
		String titre = request.getParameter("titre");
		String mail = request.getParameter("resp");
		Integer nbPlaces = Integer.parseInt(request.getParameter("nbplaces"));
		Date dateDepot = new Date();
		String nomStructure = Manager.getInstance().getNomStructure(cle_structure);
		String message ="";
		
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd"); // formatage de la date
		
		Date dateTea = null; // Création d'une date nulle
		try {
			dateTea = sdf.parse(StringDateTea); // On formate la date récupére en string vers un format util
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(dateTea.after(dateDepot)){ // Comparaison de la date de l'offre avec celle du jour
			
			Offre offre = new Offre(cle_offre,dateDepot,dateDepot, dateTea, heureDebut, heureFin, 0, description, mail, titre, cle_structure, nbPlaces, nomStructure, nom_pres,prenom_pres,0);
		
			if(cle_offre == 0){ 
				//Création offre
				Manager.getInstance().ajouterAnnonce(offre);
				System.out.println("Création");
				response.sendRedirect("mesannonces");
				message = "Offre créée correctement";
			}
			else{ 
				// modification offre
				Manager.getInstance().AnnonceModification(offre);
				System.out.println("Update");
				response.sendRedirect("mesannonces");
				message = "Offre modifiée correctement";
			}
		}
		else{ // La date est antérieur à aujourd'hui
			message = "Il faut que la date de la TEA soit après la date d'aujourd'hui";
			request.setAttribute("message",message);
			response.sendRedirect("deposerannonce?id=-1");
		}
		
	}
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Integer cle_offre=Integer.parseInt(request.getParameter("id"));
		String message ="";
		
		if(cle_offre != 0){ // édit d'une offre
			Offre offre = Manager.getInstance().getOffreById(cle_offre);
			request.setAttribute("offre",offre);
		}
		else if(cle_offre == -1 ){ // Erreur dans la date
			Offre offre = new Offre(0,null,null,null,"", "", 0,"","","",0,1,"","","",0);
			request.setAttribute("offre",offre);
			message = "Il faut que la date de la TEA soit après la date d'aujourd'hui";
		}
		else{ // création d'une offre
			Offre offre = new Offre(0,null,null,null,"", "", 0,"","","",0,1,"","","",0);
			request.setAttribute("offre",offre);
		}
		request.setAttribute("message",message);
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/pages/deposerannonce.jsp");
		view.forward(request, response);
	}

}