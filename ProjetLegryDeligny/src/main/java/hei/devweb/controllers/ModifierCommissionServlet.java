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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer idstruct = Integer.parseInt(request.getParameter("id"));
		Structure ancienneStructure = Manager.getInstance().getStructureByCle(idstruct);
		Eleve AncienPresident = (Eleve) Manager.getInstance().rechercheByParameter("", ancienneStructure.getStructure_president_nom(), ancienneStructure.getStructure_president_prenom(), "", "", true,true,true,true);
		String idAncienPresident = AncienPresident.getId_eleve();
		
		
		String ideleve = request.getParameter("ideleve");
		String StringDateDebut = request.getParameter("debut");
		String StringDateFin = request.getParameter("fin");
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
		
		Date dateDebut = null;
		Date dateFin = null;
		try {
			dateDebut = sdf.parse(StringDateDebut);
			dateFin = sdf.parse(StringDateFin);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Manager.getInstance().StructureChangement(ideleve,idstruct,dateDebut,dateFin);
		Manager.getInstance().ajouterDroits(ideleve,1);
		Manager.getInstance().ajouterDroits(idAncienPresident,0);
		
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/pages/administration.jsp");
		view.forward(request, response);
		
		
	}

}
