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
 * Servlet implementation class UpdateServlet
 */
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FilmDAO filmDAO;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init() throws ServletException {
        try 
        {
           DatabaseManager dbManager = new DatabaseManager();
           filmDAO = new FilmDAO(dbManager.getConnection());
        }
        catch (SQLException e) 
        {
            throw new ServletException(e);
        }
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		int id_f = Integer.parseInt(request.getParameter("id"));
		String titolo = request.getParameter("titolo");
		String descizione = request.getParameter("descrizione");
		String genere = request.getParameter("genere");
		double prezzo = Double.parseDouble(request.getParameter("prezzo"));
		String img = "img/" + request.getParameter("img") + ".jpg";
		boolean disp = Boolean.parseBoolean(request.getParameter("disp"));
		
		try {
			filmDAO.updateFilm(id_f, titolo, descizione, genere, prezzo, img, disp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.sendRedirect("catalogo");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
