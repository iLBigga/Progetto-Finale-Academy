package mv.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mv.bean.*;
import mv.dao.NoleggioDAO;
import mv.util.DatabaseManager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

/**
 * Servlet implementation class OrderListServlet
 */
public class OrderListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NoleggioDAO noleggioDAO;
	 
	public void init() throws ServletException {
        try {
            DatabaseManager dbManager = new DatabaseManager();
            noleggioDAO = new NoleggioDAO(dbManager.getConnection());
         }catch (SQLException e) {
             throw new ServletException(e);
         }
   }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		Utente currentUser = (Utente) session.getAttribute("currentSessionUser");
		
		try
		{
			List<Noleggio> noleggi = null;
			
			noleggi = noleggioDAO.readNoleggioUtente(currentUser.getId_u());		
			System.out.println(noleggi);
			request.setAttribute("noleggi", noleggi);
	
			RequestDispatcher dispatcher = request.getRequestDispatcher("orderListView.jsp");
			dispatcher.forward(request, response);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
