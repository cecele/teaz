package hei.devweb.controllers;

import hei.devweb.metier.Manager;
import hei.devweb.model.Eleve;
import hei.devweb.model.Offre;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RechercheServlet
 * 
 * Elle permet d'orienter les requetes à faire par la méthode de recherche.
 * Elle renvoie les paramètres donnés par l'utilisateur. Il existe deux formes de recherches :
 * <ul><li>Par matricule<li>
 * <li>Par un autre critère</li>
 * </ul>
 * 
 * SI c'est par matricule, on définit par défaut qu'on cherche un matricule, les autres champs étants vides.
 * Si c'est un autre critère, on laisse le matricule nul et on appelle la méthode de recherche avec les critères.
 * 
 * Puis on liste les résultats et on donne le nombre de résultats.
 */
@WebServlet("/RechercheServlet")
public class RechercheServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RechercheServlet() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String matricule = "";
		String nom = "";
		String prenom = "";
		String classe = "";
		String orderBy = request.getParameter("tri");
		Boolean encours = true;
		Boolean ajour = true;
		Boolean pasajour = true;
		Boolean diplome = true;
		classe = request.getParameter("classe");
		
		
		System.out.println(orderBy);
		if(request.getParameter("rech").equals("1"))
		{
			matricule = request.getParameter("matricule");
			classe = "tous";
		}
		
		if(request.getParameter("rech").equals("2")){
			
			encours = request.getParameter("encours_id")==null?false:true;                         
			ajour =request.getParameter("ajour_id")==null?false:true;                         
			pasajour = request.getParameter("pasajour_id")==null?false:true;        
			diplome = request.getParameter("diplome_id")==null?false:true;
			nom = request.getParameter("nom");
			prenom = request.getParameter("prenom");
			classe = request.getParameter("classe");
			
			
			}
		
		List<Eleve> eleves = Manager.getInstance().rechercheByParameter(matricule,nom,prenom,classe,orderBy,diplome,encours,ajour,pasajour);
		Integer results = Manager.getInstance().sizeReponse(eleves);
		
		request.setAttribute("eleves",eleves);
		request.setAttribute("results", results);
		
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/pages/gestioneleves.jsp");
		view.forward(request, response);
	}
}

