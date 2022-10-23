package WebPkg;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class FiltroLogin
 */
@WebFilter("/login.html")
public class FiltroLogin extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public FiltroLogin() {
        super();
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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		boolean manterConectado = false;
		String userName = null;
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		Cookie[] cookies = httpRequest.getCookies();
		for(Cookie ck : cookies) {
			if(ck.getName().equals("usuario")) {
				manterConectado = true;
				userName = ck.getValue();
			}
		}
		if(manterConectado) {
			httpRequest.getSession().setAttribute("usuario", userName);
			request.getRequestDispatcher("WEB-INF/telaRestrita.jsp").forward(request, response);
		}else {
			
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
