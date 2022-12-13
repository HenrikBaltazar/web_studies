package WebPkg;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class response
 */
@WebServlet("/response")
public class response extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static ArrayList<Produto> Lista = new ArrayList<Produto>();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public response() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Content-type", "application/json");
		Gson gson = new Gson();
		String output = gson.toJson(Lista);
		/*
		 * String output = "[";
		for(Produto p: lista) {
			 output+= "{\"descricao\":\""+p.getDescricao()+"\", \"preco\":"+p.getPreco()+"}";
		}
		output += "]";
		*/
		response.getWriter().append(output);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("VOCE REALIZOU UM POST!");
		String precoTxt = request.getParameter("preco");
		String descricao = request.getParameter("descricao");
		try {
			double preco = Double.parseDouble(precoTxt);
			Produto p = new Produto(descricao, preco);
			response.setStatus(200);
		}catch(Exception e) {
			response.setStatus(400);
		}
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("VOCE REALIZOU UM PUT!");
	}

	/**]
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nomeProduto = request.getParameter("produto");
		for(int i = 0; i< Lista.size(); i++) {
			if(Lista.get(i).getDescricao().equals(nomeProduto)) {
				Lista.remove(i);
				response.setStatus(200);
				return;
			}
		}
		response.setStatus(404);
	}

	/**
	 * @see HttpServlet#doHead(HttpServletRequest, HttpServletResponse)
	 */
	protected void doHead(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doOptions(HttpServletRequest, HttpServletResponse)
	 */
	protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
