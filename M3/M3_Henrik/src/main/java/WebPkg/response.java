package WebPkg;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.google.gson.Gson;

/**
 * Servlet implementation class response
 */
@WebServlet("/response")
public class response extends HttpServlet {
	private static final long serialVersionUID = 1L;
    //private static 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public response() {
        super();
    }
    public class DataSourceMySQL {
    	public Connection conectaBD() throws SQLException, ClassNotFoundException {
    	String url ="jdbc:mysql://localhost:3306/db?useSSL=false&serverTimezone=UTC";
    	String user ="admin";
    	String passwd = "admin";
    	Class.forName("com.mysql.cj.jdbc.Driver");
    	Connection con = DriverManager.getConnection(url, user, passwd);
    	return con ;
    	}
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Content-type", "application/json");
		ArrayList<Debit> returnList = new ArrayList<Debit>();
		try {
			DataSourceMySQL ds = new DataSourceMySQL();
			String sql ="select * from debit";
			Connection c = ds.conectaBD();
			PreparedStatement ps = c.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Debit d = new Debit(rs.getString("name"),rs.getString("cpf"),rs.getString("email"),rs.getString("address"),rs.getString("description"),rs.getBoolean("status"),rs.getDouble("value"));
				String justiceId = rs.getString("justiceId");
				String receiptId = rs.getString("receiptId");
				if(justiceId != null)
					d.setJusticeId(justiceId);
				if(receiptId != null)
					d.setReceiptId(receiptId);
				returnList.add(d);
			}
			} catch (Exception e) {
			System.err.println(e.getMessage());
			}
		Gson gson = new Gson();
		String output = gson.toJson(returnList);
		response.getWriter().append(output);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name"), cpf = request.getParameter("cpf"), email = request.getParameter("email");
		String address = request.getParameter("address"), description = request.getParameter("description"), statusString = request.getParameter("status");
		String justiceId = request.getParameter("justiceId"), receiptId = request.getParameter("receiptId"), valueString = request.getParameter("value");
			try {
				double value = Double.parseDouble(valueString);
				boolean status = Boolean.parseBoolean(statusString);
				Debit d = new Debit(name, cpf, email, address, description, status, value);
				d.setJusticeId(justiceId);
				d.setReceiptId(receiptId);
				ArrayList<Debit> newDebitList = new ArrayList<Debit>();
				newDebitList.add(d);
				boolean result=false;
				try {
					DataSourceMySQL ds = new DataSourceMySQL();
					Connection c = ds.conectaBD();;
					String sql = "insert into debit (name, cpf, email, address, description, status, justiceId, receiptId, value) values(?,?,?,?,?,?,?,?,?)";
					PreparedStatement ps = c.prepareStatement(sql);
					ps.setString(1, d.getName());
					ps.setString(2, d.getCpf());
					ps.setString(3, d.getEmail());
					ps.setString(4, d.getAddress());
					ps.setString(5, d.getDescription());
					ps.setBoolean(6, d.isStatus());
					ps.setString(7, d.getJusticeId());
					ps.setString(8, d.getReceiptId());
					ps.setDouble(9, d.getValue());
					ps.execute();
					result=true;
				} catch (Exception e) {
					System.err.println(e.getMessage());
					response.setStatus(404);
					response.getWriter().write(e.getMessage());
				}
				if(result == true) {
					response.setStatus(200);
					response.getWriter().write("Divida adicionada com sucesso");
				}
			}catch(Exception e) {
				response.setStatus(400);
				response.getWriter().write(e.getMessage());
			}
		
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cpf = request.getParameter("cpf"), email = request.getParameter("email");
		String address = request.getParameter("address"), description = request.getParameter("description"), statusString = request.getParameter("status");
		boolean result=false;
		if(cpf != null && !cpf.isBlank()) {
			if(email != null && !email.isBlank()) {
				try {
					DataSourceMySQL ds = new DataSourceMySQL();
					Connection c = ds.conectaBD();
					String sql = "update debit set email=? where cpf=?";
					PreparedStatement ps = c.prepareStatement(sql);
					ps.setString(1, email);
					ps.setString(2, cpf);
					ps.execute();
					result=true;
					} catch (Exception e) {
						response.setStatus(400);
						response.getWriter().write(e.getMessage());
					}
			}
			if(address != null && !address.isBlank()) {
				try {
					DataSourceMySQL ds = new DataSourceMySQL();
					Connection c = ds.conectaBD();
					String sql = "update debit set address=? where cpf=?";
					PreparedStatement ps = c.prepareStatement(sql);
					ps.setString(1, address);
					ps.setString(2, cpf);
					ps.execute();
					result=true;
					} catch (Exception e) {
						response.setStatus(400);
						response.getWriter().write(e.getMessage());
					}
			}
			if(description != null && !description.isBlank()) {
				try {
					DataSourceMySQL ds = new DataSourceMySQL();
					Connection c = ds.conectaBD();
					String sql = "update debit set description=? where cpf=?";
					PreparedStatement ps = c.prepareStatement(sql);
					ps.setString(1, description);
					ps.setString(2, cpf);
					ps.execute();
					result=true;
					} catch (Exception e) {
						response.setStatus(400);
						response.getWriter().write(e.getMessage());
					}
			}
			if(statusString != null && !statusString.isBlank()) {
				try {
					DataSourceMySQL ds = new DataSourceMySQL();
					Connection c = ds.conectaBD();
					String sql = "update debit set status=? where cpf=?";
					PreparedStatement ps = c.prepareStatement(sql);
					ps.setBoolean(1, Boolean.parseBoolean(statusString));
					ps.setString(2, cpf);
					ps.execute();
					result=true;
					} catch (Exception e) {
						response.setStatus(400);
						response.getWriter().write(e.getMessage());
					}
			}
			if(result == true) {
				response.setStatus(200);
				response.getWriter().write("Dado(s) atualizado(s) com sucesso");
			}
		}else {
			response.setStatus(400);
			response.getWriter().write("Por favor informe o CPF do usuario a ser atualizado");
		}
	}

	/**]
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cpf = request.getParameter("cpf");
		if(cpf == null || cpf.isBlank()) {
			response.setStatus(400);
			response.getWriter().write("Por favor insira o cpf do registro de divida a ser excluido");
		}else {
			System.out.println("Deletando dividas do cpf:"+cpf);
			try {
			DataSourceMySQL ds = new DataSourceMySQL();
			Connection c = ds.conectaBD();;
			String sql = "delete from debit where cpf=?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, cpf);
			ps.execute();
			} catch (Exception e) {
			System.err.println(e.getMessage());
			response.setStatus(400);
			response.getWriter().write(e.getMessage());
			}
			}
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
