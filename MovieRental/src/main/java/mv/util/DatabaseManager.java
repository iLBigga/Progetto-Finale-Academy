package mv.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;

public class DatabaseManager 
{
	private Connection connection;
	
	public DatabaseManager() throws SQLException 
	{
		try 
		{
			 Enumeration<Driver> drivers = DriverManager.getDrivers();
		        while (drivers.hasMoreElements()) {
		            System.out.println("Driver: " + drivers.nextElement().getClass().getName());
		        }
			System.out.println(System.getProperty("java.class.path"));
			Properties props = new Properties();
			props.load(getClass().getClassLoader().getResourceAsStream("db.properties"));
			String url = props.getProperty("url");
			String user = props.getProperty("user");
			String password = props.getProperty("password");
			connection = DriverManager.getConnection(url, user, password);
		} 
		catch (Exception e) 
		{
			throw new SQLException("Errore di connessione al database", e);
		}
	}
	
	public Connection getConnection() 
	{
		return connection;
	}
	
	public void close() 
	{
		try 
		{
			if (connection != null && !connection.isClosed()) 
			{
				connection.close();
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
}
