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
import model.Order;
import presentation.PDFCreator;

/**
 * 
 * @author Bivolaru Alexandru - 30225
 *
 *<p> Clasa preia conexiunea cu baza de date prin intermediul Logger.
 *	  Aceasta contine operatiile necesare modelarii tabelei Comanda din baza de date </p>
 *
 */

public class OrderDAO 
{
	protected static final Logger LOGGER = Logger.getLogger(OrderDAO.class.getName());
	private static final String insertStatementString = "INSERT into comanda (idOrder,numeClient,numeProdus,quantity,price)" + "values(?,?,?,?,?)";
	private static final String selectStatementString = "SELECT numeClient,numeProdus,quantity,price from comanda";
	
	/**
	 * 
	 * @param o - comanda ce urmeaza sa fie inserat in tabela Comanda
	 * 
	 * <p> Metoda se ocupa de inserara comenzii in tabela Comanda a bazei de date </p>
	 * 
	 */
	
	public static void insert(Order o)
	{
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement insertStatement = null;
		ResultSet rs = null;
		
		try
		{
			insertStatement = dbConnection.prepareStatement(insertStatementString,Statement.RETURN_GENERATED_KEYS);
			insertStatement.setInt(1, o.getIdOrder());
			insertStatement.setString(2, o.getNumeClient());
			insertStatement.setString(3, o.getNumeProdus());
			insertStatement.setInt(4, o.getQuantity());
			insertStatement.setDouble(5, o.getPrice());
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
	 * @param y - parametrul care contorizeaza numele pdf-ului generat
	 * 
	 * <p> Metoda se ocupa de efectuarea unui query asupra tabelei comanda din baza de date </p>
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
			pdf.OrderTablePDF(4, rst, y);
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
