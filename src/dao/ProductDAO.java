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
import model.Product;
import presentation.PDFCreator;

/**
 * 
 * @author Bivolaru Alexandru - 30225
 *
 *<p> Clasa preia conexiunea cu baza de date prin intermediul Logger.
 *	  Aceasta contine operatiile necesare modelarii tabelei Product din baza de date </p>
 *
 */

public class ProductDAO 
{
	protected static final Logger LOGGER = Logger.getLogger(ProductDAO.class.getName());
	private static final String insertStatementString = "INSERT into Product (idProdus,nume,quantity,pret)" + "values(?,?,?,?)";
	private static final String deleteStatementString = "DELETE from Product where nume=?";
	private static final String updateStatementString = "UPDATE Product SET quantity=? where nume=?";
	private static final String selectStatementString = "SELECT nume,quantity,pret from Product";
	
	/**
	 * 
	 * @param p - produsul ce urmeaza sa fie inserat in tabela Product
	 * 
	 * <p> Metoda se ocupa de inserara produsului in tabela Product a bazei de date </p>
	 * 
	 */
	
	public static void insert(Product p)
	{
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement insertStatement = null;
		ResultSet rs = null;
		
		try
		{
			insertStatement = dbConnection.prepareStatement(insertStatementString,Statement.RETURN_GENERATED_KEYS);
			insertStatement.setInt(1, p.getIdProdus());
			insertStatement.setString(2, p.getNume());
			insertStatement.setInt(3, p.getQuantity());
			insertStatement.setDouble(4, p.getPret());
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
	 * @param nume - numele produsului ce urmeaza sa fie sters din tabela Product
	 * 
	 * <p> Metoda se ocupa de stergerea produsului din tabela Product a bazei de date </p>
	 * 
	 */
	
	public static void delete(String nume)
	{
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement deleteStatement = null;
		
		try
		{
			deleteStatement = dbConnection.prepareStatement(deleteStatementString);
			deleteStatement.setString(1, nume);
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
	 * @param nume - produsul ce urmeaza sa actualizat in tabela Product
	 * @param dif - Cantitatea care urmeaza sa fie setata produsului
	 * 
	 * <p> Metoda se ocupa de actualizarea produsului in tabela Product a bazei de date </p>
	 * 
	 */
	
	public static void update(String nume, int dif)
	{
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement updateStatement = null;
		
		try
		{
			updateStatement = dbConnection.prepareStatement(updateStatementString);
			updateStatement.setInt(1, dif);
			updateStatement.setString(2, nume);
			updateStatement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			ConnectionFactory.close(updateStatement);
			ConnectionFactory.close(dbConnection);
		}
	}
	
	/**
	 * 
	 * @param y - parametrul care contorizeaza numele pdf-ului generat
	 * 
	 * <p> Metoda se ocupa de efectuarea unui query asupra tabelei Product din baza de date </p>
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
			pdf.ProductTablePDF(3, rst, y);
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
