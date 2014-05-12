package hei.devweb.filters;

import hei.devweb.model.Eleve;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AdministrationFilter
 * 
 * Filtre qui autorise uniquement le statut d'administrateur.
 * Si le statut n'est pas le bon, il redirige vers la page d'accueil
 */
@WebFilter("/AdministrationFilter")
public class AdministrationFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AdministrationFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("sessionEleve") != null){
			Eleve eleve = (Eleve) (session.getAttribute("sessionEleve"));
			Integer eleve_statut = eleve.getEleve_profil();
			if(eleve_statut == 0|| eleve_statut == 1){
				response.sendRedirect("index");
			}
			else{
				chain.doFilter(request, response);
			}
		}
		else{
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
