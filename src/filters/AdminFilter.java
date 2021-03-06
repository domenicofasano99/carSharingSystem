package filters;

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

import model.Utente;
import utilities.CookieUtilities;

@WebFilter("/UtenteFilter")
public class AdminFilter implements Filter{

	private FilterConfig filterConfig;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		String admin = (String) request.getSession().getAttribute("admin");
		String name = CookieUtilities.getUserMail(request, response);
		if(admin != null && name!=null) {
			chain.doFilter(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/");
		}
	}

	@Override
	public void destroy() {
		this.filterConfig = null;
	}
}
