package mv.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mv.dao.FilmDAO;
import mv.util.DatabaseManager;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet implementation class AggiungiServlet
 */
public class AggiungiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AggiungiServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String titolo = request.getParameter("titolo");
		String descrizione = request.getParameter("descrizione");
		String genere = request.getParameter("genere");
		double prezzo = Double.parseDouble(request.getParameter("prezzo"));
		String img = "img/" + request.getParameter("img") + ".jpg";
		boolean disp = Boolean.parseBoolean(request.getParameter("disp"));
		
		try 
		{
			DatabaseManager dm = new DatabaseManager();
			FilmDAO fDAO = new FilmDAO(dm.getConnection());
			fDAO.createFilm(titolo, descrizione, genere, prezzo, img, disp);
			
			response.sendRedirect("catalogo");
		} 
		catch (SQLException e)
		{
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
