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
       
    /**
     *  Servlet FaireTea
     *  
     *  Elle permet d'afficher les heures de tea en attente de première validation pour une structure donnée,
     *  seulement si la date de la TEA est dépassée. Cela évite la validation de TEA avant même que l'évènement n'ait eu lieu
     *  
     *  
     */
    public FaireTeaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();    //recup de la session
		Eleve eleve = (Eleve) (session.getAttribute("sessionEleve"));
		Integer cleStructure = null;
		cleStructure = eleve.getCle_structure();
		Date datedujour = new Date();
		
		if(cleStructure != null){ // La clé structure est nulle pour les utilisateurs classiques
			List<Tea> teas = Manager.getInstance().getTeaAValiderByStructure(cleStructure,datedujour);
			request.setAttribute("teas",teas);
		}
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/pages/fairetea.jsp");
		view.forward(request, response);
	}

	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
