package mv.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import mv.bean.Noleggio;

public class NoleggioDAO {
	private Connection connection;

	public NoleggioDAO(Connection connection) {
		this.connection = connection;
	}

	public void createNoleggio(int id_utente, int id_film) throws SQLException {
		String query = "INSERT INTO noleggio (id_u, id_f) VALUES (?, ?)";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setInt(1, id_utente);
			stmt.setInt(2, id_film);
			stmt.executeUpdate();
		}
	}

	public List<Noleggio> readNoleggi() throws SQLException {
		List<Noleggio> noleggi = new ArrayList<>();
		String query = "SELECT * FROM noleggio";
		try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				Noleggio noleggio = new Noleggio();
				noleggio.setId_u(rs.getInt("id_u"));
				noleggio.setId_f(rs.getInt("id_f"));
				noleggio.setData(rs.getDate("data"));
				noleggio.setRestituito(rs.getBoolean("restituito"));
				noleggi.add(noleggio);
			}
		}

		return noleggi;
	}
	
	public List<Noleggio>  readNoleggioFilmUtente(int id_f, int id_u) throws SQLException 
	{
		List<Noleggio> noleggi = null;
		Noleggio noleggio = null;
		String query = "SELECT * FROM noleggio WHERE id_f = ? and id_u = ?";
		try (PreparedStatement stmt = connection.prepareStatement(query))
		{
			stmt.setInt(1, id_f);
			stmt.setInt(2, id_u);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) 
			{
				noleggi = new ArrayList<>();
				
				do
				{
					noleggio = new Noleggio();
					noleggio.setId_u(rs.getInt("id_u"));
					noleggio.setId_f(rs.getInt("id_f"));
					noleggio.setData(rs.getDate("data_noleggio"));
					noleggio.setRestituito(rs.getBoolean("restituito"));
					noleggi.add(noleggio);
				}
				while(rs.next());
			}
		}

		return noleggi;
	}
	
	public List<Noleggio> readNoleggioUtente(int id_u) throws SQLException 
	{
		List<Noleggio> noleggi = null;
		Noleggio noleggio = null;
		String query = "SELECT * FROM noleggio WHERE id_u = ?";
		try (PreparedStatement stmt = connection.prepareStatement(query))
		{
			stmt.setInt(1, id_u);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) 
			{
				noleggi = new ArrayList<>();
				
				do
				{
					noleggio = new Noleggio();
					noleggio.setId_u(rs.getInt("id_u"));
					noleggio.setId_f(rs.getInt("id_f"));
					noleggio.setData(rs.getDate("data_noleggio"));
					noleggio.setRestituito(rs.getBoolean("restituito"));
					noleggi.add(noleggio);
				}
				while(rs.next());
			}
		}

		return noleggi;
	}

	public void updateNoleggio(int id_utente, int id_film, boolean restituito) throws SQLException {
		String query = "UPDATE noleggio SET restituito = ? WHERE id_u= ? AND id_f = ?";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setBoolean(1, restituito);
			stmt.setInt(2, id_utente);
			stmt.setInt(3, id_film);
			stmt.executeUpdate();
		}
	}

	public void deleteNoleggio(int id_utente, int id_film) throws SQLException {
		String query = "DELETE FROM noleggio WHERE id_u = ? AND id_f = ?";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setInt(1, id_utente);
			stmt.setInt(2, id_film);
			stmt.executeUpdate();
		}
	}
}