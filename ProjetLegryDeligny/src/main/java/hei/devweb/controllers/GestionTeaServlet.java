package hei.devweb.controllers;

import hei.devweb.metier.Manager;
import hei.devweb.model.Eleve;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GestionTeaServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		List<Eleve> eleves = Manager.getInstance().getEleveTotal();
		request.setAttribute("eleves",eleves);
		
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/pages/gestiontea.jsp");
		view.forward(request, response);
	}

}
