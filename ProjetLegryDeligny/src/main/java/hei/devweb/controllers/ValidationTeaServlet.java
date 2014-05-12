package hei.devweb.controllers;

import hei.devweb.metier.Manager;
import hei.devweb.model.Tea;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Affiche au responsable TEA les heures de TEA déjà validées par les responsables de structure mais pas encore validés définivement
 * 
 * @author Projet
 *
 */
@WebServlet("/ValidationTeaServlet")
public class ValidationTeaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ValidationTeaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Tea> teas = Manager.getInstance().getTeaAValiderByRespTea();
		request.setAttribute("teas",teas);
		
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/pages/validationtea.jsp");
		view.forward(request, response);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
