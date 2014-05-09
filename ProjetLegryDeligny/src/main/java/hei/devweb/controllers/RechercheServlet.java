package hei.devweb.controllers;

import hei.devweb.metier.Manager;
import hei.devweb.model.Eleve;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
		
		String matricule = request.getParameter("matricule");
		String nom = request.getParameter("nom");
		String classe = request.getParameter("classe");
		
		List<Eleve> eleves = new ArrayList<Eleve>();
		
		if(!matricule.equals(null)){
			eleves.add(Manager.getInstance().getEleveById(matricule));
		}
		else if(!nom.equals(null)){
			eleves = Manager.getInstance().rechercheEleveByNom(nom);
		}
		else{
			eleves = Manager.getInstance().rechercheEleveByClasse(classe);
		}
		request.setAttribute("eleves",eleves);
		response.sendRedirect("gestiontea");
	}

}
