package hei.devweb.controllers;

import hei.devweb.metier.Manager;
import hei.devweb.model.Eleve;
import hei.devweb.model.Offre;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RechercheServlet
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String matricule = "";
		String nom = "";
		String prenom = "";
		String classe = "";
		String orderBy = "id_eleve";
		Boolean encours = true;
		Boolean ajour = true;
		Boolean pasajour = true;
		Boolean diplome = true;
		classe = request.getParameter("classe");
		
		if(request.getParameter("rech").equals("1"))
		{
			matricule = request.getParameter("matricule");
			classe = "tous";
		}
		if(request.getParameter("rech").equals("2")){
			
			nom = request.getParameter("nom");
			prenom = request.getParameter("prenom");
			classe = request.getParameter("classe");
			System.out.println(request.getParameter("encours"));
			if(!request.getParameter("encours").equals("true"))
				encours = false;
			if(!request.getParameter("ajour").equals("true"))
				ajour = false;
			if(!request.getParameter("pasajour").equals("true"))
				pasajour = false;
			if(!request.getParameter("diplome").equals("true"))
				diplome = false;
		}
		
		
		List<Eleve> eleves = Manager.getInstance().rechercheByParameter(matricule,nom,prenom,classe,orderBy,diplome,encours,ajour,pasajour);
		
		request.setAttribute("eleves",eleves);
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/pages/gestioneleves.jsp");
		view.forward(request, response);
	}

}
