
package hei.devweb.controllers;  
import hei.devweb.model.Eleve;

import java.io.IOException; 

import javax.servlet.ServletException; 
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;

/**
 * @author Projet
 *
 */
public class DeconnexionServlet extends HttpServlet {           
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override     
	protected void doGet(HttpServletRequest request, HttpServletResponse response)                    
			throws ServletException, IOException {    
		
		HttpSession session = request.getSession();    
		Eleve eleve = (Eleve) (session.getAttribute("sessionEleve"));
		request.setAttribute("eleve",eleve);
		
		request.getSession().invalidate();    
		HttpServletResponse httpResponse = (HttpServletResponse) response;  
		
		httpResponse.sendRedirect("index");             
		return;     
		}  
	} 