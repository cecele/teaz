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
		
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
		
		Date dateTea = null;
		try {
			dateTea = sdf.parse(StringDateTea);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(dateTea.after(dateDepot)){
			
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
		else{
			message = "Il faut que la date de la TEA soit après la date d'aujourd'hui";
			request.setAttribute("message",message);
			response.sendRedirect("deposerannonce?id=-1");
		}
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Integer cle_offre=Integer.parseInt(request.getParameter("id"));
		String message ="";
		
		if(cle_offre != 0){ // édit d'une offre
			Offre offre = Manager.getInstance().getOffreById(cle_offre);
			request.setAttribute("offre",offre);
		}
		else if(cle_offre == -1 ){
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