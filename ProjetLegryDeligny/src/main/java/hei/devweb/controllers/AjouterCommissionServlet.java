package hei.devweb.controllers;

import hei.devweb.metier.Manager;
import hei.devweb.model.Eleve;
import hei.devweb.model.Structure;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AjouterCommissionServlet
 * 
 * Elle permet de gérer l'ajout d'une nouvelle commission.<br/>
 * Un formulaire arrive sur cette servlet avec le nom de la nouvelle commission comme paramètre.<br/>
 * La servlet appelle une méthode de création de structure qui prend le nom en paramètre et qui génère un id en auto incrémentation.<br/>
 * Elle récupère ensuite cet id via un méthode qui prend comme paramètre le nom de la commission nouvellement créée, et redirige via la page de modification avec comme paramètre l'id de la structure<br/>
 *
 * @author Projet
 *
 */
@WebServlet("/AjouterCommissionServlet")
public class AjouterCommissionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    /**
     * 
     */
    /**
     * 
     */
    public AjouterCommissionServlet() {
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
	 * 
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * 
	 */
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nom = request.getParameter("nom");

		Manager.getInstance().CreateStructure(nom);
		String cle_structure = String.valueOf(Manager.getInstance().getCleByNom(nom));
		
		
		response.sendRedirect("modifiercommission?id="+cle_structure); 
	}

}
