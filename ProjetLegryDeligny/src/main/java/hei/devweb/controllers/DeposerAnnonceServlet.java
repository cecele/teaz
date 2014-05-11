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

public class DeposerAnnonceServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		HttpSession session = request.getSession();    
		Eleve eleve = (Eleve) (session.getAttribute("sessionEleve"));
		Integer cle_structure = eleve.getCle_structure();
		String nom_pres = eleve.getEleve_nom();
		String prenom_pres = eleve.getEleve_prenom();
		
		String description = request.getParameter("description");
		String StringDateTea = request.getParameter("date");
		String heureDebut = request.getParameter("hdebut");
		String heureFin = request.getParameter("hfin");
		String titre = request.getParameter("titre");
		String mail = request.getParameter("resp");
		Integer nbPlaces = Integer.parseInt(request.getParameter("nbplaces"));
		Date dateDepot = new Date();
		String nomStructure = Manager.getInstance().getNomStructure(cle_structure);
		
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
		
		Date dateTea = null;
		try {
			dateTea = sdf.parse(StringDateTea);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		Offre offre = new Offre(1,dateDepot,dateDepot, dateTea, heureDebut, heureFin, 0, description, mail, titre, cle_structure, nbPlaces, nomStructure, nom_pres,prenom_pres,0);
		
		//(modif c�line)  il faut que tu rajoute le nombre de place dispo !
		// il faut aussi g�rer le fait qu'une offre ne peux pas �tre d�poser sans place dispo, par d�faut ds la BDD il y a une place dispo!
	
		Manager.getInstance().ajouterAnnonce(offre);
		response.sendRedirect("annonces");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Integer cle_offre=Integer.parseInt(request.getParameter("id"));
		
		/*Offre offre = Manager.getInstance().listerOffreByEleve(matricule);
		request.setAttribute("offre",offre);*/
		
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/pages/deposerannonce.jsp");
		view.forward(request, response);
	}

}