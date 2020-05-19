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
 *	  Aceasta contine operatiile necesare modelarii tabelei de legatura Link din baza de date </p>
 *
 */

public class LinkDAO 
{
	protected static final Logger LOGGER = Logger.getLogger(LinkDAO.class.getName());
	private static final String insertStatementString = "INSERT into link (idComanda,idClient)" + "values(?,?)";
	private static final String deleteStatementString = "DELETE from link where idClient=?";
	
	/**
	 * 
	 * @param idComanda - id-ul comenzii ce urmeaza sa fie inserata in tabela Link
	 * @param idClient - id-ul clientului ce urmeaza sa fie inserat in tabela Link
	 * 
	 * <p> Metoda se ocupa de inserara unui obiect de tip Link(idComanda, idClient) in tabela Link a bazei de date </p>
	 * 
	 */
	
	public static void insertLink(int idComanda, int idClient)
	{
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement insertStatement = null;
		ResultSet rs = null;
		
		try
		{
			insertStatement = dbConnection.prepareStatement(insertStatementString,Statement.RETURN_GENERATED_KEYS);
			insertStatement.setInt(1, idComanda);
			insertStatement.setInt(2, idClient);
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
	 * @param idClient - id-ul clientului pe care dorim sa il stergem din tabela Link
	 * 
	 * <p> Aceasta metoda sterge legatura client-comanda din tabela Link </p>
	 * 
	 */
	
	public static void deleteLink(int idClient)
	{
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement deleteStatement = null;
		
		try
		{
			deleteStatement = dbConnection.prepareStatement(deleteStatementString);
			deleteStatement.setInt(1, idClient);
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
