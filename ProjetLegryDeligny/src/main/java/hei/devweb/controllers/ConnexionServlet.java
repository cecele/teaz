package hei.devweb.controllers;  

import java.io.IOException;  
import java.util.Date;

import javax.servlet.ServletException; 
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 
import javax.servlet.http.HttpSession; 

import hei.devweb.metier.Manager;
import hei.devweb.model.Eleve; 
import hei.devweb.model.Structure;
import hei.devweb.forms.ConnexionForm; 

/**
 * Servlet de connexion.
 * 
 * Elle récupère les informations rentrées dans un formulaire de connexion, puis va chercher l'elève correspondant au matricule donnée dans la BDD, et compare les mots de passe.
 * Si le mot de passe est correct, elle crée une session correspondant à l'élève avec des données supplémentaires comme le nombre d'annonces en attente de validation, le nombres d'heures à valider ...
 * 
 * 
 * @author Projet
 *
 */
public class ConnexionServlet extends HttpServlet {     
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String ATT_USER         = "eleve";     
	public static final String ATT_FORM         = "form";     
	public static final String ATT_SESSION_USER = "sessionEleve";     
	public static final String VUE              = "/WEB-INF/pages/index.jsp";    
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void doGet( HttpServletRequest request, HttpServletResponse response ) 
			throws ServletException, IOException {       
		HttpSession session = request.getSession();    
		Eleve eleve = (Eleve) (session.getAttribute("sessionEleve"));
		request.setAttribute("eleve",eleve);
		
		
		/* Affichage de la page de connexion */         
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );     
		}     
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
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
			String ideleve = eleve.getId_eleve();
			Structure structure = Manager.getInstance().getStructure_ElevePresident(ideleve);
			
			session.setAttribute( "structure", structure );
			
				Integer nbTeaEnAttente = Manager.getInstance().getTeaEnAttente();
				Integer nbOffreEnAttente = Manager.getInstance().getOffreEnAttente();
				Date date = new Date();
				Integer nbTeaAValiderByStructure = Manager.getInstance().getNbTeaAValiderByStructure(eleve.getCle_structure(),date);
				session.setAttribute( "nbTeaEnAttente", nbTeaEnAttente );
				session.setAttribute( "nbOffreEnAttente", nbOffreEnAttente );
				session.setAttribute( "nbTeaAValiderByStructure" , nbTeaAValiderByStructure);
			
		} 
		else {             
			session.setAttribute( ATT_SESSION_USER, null );         
		}          
		/* Stockage du formulaire et du bean dans l'objet request */  
		request.setAttribute( ATT_FORM, form );         
		request.setAttribute( ATT_USER, eleve );     
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );     
		} 
	}