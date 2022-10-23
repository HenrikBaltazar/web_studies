package WebPkg;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Autenticador
 */
@WebServlet("/login")
public class Autenticador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Autenticador() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession s= request.getSession();
		s.removeAttribute("usuario");
		Cookie ck=new Cookie("usuario","");
		ck.setMaxAge(0);
		response.addCookie(ck);
		response.sendRedirect(request.getContextPath() + "/login.html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String manterConectado = request.getParameter("manter_conectado");
		if(login.equals("michael") && password.equals("123")) {
			if(manterConectado != null) {
				Cookie ck = new Cookie("usuario", login);
				ck.setMaxAge(60*60*24*3); //em segundos, nessa forma duram 3 dias
				response.addCookie(ck);
			}
			response.sendRedirect(request.getContextPath() + "/login.html");
		}else {
			request.getRequestDispatcher("/login.html").forward(request, response);
		}
	}

}
