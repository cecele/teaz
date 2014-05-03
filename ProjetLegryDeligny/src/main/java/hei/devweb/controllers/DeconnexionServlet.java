
package hei.devweb.controllers;  
import java.io.IOException; 
import javax.servlet.ServletException; 
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;  

public class DeconnexionServlet extends HttpServlet {           
	@Override     
	protected void doGet(HttpServletRequest request, HttpServletResponse response)                    
			throws ServletException, IOException {    
		
		request.getSession().invalidate();    
		HttpServletResponse httpResponse = (HttpServletResponse) response;  
		
		httpResponse.sendRedirect("index");             
		return;     
		}  
	} 