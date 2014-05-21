package hei.devweb.controllers;

import hei.devweb.metier.Manager;
import hei.devweb.model.Eleve;
import hei.devweb.model.Structure;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ModifierCommissionServlet
 * 
 * Elle permet de modifier une structure existante afin de changer son président.
 * En modifiant le président de la structure, il faut aussi lui ajouter un mandat, c'est à dire une date de début et une date de fin.
 * 
 */
/**
 * @author Projet
 *
 */
@WebServlet("/ModifierCommissionServlet")
public class ModifierCommissionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifierCommissionServlet() {
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
	
		
		int id = Integer.parseInt(request.getParameter("id"));
		Structure structure = Manager.getInstance().getStructureByCle(id);
		request.setAttribute("structure",structure);
		
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/pages/modifiercommission.jsp");
		view.forward(request, response); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Integer idstruct = Integer.parseInt(request.getParameter("id"));
		String idAncienPresident = Manager.getInstance().getPresidentIdById(idstruct);

		
		String ideleve = request.getParameter("ideleve");
		String StringDateDebut = request.getParameter("debut");
		String StringDateFin = request.getParameter("fin");
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
		Integer profil1 = 1;
		Integer profil2 = 0;
		Date dateDebut = null;
		Date dateFin = null;
		try {
			dateDebut = sdf.parse(StringDateDebut);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			dateFin = sdf.parse(StringDateFin);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Manager.getInstance().StructureChangement(ideleve,idstruct,dateDebut,dateFin);
		Manager.getInstance().ajouterDroits(ideleve,profil1);
		Manager.getInstance().ajouterDroits(idAncienPresident,profil2);
		
		response.sendRedirect("administration"); 
		
		
	}

}
