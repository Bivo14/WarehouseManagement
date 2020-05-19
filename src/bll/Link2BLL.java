package bll;

import dao.Link2DAO;

/**
 * 
 * @author Bivolaru Alexandru - 30225
 *
 *<p> Clasa contine toate operatiile efectuate de DAO-ul tabelei Link2 </p>
 *
 */

public class Link2BLL 
{
	/**
	 * 
	 * @param idComanda - id-ul comenzii pe care se efectueaza operatia
	 * @paral idProdus - id-ul produsului din comanda pe care se efectueaza operatia
	 * 
	 * <p> Metoda care apeleaza metoda insertLink2 din Link2DAO </p>
	 * 
	 */
	
	public void insertLink2(int idComanda, int idProdus)
	{
		Link2DAO.insertLink2(idComanda, idProdus);
	}
	
	/**
	 * 
	 * @paral idProdus - id-ul produsului folosit pentru stergerea comenzii din tabela Link2
	 * 
	 * <p> Metoda care apeleaza metoda deleteLink2 din LinkDAO </p>
	 * 
	 */
	
	public void deleteLink2(int idProdus)
	{
		Link2DAO.deleteLink2(idProdus);
	}
}
