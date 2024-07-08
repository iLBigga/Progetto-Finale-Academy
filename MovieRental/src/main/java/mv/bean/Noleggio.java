package mv.bean;

import java.sql.Date;

public class Noleggio 
{
	public Noleggio() {}
	
	public Noleggio(int id_u, int id_f, Date data, boolean restituito)
	{
		this.id_u = id_u;
		this.id_f = id_f;
		this.data = data;
		this.restituito = restituito;
	}
	
	public int getId_u() {
		return id_u;
	}

	public void setId_u(int id_u) {
		this.id_u = id_u;
	}

	public int getId_f() {
		return id_f;
	}

	public void setId_f(int id_f) {
		this.id_f = id_f;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public boolean isRestituito() {
		return restituito;
	}

	public void setRestituito(boolean restituito) {
		this.restituito = restituito;
	}

	private int id_u;
	private int id_f;
	private Date data;
	private boolean restituito;
}
