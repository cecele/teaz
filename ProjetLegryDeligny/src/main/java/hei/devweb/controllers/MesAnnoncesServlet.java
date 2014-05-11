package hei.devweb.controllers;

import hei.devweb.metier.Manager;
import hei.devweb.model.Eleve;
import hei.devweb.model.Offre;
import hei.devweb.model.Tea;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MesAnnoncesServlet
 */
@WebServlet("/MesAnnoncesServlet")
public class MesAnnoncesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MesAnnoncesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();    
		
		Eleve eleve = (Eleve) (session.getAttribute("sessionEleve"));
		Integer clestructure = eleve.getCle_structure();
		// On créée une map d'offres et de teas qui lui correspondent
		Map<Offre, List<Tea>> couples = new HashMap<Offre, List<Tea>>(); 
		// On récupère toutes les offres dans une liste
		List<Offre> offres = Manager.getInstance().listerOffreByStructure(clestructure);
		// on parcourt la liste d'offres
		Iterator i = offres.iterator();
		while(i.hasNext()){
			Offre offre=(Offre) i.next();
			Integer cleoffre = offre.getCle_offre();
			List<Tea> teas = Manager.getInstance().getTeaByOffre(cleoffre); // On récupère les TEAs qui correspondent à cette offre précise
			couples.put(offre, teas ); // on ajoute à la map couples l'offre et les teas qui lui correspondent 
		}
		request.setAttribute("couples",couples);
		
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/pages/mesannonces.jsp");
		view.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
