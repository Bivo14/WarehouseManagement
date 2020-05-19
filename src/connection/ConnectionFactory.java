package connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Bivolaru Alexandru - 30225
 * 
 * <p> Aceasta clasa contine metode pentru stabilirea conexiunii cu baza de date 
 * Este realizata in model Singleton pentru a putea fi reutilizata constant in diferitele clase ale proiectului </p>
 *
 */

public class ConnectionFactory 
{
	private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DBURL = "jdbc:mysql://localhost:3306/assignment3";
	private static final String USER = "root";
	private static final String PASS = "";
	
	private static ConnectionFactory singleInstance = new ConnectionFactory();
	
	private ConnectionFactory() 
	{
		try 
		{
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @return - conexiunea la baza de date
	 * 
	 * <p> Realizeaza conexiunea cu baza de date. Daca aceasta a avut succes o creeaza si o returneaza </p>
	 * 
	 */
	
	private Connection createConnection() 
	{
		Connection connection = null;
		try 
		{
			connection = DriverManager.getConnection(DBURL, USER, PASS);
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return connection;
	}

	/**
	 * 
	 * @return - conexiunea la baza de date
	 */
	
	public static Connection getConnection() 
	{
		return singleInstance.createConnection();
	}

	/**
	 * 
	 * @param connection
	 * 
	 * <p> Metoda care inchide conexiunea cu baza de date </p>
	 * 
	 */
	
	public static void close(Connection connection) 
	{
		if (connection != null) {
			try 
			{
				connection.close();
			} catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @param statement
	 * 
	 * <p> Metoda care inchide conexiunea cu statement-ul specificat ca parametru </p>
	 * 
	 */
	
	public static void close(Statement statement) 
	{
		if (statement != null) 
		{
			try 
			{
				statement.close();
			} catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @param resultSet
	 * 
	 * <p> Metoda care inchide conexiunea cu resultset-ul specificat ca parametru </p>
	 * 
	 */
	
	public static void close(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
}

