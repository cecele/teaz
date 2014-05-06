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
 * Servlet Filter implementation class ResponsableFilter
 */
@WebFilter("/ResponsableFilter")
public class ResponsableFilter implements Filter {

    /**
     * Default constructor. 
     */
    public ResponsableFilter() {
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
			if(eleve_statut == 0|| eleve_statut == 3){
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
