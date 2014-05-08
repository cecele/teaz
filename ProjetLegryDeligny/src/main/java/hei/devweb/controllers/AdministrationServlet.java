package hei.devweb.controllers;

import hei.devweb.metier.Manager;
import hei.devweb.model.Eleve;
import hei.devweb.model.Offre;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdministrationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		List<Eleve> responsablesStructure = Manager.getInstance().getEleveResponsables(1);
		request.setAttribute("respStruct",responsablesStructure);
		
		List<Eleve> adminbde = Manager.getInstance().getEleveResponsables(2);
		request.setAttribute("adminbde",adminbde);
		
		List<Eleve> admin = Manager.getInstance().getEleveResponsables(3);
		request.setAttribute("admin",admin);
		
		List<Eleve> superadmin = Manager.getInstance().getEleveResponsables(4);
		request.setAttribute("superadmin",superadmin);
		
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/pages/administration.jsp");
		view.forward(request, response);
	}

}
