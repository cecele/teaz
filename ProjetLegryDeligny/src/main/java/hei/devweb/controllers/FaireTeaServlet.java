package hei.devweb.controllers;

import hei.devweb.metier.Manager;
import hei.devweb.model.Eleve;
import hei.devweb.model.Tea;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/FaireTeaServlet")
public class FaireTeaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FaireTeaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();    
		Eleve eleve = (Eleve) (session.getAttribute("sessionEleve"));
		Integer cleStructure = null;
		cleStructure = eleve.getCle_structure();
		Date datedujour = new Date();
		
		if(cleStructure != null){
			List<Tea> teas = Manager.getInstance().getTeaAValiderByStructure(cleStructure,datedujour);
			request.setAttribute("teas",teas);
		}
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/pages/fairetea.jsp");
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
