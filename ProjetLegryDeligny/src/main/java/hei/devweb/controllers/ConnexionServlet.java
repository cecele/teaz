package hei.devweb.controllers;  

import java.io.IOException;  
import javax.servlet.ServletException; 
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 
import javax.servlet.http.HttpSession; 

import hei.devweb.model.Eleve; 
import hei.devweb.forms.ConnexionForm; 

public class ConnexionServlet extends HttpServlet {     
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String ATT_USER         = "eleve";     
	public static final String ATT_FORM         = "form";     
	public static final String ATT_SESSION_USER = "sessionEleve";     
	public static final String VUE              = "/WEB-INF/pages/index.jsp";    
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) 
			throws ServletException, IOException {         
		/* Affichage de la page de connexion */         
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );     
		}     
	
	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {         
		/* Préparation de l'objet formulaire */   
		
		ConnexionForm form = new ConnexionForm();      
		
		/* Traitement de la requête et récupération du bean en résultant */         
		Eleve eleve = form.connecterEleve( request );    
		
		/* Récupération de la session depuis la requête */         
		HttpSession session = request.getSession();          
		/**          
		 * * Si aucune erreur de validation n'a eu lieu, alors ajout du bean          
		 * * Utilisateur à la session, sinon suppression du bean de la session.          
		 * */         
		if ( form.getErreurs().isEmpty() ) {             
			session.setAttribute( ATT_SESSION_USER, eleve );         
			} else {             
				session.setAttribute( ATT_SESSION_USER, null );         
				}          
		/* Stockage du formulaire et du bean dans l'objet request */         
		request.setAttribute( ATT_FORM, form );         
		request.setAttribute( ATT_USER, eleve );          
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );     
		} 
	}