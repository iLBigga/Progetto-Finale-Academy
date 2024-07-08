package mv.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mv.bean.Utente;
import mv.dao.UtenteDAO;
import mv.util.DatabaseManager;

import java.io.IOException;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LoginServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		try
		{
			DatabaseManager dm = new DatabaseManager();
			Utente utente = new Utente();
			UtenteDAO uDAO = new UtenteDAO(dm.getConnection());
			request.setCharacterEncoding("UTF-8");
			
			utente.setUsername(request.getParameter("user"));
			utente.setPassword(request.getParameter("password"));
			uDAO.readUtente(utente);
			System.out.println(utente.getNome());
			if(utente.isValid())
			{
				HttpSession session = request.getSession();
				session.setAttribute("currentSessionUser", utente);
				response.sendRedirect("home.jsp");
				
			}
			else
			{
				HttpSession session = request.getSession();
				session.setAttribute("invalidLogin", true);
				response.sendRedirect("login.jsp");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
