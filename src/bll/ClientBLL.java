package bll;

import java.io.FileNotFoundException;

import com.itextpdf.text.DocumentException;

import dao.ClientDAO;
import model.Client;

/**
 * 
 * @author Bivolaru Alexandru - 30225
 *
 *<p> Clasa contine toate operatiile efectuate de DAO-ul Clientului </p>
 *
 */

public class ClientBLL 
{
	/**
	 * 
	 * @param c - clientul pe care se efectueaza operatia
	 * 
	 * <p> Metoda care apeleaza metoda insert din ClientDAO </p>
	 * 
	 */
	
	public void insertClient(Client c)
	{
		ClientDAO.insert(c);
	}
	
	/**
	 * 
	 * @param nume - numele clientului pe care se efectueaza operatia
	 * @paral adresa - adresa clientului pe care se efectueaza operatia
	 * 
	 * <p> Metoda care apeleaza metoda delete din ClientDAO </p>
	 * 
	 */
	
	public void deleteClient(String nume, String adresa)
	{
		ClientDAO.delete(nume, adresa);
	}
	
	/**
	 * 
	 * @param y - contorul pentru denumirea fisierelor
	 * @throws FileNotFoundException
	 * @throws DocumentException
	 * 
	 * <p> Metoda care apeleaza metoda de query "select" din ClientDAO </p>
	 * 
	 */
	
	public void selectClient(int y) throws FileNotFoundException, DocumentException
	{
		ClientDAO.select(y);
	}
}
