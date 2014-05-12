package hei.devweb.controllers;

import hei.devweb.metier.Manager;
import hei.devweb.model.Eleve;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ValiderTeaServlet
 * 
 * Valide une ou plusieurs heures de TEA par le responsable TEA pour un élève donné
 */
/**
 * @author Projet
 *
 */
@WebServlet("/ValiderTeaServlet")
public class ValiderTeaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValiderTeaServlet() {
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
		Integer cletea = Integer.parseInt(request.getParameter("id"));
		
		HttpSession session = request.getSession(); 

		Integer nbTeaEnAttente = (Integer) (session.getAttribute("nbTeaEnAttente"));
		nbTeaEnAttente --;
		
		session.setAttribute( "nbTeaEnAttente", nbTeaEnAttente );
		
		Manager.getInstance().teaValidationByResponsable(cletea);
		
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/pages/validationtea.jsp");
		view.forward(request, response);
	}

}
