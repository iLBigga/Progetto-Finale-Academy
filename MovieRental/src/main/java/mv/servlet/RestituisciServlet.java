package mv.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mv.dao.NoleggioDAO;
import mv.util.DatabaseManager;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet implementation class RestituisciServlet
 */
public class RestituisciServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private NoleggioDAO noleggioDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
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
		 	int id_u = Integer.parseInt(request.getParameter("id_u"));
	        int id_f = Integer.parseInt(request.getParameter("id_f"));
	        try {
	            noleggioDAO.updateNoleggio(id_u, id_f, true);
	            response.sendRedirect("catalogo");
	        }catch (SQLException e) {
	            throw new ServletException(e);
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
