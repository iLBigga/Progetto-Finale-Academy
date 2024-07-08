package mv.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import mv.bean.Film;
import mv.bean.Noleggio;
import mv.bean.Utente;
import mv.dao.FilmDAO;
import mv.dao.NoleggioDAO;
import mv.util.DatabaseManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CatalogoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private FilmDAO filmDAO;
	private NoleggioDAO nDAO;

	public void init() throws ServletException {
		try {
			DatabaseManager dbManager = new DatabaseManager();
			filmDAO = new FilmDAO(dbManager.getConnection());
			nDAO = new NoleggioDAO(dbManager.getConnection());
		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession s = request.getSession(false);
			Utente u = null;
			ArrayList<Film> films = null;
			String ricerca = request.getParameter("ricerca") != null ? request.getParameter("ricerca") : "";

			if (s != null) {
				u = (Utente) s.getAttribute("currentSessionUser");
			}

			if (u != null) {
				films = (ArrayList<Film>) filmDAO.readFilmByRicerca(ricerca);
				
				List<Integer> film_noleggiati = new ArrayList<Integer>();
				List<Noleggio> noleggi = null;
	            request.setAttribute("films", films);
				
	            for (Film f : films) 
	            {
					noleggi = nDAO.readNoleggioFilmUtente(f.getId_f(), u.getId_u());
					if (noleggi != null ) 
					{
						for(Noleggio n : noleggi)
						{
							if(!n.isRestituito())
							{
								film_noleggiati.add(f.getId_f());
								System.out.println(f.getTitolo());
							}
						}
					}
				}
				request.setAttribute("filmNoleggiati", film_noleggiati);
			} 
			else 
			{
				films = filmDAO.readFilmByRicerca(ricerca);
			}
			request.setAttribute("films", films);
			request.getRequestDispatcher("catalogo.jsp").forward(request, response);
		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}