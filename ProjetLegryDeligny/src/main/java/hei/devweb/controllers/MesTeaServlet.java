package hei.devweb.controllers;

import hei.devweb.metier.Manager;
import hei.devweb.model.Eleve;
import hei.devweb.model.Tea;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * MesTeaServlet
 * 
 * Récupère le profil de l'élève via la session, puis liste ses heures de TEA pour qu'il sache ou elles en sont
 *
 * @author Projet
 *
 */
/**
 * @author Projet
 *
 */
public class MesTeaServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();    
		Eleve eleve = (Eleve) (session.getAttribute("sessionEleve"));
		String matricule = eleve.getId_eleve();
		
		List<Tea> teas = Manager.getInstance().getTeaByEleve(matricule);
		request.setAttribute("teas",teas);
		
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/pages/mestea.jsp");
		view.forward(request, response);
	}

}
