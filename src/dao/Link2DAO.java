package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import connection.ConnectionFactory;

/**
 * 
 * @author Bivolaru Alexandru - 30225
 *
 *<p> Clasa preia conexiunea cu baza de date prin intermediul Logger.
 *	  Aceasta contine operatiile necesare modelarii tabelei de legatura Link2 din baza de date </p>
 *
 */

public class Link2DAO 
{
	protected static final Logger LOGGER = Logger.getLogger(Link2DAO.class.getName());
	private static final String insertStatementString = "INSERT into link2 (idComanda,idProdus)" + "values(?,?)";
	private static final String deleteStatementString = "DELETE from link2 where idProdus=?";
	
	/**
	 * 
	 * @param idComanda - id-ul comenzii ce urmeaza sa fie inserata in tabela Link2
	 * @param idProdus - id-ul produsului ce urmeaza sa fie inserat in tabela Link2
	 * 
	 * <p> Metoda se ocupa de inserara unui obiect de tip Link2(idComanda, idProdus) in tabela Link2 a bazei de date </p>
	 * 
	 */
	
	public static void insertLink2(int idComanda, int idProdus)
	{
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement insertStatement = null;
		ResultSet rs = null;
		
		try
		{
			insertStatement = dbConnection.prepareStatement(insertStatementString,Statement.RETURN_GENERATED_KEYS);
			insertStatement.setInt(1, idComanda);
			insertStatement.setInt(2, idProdus);
			insertStatement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			ConnectionFactory.close(rs);
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
	}
	
	/**
	 * 
	 * @param idProdus - id-ul produsului pe care dorim sa il stergem din tabela Link2
	 * 
	 * <p> Aceasta metoda sterge legatura produs-comanda din tabela Link </p>
	 * 
	 */
	
	public static void deleteLink2(int idProdus)
	{
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement deleteStatement = null;
		
		try
		{
			deleteStatement = dbConnection.prepareStatement(deleteStatementString);
			deleteStatement.setInt(1, idProdus);
			deleteStatement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(dbConnection);
		}
	}
}
