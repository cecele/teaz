package hei.devweb.controllers;

import hei.devweb.metier.Manager;
import hei.devweb.model.Eleve;
import hei.devweb.model.Structure;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class FaireTeaValiderServlet
 */
@WebServlet("/FaireTeaValiderServlet")
public class FaireTeaValiderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FaireTeaValiderServlet() {
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
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();    
		Integer nbTeaAFaire = (Integer) (session.getAttribute("nbTeaAValiderByStructure"));
		nbTeaAFaire --;
		session.setAttribute( "nbTeaAValiderByStructure", nbTeaAFaire );
		Integer nbTeaEnAttente = (Integer) (session.getAttribute("nbTeaEnAttente"));
		nbTeaEnAttente ++;
		session.setAttribute( "nbTeaEnAttente", nbTeaEnAttente );
   
		Eleve eleve = (Eleve) (session.getAttribute("sessionEleve"));
		String matricule = eleve.getId_eleve();
		Integer cleStructure = eleve.getCle_structure();
		Integer cleTea = Integer.parseInt(request.getParameter("id"));
		
		Manager.getInstance().teaValidationByStructure(cleTea, cleStructure,matricule);
		
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/pages/fairetea.jsp");
		view.forward(request, response);
	}

}
