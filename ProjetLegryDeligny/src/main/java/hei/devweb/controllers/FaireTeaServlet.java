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

@WebServlet("/FaireTeaServlet")
public class FaireTeaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FaireTeaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Tea> teas = Manager.getInstance().listerTeaAFaire();
		request.setAttribute("teas",teas);
		
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/pages/fairetea.jsp");
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}