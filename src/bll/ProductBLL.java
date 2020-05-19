package bll;

import java.io.FileNotFoundException;

import com.itextpdf.text.DocumentException;

import dao.ProductDAO;
import model.Product;

/**
 * 
 * @author Bivolaru Alexandru - 30225
 *
 *<p> Clasa contine toate operatiile efectuate de DAO-ul Produsului </p>
 *
 */

public class ProductBLL 
{
	/**
	 * 
	 * @param p - produsul pe care se efectueaza operatia
	 * 
	 * <p> Metoda care apeleaza metoda insert din ProductDAO </p>
	 * 
	 */
	public void insertProduct(Product p)
	{
		ProductDAO.insert(p);
	}
	
	/**
	 * 
	 * @param nume - numele produsului pe care se efectueaza operatia
	 * 
	 * <p> Metoda care apeleaza metoda delete din ProductDAO </p>
	 * 
	 */
	
	public void deleteProduct(String nume)
	{
		ProductDAO.delete(nume);
	}
	
	/**
	 * 
	 * @param nume - numele produsului pe care se efectueaza operatia
	 * @param dif - cantitatea cu care se actualizeaza produsul in tabela Product
	 * 
	 * <p> Metoda care apeleaza metoda update din ProductDAO </p>
	 * 
	 */
	
	public void updateProduct(String nume, int dif)
	{
		ProductDAO.update(nume, dif);
	}
	
	/**
	 * 
	 * @param y - contorul pentru denumirea fisierelor
	 * @throws FileNotFoundException
	 * @throws DocumentException
	 * 
	 * <p> Metoda care apeleaza metoda de query "select" din ProductDAO </p>
	 * 
	 */
	
	public void selectProduct(int y) throws FileNotFoundException, DocumentException
	{
		ProductDAO.select(y);
	}
}
