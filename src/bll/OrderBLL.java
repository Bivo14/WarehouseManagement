package bll;

import java.io.FileNotFoundException;

import com.itextpdf.text.DocumentException;

import model.Order;
import dao.ClientDAO;
import dao.OrderDAO;

/**
 * 
 * @author Bivolaru Alexandru - 30225
 *
 *<p> Clasa contine toate operatiile efectuate de DAO-ul Comenzilor </p>
 *
 */

public class OrderBLL 
{
	/**
	 * 
	 * @param o - comanda pe care se efectueaza operatia
	 * 
	 * <p> Metoda care apeleaza metoda insert din OrderDAO </p>
	 * 
	 */
	
	public void insertOrder(Order o)
	{
		OrderDAO.insert(o);
	}
	
	/**
	 * 
	 * @param y - contorul pentru denumirea fisierelor
	 * @throws FileNotFoundException
	 * @throws DocumentException
	 * 
	 * <p> Metoda care apeleaza metoda de query "select" din OrderDAO </p>
	 * 
	 */
	
	public void selectOrder(int y) throws FileNotFoundException, DocumentException
	{
		OrderDAO.select(y);
	}
}
