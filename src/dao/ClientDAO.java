package dao;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import com.itextpdf.text.DocumentException;

import connection.ConnectionFactory;
import model.Client;
import presentation.PDFCreator;

/**
 * 
 * @author Bivolaru Alexandru - 30225
 *
 *<p> Clasa preia conexiunea cu baza de date prin intermediul Logger.
 *	  Aceasta contine operatiile necesare modelarii tabelei Client din baza de date </p>
 *
 */

public class ClientDAO 
{
	protected static final Logger LOGGER = Logger.getLogger(ClientDAO.class.getName());
	private static final String insertStatementString = "INSERT into Client (idClient,nume,adresa)" + "values(?,?,?)";
	private static final String deleteStatementString = "DELETE from Client where nume=? and adresa=?";
	private static final String selectStatementString = "SELECT nume,adresa from Client";
	
	/**
	 * 
	 * @param c - clientul ce urmeaza sa fie inserat in tabela Client
	 * 
	 * <p> Metoda se ocupa de inserara clientului in tabela Client a bazei de date </p>
	 * 
	 */
	
	public static void insert(Client c)
	{
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement insertStatement = null;
		ResultSet rs = null;
		
		try
		{
			insertStatement = dbConnection.prepareStatement(insertStatementString,Statement.RETURN_GENERATED_KEYS);
			insertStatement.setInt(1, c.getIdClient());
			insertStatement.setString(2, c.getNume());
			insertStatement.setString(3, c.getAdresa());
			insertStatement.executeUpdate();
			rs = insertStatement.getGeneratedKeys();
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
	 * @param nume - numele clientului ce urmeaza sa fie sters din tabela Client
	 * @param adresa - adresa clientului ce urmeaza sa fie sters din tabela Client
	 * 
	 * <p> Metoda se ocupa de stergerea clientului din tabela Client a bazei de date </p>
	 * 
	 */
	
	public static void delete(String nume, String adresa)
	{
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement deleteStatement = null;
		
		try
		{
			deleteStatement = dbConnection.prepareStatement(deleteStatementString);
			deleteStatement.setString(1, nume);
			deleteStatement.setString(2, adresa);
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
	
	/**
	 * 
	 * @param y - parametrul care contorizeaza numele pdf-ului generat
	 * 
	 * <p> Metoda se ocupa de efectuarea unui query asupra tabelei Client din baza de date </p>
	 * 
	 */
	
	public static void select(int y) throws FileNotFoundException, DocumentException
	{
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement selectStatement = null;
		ResultSet rst = null;
		int linii = 0;
		
		try
		{
			selectStatement = dbConnection.prepareStatement(selectStatementString);
			rst = selectStatement.executeQuery();
			PDFCreator pdf = new PDFCreator();
			pdf.generateClientTablePDF(2, rst, y);
		}
	    catch (SQLException ex) 
		{
	    	System.err.println("SQLException: " + ex);
	    }
		finally
		{
			ConnectionFactory.close(selectStatement);
			ConnectionFactory.close(dbConnection);
		}
	}
}
